
package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.*;

public class Student extends Person {
    private final String regNo;
    private boolean active = true;
    private final Map<String, Enrollment> enrollments = new LinkedHashMap<>(); // courseCode -> Enrollment
    private LocalDateTime activatedAt;

    public Student(String id, String regNo, String fullName, String email){
        super(id, fullName, email);
        this.regNo = regNo;
        this.activatedAt = LocalDateTime.now();
    }

    public String getRegNo(){ return regNo; }
    public boolean isActive(){ return active; }
    public void deactivate(){ this.active = false; }
    public Map<String, Enrollment> getEnrollments(){ return enrollments; }

    public void addEnrollment(Enrollment e){ enrollments.put(e.getCourse().getCode(), e); }
    public void removeEnrollment(String courseCode){ enrollments.remove(courseCode); }

    @Override
    public String toString(){
        return String.format("Student: %s | regNo=%s | active=%s", super.toString(), regNo, active);
    }
}
