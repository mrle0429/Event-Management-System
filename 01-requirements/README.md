# Team Project: *Group 04*

## Team Members
| Number | Name          | Email(s)                           | CSGitLab Username |
|--------|---------------|------------------------------------|-----------------|
| TM1    | Le Liu        | le.liu1@ucdconnect.ie              | @Mrle |
| TM2    | Ziheng Wang   | ziheng.wang1@ucdconnect.ie         | @zihengWang                |     
| TM3    | Team Member 3 | tm3@ucdconnect.ie, personal@qq.com |@22201003        |     
| TM4    | Team Member 4 | tm4@ucdconnect.ie                  | @22201004       |     
| TM5    | Team Member 5 | tm5@ucdconnect.ie                  | @22201005       |     
| TM6    | Team Member 6 | tm6@ucdconnect.ie                  | @22201006       |     
| TM7    | Team Member 7 | tm7@ucdconnect.ie                  | @22201007       |     


# Requirements Analysis

This is the requirements analysis for the Event application, this application is good for administrator, User, Organiser.

## Use Case Diagram

The use cases are shown in this diagram

![use case diagram](./images/UseCase.png)

## Domain Model

![domain model](./images/Domain.png)


## Use Case Descriptions

This includes the following use cases:
### Login
1. [Log in]()
### Account Management
1. [Create Account](Account/createAccount.md)
2. [Delete Account](Account/DeleteAccount.md)
3. [Updata Account](Account/UpdateAccount.md)
4. [View Details Account](Account/ViewAccountDetails.md)
5. [List Accounts](Account/ListAccounts.md)

### Event Management
1. [Create Event](Event/CreateEvent.md)
2. [Delete Event](Event/DeleteEvent.md)
3. [Update Event](Event/UpdateEvent.md)
4. [View Details Event](Event/ViewDetailsEvent.md)
5. [List Events](Event/ListEvents.md)

### Venue Management
1. [Create Venue](Venue/1.CreateVenue.md)
2. [Delete Venue](Venue/3.DeleteVenue.md)
3. [Update Venue](Venue/5.UpdateVenue.md)
4. [View Details Venue](Venue/4.ViewDetailsVenue.md)
5. [List Venue](Venue/2.ListVenues.md)

### Ticket Management
1. [Create(Buy) Ticket](Ticket/Create(Buy)Ticket.md)
2. [View Details Ticket](Ticket/ViewTicketDetails.md)
3. [My Tickets](Ticket/MyTickets.md)



## System Glossary

| Term        | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| Event       | A scheduled concert or performance.                          |
| Organiser   | The individual or entity responsible for planning and managing the event.|
| administrator| A person responsible for managing the system, including user accounts, events, and overall system settings.|
|  User       | A person who uses the system to purchase tickets.|
| Venue       | The location where the event takes place|
| Ticket      | A document or digital pass that grants entry to an event.|
| Schedule    | The timetable of events, including dates and times.|
| Ticket Type | The category of a ticket, such as VIP, General Admission, or Early Bird.|
| Price       | The cost of a ticket for an event.|
| Availability| The number of tickets available for sale.|
| Sold        | The number of tickets that have been sold.|
| Event Details| Information about an event, including name, description, date, time, venue, and organiser.|
| Purchase    | The action of buying a ticket for an event.|
| Login       | The process of authenticating a user to access the system.|
| Registration| The process of creating a new user account.|
| Dashboard   | The main interface where users can access different functionalities of the system.|




## UI Prototypes/Sketches
### Login Page
| Login Page|
| --- |
|![UI Sketch](ui/Login_Page.png)|

### User Register
![User register](ui/Register_Page.png)

### Admin Main Page
![](ui/Admin_Dashboard.png)

### Organizer Main Page
![](ui/Organizer_Dashboard.png)

### User Main Page
![](ui/User_info.png)


### Admin create new User

![](ui/CreateUser.png)

### Admin manage Account
![](ui/Admin_Manage_Account.png)

### Venue List(Admin)
![](ui/AdminManageVenue.png)

### Venuew List(Organiser)
![](ui/OrganiserManageVenue.png)

### Create Venue
![](ui/CreateVenue.png)
### Edit Venue
![](ui/EditVenue.png)
### Event List(Admin)
![](ui/ListEvents(Administrator).png)
### Founded Events(Organiser)
![](ui/ListEvents(Organiser).png)
### Event Details
![](ui/ViewDetailsEvent.png)
### Create Event
![](ui/CreateEvent.png)
### Edit Event
![](ui/UpdateEvent.png)
### Buy Ticket
![Buy Ticket](ui/BuyTickets.png)
### My Tickets
![My Tickets](ui/Myticket.png)
### Ticket Details
![Ticket Details](ui/TicketsDetail.png)
## Milestone 1 Requirements Analysis

### Distribution of work on this milestone
#### Overall Distribution of Work
| Team Member | TM1 | TM2 | TM3 | TM4 | TM5 | TM6 | TM7 |
|-------------|-----|-----|-----|-----|-----|-----|-----|
| Percentage  | 12% | 12% | 12% | 12% | 12% | 12% | 12% |
#### Task Allocation
| Item               | Primary Author | Contributor | Contributor | Reviewer |
|--------------------|-|--|--|--|
| Use Case Diagram   |LL | ZH |  |  |
| Domain Model       |ZH |  |  | LL |
| System Golssary    |ZH |  |  |  |
| UI Prototypes      |SC |ZH  |  |  |
| Use Case 1: "Create Account" |LL |  |  |  |
| Use Case 2: "Delete Account" |XY|YX|
| Use Case 3: "Update Account" |XY |YX |  |  |
| Use Case 4: "View details Account" |XY |YX  |  |  |
| Use Case 5: "List all Accouts"|XY|YX|
| Use Case 6: "Create Venue"|YX| XY|   |   |
| Use Case 7: "Delete Venue"|YX|XY|
| Use Case 8: "Update Venue"|YX|XY|   |   |
| Use Case 9: "List all Venues" |XY |YX  |  |  |
| Use Case 10: "View Details Venue" |XY  |YX  |  |  |
| Use Case 11: "Create Event"|YH|BH|    |   |
| Use Case 12: "Delete Event|YH|
| Use Case 13: "Update Event"|YH|BH|  |   |
| Use Case 14: "View Details Event"|YH|BH|  |   |
| Use Case 15: "List all Events"|YH|BH|  |   |
| Use Case 16: "Create(Buy) Ticket"|BH|YH|  |   |
| Use Case 17: "View Details Ticket|BH|YH|  |   |
| USe Case 18: "My Tickets"|BH|YH|   |   |





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

