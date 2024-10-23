# Use Case 15 - Delete Event

## Description

Allows the administrator to delete a event from the system

## Actors

Administrator

## Triggers

This use case is triggered when the administrator needs to delete an event from the system

## Preconditions

- The administrator is on the list events page (14-list-events) or the view event page (16-view-event)

## Postconditions

- The system returns to the list events page (14-list-events) and the event is no longer displayed

## Courses of Events

### Basic Course of Events

1. The administrator selects the delete event functionality adjacent to the event they want to remove on the list events page
2. The system asks the administrator to confirm the deletion of the event (19-delete-event-warning)
3. The administrator clicks OK
4. The system returns to the list events page (14-list-events) and the venue is no longer displayed

### Alternate Course of Events - Not Confirmed

1. The administrator selects the delete venue functionality adjacent to the venue they want to remove on the list venues page
2. The system asks the administrator to confirm the deletion of the venue (19-delete-event-warning)
3. The administrator clicks Cancel
4. The system returns to the list venues page (14-list-events) with no changes
### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the all events will be retreived and displayed
- **DELETE** - The object representing the specific event will be removed from the system

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Event Page | ![List Event Page](/01-requirements-solution/uisketches/14-list-events.png) |
| Delete Event Warning | ![Delete Event Warning](/01-requirements-solution/uisketches/19-delete-event-warning.png) |
