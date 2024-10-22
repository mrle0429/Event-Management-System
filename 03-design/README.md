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

# Design

This phase continues the development process by performing further realisations on the courses of events that we described in the analysis phase. This requires the creation of new sequence diagrams for each course of events. These diagrams will be more detailed in that they will contain all parameter and type infromation. Additionally, the diagrams will now include the UI and database components of the system.

Decisions made while constructing these diagrams are also documented in the class diagram.

## Use Case Realisations (Sequence Diagrams)

Each use case contains the separate sequence diagrams for each course of events. The diagrams are stored in the `images` folder and referenced in the markdown files.

1. [Use Case 1](01-name.md)
2. [Use Case 2](02-name.md)
3. [Use Case 3](03-name.md)
4. [Use Case 4](04-name.md)
5. [Use Case 5](05-name.md)

## Class Diagram

The class diagram represents the information gained about the system by completing the use case realisations. 

![class diagram](images/class-diagram.png)

## Data Persistence
Within the restaurant the following classes should be maintained between executions.
1. Customer - id, name, phone number
2. Table - id, number, places
3. WalkIn - id, table, date, time, covers, is overfull
4. Reservation - id, customer, table, date, time, covers, is overfull

### Relationships
1. Customer - Reservation (1 to many)
2. Reservation - Customer (1 to 1)
2. Table - Booking (1 to many)
3. Booking - Table (1 to 1)

## Milestone 3 Design

### Distribution of work on this milestone
#### Overall Distribution of Work
| Team Member | TM1 | TM2 | TM3 | TM4 | TM5 | TM6 | TM7 | TM8 |
|-------------|-----|-----|-----|-----|-----|-----|-----|-----|
| Percentage  | 12% | 12% | 12% | 12% | 12% | 12% | 12% | 12% |
#### Task Allocation
| Item               | Primary Author | Contributor | Contributor | Reviewer |
|--------------------|-----|-----|-----|-----|
| Description of Responsibilities   | TM3 |     |     | TM1 |
| Class Diagram      | TM4 | TM2 | TM1 | TM3 |
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

