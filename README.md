# RetailStore

### Description
This is Spring Boot Java 11 application, which exposes an API such that given a
bill, it finds the net payable amount.

### Instructions
Clone repo:

```
git clone https://github.com/tumiemosweu/RetailStore.git
```

```
cd RetailStore
```


Run app with maven wrapper:

```
./mvnw spring-boot:run
```

Running tests:
```
mvn test
```

Test Coverage
Jacoco is used for test coverage. The report file is located in the target folder under target/site/jacoco/index.html .One can use the jacoco:report goal in order to generate readable code coverage reports in several formats – e.g. HTML, CSV, and XML.

API Documentation:
Swagger UI was used to document the API. The documentation can be accessed as follows
```

http://localhost:8080/swagger-ui-custom.html
```
Example request and response:
![Request response example] (Swagger_UI.png?raw=true "API Documentation")


UML Class Diagram
![Alt text](retail_store.png?raw=true "UML")

The project leverage today's best coding practices such as dependency injection and inheritance, to produce top quality code.
