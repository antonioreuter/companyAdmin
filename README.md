# Company Admin

An example of a Web Application to manage Companies and Employees.
Here, you may see an example of integration between a client side application written in Javascript with a backend application written in Java with Spring MVC, exposing a Web Api based on Rest.

#### Technologies
##### Backend
- Maven
- JDK 1.8
- Spring Boot:
    - Spring-Mvc
    - Spring-Security
    - Spring-Data
    - Spring Actuator
- h2 (embedded database) *remember, it's just a POC*
- Swagger
- Mockito
- Junit

##### Client
- Automation:
    - Grunt
    - Bower
    - Npm
- AngularJS
- CoffeeScript
- Bootstrap

### Installation
#### Requirements
- JDK 1.8
- Maven

Executing the command below, it'll install all the project dependencies and build the package.

```
    mvn clean package
```

### Running

```
    java -jar target/companyAdmin-1.0.0-SNAPSHOT.jar
```

You can find the application running in your [localhost](http://localhost:8080)

### The Application
#### Notes:
1. Here you will find basically two features, register and manage companies and their employees.

2. When the application is initialized, it'll be preloaded with two companies and a few employees on each of them.

## Web Api Documentation

The application uses Swagger to document the API, you may find it here [documentation](http://localhost:8080/swagger-ui.html). There you can see examples about how to use the Web Api with a built in client.

## Try Online
There is a sample running on **Heroku** at this [link](https://stark-reef-70759.herokuapp.com).
There you can find also our [Web Api Documentation](https://stark-reef-70759.herokuapp.com/swagger-ui.html)

When you try to access the Web Api Services directly or through the [Web Api Documentation](https://stark-reef-70759.herokuapp.com/swagger-ui.html) a prompt may pop-up for you. If so,  you need to enter the credentials below to authenticate yourself before start to use the web api services.
```
username: services

password: 123456
```

## Considerations

- This project it's just a POC, so the security strategy chosen to apply here was the simplest one: the Basic Authentication.

    -   You may find another approach about authentication in this [repository](https://github.com/antonioreuter/hateoas-oms-sec). There you can find an application implementing Spring-HATEOAS with Spring-Security.

- The project could be divided into two different applications, one with the javascript client and another just with the backend, running separately. However to keep things simple, they were put together into the same project to be deployed in just one package on **Heroku**.

- the client side pagination is not implemented in the first screen, inspite the fact that this functionality is supported by the Web API.

- The application runs with an embedded database in memory, just to avoid unnecessary configuration.

- The tests on client side weren't implemented.

- There are some tests on backend, but it's not covering the entire application. 
