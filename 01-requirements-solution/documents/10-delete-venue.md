# Use Case 10 - Delete Venue

## Description

Allows the administrator to delete a venue from the system

## Actors

Administrator

## Triggers

This use case is triggered when the administrator needs to delete a venue from the system

## Preconditions

- The administrator is on the list venues page (09-list-venues) or the view venue page (10-view-venue)

## Postconditions

- The system returns to the list venues page (09-list-venues) and the venue is no longer displayed

## Courses of Events

### Basic Course of Events

1. The administrator selects the delete venue functionality adjacent to the venue they want to remove on the list venues page
2. The system asks the administrator to confirm the deletion of the venue (13-delete-venue-warning)
3. The administrator clicks OK
4. The system returns to the list venues page (09-list-venues) and the venue is no longer displayed

### Alternate Course of Events - Not Confirmed

1. The administrator selects the delete venue functionality adjacent to the venue they want to remove on the list venues page
2. The system asks the administrator to confirm the deletion of the venue (13-delete-venue-warning)
3. The administrator clicks Cancel
4. The system returns to the list venues page (09-list-venues) with no changes
### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the all venues will be retreived and displayed
- **DELETE** - The object representing the specific venue will be removed from the system

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| Delete Venue Warning | ![Delete Venue Warning](/01-requirements-solution/uisketches/13-delete-venue-warning.png) |
