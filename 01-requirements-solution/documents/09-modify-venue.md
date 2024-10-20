# Use Case 09 - Modify Venue

## Description

Allows the administrator or organiser to modify the details of a venue in the system

## Actors

Administrator, Organizer

## Triggers

This use case is triggered when the administrator or organiser needs to modify the details of a venue in the system

## Preconditions

- The is on the administrator or organiser are on the list venues page (09-list-venues) or the view venue page (10-view-venue)

## Postconditions

- The details of the venue are shown on the view venue page (10-view-venue)

## Courses of Events

### Basic Course of Events

1. The organiser or administrator selects the edit venue functionality adjacent to the venue they want to view on the list venues page or at the bottom of the view venue page
2. The system displays the modify venue page (13-modify-venue) page
3. The organiser or administrator modifies the details of the venue
4. The system shows the updated details of the venue on the view venue page (10-view-venue)

### Alternate Course of Events - Incorrect Capacity

1. The organiser or administrator selects the edit venue functionality adjacent to the venue they want to view on the list venues page or at the bottom of the view venue page
2. The system displays the modify venue page (13-modify-venue) page
3. The organiser or administrator modifies the details of the venue
4. The system warns the organiser or administrator that the total capacity of the venue does not match the sum of the different types of tickets (13-modify-capacity-warning)
5. The organiser or administrator clicks OK
6. The system returns to step 2 of the use case

### Extension Points

None

## Inclusions

None

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| View Venue Page | ![View Venue Page](/01-requirements-solution/uisketches/10-view-venue.png) |
| Modify Venue Page | ![Modify Venue Page](/01-requirements-solution/uisketches/12-modify-venue.png) |
| Modify Capacity Warning Page | ![Modify Capacity Warning Page](/01-requirements-solution/uisketches/12-modify-capacity-warning.png) |

## Data Outcomes
**READ** - The details of the all accounts will be read and displayed

**UPDATE** - The details of the venue will be updated
