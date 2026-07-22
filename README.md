# AI-Based Internship Recommendation Engine for PM Internship Scheme

## Project Overview

The **AI-Based Internship Recommendation Engine for PM Internship Scheme** is a Java-based command-line application designed to help students find suitable internship opportunities based on their skills, interests, academic background, and location preferences.

The system aims to simplify the internship selection process by providing personalized internship recommendations and reducing the difficulty of searching through a large number of internship opportunities.

---

## Objective

* Help students identify internships that match their skills and interests.
* Provide personalized internship suggestions.
* Reduce the time required to search for suitable opportunities.
* Create a simple and user-friendly system for students from different backgrounds.

---

## Features

### Student Features

* Student Registration
* Student Login
* View Profile
* Update Profile

Student information stored:

* Student ID
* Name
* Age
* Degree
* CGPA
* Skills
* Interests
* Preferred Location
* Password

### Admin Features

* Admin Login
* Manage internship-related information

---

# Project Structure

```
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
│
├── Module 3- Recommendation Engine/
│
├── Module 4- Search and Filter/
│
├── Module 5- Data Storage/
│
├── Module 6- Validation and Exception Handling/
│
├── Module 7- Background Processing/
│
└── Module 8- Collections and Optimization/
```

---

# Modules

## Module 1: User Management

This module manages users of the system.

Implemented Features:

* Student Registration
* Student Login
* Admin Login
* View Profile
* Update Profile

Java Concepts Used:

* Classes
* Objects
* Constructors
* Methods
* Arrays
* Strings
* Encapsulation

---

## Module 2: Internship Management

This module manages internship information handled by administrators.

Planned Features:

* Add Internship
* Update Internship
* Delete Internship
* View Internship Details

Internship Data:

* Company Name
* Role
* Required Skills
* Required CGPA
* Location
* Stipend
* Duration

Java Concepts Used:

* Classes
* Objects
* Methods
* String Handling
* Collections

---

## Module 3: Recommendation Engine

This is the core module of the project that generates personalized internship recommendations.

The system compares student profiles with available internships and assigns matching scores.

Planned Features:

* Skill matching
* CGPA matching
* Location matching
* Internship ranking
* Top internship suggestions

Java Concepts Used:

* Loops
* Methods
* Arrays
* Sorting
* Streams API
* Lambda Expressions

---

## Module 4: Search and Filter

This module allows students to manually search for internships.

Planned Filters:

* Skills
* Company
* Location
* Minimum CGPA

Java Concepts Used:

* String Handling
* Collections
* Iterators
* Streams API

---

## Module 5: Data Storage

This module manages storing and retrieving application data.

Planned Implementation:

* File Handling
* MySQL Database Integration

Stored Data:

* Student Details
* Internship Details

Java Concepts Used:

* File Handling
* BufferedReader
* BufferedWriter
* JDBC

---

## Module 6: Validation and Exception Handling

This module ensures reliable system operation.

Handles:

* Invalid inputs
* Missing information
* Incorrect credentials
* Data validation

Java Concepts Used:

* try
* catch
* finally
* throw
* throws
* Custom Exceptions

---

## Module 7: Background Processing

This module handles tasks that can run in the background.

Examples:

* Recommendation generation
* Automatic data saving
* Loading processes

Java Concepts Used:

* Threads
* Thread Lifecycle
* Synchronization
* Concurrent Programming

---

## Module 8: Collections and Optimization

This module improves data management efficiency using Java Collections.

Collections Used:

* ArrayList for storing students and internships
* HashSet for managing unique skills
* HashMap for quick student lookup
* Queue for managing recent registrations

Java Concepts Used:

* Collections Framework
* Lists
* Sets
* Maps
* Queues
* Iterators

---

# Technologies Used

* Java
* Object-Oriented Programming Concepts
* Command Line Interface
* File Handling
* MySQL Database (Planned)

---

# How to Run

## Clone Repository

```bash
git clone https://github.com/minhuparthasarathy/JAVA-PROJECT.git
```

## Navigate to Project Folder

```bash
cd JAVA-PROJECT
```

## Compile the Application

```bash
javac Main.java
```

## Run the Application

```bash
java Main
```

---

# Development Status

Completed:

* Module 1 - User Management

In Progress:

* Module 2 - Internship Management
* Module 3 - Recommendation Engine
* Module 4 - Search and Filter
* Module 5 - Data Storage
* Module 6 - Validation and Exception Handling
* Module 7 - Background Processing
* Module 8 - Collections and Optimization

---

# Team

Developed as part of a Java project based on the **AI-Based Internship Recommendation Engine for PM Internship Scheme**.

---

# License

This project is developed for educational purposes.
