# Internship Recommendation Engine for PM Internship Scheme

A Java-based desktop application that helps students discover suitable internship opportunities based on their academic qualifications, skills, interests, preferred locations, and stipend expectations.

Built using **Java Swing**, **Object-Oriented Programming**, the **Java Collections Framework**, and file-based storage, the application combines personalized internship recommendations with manual search, filtering, and sorting.

## Overview

Finding a suitable internship can be challenging when students must compare eligibility requirements, technical skills, locations, and stipends across multiple opportunities.

The Internship Recommendation Engine simplifies this process by allowing students to maintain their profiles and receive recommendations based on internship requirements. Administrators can manage internship listings through a dedicated interface.

The project demonstrates how core Java concepts can be combined to build a modular desktop application with persistent data storage, input validation, and responsive background processing.

## Features

### Student Portal

- Student registration and login
- View and update student profiles
- Change passwords
- Manage preferred locations and stipends
- Receive personalized internship recommendations
- Search internships by company, role, location, and required skills
- Filter internships by minimum CGPA and other criteria
- Sort internship listings

### Admin Portal

- Administrator login
- Add new internship opportunities
- Update existing internship records
- Delete internship listings
- View internship details
- Look up internships using their IDs
- Manage persistent internship records

### Personalized Recommendation Engine

The recommendation engine compares student profiles against internship requirements using:

- **CGPA eligibility:** Checks whether the student meets the minimum academic requirement.
- **Skill matching:** Identifies matching skills using case-insensitive, whitespace-tolerant comparisons.
- **Location preferences:** Considers the student's preferred location.
- **Stipend preferences:** Takes the student's preferred stipend into account.
- **Match scoring:** Evaluates relevant criteria to help identify suitable opportunities.

An internship is considered eligible when the student meets its minimum CGPA requirement and has at least one matching skill. Other preferences contribute to the recommendation process.

### Search, Filtering, and Sorting

Students can search for internships without relying exclusively on generated recommendations.

Supported capabilities include:

- Search by company name
- Search by internship role
- Search by location
- Search by required skills
- Filter by minimum CGPA
- Partial and case-insensitive matching
- Combine multiple filtering criteria
- Sort internship results

### Data Persistence

The application uses local text files instead of a database.

- `students.txt` — Stores student records.
- `internships.txt` — Stores internship records.

Student and internship information is loaded when the application starts and saved as records are created or updated. The project also supports backward compatibility with older student records that do not contain newer preference fields.

### Responsive GUI Processing

Recommendation generation can run in the background so that lengthy operations do not unnecessarily block the Swing interface.

The implementation uses `SwingWorker`, background processing, and Swing's Event Dispatch Thread (EDT) to manage GUI responsiveness and UI updates.

## Application Modules

| Module | Responsibility |
|---|---|
| Module 1: User Management | Registration, authentication, profiles, and preferences |
| Module 2: Internship Management | Internship creation, updates, deletion, and lookup |
| Module 3: Recommendation Engine | Eligibility checks, skill matching, and recommendation scoring |
| Module 4: Search and Filter | Search, combined filtering, and sorting |
| Module 5: Data Storage | File-based persistence and record retrieval |
| Module 6: Validation and Exception Handling | Input validation, exception handling, and error messages |
| Module 7: Background Processing | Non-blocking recommendation generation |
| Module 8: Collections and Optimization | Efficient data organization, lookups, and matching |

## Technology Stack

- **Language:** Java
- **GUI:** Java Swing
- **Programming principles:** Object-Oriented Programming
- **Collections:** `ArrayList`, `HashMap`, `HashSet`
- **Sorting:** `Comparator` and `List.sort()`
- **Storage:** Java File I/O
- **Concurrency:** Threads, `SwingWorker`, and the Event Dispatch Thread
- **Reliability:** Input validation, exception handling, and custom exceptions
- **Version control:** Git and GitHub

## Project Structure

