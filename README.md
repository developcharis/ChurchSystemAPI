# **Church Management Application API**

## Overview
The Church Management API is designed to streamline the management of church activities, volunteers, and members. This RESTful API provides a suite of functionalities to create, read, update, and delete (CRUD) volunteer information, alongside advanced search capabilities to filter volunteers based on specific criteria.


## Features
- Full CRUD operations for managing volunteers.
- Implementation of an algorithm - Advanced search functionality to filter volunteers by skills, roles, and active status.
- Data persistence in a JSON file format.
- Comprehensive exception handling for robust error management.
- Utilizes Spring Boot for a lightweight, standalone application setup.


---

## Prerequisites
1. [JDK 17](https://learn.microsoft.com/en-gb/java/openjdk/download#openjdk-17) (or later)
2. Maven 3.6.3 or later (if building from source)
3. An IDE of your choice (e.g., IntelliJIDEA, Eclipse) VS Code was used for this project. [Visual Studio Code](https://code.visualstudio.com/Download)
   1. [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   2. [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)



### Setup and Installation

#### 1. Clone the Repository:

```sh
git clone [repository URL]
```

#### 2. Navigate to the directory:

```sh
cd [project directory]
```

#### 3. Build the project using Maven (optional if running from an IDE):

```sh
mvn clean install
```

#### 4. Run the application
To start the API in VS Code, from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```

Open your browser and navigate to `http://localhost:8080`.


---

## API Endpoints

Below are the endpoints provided by the Church Management API, including their HTTP methods and a brief description of their functionality:

### Volunteer Management

#### Create a New Volunteer
- **Method**: POST
- **Endpoint**: /api/volunteers
- **Description**: Creates a new volunteer entry in the system. The request body should contain volunteer details such as name, contact information, skills, and role.

#### Retrieve All Volunteers
- **Method**: GET
- **Endpoint**: /api/volunteers
- **Description**: Returns a list of all volunteers currently registered in the system, including their details.

#### Retrieve a Volunteer by ID
- **Method**: GET
- Endpoint: /api/volunteers/{id}
- Description: Fetches a single volunteer's details by their unique identifier (ID). Replace {id} with the actual volunteer ID.

#### Update a Volunteer's Information
- **Method**: PUT
- **Endpoint**: /api/volunteers/{id}
- **Description**: Updates the information of an existing volunteer. The request body should contain the updated details. The volunteer to be updated is identified by the {id} path variable.

#### Delete a Volunteer
- **Method**: DELETE
- **Endpoint**: /api/volunteers/{id}
- **Description**: Removes a volunteer from the system based on their unique ID. The volunteer to be deleted is specified by the {id} path variable.

### Advanced Volunteer Search
**Search for Volunteers**
- **Method**: GET
- **Endpoint**: /api/volunteers/search
- **Description**: Allows for advanced searching of volunteers based on criteria such as skills, active status, and role. Query parameters can be used to specify search criteria.

### Greetings (Sample/Test Endpoint)
**Greet User**
- **Method**: GET
- **Endpoint**: /greeting
- **Description**: A sample endpoint to demonstrate the API's functionality. It returns a greeting message and can accept a name query parameter to personalize the greeting.


---

## Using the API with Postman 
To facilitate testing and interaction with the API, a Postman collection has been prepared. Follow these steps to import and use the collection:

- Download and install [Postman](https://www.postman.com/).
- Access the shared ([Postman API Documentation](https://www.postman.com/technical-candidate-69039358/workspace/cbf-academy-assessment/collection/33023939-43bb7f5d-58cb-452f-95b8-e6ab5e4ef09b?action=share&creator=33023939)).
- Import the collection into your Postman application.
- Select an environment (if applicable) and begin making requests to the API endpoints.


## Error Handling
The API provides meaningful error messages in standard HTTP response formats. For example, attempting to access a non-existent volunteer returns a 404 Not Found status with a descriptive message.