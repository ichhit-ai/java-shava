
package edu.ccrm.domain;

import java.util.Objects;

/**
 * Course demonstrates Builder pattern and immutability for key fields.
 */
public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final Semester semester;
    private final String department;
    private Instructor instructor;

    private Course(Builder b){
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.semester = b.semester;
        this.department = b.department;
        this.instructor = b.instructor;
    }

    public String getCode(){ return code; }
    public String getTitle(){ return title; }
    public int getCredits(){ return credits; }
    public Semester getSemester(){ return semester; }
    public String getDepartment(){ return department; }
    public Instructor getInstructor(){ return instructor; }
    public void setInstructor(Instructor i){ this.instructor = i; }

    @Override
    public String toString(){
        return String.format("%s - %s (%d credits) [%s] Dept:%s Instr:%s", code, title, credits, semester, department, (instructor==null? "TBD": instructor.getFullName()));
    }

    public static class Builder {
        private final String code;
        private String title = "";
        private int credits = 3;
        private Semester semester = Semester.FALL;
        private String department = "General";
        private Instructor instructor = null;

        public Builder(String code){ this.code = Objects.requireNonNull(code); }
        public Builder title(String t){ this.title = t; return this; }
        public Builder credits(int c){ this.credits = c; return this; }
        public Builder semester(Semester s){ this.semester = s; return this; }
        public Builder department(String d){ this.department = d; return this; }
        public Builder instructor(Instructor i){ this.instructor = i; return this; }
        public Course build(){ return new Course(this); }
    }
}
