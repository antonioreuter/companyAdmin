# Company Admin

An example of a Web Application for manage Companies and Employees.
Here, I want to demonstrate an example of integration between a client side written in Javascript with a backend written in Java with Spring MVC, exposing a Web Api based on Rest.

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

Executing the command below, it'll install all the project dependencies.

```
    mvn clean install
```

After install all the components, you will notice some new folders under the root folder. All of them already are into the .gitignore file.
They will be necessary to build our JavaScript Client.

- bower_components
- node
- node_modules


### Running

```
    java -jar ~/.m2/repository/com/company/admin/companyAdmin/1.0.0-SNAPSHOT/companyAdmin-1.0.0-SNAPSHOT.jar
```

You can find the application running on (http://localhost:8080)

### The Application
#### Notes:
1- Here you will find basically two features, register and manage companies and their employees.

2- When the application is initialized I preload it with two companies and a few employees on each of them.

## Web Api Documentation

As I mentioned before, in the **Requirements** section, we are using Swagger. A Java library that helps us to build an elegant documentation about our WEB Api.

You can check it out more ont http://localhost:8080/swagger-ui.html
There you can see a lot of examples about how to use the Web Api with a built in client.

## Online
There is a sample running on **Heroku** at this link: https://stark-reef-70759.herokuapp.com .
There you can find also our Web Api Documentation on https://stark-reef-70759.herokuapp.com/swagger-ui.html

When you try to access the Web Api Services a prompt may pop-up for you. If so,  you need to enter with the credentials below to authenticate yourself before start to use the web api services.

username: **services**

password: **123456**


## Considerations

- As I mentioned before, this project it's just a POC, so we don't worry about to apply a mechanism more sophisticated for authentication, so here I've chosen the simplest one: the Basic Authentication.

    -   If you want to see another approaches about authentication, check it out this repository: https://github.com/antonioreuter/hateoas-oms-sec. 
There you can find an implementation of Spring-HATEOAS with Spring-Security.

- I could have two different projects here, with the client side and the backend running separately. However,
as we wanted to run this app on **Heroku**, we decided to keep them into a single package, just to make our life easier.

- In the first screen I did not implement the client side pagination, however this functionality is supported by the Web API.

- I decide to use an embedded database in memory.

- I did not implement tests on client side, even though you can find a few of them in the backend.
