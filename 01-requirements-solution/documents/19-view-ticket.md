# Use Case 19 - View Ticket

## Description

Allows the customer view the details of a ticket that they have purchased
## Actors

Customer

## Triggers

This use case is triggered when a user needs to view the details of a ticket in the system

## Preconditions

- The user is on the list tickets page (20-list-tickets)

## Postconditions

- The details of the ticket and event are shown on the view ticket page (21-view-ticket)

## Courses of Events

### Basic Course of Events

1. The customer selects the view ticket functionality adjacent to the ticket they want to view
2. The system displays the view ticket page (21-view-ticket) with the details of the ticket and event

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the selected ticket and the event it is for will be retrieved and displayed

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Tickets Page | ![List Events Page](/01-requirements-solution/uisketches/20-list-tickets.png) |
| View Ticket Page | ![View Event Page](/01-requirements-solution/uisketches/21-view-ticket.png) |
