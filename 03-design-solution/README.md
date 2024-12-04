# Design Sample Solution

I have only completed the basic course of events for each of the use cases. These will give examples of how they should have been accomplished. 

## Class Diagram

![Class Diagram](/03-design-solution/images/class-diagram.svg)

## Database Description

### Entities

1. Administrator - id, name, email, password
2. Customer - id, name, email, password
3. Organiser - id, name, email, password, companyName, companyAddress, phoneNumber
4. Venue - id, name, address, contactName, contactEmail, contactPhone, (ticketAvailabilities - type, numberAvailable)
5. Event - id, name, description, date, time, (ticketQuantities - type, numAvailable, numSold, price)
6. Ticket - id, type, price, quantity

### Relationships

1. Venue 1 - M Event
2. Event 1 - 1 Venue
3. Event 1 - M Ticket
4. Ticket 1 - 1 Event
5. Customer 1 - M Ticket
6. Ticket 1 - M Customer
7. Organiser 1 - M Event
8. Event 1 - 1 Organiser

### Note
I am planning to include the ticket availabilities and quantities as embedded within the Venue and Event entities. However, if I was not planning to do this, I would have to create separate entities for these relationships.

#### Separate Entities
1. TicketAvailability - id, type, numberAvailable
2. TicketQuantity - id, type, numAvailable, numSold, price

#### Relationships

1. Venue 1 - M TicketAvailability
2. TicketAvailability 1 - 1 Venue
3. Event 1 - M TicketQuantity
4. TicketQuantity 1 - 1 Event

## Example Seqquence Diagrams

### 01 - Create Account

![Create Account Sequence Diagram](/03-design-solution/usecases/images/01-create-account-basic.png)

### 02 - List Accounts

![List Accounts Sequence Diagram](/03-design-solution/usecases/images/02-list-accounts-basic.png)

### 03 - View Account

![View Account Sequence Diagram](/03-design-solution/usecases/images/03-view-account-basic.png)

### 04 - Modify Account
![Modify Account Sequence Diagram](/03-design-solution/usecases/images/04-modify-account-basic.png)

### 05 - Register Account

![Register Account Sequence Diagram](/03-design-solution/usecases/images/05-register-account-basic.png)


### 06 - List Venues

![List Venues Sequence Diagram](/03-design-solution/usecases/images/06-list-venues-basic.png)


### 07 - Add Venue

![Add Venue Sequence Diagram](/03-design-solution/usecases/images/07-add-venue-basic.png)

### 08 - View Venue

![View Venue Sequence Diagram](/03-design-solution/usecases/images/08-view-venue-basic.png)


### 09 - Modify Venue

![Modify Venue Sequence Diagram](/03-design-solution/usecases/images/09-modify-venue-basic.png)


### 10 - Delete Venue

![Delete Venue Sequence Diagram](/03-design-solution/usecases/images/10-delete-venue-basic.png)


### 11 - List Events

![List Events Sequence Diagram](/03-design-solution/usecases/images/11-list-events-basic.png)


### 12 - Add Event

![Add Event Sequence Diagram](/03-design-solution/usecases/images/12-add-event-basic.png)


### 13 - View Event

![View Event Sequence Diagram](/03-design-solution/usecases/images/13-view-event-basic.png)


### 14 - Modify Event

![Modify Event Sequence Diagram](/03-design-solution/usecases/images/14-modify-event-basic.png)


### 15 - Delete Event

![Delete Event Sequence Diagram](/03-design-solution/usecases/images/15-delete-event-basic.png)


### 16 - List My Events

![List My Events Sequence Diagram](/03-design-solution/usecases/images/16-list-my-events-basic.png)


### 17 - List My Tickets


![List My Tickets Sequence Diagram](/03-design-solution/usecases/images/17-list-my-tickets-basic.png)


### 18 - Buy Tickets


![Buy Tickets Sequence Diagram](/03-design-solution/usecases/images/18-buy-tickets-basic.png)


### 19 - View Ticket


![View Ticket Sequence Diagram](/03-design-solution/usecases/images/19-view-ticket-basic.png)

### 20 - Delete Account

![Delete Account Sequence Diagram](/03-design-solution/usecases/images/20-delete-account-basic.png)

