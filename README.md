## Cake Management Service

This is spring boot service that provides RESTful API for  

Build a simple RESTful API to provide the following operations to the app:
 - GET /cakes - List of all cakes returned
 - GET /cakes/{id} - Specified cake is returned
 - POST /cakes  - Create a new cake
 - PUT /cakes/{id} - Update a cake
 - DELETE /cakes/{id} - Deletes a  cake

## Running Spring Boot Service

### Prerequisites

- Java 8
- Maven

### Loading into IntelliJ
From the main IntelliJ startup menu - Import Project > Import project from external model > Maven

or from an opened IntelliJ - Top Menu > File > New > Project from Existing Sources (select directory) > Import project from external model > Maven

### API Docs
The rest API docs can be viewed here:

http://localhost:8080/swagger-ui/index.html

### Logging
The Logging framework used in this code is SLF4J. The debug logging is output at INFO level.

This can be changed in the application.properties if required.

### Usage
- This Spring Boot application is configured to listen on port 8080
- The tomcat logs directory is /tmp/tomcat/cake-manager

### Authentication

This application provides Authentication via basic auth using the Spring framework:

### Maven commands (from a command prompt or IntelliJ terminal window)

If running from the command prompt or terminal, go to the project's root folder, then type:

./mvnw clean verify  						(to run unit and integration tests)

./mvnw clean package 						(to build the application)
    
./mvnw spring-boot:run 						(to start the application)

