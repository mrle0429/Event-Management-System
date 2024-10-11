# Create Event

## Description

This use case allows organizers to create a new event by entering relevant details such as the event name, date, venue, and ticket information. It simplifies the process of organizing events, allowing organizers to publish new events and manage them within the system.

## Actors

Organizers, Administrators

## Triggers

This use case is triggered when an organizer decides to create a new event.

## Preconditions

- The organizer must be logged in.
- The event must be created with valid data.

## Postconditions

A new event is created and stored in the system.

## Courses of Events

### 01 - Basic course of events
#### Course of Events
1. The organizer navigates to the "Create Event" page.
2. The system prompts the organizer to enter the following details:
  - **Event Name**: The title of the event.
  - **Description**: A detailed overview of the event.
  - **Date & Time**: The date and time for the event.
  - **Venue**: Select from an existing venue or add a new venue.
  - **Ticket Types**: Define different ticket types (e.g., standing, seating, VIP).
  - **Ticket Prices**: Set prices for each ticket type.
  - **Ticket Availability**: Specify the number of available tickets.
  - **Sales Period**: Set the start and end date for ticket sales.
  - **Media Upload**: Upload images or promotional material related to the event.
3. The organizer submits the form.
4. The system validates the data entered by the organizer.
5. Upon successful validation, the system creates the event and saves it in the database.
6. The system displays a confirmation message that the event has been successfully created.

#### Related UI Prototypes
| 03 - Create Event Page |
|:--:|
| ![Create Event](ui/CreateEvent.png) |

### 02 - Alternate course of events - Validation Failure
#### Course of Events
1. The organizer navigates to the "Create Event" page and enters event details.
2. The system detects invalid or incomplete data.
3. The system displays error messages, indicating the fields that need correction.
4. The organizer corrects the data and resubmits the form.
5. The system revalidates and creates the event upon successful validation.

#### Related UI Prototypes
| 03 - Create Event Error Page |
|:--:|
| ![Create Event Error](ui/CreateEventError.png) |

## API Endpoints

- `POST /events`: Creates a new event with the specified details.
- `GET /venues`: Fetches a list of available venues.
- `POST /venues`: Allows organizers to create a new venue if the desired venue is not listed.

## Inclusions

Validate Event Details, Save Event

## Data Outcomes

**CREATE** - A new event is created in the database.

---

## Features

- **Event Information Input**:
  - **Event Name**: Enter the event title.
  - **Description**: Provide a detailed description of the event.
  - **Date & Time**: Set the start and end date and time for the event.
  - **Venue**: Choose an existing venue or create a new one.
  - **Organizer Information**: Automatically populate the organizer’s details.

- **Ticket Management**:
  - **Ticket Types**: Define ticket types (e.g., standing, seating, VIP).
  - **Ticket Prices**: Set ticket prices for each type.
  - **Ticket Availability**: Specify the number of tickets available for each type.
  - **Sales Period**: Define the start and end date for ticket sales.

- **Media Upload**: Upload event-related images such as banners or promotional materials.

- **Preview Event**: Before finalizing, organizers can preview the event as users would see it.

- **Event Publication**: Upon saving, the event is published and visible to users.

## Future Enhancements

- **Draft Mode**: Allow organizers to save events as drafts and complete them later.
- **Event Templates**: Enable the reuse of event templates for recurring events.
- **Advanced Ticketing Options**: Add support for discount codes, group tickets, or early-bird pricing.
