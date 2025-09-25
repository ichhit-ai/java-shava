
package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Enrollment holds marks and computed grade.
 */
public class Enrollment {
    private final Course course;
    private final LocalDateTime enrolledAt;
    private Integer marks = null;
    private Grade grade = null;

    public Enrollment(Course course){
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
    }

    public Course getCourse(){ return course; }
    public LocalDateTime getEnrolledAt(){ return enrolledAt; }
    public Optional<Integer> getMarks(){ return Optional.ofNullable(marks); }
    public Optional<Grade> getGrade(){ return Optional.ofNullable(grade); }

    public void recordMarks(int marks){
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    @Override
    public String toString(){
        return String.format("%s | marks=%s | grade=%s", course.getCode(), marks==null? "NA": marks, grade==null? "NA": grade);
    }
}
