
package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.exceptions.*;
import java.util.*;

public class EnrollmentService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final int MAX_CREDITS = 30;

    public EnrollmentService(StudentService ss, CourseService cs){
        this.studentService = ss; this.courseService = cs;
    }

    public void enroll(String studentId, String courseCode) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        Student s = studentService.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course c = courseService.find(courseCode).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        if(s.getEnrollments().containsKey(courseCode)) throw new DuplicateEnrollmentException("Already enrolled in " + courseCode);
        int current = s.getEnrollments().values().stream().mapToInt(e -> e.getCourse().getCredits()).sum();
        if(current + c.getCredits() > MAX_CREDITS) throw new MaxCreditLimitExceededException("Credit limit exceeded");
        Enrollment e = new Enrollment(c);
        s.addEnrollment(e);
    }

    public void unenroll(String studentId, String courseCode){
        Student s = studentService.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        s.removeEnrollment(courseCode);
    }

    public void recordMarks(String studentId, String courseCode, int marks){
        Student s = studentService.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Enrollment e = s.getEnrollments().get(courseCode);
        if(e==null) throw new IllegalArgumentException("Not enrolled in course");
        e.recordMarks(marks);
    }

    public double computeGPA(String studentId){
        Student s = studentService.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return s.getEnrollments().values().stream()
            .filter(en -> en.getGrade().isPresent())
            .mapToDouble(en -> en.getGrade().get().getPoints() * en.getCourse().getCredits())
            .sum() / Math.max(1.0, s.getEnrollments().values().stream().filter(en -> en.getGrade().isPresent()).mapToInt(en -> en.getCourse().getCredits()).sum());
    }
}
