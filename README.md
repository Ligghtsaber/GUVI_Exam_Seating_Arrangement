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
CREATE TABLE Students (
    student_id INT PRIMARY KEY IDENTITY(1,1),
    reg_no VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    session VARCHAR(20),
    semester VARCHAR(20),
    year VARCHAR(20),
    program VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE CourseUnits (
    id INT PRIMARY KEY IDENTITY(1,1),
    student_id INT,
    course_code VARCHAR(20),
    course_title VARCHAR(100),
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

CREATE TABLE Rooms (
    room_id INT PRIMARY KEY IDENTITY(1,1),
    room_name VARCHAR(50),
    seat_capacity INT
);

CREATE TABLE SeatingArrangement (
    id INT PRIMARY KEY IDENTITY(1,1),
    student_id INT,
    room_id INT,
    seat_number INT,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (room_id) REFERENCES Classrooms(room_id)
);

CREATE TABLE Admins (
    admin_id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50),
    password VARCHAR(50)
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

---

## ✨ License
This project is for educational purposes.

