# Problem Statement

## 1. Title

**Home Healthcare and Nurse Visit Scheduling System**

## 2. Domain

**HealthTech / Healthcare Management**

## 3. Who is the user? (2-3 user types, with roles)

### 1. Patient

A patient can register and log in to the system, view available home healthcare services, request a nurse visit, select a preferred date and time, track appointment status, and provide feedback after a completed visit.

### 2. Nurse

A nurse can log in, manage their availability, view assigned home visit requests, accept or reject visit requests, update visit status, and record basic visit notes.

### 3. Admin

An administrator can manage patients, nurses, healthcare services, visit requests, appointments, and monitor the overall scheduling process.

## 4. What problem are we solving? (3-5 sentences, real-life example)

Patients who require healthcare services at home may face difficulties in finding a suitable nurse and scheduling a visit at a convenient time. Nurses may also face difficulties in managing their availability and handling multiple home visit requests efficiently. Manual scheduling can result in appointment conflicts, delayed responses, and inefficient allocation of nurses.

For example, a patient who needs a home nursing service may request a visit for a specific date and time but may not know which suitable nurse is available. The proposed system provides a centralized platform to manage patient requests, nurse availability, and home visit appointments.

## 5. Proposed Solution (what the application will do, feature-wise)

### Patient Module

* Patient registration and login
* View available healthcare services
* Create a home healthcare visit request
* Select preferred visit date and time
* Provide visit location
* View appointment and visit status
* Request cancellation or rescheduling
* Provide feedback after a completed visit

### Nurse Module

* Nurse login
* Manage professional details and specialization
* Manage availability
* View assigned visit requests
* Accept or reject visit requests
* Update visit status
* Add basic visit notes

### Admin Module

* Admin login
* Manage patients
* Manage nurses
* Manage healthcare services
* View and manage visit requests
* Manage appointments
* Monitor appointment status

### Scheduling Business Logic

* Check nurse availability before scheduling
* Prevent conflicting nurse appointments
* Match the requested healthcare service with the nurse's specialization
* Consider the patient's preferred visit time
* Support priority handling for urgent visit requests

## 6. Core Entities / Database Tables

The system will contain the following core database entities:

1. **Users** — stores common user and authentication information.
2. **Patients** — stores patient-specific information.
3. **Nurses** — stores nurse-specific information, specialization, and professional details.
4. **Services** — stores available home healthcare services.
5. **NurseAvailability** — stores nurse availability slots.
6. **VisitRequests** — stores patient requests for home healthcare visits.
7. **Appointments** — stores scheduled nurse visits.
8. **StatusHistory** — stores changes in request and appointment status.
9. **Feedback** — stores feedback submitted by patients after completed visits.

## 7. User Roles & Permissions

| Role        | Permissions                                                                                                                  |
| ----------- | ---------------------------------------------------------------------------------------------------------------------------- |
| **Patient** | Register/login, view services, create visit requests, view appointments, request cancellation/rescheduling, provide feedback |
| **Nurse**   | Login, manage availability, view assigned visits, accept/reject requests, update visit status, add visit notes               |
| **Admin**   | Manage patients, nurses, services, visit requests, appointments, and monitor system activity                                 |

## 8. Success Criteria

The system will be considered successful when:

* A patient can register and securely log in.
* A patient can create a home healthcare visit request.
* The system checks nurse availability before scheduling.
* The system prevents conflicting nurse appointments.
* A suitable nurse can be identified based on required service, specialization, availability, and preferred visit time.
* A nurse can view and manage assigned visits.
* Patients can track appointment status.
* Completed visits can receive patient feedback.
* Role-based access prevents unauthorized users from accessing restricted functions.
* The application can be deployed and accessed through a public URL.

## 9. Out of Scope

The following features are outside the initial scope:

* Online medical diagnosis
* Prescription generation
* Video consultation
* Hospital or clinic management
* Medicine delivery
* Real-time ambulance tracking
* Insurance claim processing
* Real-time GPS tracking of nurses
* Advanced AI-based medical diagnosis

These features may be considered as future enhancements if required.

## 10. Chosen Track

**Java Track**

* **Frontend:** React.js
* **Backend:** Spring Boot 3.x
* **Programming Language:** Java 17
* **Authentication:** Spring Security + JWT
* **ORM / Data Layer:** Spring Data JPA + Hibernate
* **Database:** MySQL 8
* **Build Tool:** Maven
* **Testing:** JUnit 5
* **API Documentation:** Springdoc OpenAPI / Swagger
* **CI/CD:** GitHub Actions
* **Backend Hosting:** Render
* **Frontend Hosting:** Vercel