```text
JAVA-PROJECT/
└── AI-Based-Internship-Recommendation-Engine/
    ├── Main.java
    ├── Module 1- User Management/
    │   ├── User.java
    │   ├── Student.java
    │   ├── Admin.java
    │   ├── UserManager.java
    │   └── UserOperations.java
    ├── Module 2- Internship Management/
    │   ├── Internship.java
    │   ├── InternshipManager.java
    │   └── InternshipOperations.java
    ├── Module 3- Recommendation Engine/
    │   └── RecommendationEngine.java
    ├── Module 4- Search and Filter/
    │   └── SearchFilter.java
    ├── Module 5- Data Storage/
    │   └── StudentStorage.java
    ├── Module 6- Validation and Exception Handling/
    ├── Module 7- Background Processing/
    ├── Module 8- Collections and Optimization/
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
    ├── internships.txt
    ├── students.txt
    └── run.bat
```

*Note: The structure above highlights the main application directory and the files described in the project. Some modules may contain additional implementation files.*

## Prerequisites

Before running the application, ensure you have:

- **Java Development Kit (JDK):** Installed and configured in your system's `PATH`.
- **Git:** Required if you want to clone the repository.
- **Windows:** Required to run the included `run.bat` file directly.

Verify your Java installation:

```bash
java -version
javac -version
```

Both commands should work before you proceed.

## How to Run

### Option 1: Run on Windows using `run.bat`

This is the recommended approach for Windows users.

**1. Clone the repository**

```bash
git clone https://github.com/minhuparthasarathy/JAVA-PROJECT.git
```

**2. Open the project directory**

```bash
cd JAVA-PROJECT/AI-Based-Internship-Recommendation-Engine
```

**3. Run the application**

Double-click `run.bat`, or execute it from Command Prompt:

```bat
run.bat
```

The batch file is intended to compile the required Java source files and launch the application. Once execution succeeds, the Java Swing interface should open.

### Option 2: Compile and run manually

Navigate to the application directory and inspect the source structure and compilation requirements.

For a simple project with no additional compilation dependencies, the basic commands are:

```bash
javac Main.java
java Main
```

**Important:** The application is modular, so compiling `Main.java` alone may not be sufficient if other source files must be compiled together. If compilation fails, use the project's `run.bat` instructions or compile the required Java files according to their package and dependency structure.

## Data Storage and Privacy

The application stores its student and internship records in local text files. It does not require a database server for the described storage workflow.

Because student profiles can contain personal information, avoid sharing the generated data files publicly. The local file-based design is suitable for a standalone project, but it does not provide the access controls, centralized management, or security guarantees expected from a production multi-user system.

## Project Limitations

- Uses local text files instead of a centralized database.
- Runs as a standalone desktop application.
- Does not fetch live internship listings from external APIs.
- Uses rule-based matching rather than a trained machine-learning model.
- Has not been described as undergoing extensive multi-user concurrency or load testing.

## Future Enhancements

Potential improvements include:

- Database integration using MySQL or PostgreSQL
- Live internship listings through external APIs
- Resume-based skill extraction and recommendations
- Machine-learning-assisted recommendation ranking
- Internship application tracking and notifications
- Recommendation history and student feedback
- Cloud storage and a web-based interface
- Stronger authentication and secure password storage

## Learning Outcomes

This project provides practical experience with:

- Java Object-Oriented Programming
- Interfaces, inheritance, encapsulation, and polymorphism
- Collections and efficient data lookup
- File handling and persistent storage
- Input validation and exception handling
- GUI development using Java Swing
- Background processing and thread-safe UI updates
- Modular design and Git-based collaboration

## Repository

[View JAVA-PROJECT on GitHub](https://github.com/minhuparthasarathy/JAVA-PROJECT)

## License

This project includes a `LICENSE` file. Refer to the repository's license for the applicable terms.

---

**Built with Java to make internship discovery more structured, accessible, and personalized.**
