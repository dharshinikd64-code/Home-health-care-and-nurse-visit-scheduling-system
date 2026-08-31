# Database Design

## 1. Overview

The Home Healthcare and Nurse Visit Scheduling System uses a relational MySQL database to manage patients, nurses, healthcare services, nurse availability, visit requests, appointments, status tracking, and feedback.

The database is designed to maintain data integrity through primary keys, foreign keys, unique constraints, and appropriate relationships between entities.

## 2. Tables

### 2.1 users

Stores common authentication and user information.

| Column        | Type         | Constraints                 |
| ------------- | ------------ | --------------------------- |
| id            | BIGINT       | Primary Key, Auto Increment |
| name          | VARCHAR(100) | NOT NULL                    |
| email         | VARCHAR(150) | UNIQUE, NOT NULL            |
| password_hash | VARCHAR(255) | NOT NULL                    |
| role          | VARCHAR(20)  | NOT NULL                    |
| phone         | VARCHAR(20)  |                             |
| created_at    | TIMESTAMP    | NOT NULL                    |
| updated_at    | TIMESTAMP    | NOT NULL                    |

Roles:

* PATIENT
* NURSE
* ADMIN

---

### 2.2 patients

Stores patient-specific information.

| Column            | Type         | Constraints                    |
| ----------------- | ------------ | ------------------------------ |
| id                | BIGINT       | Primary Key, Auto Increment    |
| user_id           | BIGINT       | Foreign Key → users.id, UNIQUE |
| date_of_birth     | DATE         |                                |
| gender            | VARCHAR(20)  |                                |
| address           | TEXT         |                                |
| emergency_contact | VARCHAR(100) |                                |

Relationship:

**users 1 : 1 patients**

---

### 2.3 nurses

Stores nurse professional information.

| Column           | Type         | Constraints                    |
| ---------------- | ------------ | ------------------------------ |
| id               | BIGINT       | Primary Key, Auto Increment    |
| user_id          | BIGINT       | Foreign Key → users.id, UNIQUE |
| specialization   | VARCHAR(100) | NOT NULL                       |
| qualification    | VARCHAR(150) |                                |
| experience_years | INT          |                                |
| service_area     | VARCHAR(150) |                                |
| status           | VARCHAR(20)  | NOT NULL                       |

Possible status values:

* ACTIVE
* INACTIVE
* ON_LEAVE

Relationship:

**users 1 : 1 nurses**

---

### 2.4 services

Stores available home healthcare services.

| Column           | Type          | Constraints                 |
| ---------------- | ------------- | --------------------------- |
| id               | BIGINT        | Primary Key, Auto Increment |
| name             | VARCHAR(100)  | UNIQUE, NOT NULL            |
| description      | TEXT          |                             |
| duration_minutes | INT           | NOT NULL                    |
| base_fee         | DECIMAL(10,2) | NOT NULL                    |
| active           | BOOLEAN       | NOT NULL                    |

Example services:

* General Nursing
* Elderly Care
* Post-Surgery Care
* Wound Care
* Medication Assistance

---

### 2.5 nurse_availability

Stores the available time slots of nurses.

| Column         | Type        | Constraints                 |
| -------------- | ----------- | --------------------------- |
| id             | BIGINT      | Primary Key, Auto Increment |
| nurse_id       | BIGINT      | Foreign Key → nurses.id     |
| available_date | DATE        | NOT NULL                    |
| start_time     | TIME        | NOT NULL                    |
| end_time       | TIME        | NOT NULL                    |
| status         | VARCHAR(20) | NOT NULL                    |

Relationship:

**nurses 1 : M nurse_availability**

---

### 2.6 visit_requests

Stores home healthcare visit requests submitted by patients.

| Column         | Type        | Constraints                 |
| -------------- | ----------- | --------------------------- |
| id             | BIGINT      | Primary Key, Auto Increment |
| patient_id     | BIGINT      | Foreign Key → patients.id   |
| service_id     | BIGINT      | Foreign Key → services.id   |
| preferred_date | DATE        | NOT NULL                    |
| preferred_time | TIME        | NOT NULL                    |
| location       | TEXT        | NOT NULL                    |
| priority       | VARCHAR(20) | NOT NULL                    |
| description    | TEXT        |                             |
| status         | VARCHAR(20) | NOT NULL                    |
| created_at     | TIMESTAMP   | NOT NULL                    |

