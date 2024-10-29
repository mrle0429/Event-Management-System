# Use Case 07 - Add Venue

## Description

Allows the manager to add a new venue to the system

## Actors

Manager

## Triggers

This use case is triggered when a new venue needs to be added to the system

## Preconditions

- The manager is on the list venues page (09-list-venues)

## Postconditions

- The manager is shown the view venue page for the newly created venue (10-view-venue)

## Courses of Events

### Basic Course of Events

1. The manager selects the add venue functionality
2. The system displays the add venue page (11-add-venue) page
3. The manager enters the details of the venue
4. The system shows the created details of the venue on the view venue page (10-view-venue)

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the venue will be retrieved and displayed
- **CREATE** - An object representing the details of the venue will be created

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Admin Main Page | ![Admin Main Page](/01-requirements-solution/uisketches/01-main-admin.png) |
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| Add Venue Page | ![Add Venue Page](/01-requirements-solution/uisketches/11-add-venue.png) |
| View Venue Page | ![View Venue Page](/01-requirements-solution/uisketches/10-view-venue.png) |

