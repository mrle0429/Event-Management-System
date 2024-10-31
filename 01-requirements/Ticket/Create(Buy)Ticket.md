### Buy Ticket

**Use Case Name:** Buy Ticket

**Primary Actor:** User

**Brief Description:** This use case involves allowing a user to purchase a ticket for an event. Users can browse available events, select an event, choose a ticket type, and proceed with the purchase. The system processes the purchase and updates the ticket availability accordingly.

**Actors:**
- User

**Triggers:**
- The user wishes to purchase a ticket for an event.

**Preconditions:**
- The user must be logged in to the system.
- The selected event must have tickets available for purchase.

**Postconditions:**
- The system confirms the purchase and provides the user with a ticket.
- The system updates the availability of tickets for the selected event.

**Courses of Events**

### 01 - Basic course of events
#### Course of Events
1. User clicked "Buy now" button on the event page.
2. The system redirect the user to purchase successful page.
#### Related UI Prototypes
| 01 - Main Page                     |
|------------------------------------|
| ![Main Page](../ui/BuyTickets.png) |

| 02 - UserDashboard      |
|-------------------------|
| ![UserDashboard Page]() |

### 02 - Alternate course of events - No Tickets Available
#### Course of Events
1. The User selects the "Buy Now" option on the event page
2. The system redirect the user to purchase unsuccessful page.

#### Related UI Prototypes
![Main Page](../ui/BuyTickets.png)

**Special Requirements:**
- The system should display all available ticket types and quantities clearly.


**Inclusions:**
- Select Event
- Choose Ticket Type
- Payment Processing

**Data Outcomes**
**READ** - The system retrieves the details of the selected event and available tickets.
**UPDATE** - The ticket availability is updated after a successful purchase.
**CREATE** - A new ticket record is created and added to the user's account.

**Primary Actor's Goal:** To purchase a ticket for an event and receive confirmation of the purchase.