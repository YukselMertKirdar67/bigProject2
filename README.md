# Employee Management System with Redis Cache & Hadoop HDFS
This project demonstrates integration of Redis Cache and Hadoop HDFS in a Java Spring Boot web application.
The system performs CRUD operations on employee data and retrieves employee images from HDFS.

## 📌 Project Description

The application is built to demonstrate:

- Redis as caching layer for database access
- CRUD operations on EMP table (SCOTT schema)
- HDFS integration for storing employee images
- SQL JOIN operation for displaying relational data
- File upload & image display on web interface

## 🧱 System Architecture

Database (MySQL SCOTT schema)
        ↓
     Redis Cache
        ↓
Spring Boot Application
        ↓
      HDFS (Images)
        ↓
     Web Interface

## 🗄️ Database

- Schema: SCOTT
- Tables used:
  - EMP
  - DEPT

### CRUD Operations Implemented

- Create Employee
- Read Employee
- Update Employee
- Delete Employee

All employee and department data are retrieved through Redis cache layer.

## ⚡ Redis Cache Integration

- Redis is used as caching mechanism between application and database.
- Frequently accessed employee and department data are cached.
- Improves read performance.
- Reduces direct database load.

## 🖼️ Hadoop HDFS Integration

- Employee images are stored in Hadoop HDFS.
- Images are fetched dynamically from HDFS.
- Users can upload new employee images.
- Uploaded files are saved to HDFS.
- Images are displayed on the web page.

## 🌐 Web Page

There is a single web page that displays employee information using JOIN operation.
Displayed Fields:
- Employee Name
- Manager Name (Self Join on EMP table)
- Salary
- Commission
- Department Name

The data is shown in a table format.

## 🔁 JOIN Operation

The following SQL logic is used:
- EMP table self-join to retrieve manager name
- Join between EMP and DEPT table

This ensures relational data consistency.

## 📂 File Upload Feature

- User uploads employee image
- File is stored in HDFS
- Image is retrieved and displayed on UI
- Upload and display functionality is verified

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Redis
- Hadoop HDFS

### Database
- MySQL (SCOTT schema)

### Frontend
- Thymeleaf / HTML


## 🚀 How to Run

### 1️⃣ Start Services
- Start MySQL DB
- Start Redis
- Start Hadoop HDFS

### 2️⃣ Run Application

mvn clean install
mvn spring-boot:run

Application runs on: http://localhost:8080

### 📊 Expected Output 

Employee table displayed on web page
Manager names resolved correctly
Department information shown
Employee images displayed from HDFS
CRUD operations working correctly
File upload functionality verified

### 🎯 Learning Outcomes

Redis caching architecture
HDFS file management
SQL JOIN & Self-Join
File upload handling in Spring Boot
Multi-layered enterprise application design

### 👤 Developer

Yüksel Mert Kırdar
Software Engineering Student








