# Movies Backend Project

## Overview
The Movies Backend project is a Java-based Spring Boot application designed to manage a movie database. It provides a RESTful API for handling movie-related operations, such as:

- Adding, updating, and deleting movies.
- Viewing movie details.
- User authentication and authorization.
- Allowing users to rate movies, with average ratings calculated automatically.

This project is ideal for scenarios requiring a movie management system with role-based access control.

---

## Technologies Used

- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **ORM**: Hibernate
- **Security**: Spring Security (JWT-based Authentication)
- **Build Tool**: Maven
- **API Documentation**: Swagger (if configured)

---

## Features

1. **Authentication & Authorization**
   - Role-based access control (e.g., Admin and User roles).
   - Secure endpoints with token-based authentication (JWT).

2. **CRUD Operations for Movies**
   - Endpoints for creating, reading, updating, and deleting movies.

3. **Movie Rating System**
   - Users can rate movies.
   - The system calculates and stores average ratings for each movie.

4. **RESTful API Design**
   - Follows REST principles for resource handling.

---

## Installation Instructions

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher.
- **Maven**: For building and running the application.
- **MySQL**: For the database.

### Steps to Install and Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/AhmedAshrafOS/Movies-backend.git
   cd Movies-backend
   ```

2. **Set Up the Database**

   - Ensure MySQL is installed and running.
   - Create a database (e.g., `movies_db`).
   - Update the `application.properties` file in `src/main/resources/` with your database credentials:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/movies_db
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the Project**

   - Use Maven to clean and build the project:

     ```bash
     mvn clean install
     ```

4. **Run the Application**

   - Start the application using:

     ```bash
     mvn spring-boot:run
     ```

   - The server will start on `http://localhost:8080`.

5. **Access API Documentation** (Optional)

   - If Swagger is enabled, you can view the API documentation at:

     ```
     http://localhost:8080/swagger-ui.html
     ```

---

## API Endpoints

### Authentication
- **POST /api/auth/login**: Login and receive a JWT token.

### Movies
- **GET /api/admin**: Retrieve all movies.
- **POST /api/admin**: Add a new movie (Admin only).
- **DELETE /api/admin/{id}**: Delete a movie (Admin only).
- **POST /api/admin/login: Login to dashboard.

### Ratings
- **POST /api/movies/{id}/rate**: Submit a rating for a movie.

---

## Project Structure

- `src/main/java/com/fawry/movies/`
  - **Controllers**: Handle HTTP requests and responses.
  - **Services**: Business logic for the application.
  - **Repositories**: Database interactions using Spring Data JPA.
  - **Models**: Entity classes representing database tables.

- `src/main/resources/`
  - **application.properties**: Configuration file for database and application settings.

---

## Notes for Reviewers

- **Purpose**: This project demonstrates a robust backend solution for managing movie data with secure, role-based access control.
- **Focus Areas**:
  - Authentication and authorization (JWT).
  - RESTful API design.
  - Hibernate-based database interactions.

Feel free to explore and test the API endpoints. Contributions and feedback are welcome!

