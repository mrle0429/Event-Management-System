# Use Case 07 - Add Venue

## Description

Allows the administrator or organiser to add a new venue to the system

## Actors

Administrator, Organizer

## Triggers

This use case is triggered when a new venue needs to be added to the system

## Preconditions

- The is on the administrator or organiser are on the list venues page (09-list-venues) or the main page (01-main-admin, 02-main-organiser)

## Postconditions

- The user is shown the list venues page on the view venue page (10-view-venue)

## Courses of Events

### Basic Course of Events

1. The organiser or administrator selects the add venue functionality
2. The system displays the add venue page (11-add-venue) page
3. The organiser or administrator enters the details of the venue
4. The system shows the created details of the venue on the view venue page (10-view-venue)

### Alternate Course of Events - Incorrect Capacity

1. The organiser or administrator selects the add venue functionality
2. The system displays the add venue page (11-add-venue) page
3. The organiser or administrator enters the details of the venue
4. The system warns the organiser or administrator that the total capacity of the venue does not match the sum of the different types of tickets (13-add-capacity-warning)
5. The organiser or administrator clicks OK
6. The system returns to step 2 of the use case

### Extension Points

None

## Inclusions

None

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Admin Main Page | ![Admin Main Page](/01-requirements-solution/uisketches/01-main-admin.png) |
| List Venues Page | ![List Venues Page](/01-requirements-solution/uisketches/09-list-venues.png) |
| View Venue Page | ![View Venue Page](/01-requirements-solution/uisketches/10-view-venue.png) |
| Add Capacity Warning Page | ![Modify Capacity Warning Page](/01-requirements-solution/uisketches/12-modify-capacity-warning.png) |
## Data Outcomes
**READ** - The details of the all accounts will be read and displayed

**CREATE** - The details of the venue will be created

