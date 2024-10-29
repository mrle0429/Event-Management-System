# Use Case 14 - Modify Event

## Description

Allows the manager to modify an event in the system

## Actors

Manager

## Triggers

This use case is triggered when changes need to be made to an event

## Preconditions

- The manager is on the list events page (14-list-events) or the view event page (16-view-event)

## Postconditions

- The manager is shown the view event page for the modified event (16-view-event)

## Courses of Events

### Basic Course of Events

1. The manager selects the modify event functionality (either adjacent to the event on the list events page or on the view event page)
2. The system displays the add event page (18-modify-event) page
3. The manager updates the details of the event
4. The system shows the details of the modified event on the view event page (16-view-event)

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the event will be retrieved and displayed
- **UPDATE** - The object representing the details of the event will be modified

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Events Page | ![List Events Page](/01-requirements-solution/uisketches/14-list-events.png) |
| Modify Event Page | ![Modify Event Page](/01-requirements-solution/uisketches/18-modify-event.png) |
| View Event Page | ![View Event Page](/01-requirements-solution/uisketches/16-view-event.png) |
