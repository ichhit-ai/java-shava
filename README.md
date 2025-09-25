# Campus Course & Records Manager (CCRM)

## 📖 Project Overview

This is a console-based application built with Java for managing student and course records at an educational institution. The project demonstrates core Java principles, Object-Oriented Programming (OOP), file I/O with NIO.2, and common design patterns like the Singleton and Builder patterns. All data is managed in-memory and can be exported for persistence.

---

## 🏗️ How to Build and Run

### Prerequisites
* JDK 11 or higher
* An IDE like IntelliJ IDEA or Eclipse (or compile from the command line)

### Running from an IDE
1.  Open the `CCRM_Project_Attachment` folder as a new project.
2.  Locate the `Main.java` file in `src/edu/ccrm/cli/`.
3.  Run the `main` method.

### Running from the Command Line
1.  Open a terminal in the project's root directory.
2.  Compile the source code:
    ```bash
    javac -d out src/edu/ccrm/cli/Main.java
    ```
3.  Run the application:
    ```bash
    java -cp out edu.ccrm.cli.Main
    ```

---

## 🕰️ A Brief History of Java
* **1995**: Java 1.0 is released with the "Write Once, Run Anywhere" promise.
* **1998**: Java 2 (J2SE) arrives, introducing major enhancements like the Collections Framework.
* **2004**: Java 5 (Tiger) revolutionizes development with generics, annotations, and enums.
* **2014**: Java 8 is a landmark release, introducing lambdas, streams, and a new Date/Time API.
* **2018-Present**: Faster release cycles begin, with Long-Term Support (LTS) versions like Java 11 and 17 solidifying modern Java development.

---

🖥 Install on Windows
Download JDK 17 from Oracle.
Install and set environment variables:
JAVA_HOME=C:\Program Files\Java\jdk-17
Add %JAVA_HOME%\bin to PATH
Verify:
java -version
javac -version
Install Eclipse IDE → Add Maven plugin if not preinstalled.
Import project: File → Import → Existing Maven Project → select ccrm-parent/pom.xml

## ☕ Java Platform Explained: ME vs. SE vs. EE

| Edition | Full Name          | Primary Use Case                               |
| :------ | :----------------- | :--------------------------------------------- |
| **Java SE** | Standard Edition   | The core Java platform for desktop and server applications (this project uses SE). |
| **Java EE** | Enterprise Edition | Extends SE with APIs for large-scale, web-based, and enterprise applications. |
| **Java ME** | Micro Edition      | A lightweight version for resource-constrained devices like older mobile phones and IoT. |

---

## 🔑 JDK vs. JRE vs. JVM

* **JVM (Java Virtual Machine)**: An abstract machine that executes compiled Java bytecode. It's what makes Java platform-independent.
* **JRE (Java Runtime Environment)**: The software package that provides the JVM and the standard libraries needed to *run* a Java application.
* **JDK (Java Development Kit)**: The full toolkit for developers. It includes the JRE, the compiler (`javac`), debuggers, and other tools needed to *build* a Java application.

---

## 🖥️ Setup and Screenshots
*Project structure*
CCRM_Project_Attachment/
├── .idea/
│   ├── misc.xml
│   ├── modules.xml
│   └── workspace.xml
├── ccrm_data/
│   └── student_records_export.csv
├── out/
│   └── production/
│       └── CCRM_Project_Attachment/
│           └── edu/
│               └── ccrm/
│                   ├── cli/
│                   ├── config/
│                   ├── domain/
│                   ├── exceptions/
│                   ├── io/
│                   └── service/
├── sample_data/
│   ├── courses_sample.csv
│   └── students_sample.csv
├── screenshots/
├── src/
│   └── edu/
│       └── ccrm/
│           ├── cli/
│           │   └── Main
│           ├── config/
│           ├── domain/
│           ├── exceptions/
│           ├── io/
│           └── service/
├── .gitignore
└── CCRM_Project_Attachment.iml




### 1. JDK installation 
<img width="932" height="164" alt="jdk" src="https://github.com/user-attachments/assets/c692196c-b1fc-422c-8cc8-57fb4dc79edf" />



### 2. Folder structure

<img width="558" height="329" alt="struct" src="https://github.com/user-attachments/assets/e0f3b562-4a6b-470a-a76b-27a7d1eb0534" />


### 3. Main menu

<img width="570" height="434" alt="main interface" src="https://github.com/user-attachments/assets/52e917a2-9f7c-4109-ae36-d0b6e9471e2f" />

### 4. Functioning 
<img width="577" height="383" alt="function" src="https://github.com/user-attachments/assets/d9c33945-3665-4dc1-bfd7-a2e547f1c069" />

### 4. Data saving to csv 
<img width="667" height="248" alt="Screenshot 2025-09-25 174244" src="https://github.com/user-attachments/assets/2071892d-47ae-43e0-b7e1-90ab0429bb63" />


---

## 📑 Syllabus Topic Mapping

| Syllabus Topic | File Location in Project |
| :--- | :--- |
| **Singleton Pattern** | `src/edu/ccrm/config/AppConfig.java` |
| **Builder Pattern** | `src/edu/ccrm/domain/Course.java` |
| **Inheritance & Abstraction** | `src/edu/ccrm/domain/Person.java` is extended by `Student.java`. |
| **Custom Exceptions** | `src/edu/ccrm/exceptions/DuplicateEnrollmentException.java` |
| **NIO.2 File I/O** | `src/edu/ccrm/io/FileUtil.java` (used for CSV export and backups). |
| **Streams & Lambdas** | `src/edu/ccrm/service/CourseService.java` (in the `filterBy...` methods). |
| **Enums with Fields**| `src/edu/ccrm/domain/Grade.java` |

---
