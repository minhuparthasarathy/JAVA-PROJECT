# AI-Based Internship Recommendation Engine for PM Internship Scheme

## Project Overview

The **AI-Based Internship Recommendation Engine for PM Internship Scheme** is a Java-based application designed to help students find suitable internship opportunities based on their academic profile, skills, interests, location preferences, and stipend preferences.

The system provides personalized internship recommendations while also allowing students to search and filter available internship opportunities manually.

The application uses a **Java Swing graphical user interface** with a simple, user-friendly design suitable for students with different levels of digital familiarity.

---

## Objective

* Help students identify internships that match their skills and interests.
* Provide personalized internship recommendations.
* Reduce the time required to search for suitable internship opportunities.
* Consider academic eligibility, skills, location, and stipend preferences.
* Provide a simple and user-friendly interface.
* Demonstrate the practical application of Java Object-Oriented Programming and Collections concepts.

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

Student information includes:

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

## Admin Features

* Admin Login
* Add Internship
* Update Internship
* Delete Internship
* View Internship Details
* Manage Internship Records

## Recommendation Features

The recommendation engine evaluates internships based on:

* CGPA eligibility
* Skill matching
* Location preference
* Preferred stipend

A student must satisfy the required CGPA and have at least one matching skill for an internship to be considered eligible.

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
└── students.txt
```

---

# Modules

## Module 1: User Management

This module manages students and administrators in the system.

### Implemented Features

* Student Registration
* Student Login
* Admin Login
* View Profile
* Update Profile
* Password Management

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

This module manages internship information handled by administrators.

### Implemented Features

* Add Internship
* Update Internship
* Delete Internship
* View Internship Details
* Internship ID-based lookup
* File-based internship persistence

### Internship Data

* Internship ID
* Company Name
* Role
* Required Skills
* Required CGPA
* Location
* Stipend
* Duration

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

This is the core module of the project that generates personalized internship recommendations.

The system compares student profiles with available internships and calculates a matching score.

### Implemented Features

* Skill matching
* CGPA eligibility
* Location preference
* Preferred stipend consideration
* Match score calculation
* Internship recommendations
* Skill comparison

Skill matching is performed using case-insensitive and whitespace-tolerant comparisons.

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

This module allows students to manually search and filter internship opportunities.

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

This module manages persistent application data using Java File I/O.

### Implemented Features

* Student data storage
* Student data retrieval
* Internship data storage
* Internship data retrieval
* Profile persistence
* Preferred stipend persistence
* Backward compatibility for older student records

### Data Files

* `students.txt`
* `internships.txt`

### Java Concepts Used

* File Handling
* FileReader
* FileWriter
* BufferedReader
* BufferedWriter
* ArrayList
* Exception Handling

---

## Module 6: Validation and Exception Handling

This module ensures reliable system operation by validating user input and handling errors.

### Implemented Features

* Input validation
* Required field validation
* Numeric validation
* CGPA validation
* Stipend validation
* Credential validation
* Internship validation
* Exception handling
* User-friendly error messages

### Java Concepts Used

* `try`
* `catch`
* `finally`
* `throw`
* `throws`
* Custom Exceptions

---

## Module 7: Background Processing

This module handles time-consuming recommendation processing without blocking the graphical user interface.

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

This module improves the efficiency and organization of data processing using Java Collections.

### Implemented Features

* `ArrayList` for internship records
* `HashMap<Integer, Internship>` for fast internship ID lookup
* `HashSet` for efficient skill matching
* Synchronized internship collections during CRUD operations
* Efficient internship lookup
* Collection-based filtering
* Comparator-based sorting
* Partial and case-insensitive search
* Optimized recommendation skill matching

### Java Concepts Used

* Collections Framework
* Lists
* Sets
* Maps
* `HashMap`
* `HashSet`
* `Comparator`
* `List.sort()`
* Generics

---

# Graphical User Interface

The application uses **Java Swing** for its graphical user interface.

The interface provides:

* Student registration and login screens
* Student dashboard
* Profile management
* Internship recommendations
* Internship search
* Admin dashboard
* Internship management
* Internship update and viewing screens
* Simple white and blue government-portal-style interface
* User-friendly input forms
* Background recommendation processing

---

# Technologies Used

* Java
* Java Swing
* Object-Oriented Programming
* Java Collections Framework
* File I/O
* Exception Handling
* `SwingWorker`
* Git and GitHub

---

# Data Storage

The application currently uses text files for persistent storage.

```text
students.txt
internships.txt
```

Student and internship information is loaded when the application starts and saved when records are created or updated.

---

# How to Run

## Clone Repository

```bash
git clone https://github.com/minhuparthasarathy/JAVA-PROJECT.git
```

## Navigate to Project Folder

```bash
cd JAVA-PROJECT/AI-Based-Internship-Recommendation-Engine
```

## Compile the Application

On Windows, compile the Java source files according to the project structure.

For example:

```bash
javac Main.java
```

## Run the Application

```bash
java Main
```

---

# Development Status

## Completed

* Module 1 - User Management
* Module 2 - Internship Management
* Module 3 - Recommendation Engine
* Module 4 - Search and Filter
* Module 5 - Data Storage
* Module 6 - Validation and Exception Handling
* Module 7 - Background Processing
* Module 8 - Collections and Optimization

---

# Version Control

The project is maintained using **Git and GitHub**.

Development is organized using feature/module branches and pull requests to `main`.

Major completed modules are merged into the `main` branch after testing.

---

# Team

Developed as part of a Java project based on the **AI-Based Internship Recommendation Engine for PM Internship Scheme**.

---

# License

This project is developed for educational purposes.
