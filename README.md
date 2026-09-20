# AI-Based Internship Recommendation Engine for PM Internship Scheme

## Project Overview

The **AI-Based Internship Recommendation Engine for PM Internship Scheme** is a Java-based standalone desktop application designed to help students identify suitable internship opportunities based on their academic profile, skills, interests, location preferences, and stipend preferences.

The system provides personalized internship recommendations while also allowing students to manually search, filter, and sort available internship opportunities.

The application uses a **Java Swing graphical user interface** with a simple, user-friendly design suitable for students with different levels of digital familiarity.

---

## Objectives

* Help students identify internships that match their skills and interests.
* Provide personalized internship recommendations.
* Reduce the time required to search for suitable internship opportunities.
* Consider academic eligibility, skills, location, and stipend preferences.
* Provide a simple and user-friendly graphical interface.
* Demonstrate the practical application of Java Object-Oriented Programming and Collections concepts.
* Apply file handling, validation, exception handling, background processing, and modular software development.

---

# Features

## Student Features

* Student Registration
* Student Login
* View Profile
* Update Profile
* Change Password
* Preferred Location
* Preferred Stipend
* Personalized Internship Recommendations
* Internship Search
* Internship Filtering
* Internship Sorting

### Student Information

* Student ID
* Name
* Age
* Degree
* CGPA
* Skills
* Interests
* Preferred Location
* Preferred Stipend
* Password

---

## Admin Features

* Admin Login
* Add Internship
* Update Internship
* Delete Internship
* View Internship Details
* Internship ID-based lookup
* Manage Internship Records
* Persistent internship data storage

### Internship Information

* Internship ID
* Company Name
* Role
* Required Skills
* Required CGPA
* Location
* Stipend
* Duration

---

# Recommendation Engine

The Recommendation Engine generates personalized internship recommendations by comparing the student's profile with available internship requirements.

The recommendation process considers:

* CGPA eligibility
* Skill matching
* Location preference
* Preferred stipend
* Match score

A student must satisfy the required CGPA and have at least one matching skill for an internship to be considered eligible.

Skill comparison is performed using **case-insensitive and whitespace-tolerant matching**.

The recommendation process can run in the background so that the graphical user interface remains responsive while recommendations are being generated.

---

# Search and Filter

Students can manually search and filter available internships using multiple criteria.

### Supported Criteria

* Company
* Role
* Location
* Required Skills
* Minimum CGPA

### Search Features

* Partial matching
* Case-insensitive search
* Combined filtering
* Internship sorting
* Collection-based filtering

---

# Project Structure

```text
AI-Based-Internship-Recommendation-Engine/

│
├── Main.java
│
├── Module 1- User Management/
│   ├── User.java
│   ├── Student.java
│   ├── Admin.java
│   ├── UserManager.java
│   └── UserOperations.java
│
├── Module 2- Internship Management/
│   ├── Internship.java
│   ├── InternshipManager.java
│   └── InternshipOperations.java
│
├── Module 3- Recommendation Engine/
│   └── RecommendationEngine.java
│
├── Module 4- Search and Filter/
│   └── SearchFilter.java
│
├── Module 5- Data Storage/
│   └── StudentStorage.java
│
├── Module 6- Validation and Exception Handling/
│
├── Module 7- Background Processing/
│
├── Module 8- Collections and Optimization/
│
├── gui/
│   ├── AdminDashPanel.java
│   ├── AdminLoginPanel.java
│   ├── ProfilePanel.java
│   ├── SearchPanel.java
│   ├── StudentDashPanel.java
│   ├── StudentLoginPanel.java
│   ├── StudentRegPanel.java
│   ├── UpdateInternshipPanel.java
│   └── ViewInternshipsPanel.java
│
├── internships.txt
├── students.txt
├── run.bat
└── .gitignore
```

---

# Modules

## Module 1: User Management

The User Management module manages students and administrators.

### Implemented Features

* Student Registration
* Student Login
* Admin Login
* View Profile
* Update Profile
* Change Password
* Preferred location management
* Preferred stipend management

