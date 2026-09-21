# System Architecture

## 1. Architecture Overview

The Home Healthcare and Nurse Visit Scheduling System follows a layered web application architecture.

The system consists of:

- Patient, Nurse and Admin users
- React.js frontend
- Spring Boot backend
- Spring Security with JWT authentication
- MySQL database
- Cloud deployment environment

## 2. Architecture Flow

```text
+-----------------------------+
|       Users                 |
|-----------------------------|
| Patient | Nurse | Admin     |
+-------------+---------------+
              |
              v
+-----------------------------+
|      React.js Frontend      |
|-----------------------------|
| Login                       |
| Service Selection           |
| Visit Request               |
| Appointment Management      |
| Status Tracking             |
| Feedback                    |
+-------------+---------------+
              |
              v
+-----------------------------+
|   Spring Boot REST API      |
|-----------------------------|
| Authentication & Authorization |
| User Management             |
| Nurse Management            |
| Service Management          |
| Visit Request Management    |
| Scheduling & Availability   |
| Appointment Management      |
| Status Tracking             |
| Feedback Management         |
+-------------+---------------+
              |
              v
+-----------------------------+
|       MySQL Database        |
|-----------------------------|
| Users                       |
| Patients                    |
| Nurses                      |
| Services                    |
| Nurse Availability          |
| Visit Requests              |
| Appointments                |
| Status History              |
| Feedback                    |
+-----------------------------+