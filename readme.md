# Veterinary Management System

This is a Veterinary Management System built with Spring Boot. It includes functionality to manage customers, doctors, animals, appointments, vaccinations, reports, and workdays.

## UML Class Diagram

![umlDiagram](https://github.com/user-attachments/assets/b31b9dbc-4fc6-43be-ae95-de235f2ac3da)

## API Endpoints

### Animal Endpoints
- **GET /api/v1/animals**
  - Retrieves all animals with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/animals/{id}**
  - Retrieves a specific animal by ID.

- **GET /api/v1/animals/searchByName**
  - Retrieves animals by name with pagination.
  - Parameters: `name`, `pageNumber`, `pageSize`.

- **GET /api/v1/animals/searchByCustomerName**
  - Retrieves animals by customer name with pagination.
  - Parameters: `customerName`, `pageNumber`, `pageSize`.

- **POST /api/v1/animals**
  - Creates a new animal.
  - Body: `AnimalRequest`.

- **PUT /api/v1/animals/{id}**
  - Updates an existing animal by ID.
  - Body: `AnimalRequest`.

- **DELETE /api/v1/animals/{id}**
  - Deletes an animal by ID.

### Appointment Endpoints
- **GET /api/v1/appointments**
  - Retrieves all appointments with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/appointments/{id}**
  - Retrieves a specific appointment by ID.

- **GET /api/v1/appointments/searchByDoctorAndDateRange**
  - Retrieves appointments by doctor ID and date range with pagination.
  - Parameters: `doctorId`, `startDate`, `endDate`, `pageNumber`, `pageSize`.

- **GET /api/v1/appointments/searchByAnimalAndDateRange**
  - Retrieves appointments by animal ID and date range with pagination.
  - Parameters: `animalId`, `startDate`, `endDate`, `pageNumber`, `pageSize`.

- **POST /api/v1/appointments**
  - Creates a new appointment.
  - Body: `AppointmentRequest`.

- **PUT /api/v1/appointments/{id}**
  - Updates an existing appointment by ID.
  - Body: `AppointmentRequest`.

- **DELETE /api/v1/appointments/{id}**
  - Deletes an appointment by ID.

### Customer Endpoints
- **GET /api/v1/customers**
  - Retrieves all customers with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/customers/{id}**
  - Retrieves a specific customer by ID.

- **GET /api/v1/customers/searchByName**
  - Retrieves customers by name with pagination.
  - Parameters: `name`, `pageNumber`, `pageSize`.

- **POST /api/v1/customers**
  - Creates a new customer.
  - Body: `CustomerRequest`.

- **PUT /api/v1/customers/{id}**
  - Updates an existing customer by ID.
  - Body: `CustomerRequest`.

- **DELETE /api/v1/customers/{id}**
  - Deletes a customer by ID.

### Doctor Endpoints
- **GET /api/v1/doctors**
  - Retrieves all doctors with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/doctors/{id}**
  - Retrieves a specific doctor by ID.

- **GET /api/v1/doctors/searchByName**
  - Retrieves doctors by name with pagination.
  - Parameters: `name`, `pageNumber`, `pageSize`.

- **POST /api/v1/doctors**
  - Creates a new doctor.
  - Body: `DoctorRequest`.

- **PUT /api/v1/doctors/{id}**
  - Updates an existing doctor by ID.
  - Body: `DoctorRequest`.

- **DELETE /api/v1/doctors/{id}**
  - Deletes a doctor by ID.

### Report Endpoints
- **GET /api/v1/reports**
  - Retrieves all reports with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/reports/{id}**
  - Retrieves a specific report by ID.

- **POST /api/v1/reports**
  - Creates a new report.
  - Body: `ReportRequest`.

- **PUT /api/v1/reports/{id}**
  - Updates an existing report by ID.
  - Body: `ReportRequest`.

- **DELETE /api/v1/reports/{id}**
  - Deletes a report by ID.

### Vaccination Endpoints
- **GET /api/v1/vaccinations**
  - Retrieves all vaccinations with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/vaccinations/{id}**
  - Retrieves a specific vaccination by ID.

- **GET /api/v1/vaccinations/searchByVaccinationRange**
  - Retrieves vaccinations by protection finish date range with pagination.
  - Parameters: `startDate`, `endDate`, `pageNumber`, `pageSize`.

- **GET /api/v1/vaccinations/searchByAnimal**
  - Retrieves vaccinations by animal name with pagination.
  - Parameters: `name`, `pageNumber`, `pageSize`.

- **GET /api/v1/vaccinations/searchByName**
  - Retrieves vaccinations by name with pagination.
  - Parameters: `name`, `pageNumber`, `pageSize`.

- **GET /api/v1/vaccinations/searchByDate**
  - Retrieves vaccinations by date range with pagination.
  - Parameters: `startDate`, `endDate`, `pageNumber`, `pageSize`.

- **POST /api/v1/vaccinations**
  - Creates a new vaccination.
  - Body: `VaccinationRequest`.

- **PUT /api/v1/vaccinations/{id}**
  - Updates an existing vaccination by ID.
  - Body: `VaccinationRequest`.

- **DELETE /api/v1/vaccinations/{id}**
  - Deletes a vaccination by ID.

### WorkDay Endpoints
- **GET /api/v1/workdays**
  - Retrieves all workdays with pagination.
  - Parameters: `pageNumber`, `pageSize`.

- **GET /api/v1/workdays/{id}**
  - Retrieves a specific workday by ID.

- **GET /api/v1/workdays/byDoctor/{doctorId}**
  - Retrieves workdays by doctor ID.

- **POST /api/v1/workdays**
  - Creates a new workday.
  - Body: `WorkDayRequest`.

- **PUT /api/v1/workdays/{id}**
  - Updates an existing workday by ID.
  - Body: `WorkDayRequest`.

- **DELETE /api/v1/workdays/{id}**
  - Deletes a workday by ID.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/umutkozan/Veterinary.git
mvn install
Install Maven dependencies

Run the application
mvn spring-boot:run

License
Distributed under the Apache License 2.0. See LICENSE for more information.

Contact
Your Name - umutkkozan@gmail.com

Project Link: https://github.com/umutkozan/Veterinary