### Java Concepts Used

* Classes
* Objects
* Constructors
* Methods
* Arrays
* Strings
* Encapsulation
* Inheritance
* Interfaces
* Polymorphism

---

## Module 2: Internship Management

The Internship Management module manages internship information handled by administrators.

### Implemented Features

* Add Internship
* Update Internship
* Delete Internship
* View Internship Details
* Internship ID-based lookup
* Internship data persistence

### Java Concepts Used

* Classes
* Objects
* Methods
* String Handling
* ArrayList
* HashMap
* File Handling

---

## Module 3: Recommendation Engine

The Recommendation Engine is the core module responsible for generating personalized internship recommendations.

### Implemented Features

* CGPA eligibility checking
* Skill matching
* Location preference matching
* Preferred stipend consideration
* Match score calculation
* Internship recommendations
* Efficient skill comparison

### Java Concepts Used

* Methods
* Loops
* Arrays
* ArrayList
* HashSet
* String Handling
* Object-Oriented Programming

---

## Module 4: Search and Filter

The Search and Filter module allows students to manually locate suitable internships.

### Implemented Features

* Search by company
* Search by role
* Search by location
* Search by required skills
* Minimum CGPA filtering
* Partial search
* Case-insensitive search
* Combined filtering
* Internship sorting

### Java Concepts Used

* String Handling
* Collections
* ArrayList
* HashSet
* Comparator
* List sorting

---

## Module 5: Data Storage

The Data Storage module provides persistent storage using Java File I/O.

### Implemented Features

* Student data storage
* Student data retrieval
* Internship data storage
* Internship data retrieval
* Profile persistence
* Preferred stipend persistence
* Backward compatibility for older student records

### Data Files

```text
students.txt
internships.txt
```

### Java Concepts Used

* FileReader
* FileWriter
* BufferedReader
* BufferedWriter
* ArrayList
* Exception Handling

---

## Module 6: Validation and Exception Handling

This module improves reliability by validating user input and handling errors.

### Implemented Features

* Required field validation
* Numeric validation
* CGPA validation
* Stipend validation
* Credential validation
* Internship validation
* Exception handling
* User-friendly error messages
* Custom exception handling

### Java Concepts Used

* `try`
* `catch`
* `finally`
* `throw`
* `throws`
* Custom Exceptions

---

## Module 7: Background Processing

The Background Processing module prevents time-consuming recommendation operations from blocking the graphical user interface.

### Implemented Features

* Background recommendation generation
* Non-blocking GUI operations
* Recommendation processing status
* Button state management during processing

### Java Concepts Used

* Threads
* `SwingWorker`
* Background Processing
* Event Dispatch Thread (EDT)

---

## Module 8: Collections and Optimization

This module improves data organization and processing efficiency using the Java Collections Framework.

### Implemented Features

* `ArrayList` for internship records
* `HashMap<Integer, Internship>` for fast internship ID lookup
* `HashSet` for efficient skill matching
* Collection-based filtering
* Comparator-based sorting
* Partial search
* Case-insensitive search
* Optimized recommendation skill matching
* Synchronized internship collections during CRUD operations
* Generic collection usage

### Java Concepts Used

* Collections Framework
* Lists
* Sets
* Maps
* `ArrayList`
* `HashMap`
* `HashSet`
* `Comparator`
* `List.sort()`
* Generics

---

# Graphical User Interface

The application uses **Java Swing** to provide a graphical interface.

The GUI includes:

* Student Registration
* Student Login
* Student Dashboard
* Profile Management
* Password Management
* Internship Recommendations
* Internship Search
* Internship Filtering
* Admin Login
* Admin Dashboard
* Internship Management
* Internship Update
* Internship Viewing
* User-friendly input forms
* Background recommendation processing

The interface follows a simple white-and-blue design suitable for a government-style internship portal.

---

# Technologies Used

