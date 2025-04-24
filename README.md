# Event Management API – CS 3250 Team Project (Ongoing Improvements)

This is a Java-based Spring Boot application originally developed by me, **Jesse**, and **Anthony** as part of *CS 3250: Software Development Methods and Tools*. The app provides a RESTful API for managing events, venues, cities, and countries, built with PostgreSQL and Docker.

 **Note**: While the core of this project was built a few years ago, it is now actively being revisited and improved to reflect updated practices and evolving backend development skills.

---

## Overview

Built a full-stack backend system designed around real-world software development principles. The API allows for the creation, retrieval, updating, and deletion (CRUD) of event-related data, with clearly defined relationships between entities. The project follows a layered architecture and integrates testing, persistence, and deployment best practices.

---

## Work In Progress Improvements

- Codebase cleanup and modern refactoring
- Updates to Spring Boot dependencies and build tools
- Added new validation rules and improved request handling
- Enhanced unit testing coverage
- Docker configuration updates for smoother local dev

---

## Key Features

- **RESTful API** with full CRUD operations for:
  - Events
  - Venues
  - Cities
  - Countries
- **Entity Relationships** modeled using JPA/Hibernate
- **Layered Architecture** with controllers, services, repositories, and models
- **Dockerized Development Environment** with PostgreSQL and Spring Boot containers
- **Unit Testing** using JUnit and Mockito
- **Manual API Testing** with Postman collections
- **Maven** for dependency and build management

---

## Testing & Validation

- Unit tests written for controller and service layers
- Isolated testing environment using the H2 in-memory database
- Manual endpoint validation using Postman

---

## Development Environment

- Containerized setup using Docker Compose:
  - Spring Boot application
  - PostgreSQL database
- Shell scripts provided for easy start/stop workflows

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 18 | Application runtime |
| Spring Boot 2.7.3 | Web framework |
| PostgreSQL | Primary database |
| H2 | Testing database |
| JPA/Hibernate | ORM and persistence |
| Docker | Containerization |
| Maven | Build tool |
| JUnit + Mockito | Testing |
| Postman | API validation |

---

## Learning Outcomes

Through this project, we gained practical experience with:
- Designing and implementing a RESTful API from scratch
- Modeling real-world data relationships
- Applying clean coding principles in a team setting
- Writing and running meaningful tests
- Using containers for consistent development environments
