# Analysis Sample Solution

This is a partial sample solution for the analysis part of the project. It is not complete. I will aim to add the remaining components in the coming days.


## Use Case Realisations

This includes the following use cases:

1. [Create Account](/02-analysis-solution/usecases/docs/01-create-account.md)
2. [List Accounts](/02-analysis-solution/usecases/docs/02-list-accounts.md)
3. [View Account](/02-analysis-solution/usecases/docs/03-view-account.md)
4. [Modify Account](/02-analysis-solution/usecases/docs/04-modify-account.md)
5. [Register Account](/02-analysis-solution/usecases/docs/05-register-account.md)
6. [List Venues](/02-analysis-solution/usecases/docs/06-list-venues.md)
7. [Add Venue](/02-analysis-solution/usecases/docs/07-add-venue.md)
8. [View Venue](/02-analysis-solution/usecases/docs/08-view-venue.md)
9. [Modify Venue](/02-analysis-solution/usecases/docs/09-modify-venue.md)
10. [Delete Venue](/02-analysis-solution/usecases/docs/10-delete-venue.md)
11. [List Events](/02-analysis-solution/usecases/docs/11-list-events.md)
12. [Add Event](/02-analysis-solution/usecases/docs/12-add-event.md)
13. [View Event](/02-analysis-solution/usecases/docs/13-view-event.md)
14. [Modify Event](/02-analysis-solution/usecases/docs/14-modify-event.md)
15. [Delete Event](/02-analysis-solution/usecases/docs/15-delete-event.md)
16. [List My Events](/02-analysis-solution/usecases/docs/16-list-my-events.md)
17. [List Tickets](/02-analysis-solution/usecases/docs/17-list-my-tickets.md)
18. [Buy Ticket](/02-analysis-solution/usecases/docs/18-buy-ticket.md)
19. [View Ticket](/02-analysis-solution/usecases/docs/19-view-ticket.md)


## Class Diagram 

The class diagram for the analysis part of the project is shown below. Note how the methods included in each of the classes are those that appear in the various sequence diagrams.

![Class Diagram](/02-analysis-solution/images/class-diagram.png)

## Class Responsibilities

### Entities

1. Account - An abstract class that represents an account in the system. It represents the common attributes and methods that all accounts have.
2. Customer - A concrete class that represents a customer account in the system. Each object is responsible for representing the information of a single customer. 
3. Organiser - A concrete class that represents an organiser account in the system. Each object is responsible for representing the information of a single organiser.
4. Administrator - A concrete class that represents an administrator account in the system. Each object is responsible for representing the information of a single administrator.
5. Role - An enumeration that represents the different roles that an account can have in the system.
6. Venue - A class that represents a venue in the system. Each object is responsible for representing the information of a single venue.
7. TicketAvailability - A class that represents the availability of different types of tickets available at a venue. Each object is responsible for representing the availability of one type of ticket at a venue. Objects of this class are immutable.
8. Event - A class that represents an event in the system. Each object is responsible for representing the information of a single event.
9. TicketQuantity - A class that represents the quantity of tickets available for a particular event. Each object is responsible for representing the quantity of tickets of a single type available for a particular event as well as their price.
10. Ticket - A class that represents a ticket in the system. Each object is responsible for representing the information of a single ticket.
11. Error - An enumeration that represents the different types of errors that can occur in the system.

### Services

1. AccountService - A class that provides services related to accounts in the system. It is responsible for creating, listing, viewing, modifying, and registering accounts.
2. EventService - 

