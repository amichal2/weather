# Weather service

### Overview
* Structure

Spring Boot application with default settings for both servlet container (Tomcat 8, port 8080) and REST implementation (Spring Web MVC). Project structure was generated with Spring Initializr with Maven as project build tool.

* Build and run

build: mvn package

run: mvn spring-boot:run - alternatively, since it's executable jar file: java -jar target/weather-0.0.1-SNAPSHOT.jar

entry page: http://localhost:8080/weather

* Absence of interfaces

At current stage adding interfaces for Spring beans does not provide any added value. Spring framework is still able to create its proxy classes on the fly using CGLIB.

* Improvements

Potential technical improvements have been marked in the code as TODO tasks.

Potential business improvements include:

    * add functionality for managing cities i.e. allow user to check weather in any city, display list of all cities with the same name and let user to choose the right one
    * display sunrise and sunset times also in the city's local timezone, rather than only in UTC standard
