# Implementation 

Some parts of the requirements of this project are for elements that I have not taught directly in class. As a result, I am providing an example of how these elements can be implemented. You may use the code provided as a reference to implement your own project, or you may use the code provided as part of your project.

The two primary elements are the use of docker and docker compose, as well as the use of security within the application.

## Docker and Docker Compose

Docker provides a great way to setup and execute applications in a consistent manner. When using docker, I will be able to more run the application on my local machine in the same way that it executed on your machine. It ensures that there are no requirements to installing libraries or other dependencies on my machine.

There are two parts to the setup of docker in the project. The first is the Dockerfile, this defines the setup of the application. It builds an image using the generated jar file from the project. When executed using docker, the application will execute with the profile 'prod'. This means that the application will use the application-prod.yml file for configuration. If you make any changes to the project, you will need to rebuild the jar file and the docker image.

The second part is the compose file. This defines the services that make up this application. In this case there are two services, the application and the database. The application service uses the image that was built using the Dockerfile. The database uses a mysql image, and sets up the database with the required user and passwords. It should be noted that the database is persisted on your local machine so data should be remembered between restarts of the application.

### Working with Docker
Here are the commands that you will need to use to work with docker:

#### Build the Application Jar File

```
mvn clean package
```

This should create a jar file in the target directory.

#### Start the application

```
docker-compose up
```

This will start the application and the database. You can access the application at http://localhost:8080

#### Stop the application

```
docker-compose down
```

This will stop the application and the database.

#### Rebuild the application

```
mvn clean package

docker-compose up --build
```

This will rebuild the application and start it.

#### Remove the database

```
docker-compose down -v
```

This will stop the application and remove the database. This is useful if you want to start fresh.

### Working Without Docker

The use of docker is good for the final deployment of the application, but it is not required for development. When you are developing the application, you can use the embedded database and run the application using the following command:

```
mvn spring-boot:run
```

Alternatively, you can execute the same command through the Maven menu in IntelliJ. The project is setup to use the application-dev.yml file when running in development mode. This means it will use an embedded database and will not remember data between restarts. The class ApplicationRunner executes only when the project is started in development mode. This can be used to place some initial data into the database.

## Spring Security

Adding access control to a Spring Boot application is relatively easy. We just need to add the spring-boot-starter-security dependency to the project. However, it can be difficult to get the application to work the way we want it to. This project has the basic classes of the system set up and connected to the access control system. 

1. ```AccountWrapper``` - A class that wraps the Account object. This is used to provide the user information to the security system.
2. ```AccountDetailsService``` - This is used to load the user information from the database and then wrap it in an AccountWrapper object.
3. ```WebSecurityConfig``` - This is the configuration for the security system. It defines the access control for the application. In this case, we are allowing all users to access the urls ```'/'```, ```'/login'```, ```'/logout'```, and ```'/register'```. 
    - The URL ```'/administrator'``` is only accessible if the user has the role ```ADMININISTRATOR```. 
    - The URL ```'/customer'``` is only accessible if the user has the role ```CUSTOMER```. 
     - The URL ```'/organiser'``` is only accessible if the user has the role ```ORGANISER```. 
4. ```AuthSuccess``` - This class determines what should happen when users are logged in successfully. When a user logs in, they are redirected to a page based on their role. 
    - If the user has the role ```ADMININISTRATOR```, they are redirected to ```'/administrator'```. 
    - If the user has the role ```CUSTOMER```, they are redirected to ```'/customer'```. 
    - If the user has the role ```ORGANISER```, they are redirected to ```'/organiser'```.

### Working with Security
There should be no requirement for you to modify the ```AccountWrapper``` or ```AccountDetailsService``` classes. As you add more endpoints to your application, these will need to be added to the ```WebSecurityConfig``` class so that they are accessible by only the correct roles.

The majority of this configuration is done in the lambda expression that is a parameter of the ```authorizeHttpRequests``` method. Each new endpoint that you add to the application will need to be added as a method call on the requests object. 

A typical example of this is the following:
```java
.requestMatchers("/info").permitAll()
```

This line of code will allow all users to access the URL ```'/info'```. If you wanted to restrict access to a URL based on the role of the user, you could use the following:

```java
.requestMatchers("/customer").hasAuthority("CUSTOMER")
```

This line of code will ensure that the URL ```'/customer'``` is only accessible by users with the role ```CUSTOMER```. If this was for an end point that was accessible by multiple roles, you could use the following:

```java
.requestMatchers("/organiser").hasAnyAuthority("ORGANISER", "ADMINISTRATOR")
```

This line of code will ensure that the URL ```'/organiser'``` is only accessible by users with the role ```ORGANISER``` or ```ADMINISTRATOR```.

The methods ```formLogin``` and ```logout``` are used to define the login and logout pages for the application. The ```successHandler``` method is used to define the class that will be used to determine what happens when a user logs in successfully. You should not need to change any of these.

#### Forms
Spring security includes some features about data that is being posted as a part of a form. In order to make this work, we should include a csrf token in the form. This is done by including the following code in the form:

```html
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
```

Thymeleaf will replace the ```${_csrf.parameterName}``` and ```${_csrf.token}``` with the correct values. This is required for the form to work correctly.

### Other Points Of Note

I implemented the minimum parts of the system required to get the security system working. However, in this there are several examples that can be helppul not only to the implementation of the system, but in consideration of the design that you are just completing. 

1. The classes represent the user of the system. These show examples of how JPA can be set up to remember the data of the users in the database. The database tables and information are created automatically based on then annotations in the classes. 
2. I make frequent use of the dependency Lombok. This is a library that can be used to reduce the amount of boilerplate code that is required in Java. It is not required for the project, but it can be useful. For example, it defines annotations such as ```@NoArgsConstructor```, ```@AllArgsConstructor```, ```@Getter```, ```@Setter```, and ```@ToString```. These mean that you do not need to write the code for these methods. Possibly the most useful is the ```@Data``` annotation. This is the same as using all of the above annotations.
3. There are some examples of inserting basic information from the model into the templates of the web page. But this is not extensive. You will need to add more to the templates to display the information that you want to show.
4. There are examples of setting up and using the JPA repositories.
5. There are examples of using the service classes as well as dependency injection to set up the system.
6. You should note the creation of the ```AccountDTO``` object that is added to the model for the login page. This is used in the template to capture the data that is entered into the form. Additionally, the data should be persisted if the user makes a mistake (like a duplicate email address) and the page is reloaded. 