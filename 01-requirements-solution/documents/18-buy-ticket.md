# Use Case 18 - Buy Ticket

## Description

Allows the customer purchas a ticket to an event
## Actors

Customer

## Triggers

This use case is triggered when a customer wants to buy a ticket to an event

## Preconditions

- The customer is on the view event page (16-view-event)

## Postconditions

- The details of the ticket and event are shown on the view ticket page (21-view-ticket)

## Courses of Events

### Basic Course of Events

1. The customer selects the buy ticket functionality 
2. The system displays the buy ticket page (22-buy-ticket) with a form to fill in the purchas details
3. The customer fills in the form and submits it
2. The system displays the view ticket page (21-view-ticket) with the details of the ticket and event that was just purchased

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the selected ticket and the event it is for will be retrieved and displayed
- **CREATE** - An object representing the ticket will be created and associated with the customer account
- **UPDATE** - The event will be updated to reflect the new ticket purchase


## Relevant UI Sketches

| Page Name | Image |
|----|------|
| View Event Page | ![View Event Page](/01-requirements-solution/uisketches/16-view-event.png) |
| Buy Ticket Page | ![Buy Ticket Page](/01-requirements-solution/uisketches/22-buy-ticket.png) |
| View Ticket Page | ![View Ticket Page](/01-requirements-solution/uisketches/21-view-ticket.png) |
