package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.exceptions.*;
import edu.ccrm.io.FileUtil;
import edu.ccrm.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main runnable class for the CCRM command-line interface.
 * This class handles all user interaction and orchestrates calls to the various services.
 */
public class Main {

    // A single scanner to handle all user input for the application.
    private static final Scanner userInputScanner = new Scanner(System.in);

    // Instantiate the services that contain the core application logic.
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("* Welcome to the University Record System      *");
        System.out.println("*************************************************");
        populateInitialData(); // Load some sample data for a better demo.
        runApplicationMenu(); // Start the main user loop.
        System.out.println("\nSystem shutting down. Thank you for using the service!");
    }

    /**
     * The primary loop that displays the main menu and processes user commands.
     */
    private static void runApplicationMenu() {
        boolean continueRunning = true;
        while (continueRunning) {
            displayMainMenu();
            String command = userInputScanner.nextLine().trim();

            switch (command) {
                case "1": handleStudentOptions(); break;
                case "2": handleCourseOptions(); break;
                case "3": handleEnrollmentOptions(); break;
                case "4": handleDataExport(); break;
                case "5": handleBackupAndReports(); break;
                case "0": continueRunning = false; break;
                default: System.out.println("!! ERROR: Unrecognized command. Please select a valid option.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n--- Main Navigation Menu ---");
        System.out.println("  1. Student Record Management");
        System.out.println("  2. Course Catalog Management");
        System.out.println("  3. Enrollment & Grade Management");
        System.out.println("  4. Export Data");
        System.out.println("  5. System Backup & Reports");
        System.out.println("  0. Exit Application");
        System.out.print(">> Enter your selection: ");
    }

    private static void handleStudentOptions() {
        System.out.println("\n-- Student Record Options --");
        studentService.listAll().forEach(System.out::println);
        // Additional options like add/update/deactivate would be placed here.
    }

    private static void handleCourseOptions() {
        System.out.println("\n-- Course Catalog --");
        courseService.listAll().forEach(System.out::println);
    }

    private static void handleEnrollmentOptions() {
        System.out.println("\n-- New Student Enrollment --");
        System.out.print("Enter Student ID to enroll (e.g., S1): ");
        String studentId = userInputScanner.nextLine().trim();
        System.out.print("Enter Course Code (e.g., CS101): ");
        String courseCode = userInputScanner.nextLine().trim();

        try {
            enrollmentService.enroll(studentId, courseCode);
            System.out.println("## SUCCESS: Enrollment has been processed successfully.");
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException | IllegalArgumentException e) {
            System.err.println("!! ENROLLMENT ERROR: " + e.getMessage());
        }
    }

    private static void handleDataExport() {
        try {
            Path exportPath = AppConfig.getInstance().getDataFolder().resolve("student_records_export.csv");
            Files.createDirectories(exportPath.getParent());
            List<String> header = List.of("ID", "RegistrationNo", "FullName", "Email", "IsActive");
            List<List<String>> rows = new ArrayList<>();
            for (Student s : studentService.listAll()) {
                rows.add(List.of(s.getId(), s.getRegNo(), s.getFullName(), s.getEmail(), String.valueOf(s.isActive())));
            }
            FileUtil.exportSimpleCsv(exportPath, header, rows);
            System.out.println("## SUCCESS: Exported " + rows.size() + " student records to the data folder.");
        } catch (IOException e) {
            System.err.println("!! EXPORT FAILED: " + e.getMessage());
        }
    }

    private static void handleBackupAndReports() {
        System.out.println("\n-- System Utilities --");
        try {
            Path dataDirectory = AppConfig.getInstance().getDataFolder();
            if (!Files.exists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            Path backupLocation = FileUtil.backupFolder(dataDirectory);
            System.out.println("## SUCCESS: Backup created at: " + backupLocation);
            System.out.println("   Total backup size: " + FileUtil.folderSize(backupLocation) + " bytes.");
        } catch (IOException e) {
            System.err.println("!! BACKUP FAILED: " + e.getMessage());
        }
    }

    /**
     * Populates the system with some initial sample data for demonstration purposes.
     */
    private static void populateInitialData() {
        studentService.addStudent("S1", "24BCE1001", "Aarav Sharma", "aarav.s@example.com");
        studentService.addStudent("S2", "24BME1002", "Diya Patel", "diya.p@example.com");
        studentService.addStudent("S3", "24BCE1103", "Rohan Verma", "rohan.v@example.com");
        studentService.addStudent("S4", "24BIT1204", "Isha Singh", "isha.s@example.com");

        Instructor profKumar = new Instructor("I1", "Dr. Kumar", "kumar@uni.edu", "Physics");
        courseService.add(new Course.Builder("CS101").title("Intro to Algorithms").credits(4).instructor(profKumar).department("Computer Science").build());
        Instructor profGupta = new Instructor("I1", "Dr. Rohan Gupta", "gupta.r@uni.edu", "Computer Science");
        Instructor profVerma = new Instructor("I2", "Dr. Anjali Verma", "verma.a@uni.edu", "Mathematics");
        Instructor profSingh = new Instructor("I3", "Dr. Priya Singh", "singh.p@uni.edu", "Physics");

        // --- Create Courses ---
        // Using the Builder pattern to create a variety of courses
        courseService.add(new Course.Builder("CS101").title("Intro to Java Programming").credits(4).semester(Semester.FALL).department("Computer Science").instructor(profGupta).build());
        courseService.add(new Course.Builder("CS102").title("Data Structures").credits(4).semester(Semester.SPRING).department("Computer Science").instructor(profGupta).build());
        courseService.add(new Course.Builder("MA201").title("Linear Algebra").credits(3).semester(Semester.FALL).department("Mathematics").instructor(profVerma).build());
        courseService.add(new Course.Builder("PHY101").title("Classical Mechanics").credits(3).semester(Semester.FALL).department("Physics").instructor(profSingh).build());
        courseService.add(new Course.Builder("CS205").title("Database Systems").credits(3).semester(Semester.SPRING).department("Computer Science").instructor(profGupta).build());

    }
}