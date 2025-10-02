Project Title: Student Management System
Project Description:

The Student Management System is a desktop application built using Java (Swing for GUI) and MySQL for database management. This system simplifies and automates the process of managing student records in educational institutes. It provides an intuitive interface for performing student-related operations efficiently.

Key Features:

Add Student: Add new student details such as Name, Email, Course, Roll Number, and Section.

View Students: Displays all registered students in a table with all details.

Update Student: Select a student from the table and update their information.

Delete Student: Remove a student record from the database.

Database Connectivity: Stores and retrieves data securely using MySQL.

Interactive GUI: Easy-to-use interface built with Java Swing.

Technologies Used:

Frontend: Java Swing (JFrame, JTable, JButton, JTextField, JOptionPane)

Backend / Database: Java JDBC with MySQL

Development Tools: JDK, MySQL Workbench

Implementation / How to Run the Program:

Install Prerequisites:

Install Java JDK and MySQL Server.

Set up MySQL Workbench for database management.

Database Setup:

Create a database called student_db:

CREATE DATABASE student_db;


Create a table students with columns for ID, Name, Email, Course, Roll Number, and Section:

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    course VARCHAR(50),
    roll_number VARCHAR(20),
    section VARCHAR(10)
);


Project Setup:

Download or copy the project files:

Student.java – Model class

StudentDAO.java – Database operations

StudentGUI.java – GUI interface

Main.java – Optional console-based version

Add the MySQL Connector JAR (mysql-connector-j-9.4.0.jar) to your project classpath.

Running the Program:

Compile the Java files:

javac -cp ".;lib/mysql-connector-j-9.4.0.jar" *.java


Run the GUI program:

java -cp ".;lib/mysql-connector-j-9.4.0.jar" StudentGUI


Use the interface to add, view, update, or delete students.

Expected Output:

A GUI window will open showing input fields for Name, Email, Course, Roll Number, and Section.

Below the input, a table will display all student records stored in the database.

Buttons allow you to add, view, update, or delete students interactively.

Benefits:

Reduces manual effort and paper records.

Centralized digital system for managing student information.

Easy retrieval, update, and deletion of records.