* **Java**
* **Java Swing**
* **Object-Oriented Programming**
* **Java Collections Framework**
* **ArrayList**
* **HashMap**
* **HashSet**
* **Comparator**
* **Java File I/O**
* **Exception Handling**
* **Custom Exceptions**
* **Threads**
* **SwingWorker**
* **Event Dispatch Thread (EDT)**
* **Git**
* **GitHub**

---

# Data Storage

The application uses text files for persistent local storage instead of a database.

```text
students.txt
internships.txt
```

Student and internship information is loaded when the application starts and saved when records are created or updated.

The application also supports preferred stipend information and maintains compatibility with older student records that may not contain the newer fields.

---

# How to Run

## Requirements

Before running the application, make sure the following are installed:

* Java Development Kit (JDK)
* Git, if cloning the repository
* Windows operating system for using `run.bat`

---

## Option 1: Run Using `run.bat` — Recommended

The project includes a **`run.bat` Windows batch file** that simplifies compilation and execution.

The batch file is provided so users do not need to manually enter multiple Java compilation and execution commands.

### Steps

1. Clone the repository:

```bash
git clone https://github.com/minhuparthasarathy/JAVA-PROJECT.git
```

2. Open the project directory:

```text
JAVA-PROJECT\AI-Based-Internship-Recommendation-Engine
```

3. Make sure the JDK is installed and configured in the system PATH.

4. Double-click:

```text
run.bat
```

### Command Prompt Method

Alternatively, open Command Prompt inside the project folder and run:

```bat
run.bat
```

The batch file compiles the required Java source files and launches the application.

After successful execution, the **Java Swing graphical interface** will open.

---

## Option 2: Manual Execution

The application can also be compiled and executed manually using Java commands.

For a simple project entry point:

```bash
javac Main.java
java Main
```

For the complete modular project, the Java source files should be compiled according to their project structure.

---

# Development Status

All eight project modules have been implemented and integrated.

### Completed Modules

* Module 1 — User Management
* Module 2 — Internship Management
* Module 3 — Recommendation Engine
* Module 4 — Search and Filter
* Module 5 — Data Storage
* Module 6 — Validation and Exception Handling
* Module 7 — Background Processing
* Module 8 — Collections and Optimization

### Final Application

The completed application provides:

* Student registration and authentication
* Admin authentication
* Student profile management
* Internship CRUD operations
* Personalized recommendations
* Search and filtering
* Internship sorting
* Persistent file storage
* Input validation
* Exception handling
* Background recommendation processing
* Java Swing graphical interface
* Optimized Java Collections usage

---

# Testing and Verification

The application was tested throughout development to verify:

* Student registration and login
* Admin login
* Profile updates
* Internship creation, updating, deletion, and viewing
* Recommendation generation
* CGPA eligibility
* Skill matching
* Location preference
* Stipend preference
* Search and filtering
* Internship sorting
* File-based data persistence
* Invalid input handling
* Exception handling
* GUI navigation
* Background recommendation processing
* Integration of all eight modules

The project was also verified from a fresh clone of the GitHub repository after the completed changes were merged into the `main` branch.

---

# Version Control

The project is maintained using **Git and GitHub**.

Repository:

```text
https://github.com/minhuparthasarathy/JAVA-PROJECT.git
```

Development was organized using feature/module branches. Completed changes were tested and merged into the `main` branch.

---

# Project Limitations

* The application uses local text-file storage rather than a centralized database.
* It is currently designed as a standalone desktop application.
* It does not retrieve live internship listings from external portals or APIs.
* The recommendation system uses predefined matching rules rather than a trained machine-learning model.
* Extensive multi-user concurrency and load testing are outside the current project scope.

---

# Future Enhancements

Possible future improvements include:

* Database integration using MySQL or another database system
* Web and mobile versions
* Cloud-based storage
* Multi-user and centralized access
* Live internship API integration
* Machine-learning-based recommendations
* Notifications and application tracking
* Resume-based recommendation
* Recommendation history and student feedback

---

# Team

Developed as part of a Java Project based on the:

**AI-Based Internship Recommendation Engine for PM Internship Scheme**

---

# License

This project is developed for educational purposes.
