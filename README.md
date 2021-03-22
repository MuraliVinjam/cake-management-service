## Cake Management Service

This is spring boot service that provides RESTful API for  

Contains a simple RESTful API to provide the following operations to the app:
 - GET /cakes - List of all cakes returned
 - POST /cakes  - Create a new cake

Also contains a simple web page at root http://localhost:8080 which will present list of all cakes in the application

## Running Spring Boot Service

### Prerequisites

- Java 8
- Maven

### Loading into IntelliJ
From the main IntelliJ startup menu - Import Project > Import project from external model > Maven

or from an opened IntelliJ - Top Menu > File > New > Project from Existing Sources (select directory) > Import project from external model > Maven

### Logging
The Logging framework used in this code is SLF4J. The debug logging is output at INFO level.

This can be changed in the application.properties if required.

### Usage
- This Spring Boot application is configured to listen on port 8080
- The tomcat logs directory is /tmp/tomcat/cake-management-servive

### Authentication

This application provides Authentication via basic auth using the Spring framework:
 - Username/password : demo/demo

### Maven commands (from a command prompt or IntelliJ terminal window)

If running from the command prompt or terminal, go to the project's root folder, then type:

./mvn clean install  						(to build application with unit and integration tests)

./mvn clean install -DskipTests=true 		(to build the application without tests i.e. fast build)
    
./mvn spring-boot:run 						(to start the application)

