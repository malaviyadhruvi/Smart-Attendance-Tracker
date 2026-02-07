# Smart Attendance & Performance Tracker

## Overview
This is a **Java console-based application** to manage student attendance and performance.  
It allows an **admin/teacher** to add student details, mark attendance, and enter marks.  
Students can view their report, including **attendance percentage**, **marks**, and **performance remarks**.

The system also includes **AI-assisted features**:
- Performance remarks based on marks
- Attendance shortage warning (< 75%)

---

## Features

### Admin Panel
- Login with username and password
- Add student details (roll number, name, course, semester)
- Enter total lectures and attended lectures
- Enter marks for a subject
- All data saved in MySQL database

### Student Panel
- View student report by roll number
- Shows attendance percentage
- Marks obtained
- AI-generated performance remark:
  - Marks ≥ 75 → Good  
  - Marks 50–74 → Average  
  - Marks < 50 → Needs Improvement  
- Attendance warning if < 75%

---

## Technologies Used
- **Java** – Console-based application
- **MySQL / phpMyAdmin** – Database
- **JDBC (MySQL Connector/J)** – Java-MySQL connectivity
- **Scanner class** – User input
- **PreparedStatement** – Secure SQL queries

---

## Database Setup

### Database Name: `attendance_db`

### Tables

```sql
-- STUDENTS TABLE
CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    course VARCHAR(10),
    semester INT
);

-- ATTENDANCE TABLE
CREATE TABLE attendance (
    roll_no INT,
    total_lectures INT,
    attended_lectures INT,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no)
);

-- MARKS TABLE
CREATE TABLE marks (
    roll_no INT,
    subject_marks INT,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no)
);

-- ADMIN TABLE
CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

-- Example admin login
INSERT INTO admin VALUES ('admin', 'admin123');
