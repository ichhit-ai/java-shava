package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages the course catalog, including adding and filtering courses.
 */
public class CourseService {
    // In-memory storage for all available courses.
    private final Map<String, Course> courseCatalog = new LinkedHashMap<>();

    public Course add(Course c) {
        courseCatalog.put(c.getCode(), c);
        return c;
    }

    public Optional<Course> find(String code) {
        return Optional.ofNullable(courseCatalog.get(code));
    }

    public List<Course> listAll() {
        return new ArrayList<>(courseCatalog.values());
    }

    public List<Course> filterByInstructorName(String name) {
        return courseCatalog.values().stream()
                .filter(c -> c.getInstructor() != null && c.getInstructor().getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> filterByDept(String dept) {
        return courseCatalog.values().stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester sem) {
        return courseCatalog.values().stream()
                .filter(c -> c.getSemester() == sem)
                .collect(Collectors.toList());
    }
}