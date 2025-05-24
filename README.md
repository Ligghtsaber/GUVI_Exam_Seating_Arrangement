# Exam Seating Arrangement System (Java + MSSQL)

## 📚 Overview
This is a Java Swing-based application for managing exam seating arrangements for students. It automates the process based on input data from students and admins.

---

## 👤 Student Features
- Register and login using Reg No
- Input course units (course code and title)
- View entered course units

---

## 🛠️ Admin Features
- Login securely
- Input classrooms and their seating capacity
- Generate automatic seating arrangement
- View seating by student and room
- Export seating arrangement as a PDF (iText)

---

## 🗃️ Database (MSSQL)
Make sure to create the following tables:
```sql
CREATE DATABASE exam_seating_arrangement;
```

After running the above script, run the below script:

```sql
USE exam_seating_arrangement;

-- Create Students table
CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    reg_no VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    session VARCHAR(20) NOT NULL,
    semester VARCHAR(20) NOT NULL,
    year VARCHAR(20) NOT NULL,
    program VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Create CourseUnits table
CREATE TABLE CourseUnits (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_code VARCHAR(20) NOT NULL,
    course_title VARCHAR(100) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

-- Create Rooms table
CREATE TABLE Rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(50) NOT NULL,
    seat_capacity INT NOT NULL
);

-- Create SeatingArrangement table
CREATE TABLE SeatingArrangement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    room_id INT NOT NULL,
    seat_number INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id)
);

-- Create Admins table
CREATE TABLE Admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
```

---

## 🏃 Running the Project
1. Add `itextpdf-5.x.x.jar` to your project classpath for PDF export.
2. Configure MSSQL DB credentials in `DBConnection.java`.
3. Run `Main.java` to launch.

---

## 📦 Structure

- `StudentRegistrationGUI.java` - Student registration
- `StudentLoginGUI.java` - Student login
- `StudentDashboardGUI.java` - Course input/view
- `AdminLoginGUI.java` - Admin login
- `AdminDashboardGUI.java` - Manage classrooms/seating
- `CourseUnitInputGUI.java` - Add course units
- `ViewCourseUnitsGUI.java` - View added units
- `ClassroomInputGUI.java` - Input room/seat info
- `SeatingGeneratorGUI.java` - Auto assign seats
- `SeatingViewGUI.java` - View seating plan
- `PDFExporter.java` - Export seating to PDF
- `DBConnection.java` - Database connection
- `Main.java` - Entry point

---

## ✅ Requirements
- Java 8+
- MSSQL Server
- iText 5.x (for PDF export)
- Any Java IDE (IntelliJ, NetBeans, Eclipse)
