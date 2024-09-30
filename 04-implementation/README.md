
# Team Project: *Group XX*

## Team Members
| Number | Name | Email(s) | CSGitLab Username |
|--------|------|----------|-------------------|
| TM1    | Team Member 1 | tm1@ucdconnect.ie | @22201001 |
| TM2    | Team Member 2 | tm2@ucdconnect.ie | @22201002 |     
| TM3    | Team Member 3 | tm3@ucdconnect.ie, personal@qq.com | @22201003 |     
| TM4    | Team Member 4 | tm4@ucdconnect.ie | @22201004 |     
| TM5    | Team Member 5 | tm5@ucdconnect.ie | @22201005 |     
| TM6    | Team Member 6 | tm6@ucdconnect.ie | @22201006 |     
| TM7    | Team Member 7 | tm7@ucdconnect.ie | @22201007 |     
| TM8    | Team Member 8 | tm8@ucdconnect.ie | @22201008 |

# Implementation

This part includes the implementation of the restaurant example system. In order to execute the application you will need to have the following installed:
1. Java 21
2. Maven
3. Docker
4. Docker Compose

## Running the Application	
Assuming that you have correctly installed and set up the above, you can run the application by following these steps:
1. Use maven to create a jar file of the application by running `mvn package` in the root directory of the project.
```bash
mvn package
```
2. Run the docker compose command to build and run the application.
```bash
docker-compose up --build -d
```

This will build the application and run it on port 8080. You can then access the application by navigating to `localhost:8080` in your browser. This also starts the database, as such any changes you make shoudl persist between runs.

## Stopping the Application
When you are finished and want to stop the application, you can use the following command:
```bash
docker-compose down
```

If you want to stop the application and remove the database, you can use the following command:
```bash
docker-compose down -v
```

## Milestone 4 Implementation

### Distribution of work on this milestone
#### Overall Distribution of Work
| Team Member | TM1 | TM2 | TM3 | TM4 | TM5 | TM6 | TM7 | TM8 |
|-------------|-----|-----|-----|-----|-----|-----|-----|-----|
| Percentage  | 12% | 12% | 12% | 12% | 12% | 12% | 12% | 12% |
#### Task Allocation
| Item               | Primary Author | Contributor | Contributor | Reviewer |
|--------------------|-----|-----|-----|-----|
| Docker setup       | TM3 |     |     | TM1 |
| UI development     | TM4 | TM2 | TM1 | TM3 |
| Use Case 1: "Name" | TM4 |     |     | TM3 |
| Use Case 2: "Name" | TM1 |     |     | TM4 |
| Use Case 3: "Name" | TM2 |     |     | TM1 |
| Use Case 4: "Name" | TM3 |     |     | TM2 |
| Use Case N: "Name" | TM4 |     |     | TM3 |


#### Reflection Statements
| Team Member | Contribution Reflection Statement |
|-------------|-------------------|
|TM1| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM2| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM3| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM4| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM5| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM6| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM7| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |
|TM8| <*Required*: The percentage data is unlikely to tell the whole story about your contribution. Write a brief statement explaining and reflecting on your contribution to this phase of the project.> |

