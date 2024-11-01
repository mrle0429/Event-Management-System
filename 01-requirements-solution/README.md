# Requirements Analysis - Sample Solution
**Note**: I didn't have the time to complete this sample solution. However, it describes the use cases that I considered important for the software and has a number of documents that can be used as examples for the requirements analysis. Also, I completed the solution in a rush so there may be some mistakes in the documents.

## Use Case Descriptions

This includes the following use cases:

1. [Create Account](/01-requirements-solution/documents/01-create-account.md)
2. [List Accounts](/01-requirements-solution/documents/02-list-accounts.md)
3. [View Account](/01-requirements-solution/documents/03-view-account.md)
4. [Modify Account](/01-requirements-solution/documents/04-modify-account.md)
5. [Register Account](/01-requirements-solution/documents/05-register-account.md)
6. [List Venues](/01-requirements-solution/documents/06-list-venues.md)
7. [Add Venue](/01-requirements-solution/documents/07-add-venue.md)
8. [View Venue](/01-requirements-solution/documents/08-view-venue.md)
9. [Modify Venue](/01-requirements-solution/documents/09-modify-venue.md)
10. [Delete Venue](/01-requirements-solution/documents/10-delete-venue.md)
11. [List Events](/01-requirements-solution/documents/11-list-events.md)
12. [Add Event](/01-requirements-solution/documents/12-add-event.md)
13. [View Event](/01-requirements-solution/documents/13-view-event.md)
14. [Modify Event](/01-requirements-solution/documents/14-modify-event.md)
15. [Delete Event](/01-requirements-solution/documents/15-delete-event.md)
16. [List My Events](/01-requirements-solution/documents/16-list-my-events.md)
17. [List Tickets](/01-requirements-solution/documents/17-list-tickets.md)
18. [Buy Ticket](/01-requirements-solution/documents/18-buy-ticket.md)
19. [View Ticket](/01-requirements-solution/documents/19-view-ticket.md)

## Use Case Diagram

The use cases are shown in this diagram

![use case diagram](/01-requirements-solution/images/usecase.png)

## Domain Model

![domain model](/01-requirements-solution/images/domain-model.png)

## System Glossary

| Term        | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| Event       | A planned event that is to be held at a specific date and time that users can buy tickets to attend |
| Venue       | A location where an event is to be held                      |
| Ticket      | A pass that allows a user to attend an event                 |
| Account     | A user account that allows a user to access the system       |
| Administrator | A user with special privileges that can manage the system    |
| Organiser    | A user that can create and manage events                     |
| Customer     | A user that can buy tickets to attend events                 |
| Manager      | A representation of the collecter roles that are common to the Administrator and Organiser actors |
| User         | A generalisation of the three types of users                 |
| Register     | The process of creating a new account                        |
| Ticket Type  | The type of ticket that can be bought for an event (premium, standing, seated) |
| Premium Ticket | A ticket that allows a user to access special privileges     |
| Standing Ticket | A ticket that allows a user to access the event but does not have a specific seat |
| Seated Ticket | A ticket that allows a user to access the event and has a specific seat |


## UI Prototypes/Sketches
| Page Name   | Image                                                  |
| ----------- | ------------------------------------------------------------ |
| Start Page  | ![Start Page](/01-requirements-solution/uisketches/00-start.png) |
| Register/Create Account duplicate warning | ![Register/Create Account duplicate warning](/01-requirements-solution/uisketches/00-start-duplicate.png)|
| Admin Main Page | ![Admin Main Page](/01-requirements-solution/uisketches/01-main-admin.png) |
| Organiser Main Page | ![Organiser Main Page](/01-requirements-solution/uisketches/02-main-organiser.png) |
| Customer Main Page | ![Customer Main Page](/01-requirements-solution/uisketches/03-main-customer.png) |
| List Accounts Page | ![List Accounts Page](/01-requirements-solution/uisketches/04-list-accounts.png) |
| Create Account Page | ![Create Account Page](/01-requirements-solution/uisketches/05-create-account.png) |
| Create Account Details Missing Warning | ![Create Account Details Missing Warning](/01-requirements-solution/uisketches/05-create-account-missing.png) |
| View Account Page | ![View Account Page](/01-requirements-solution/uisketches/06-view-account.png) |
| Modify Account Page | ![Modify Account Page](/01-requirements-solution/uisketches/07-modify-account.png) |
| Modify Account Password Warning | ![Modify Account Password Warning](/01-requirements-solution/uisketches/07-modify-account-password.png) |
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| View Venue Page | ![View Venue Page](/01-requirements-solution/uisketches/10-view-venue.png) |
| Add Venue Page | ![Add Venue Page](/01-requirements-solution/uisketches/11-add-venue.png) |
| Modify Venue Page | ![Modify Venue Page](/01-requirements-solution/uisketches/12-modify-venue.png) |
| Modify Capacity Warning Page | ![Modify Capacity Warning Page](/01-requirements-solution/uisketches/12-modify-capacity-warning.png) |
| Delete Venue Warning | ![Delete Venue Warning](/01-requirements-solution/uisketches/13-delete-venue-warning.png) |
| List Events Page | ![List Events Page](/01-requirements-solution/uisketches/14-list-events.png) |
| View Event Page | ![View Event Page](/01-requirements-solution/uisketches/16-view-event.png) |
| Add Event Page | ![Add Event Page](/01-requirements-solution/uisketches/17-add-event.png) |
| Modify Event Page | ![Modify Event Page](/01-requirements-solution/uisketches/18-modify-event.png) |
## UI and Use Case Mapping
This diagram was not required for the assignment but it shows the plan that I have for the UI and how different use cases can be used to navigate between the different pages.

![UI and Use Case Mapping](/01-requirements-solution/images/ui-states.png)
