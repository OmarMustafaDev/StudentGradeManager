# Student Grade Manager

A Java console-based application for managing students, courses, grades, and GPA calculations.

This project was built to practice and apply core Java concepts in a complete application rather than isolated exercises.

## Features

- Add and remove students
- Add and remove courses
- Assign grades to students
- Calculate student GPA
- Generate student transcripts
- Save application data to CSV files
- Load application data from CSV files
- Input validation
- Exception handling
- Interactive command-line menu

## Technologies & Concepts

- **Java**
- Object-Oriented Programming (OOP)
- Encapsulation
- Collections Framework
  - `HashMap`
  - `Map`
- Exception Handling
- File I/O
  - `FileReader`
  - `FileWriter`
  - `BufferedReader`
  - `BufferedWriter`
- CSV data storage
- Regular Expressions
- Java Packages
- Modular class design

## Project Structure

```text
Student Grade Manager/
│
├── data/
│   ├── students.csv
│   ├── courses.csv
│   └── grades.csv
│
├── src/
│   ├── app/
│   │   └── Main.java
│   │
│   ├── model/
│   │   ├── Student.java
│   │   └── Course.java
│   │
│   ├── service/
│   │   └── GradeService.java
│   │
│   ├── storage/
│   │   └── FileStorage.java
│   │
│   └── util/
│       └── GpaCalculator.java
│
└── README.md
