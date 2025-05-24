CREATE DATABASE exam_seating;

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