Priority values:

* NORMAL
* URGENT

Status values:

* PENDING
* ASSIGNED
* CANCELLED
* COMPLETED

Relationships:

* **patients 1 : M visit_requests**
* **services 1 : M visit_requests**

---

### 2.7 appointments

Stores scheduled home healthcare visits.

| Column           | Type        | Constraints                             |
| ---------------- | ----------- | --------------------------------------- |
| id               | BIGINT      | Primary Key, Auto Increment             |
| visit_request_id | BIGINT      | Foreign Key → visit_requests.id, UNIQUE |
| nurse_id         | BIGINT      | Foreign Key → nurses.id                 |
| scheduled_date   | DATE        | NOT NULL                                |
| start_time       | TIME        | NOT NULL                                |
| end_time         | TIME        | NOT NULL                                |
| status           | VARCHAR(20) | NOT NULL                                |
| notes            | TEXT        |                                         |
| created_at       | TIMESTAMP   | NOT NULL                                |

Status values:

* SCHEDULED
* ACCEPTED
* IN_PROGRESS
* COMPLETED
* CANCELLED

Relationships:

* **visit_requests 1 : 1 appointments**
* **nurses 1 : M appointments**

---

### 2.8 status_history

Stores appointment status changes for tracking and auditing.

| Column         | Type        | Constraints                   |
| -------------- | ----------- | ----------------------------- |
| id             | BIGINT      | Primary Key, Auto Increment   |
| appointment_id | BIGINT      | Foreign Key → appointments.id |
| old_status     | VARCHAR(30) |                               |
| new_status     | VARCHAR(30) | NOT NULL                      |
| changed_by     | BIGINT      | Foreign Key → users.id        |
| changed_at     | TIMESTAMP   | NOT NULL                      |
| remarks        | TEXT        |                               |

Relationships:

* **appointments 1 : M status_history**
* **users 1 : M status_history**

---

### 2.9 feedback

Stores patient feedback for completed appointments.

| Column         | Type      | Constraints                           |
| -------------- | --------- | ------------------------------------- |
| id             | BIGINT    | Primary Key, Auto Increment           |
| appointment_id | BIGINT    | Foreign Key → appointments.id, UNIQUE |
| patient_id     | BIGINT    | Foreign Key → patients.id             |
| rating         | INT       | NOT NULL                              |
| comments       | TEXT      |                                       |
| created_at     | TIMESTAMP | NOT NULL                              |

Rating range:

**1–5**

Relationships:

* **appointments 1 : 1 feedback**
* **patients 1 : M feedback**

## 3. Relationship Summary

| Parent Table   | Child Table        | Relationship |
| -------------- | ------------------ | ------------ |
| users          | patients           | 1 : 1        |
| users          | nurses             | 1 : 1        |
| nurses         | nurse_availability | 1 : M        |
| patients       | visit_requests     | 1 : M        |
| services       | visit_requests     | 1 : M        |
| visit_requests | appointments       | 1 : 1        |
| nurses         | appointments       | 1 : M        |
| appointments   | status_history     | 1 : M        |
| users          | status_history     | 1 : M        |
| appointments   | feedback           | 1 : 1        |
| patients       | feedback           | 1 : M        |

## 4. Scheduling Business Logic

Before creating an appointment, the system should verify:

1. The requested healthcare service exists and is active.
2. The selected nurse is active.
3. The nurse's specialization is suitable for the requested service.
4. The nurse has an availability slot for the requested date and time.
5. The nurse does not already have a conflicting appointment.
6. The appointment time falls within the nurse's available slot.
7. The visit request has not already been cancelled or assigned.

If the conditions are satisfied, the appointment can be created and the visit request status can be updated.

## 5. Data Integrity Rules

* Every table has a primary key.
* Foreign keys maintain relationships between related records.
* User email addresses must be unique.
* Service names must be unique.
* A nurse cannot have overlapping appointments.
* A feedback record can be submitted only once for an appointment.
* Appointment status changes are recorded in `status_history`.
* Passwords are stored as secure hashes rather than plain text.
