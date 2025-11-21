# College Management System

A comprehensive Java-based console application for managing university operations including departments, courses, students, employees, and academic records.

## 🎯 Overview

The College Management System is a role-based database application that streamlines administrative and academic workflows for educational institutions. It provides distinct interfaces for management staff, professors, and students, each with appropriate access controls and functionality.

**Key Users:**
- **Management Staff**: Complete administrative control over all system entities
- **Professors**: Course management, student grading, and academic record keeping
- **Students**: Course registration, grade viewing, and profile management

## ✨ Features

### Management Operations 
- **Department Management**: Create, view, update, and delete departments
- **Class Management**: Manage academic classes/levels
- **Course Management**: Full CRUD operations for courses with professor and department assignment
- **Employee Management**: Add and manage faculty and staff records
- **Student Management**: Complete student lifecycle management
- **Password Management**: Change user passwords securely

### Professor Operations 
- View all departments, classes, and courses
- Access assigned courses
- View enrolled students in courses
- Add, view, update, and delete course grades
- Manage maximum grade thresholds
- Delete specific or all course grades
- Update personal password

### Student Operations
- Register for available courses
- View personal information
- Browse available courses with details
- Check grades across all registered courses
- Change account password

## 🛠️ Tech Stack

- **Language**: Java (Console-based)
- **Database**: MySQL
- **Architecture**: MVC Pattern (Model-View-Controller)
- **JDBC**: Direct database connectivity
- **Input Handling**: Java Scanner for console I/O

## 📁 Project Structure

```
├── Model/
│   ├── Class.java           # Class entity with CRUD operations
│   ├── Course.java          # Course entity with student enrollment
│   ├── Database.java        # Database connection handler
│   ├── Department.java      # Department entity management
│   ├── Employee.java        # Employee entity with role operations
│   ├── Student.java         # Student entity with registration
│   ├── Grade.java           # Grade data model
│   └── Operation.java       # Interface for controller operations
│
└── Controller/
    ├── Department Operations
    │   ├── CreateDepartment.java
    │   ├── ReadDepartments.java
    │   ├── UpdateDepartment.java
    │   └── DeleteDepartment.java
    │
    ├── Class Operations
    │   ├── CreateClass.java
    │   ├── ReadClasses.java
    │   ├── UpdateClass.java
    │   └── DeleteClass.java
    │
    ├── Course Operations
    │   ├── CreateCourse.java
    │   ├── ReadCourses.java
    │   ├── UpdateCourse.java
    │   ├── DeleteCourse.java
    │   ├── ReadEmployeeCourse.java
    │   └── ReadCourseStudents.java
    │
    ├── Employee Operations
    │   ├── CreateEmployee.java
    │   ├── ReadEmployees.java
    │   ├── UpdateEmployee.java
    │   ├── DeleteEmployee.java
    │   └── UpdateEmployeePassword.java
    │
    ├── Student Operations
    │   ├── CreateStudent.java
    │   ├── Readstudents.java
    │   ├── UpdateStudent.java
    │   ├── DeleteStudent.java
    │   ├── RegisterCourse.java
    │   ├── ReadStudentData.java
    │   ├── ReadAvailableCourses.java
    │   ├── ReadStudentGrades.java
    │   └── UpdateStudentPassword.java
    │
    └── Grade Operations
        ├── AddCourseGrades.java
        ├── ReadCourseGrades.java
        ├── UpdateCourseGrade.java
        ├── UpdateCourseMaxGrade.java
        ├── DeleteCourseGrades.java
        └── DeleteSpecCourseGrade.java
```

## 🗄️ Database Schema

### Tables

**departments**
- ID (Primary Key)
- Name

**classes**
- ID (Primary Key)
- Name

**courses**
- ID (Primary Key)
- Name
- Class (Foreign Key)
- Description
- Lim (Student limit)
- Prof (Foreign Key to employees)
- Department (Foreign Key)

**employees**
- ID (Primary Key)
- FirstName
- LastName
- Email
- PhoneNumber
- BirthDate
- Salary
- Department (Foreign Key)
- Password

**students**
- ID (Primary Key)
- FirstName
- LastName
- Email
- PhoneNumber
- BirthDate
- Class (Foreign Key)
- Password

**Dynamic Course Tables** (`course [ID]`)
- Student (Foreign Key)
- Grade-related columns (dynamically managed)

## 🚀 Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL JDBC Driver (Connector/J)

### Database Configuration

1. **Create Database**
```sql
CREATE DATABASE university;
USE university;
```

2. **Create Tables**
```sql
CREATE TABLE departments (
    ID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);

CREATE TABLE classes (
    ID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
    ID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    BirthDate DATE,
    Salary DOUBLE,
    Department INT,
    Password VARCHAR(100),
    FOREIGN KEY (Department) REFERENCES departments(ID)
);

CREATE TABLE students (
    ID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    BirthDate DATE,
    Class INT,
    Password VARCHAR(100),
    FOREIGN KEY (Class) REFERENCES classes(ID)
);

CREATE TABLE courses (
    ID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Class INT,
    Description TEXT,
    Lim INT,
    Prof INT,
    Department INT,
    FOREIGN KEY (Class) REFERENCES classes(ID),
    FOREIGN KEY (Prof) REFERENCES employees(ID),
    FOREIGN KEY (Department) REFERENCES departments(ID)
);
```

3. **Update Database Credentials**

Edit `Model/Database.java`:
```java
private String user = "your_username";
private String pass = "your_password";
private String url = "jdbc:mysql://localhost:3306/university";
```

### Running the Application

1. **Compile**
```bash
javac -cp .:mysql-connector-java.jar Model/*.java Controller/*.java Login.java
```

2. **Run**
```bash
java -cp .:mysql-connector-java.jar Main
```

3. **Login**
- Enter your user type (Student/Employee)
- Provide ID and password
- Access your role-specific menu

## 💡 Usage Examples

### Management Staff Login
```
Enter Department Name: Management
Enter Employee ID: 1001
Enter Password: ****
```

### Professor Operations
```
Select Operation:
04. Show my courses
05. Show Course Students
06. Add Course Grades
```

### Student Operations
```
Select Operation:
01. Register Courses
03. Show Available Courses
04. Show My Grades
```

## 🔒 Security Features

- Password-protected authentication for all users
- Role-based access control (Management/Professor/Student)
- Department-based employee permissions

## ⚠️ Known Limitations

- Console-based interface only (no GUI)
- Passwords stored in plain text (should implement hashing)
- SQL queries use string concatenation (vulnerable to injection)
- No session management or concurrent user support
- Limited error handling and validation


---

**Note**: This system is designed for learning purposes. For production use, implement proper security measures, input validation, and error handling.
