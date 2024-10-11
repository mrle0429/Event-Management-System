# Edit Event

## Description

This use case allows organizers to modify the details of events they have previously created. It is essential for updating or correcting event information, ticket availability, or other related details. Only organizers and administrators have access to this feature.

## Actors

Organizers, Administrators

## Triggers

This use case is triggered when an organizer decides to update an event they have created.

## Preconditions

- The event must exist in the system.
- The organizer must have permission to edit the event.

## Postconditions

The event details are updated in the system.

## Courses of Events

### 01 - Basic course of events
#### Course of Events
1. The organizer selects an event from their event list.
2. The system fetches the event details.
3. The organizer modifies any of the following details:
  - **Event Name**: The title of the event.
  - **Description**: Update the event description.
  - **Date & Time**: Adjust the event's date and time.
  - **Venue**: Update the event venue or select a new venue.
  - **Organizer Information**: Update the organizer’s contact or company details.
  - **Ticket Types, Prices, and Availability**: Modify ticket-related information.
  - **Media Upload**: Replace or upload event images (e.g., banners or promotional images).
  - **Event Status**: Set the event as "Active," "Postponed," or "Cancelled."
4. The organizer submits the changes.
5. The system validates the changes and updates the event.
6. The system displays a confirmation message indicating that the changes have been saved successfully.

#### Related UI Prototypes
| 04 - Edit Event Page |
|:--:|
| ![Edit Event](ui/EditEvent.png) |

### 02 - Alternate course of events - Invalid Data

#### Course of Events
1. The organizer selects an event to edit.
2. The organizer enters invalid or incomplete data.
3. The system detects validation errors and displays appropriate error messages.
4. The organizer corrects the data or cancels the changes.
5. The system revalidates and updates the event details upon successful validation.

#### Related UI Prototypes
| 04 - Edit Event Error Page |
|:--:|
| ![Edit Event Error](ui/EditEventError.png) |

## API Endpoints

- `PUT /events/{eventId}`: Updates the event details based on the provided input.
- `GET /events/{eventId}`: Retrieves the current details of the event for editing.

## Inclusions

Validate Event Details, Save Changes

## Data Outcomes

**UPDATE** - The event details are modified in the database.

---

## Features

- **Modify Event Details**:
  - **Event Name**: Change the event title if needed.
  - **Description**: Update the event description to reflect new or additional information.
  - **Date & Time**: Adjust the event's date and time, including adding or changing the end time.
  - **Venue**: Update or change the event venue. Organizers can select a new venue from the list or create a new one if necessary.
  - **Organizer Information**: Update the organizer’s contact or company details if changes are needed.

- **Update Ticket Information**:
  - **Ticket Types**: Add or remove ticket types (e.g., standing, seating, VIP).
  - **Ticket Prices**: Modify ticket prices for existing ticket types.
  - **Ticket Availability**: Adjust the number of available tickets based on demand or capacity changes.
  - **Sales Period**: Extend or shorten the ticket sales window.

- **Media Update**: Organizers can replace or upload additional event images (e.g., promotional banners or event photos).

- **Event Status**: Organizers can set the event as "Active," "Postponed," or "Cancelled," providing clear communication to users. If an event is canceled or postponed, an automated notification is sent to ticket holders.

- **Save Changes**: Organizers must confirm and save any changes to apply them to the live event.

## Future Enhancements

- **Version Control**: Allow organizers to track changes made to an event, with an option to revert to previous versions.
- **Audit Log**: Show a history of all changes made to the event by any authorized user.
- **Notifications**: Automatically notify users who have already purchased tickets if significant event changes occur (e.g., date, venue, or cancellation).
