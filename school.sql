CREATE DATABASE school;

USE school;

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    subject VARCHAR(100),
    attendance_date DATE,
    status VARCHAR(10)
);
