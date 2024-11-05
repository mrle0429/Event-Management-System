# Team Project: *Group XX*

## Team Members
| Number | Name         | Email(s)                   | CSGitLab Username |
|--------|--------------|----------------------------|-------------------|
| TM1    | Le Liu       | le.liu1@ucdconnect.ie      | @Mrle             |
| TM2    | Ziheng Wang  | ziheng.wang1@ucdconnect.ie | @zihengWang       |     
| TM3    | Yunhan Gao   | yunhan.gao@ucdconnect.ie   | @YunhanGao        |     
| TM4    | Bohan Zhang  | bohan.zhang@ucdconnect.ie  | @BohanZhang       |     
| TM5    | Sicheng Yi   | sicheng.yi@ucdconnect.ie   | @Prestine         |     
| TM6    | Xinya Shi    | xinya.shi@ucdconnect.ie    | @Shirley          |     
| TM7    | Yuxuan Zhang | yuxuan.zhang@ucdconnect.ie | @WhiteCamellia    |

# Design

This phase continues the development process by performing further realisations on the courses of events that we described in the analysis phase. This requires the creation of new sequence diagrams for each course of events. These diagrams will be more detailed in that they will contain all parameter and type infromation. Additionally, the diagrams will now include the UI and database components of the system.

Decisions made while constructing these diagrams are also documented in the class diagram.

## Use Case Realisations (Sequence Diagrams)

Each use case contains the separate sequence diagrams for each course of events. The diagrams are stored in the `images` folder and referenced in the markdown files.

### Account Management
1. [Create Account](/03-design/usecases/docs/01-create-account)
2. [Delete Account]()
3. [Updata Account]()
4. [View Details Account]()
5. [List Accounts]()

### Event Management
6. [Create Event]()
7. [Delete Event]()
8. [Update Event]()
9. [View Details Event]()
10. [List Events]()

### Venue Management
11. [Create Venue]()
12. [Delete Venue]()
13. [Update Venue]()
14. [View Details Venue]()
15. [List Venue]()

### Ticket Management
16. [Create(Buy) Ticket]()
17. [View Details Ticket]()
18. [My Tickets]()

## Class Diagram

The class diagram represents the information gained about the system by completing the use case realisations. 

![class diagram]()

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

| Team Member | TM1 | TM2 | TM3 | TM4 | TM5 | TM6 | TM7 |
|-------------|-----|-----|-----|-----|-----|-----|-----|
| Percentage  | 15% | 15% | 14% | 14% | 14% | 14% | 14% |

#### Task Allocation
| Item               | Primary Author | Contributor | Contributor | Reviewer |
|--------------------|-----|-----|-----|-----|
| Description of Responsibilities   |ZihengWang |     |     |  |
| Class Diagram      | ZihengWang  |     |     |  LeLiu   |
| Use Case 1: "Create Account" | LeLiu |             |  | ZihengWang |
| Use Case 2: "Delete Account" | YunhanGao      | BohanZhang          |  |LeLiu|
| Use Case 3: "Update Account" | YunhanGao      | BohanZhang          |  |LeLiu|
| Use Case 4: "View details Account" | YunhanGao      | BohanZhang          |  |LeLiu|
| Use Case 5: "List all Accouts"| SichengYi      |           |   |  LeLiu |
| Use Case 6: "Create Venue"| XinyaShi       | YuxuanZhang          |   | LeLiu  |
| Use Case 7: "Delete Venue"| XinyaShi       | YuxuanZhang          |    |  ZihengWang |
| Use Case 8: "Update Venue"| XinyaShi       | YuxuanZhang          |   |  ZihengWang |
| Use Case 9: "List all Venues" | SichengYi       |           |  | ZihengWang |
| Use Case 10: "View Details Venue" | BohanZhang       | YuxuanZhang          |  | ZihengWang |
| Use Case 11: "Create Event"| YuxuanZhang    | XinyaShi          |    | ZihengWang  |
| Use Case 12: "Delete Event| YuxuanZhang    |XinyaShi|   |  ZihengWang  |
| Use Case 13: "Update Event"| YuxuanZhang    | XinyaShi          |  | ZihengWang  |
| Use Case 14: "View Details Event"| YuxuanZhang    | XinyaShi          |  | LeLiu  |
| Use Case 15: "List all Events"| SichengYi    | XinyaShi          |  | LeLiu  |
| Use Case 16: "Create(Buy) Ticket"| BohanZhang     | YunhanGao       |  |  LeLiu |
| Use Case 17: "View Details Ticket| BohanZhang             | YunhanGao          |  |  LeLiu |
| USe Case 18: "My Tickets"| BohanZhang             | YunhanGao          |   |  LeLiu |


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

