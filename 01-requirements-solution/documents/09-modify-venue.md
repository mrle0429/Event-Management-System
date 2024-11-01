# Use Case 09 - Modify Venue

## Description

Allows the manager to modify the details of a venue in the system

## Actors

Manager

## Triggers

This use case is triggered when the manager needs to modify the details of a venue in the system

## Preconditions

- The manager is on the list venues page (09-list-venues) or the view venue page (10-view-venue)

## Postconditions

- The details of the venue are shown on the view venue page (10-view-venue)

## Courses of Events

### Basic Course of Events

1. The manager selects the edit venue functionality adjacent to the venue they want to view on the list venues page or at the bottom of the view venue page
2. The system displays the modify venue page (13-modify-venue) page
3. The manager modifies the details of the venue
4. The system shows the updated details of the venue on the view venue page (10-view-venue)

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the updated venue will be retreived and displayed
- **UPDATE** - The details of the venue will be updated

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| View Venue Page | ![View Venue Page](/01-requirements-solution/uisketches/10-view-venue.png) |
| Modify Venue Page | ![Modify Venue Page](/01-requirements-solution/uisketches/12-modify-venue.png) |
