### Message From Sean

I have now checked out the contents of the repository for grading. Any further details added after the deadline will be ignored.


# Team Project: *Group 04*

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


# Analysis

This phase continues the development process by realising the courses of events that we described in the requirements analysis phase. This requires the creation of sequence diagrams for each course of events. Decisions made while constructing these diagrams are also documented in the class diagram.

## Use Case Realisations (Sequence Diagrams)

Each use case contains the separate sequence diagrams for each course of events. The diagrams are stored in the `images` folder and referenced in the markdown files.

### Account Management
1. [Create Account](Account/createAccount.md)
2. [Delete Account](Account/DeleteAccount.md)
3. [Updata Account](Account/UpdateAccount.md)
4. [View Details Account](Account/ViewAccountDetails.md)
5. [List Accounts](Account/ListAccounts.md)

### Event Management
6. [Create Event](Event/CreateEvent.md)
7. [Delete Event](Event/DeleteEvent.md)
8. [Update Event](Event/UpdateEvent.md)
9. [View Details Event](Event/ViewDetailsEvent.md)
10. [List Events](Event/ListEvents.md)

### Venue Management
11. [Create Venue](Venue/1.CreateVenue.md)
12. [Delete Venue](Venue/3.DeleteVenue.md)
13. [Update Venue](Venue/5.UpdateVenue.md)
14. [View Details Venue](Venue/4.ViewDetailsVenue.md)
15. [List Venue](Venue/2.ListVenues.md)

### Ticket Management
16. [Create(Buy) Ticket](Ticket/Create(Buy)Ticket.md)
17. [View Details Ticket](Ticket/ViewTicketDetails.md)
18. [My Tickets](Ticket/MyTickets.md)


## Class Diagram

The class diagram represents the information gained about the system by completing the use case realisations. 

![class diagram](./02-analysis/images/class.png)

## Description of Responsibilities

### Role
The `Role` enumeration defines the role types of different users in the system, including Administrator, Organizer, and User.

### TicketType
The `TicketType` enumeration defines the available ticket types in the system, including Standing, Seat, and VIP, which are used to distinguish the characteristics of different ticket types.

### Account
The `Account` class is a basic entity class. It serves as the basis for all account types and has account attributes such as name, email, password, and role for subclasses to inherit

### User
The `User` class represents ordinary users in the system and is inherited from the `Account` class. It manages the ticket information purchased by the user, contains the list of tickets held by the user, and provides related get and set methods.

### Organizer
The `Organizer` class represents the event organizer and is inherited from the `Account` class. It manages the organizer's details such as address, company name, phone number, and the list of events created and managed by the organizer.

### Administrator
The `Administrator` class represents the system administrator and is inherited from the `Account` class. It is responsible for the overall management and maintenance of the system, has the highest authority, and can perform all operations and configurations.

### AccountSystem
The `AccountSystem` class, as the control layer of account management, handles the interaction between the user interface and `AccountService`. It is responsible for the creation, update, deletion, and display of interfaces related to account management.

### AccountService
The `AccountService` class is responsible for the business logic of account management. It handles the creation, update, deletion, and query operations of accounts, and provides the functions of obtaining accounts through email, listing all accounts, and notifying administrators.

### Venue
The `Venue` class manages the basic information of the event venue, including name, address, contact information, etc.

### VenueSystem
The `VenueSystem` class, as the control layer of venue management, handles the interaction between the user interface and `VenueService`. It is responsible for the creation, update, deletion, and display of interfaces and detailed information related to venue management.

### VenueService
The `VenueService` class is responsible for the business logic of venue management. It handles the creation, update, deletion, and query operations of venues, and provides the functions of saving venue information, obtaining venues by name or ID, and listing all venues.

### Event
The `Event` class manages the basic information of an event, including name, description, date, time, associated venue, and organizer.

### EventSystem
The `EventSystem` class, as the control layer of event management, handles the interaction between the user interface and the `EventService`. It is responsible for the creation, update, deletion, and display of event management related interfaces and detailed information.

### EventService
The `EventService` class is responsible for the business logic of event management. It handles the creation, update, deletion, and query operations of events, and provides the functions of checking venue availability, obtaining event information, and listing events by organizer.

### Ticket
The `Ticket` class manages the basic information of a single ticket, including the associated event (`Event`), ticket type (`TicketType`), purchasing user (`User`), and price.

### TicketSystem
The`TicketSystem` class is the control layer of ticket management, handling the interaction between the user interface and `TicketService`. It is responsible for purchasing tickets, viewing user tickets, and displaying interfaces and detailed information related to ticket management.

### TicketService
The`TicketService` class is responsible for the business logic of ticket management. It handles ticket purchase, query and management operations, and provides users with the functions of purchasing tickets, listing all user tickets, obtaining ticket details and creating new tickets.

## Milestone 2 Analysis

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



