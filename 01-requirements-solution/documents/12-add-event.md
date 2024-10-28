# Use Case 12 - Add Event

## Description

Allows the organiser to add a new event to the system

## Actors

Organiser

## Triggers

This use case is triggered when a new event needs to be added to the system

## Preconditions

- The organiser is on the list events page (14-list-events)

## Postconditions

- The organiser is shown the view event page for the newly created event (16-view-event)

## Courses of Events

### Basic Course of Events

1. The organiser selects the add event functionality
2. The system displays the add event page (17-add-event) page
3. The organiser enters the details of the event
4. The system shows the created details of the event on the view event page (16-view-event)

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the event will be retrieved and displayed
- **CREATE** - An object representing the details of the event will be created

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Events Page | ![List Events Page](/01-requirements-solution/uisketches/14-list-events.png) |
| Add Event Page | ![Add Event Page](/01-requirements-solution/uisketches/17-add-event.png) |
| View Event Page | ![View Event Page](/01-requirements-solution/uisketches/16-view-event.png) |

