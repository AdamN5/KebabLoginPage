# KebabLoginPage

A microservice-based login and user management system built as part of a Kebab Review Platform project at ATU Galway.

## Overview

This service handles all user authentication and account management for the Kebab Review Platform. It runs as an independent microservice with its own H2 database, separate from the review service.

## Features

- User registration, login, and logout
- Account update and deletion
- H2 in-memory database for user data storage
- REST API endpoints consumed by the frontend

## Tech Stack

- **Backend:** Java, Spring Boot
- **Frontend:** JavaScript, HTML, CSS
- **Database:** H2
- **Architecture:** Microservices
  

### Prerequisites

- Java 17+
- Maven


## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Create a new account |
| POST | `/login` | Log in to an existing account |
| PUT | `/update` | Update account details |
| DELETE | `/delete` | Delete an account |


## Authors

- Adam Nafea
- Oliwier Kardynal

## Academic Context

Year 2 Team Project - Software and Electronic Engineering, ATU Galway
