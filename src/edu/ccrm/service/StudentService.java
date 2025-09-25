package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

/**
 * This service handles all business logic related to student records.
 */
public class StudentService {
    // This map acts as an in-memory database for student data.
    private final Map<String, Student> studentDatabase = new LinkedHashMap<>();

    public Student addStudent(String id, String regNo, String name, String email) {
        Student newStudentRecord = new Student(id, regNo, name, email);
        studentDatabase.put(id, newStudentRecord);
        return newStudentRecord;
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(studentDatabase.get(id));
    }

    public List<Student> listAll() {
        return new ArrayList<>(studentDatabase.values());
    }

    public void deactivate(String id) {
        findById(id).ifPresent(Student::deactivate);
    }
}