# Use Case 05 - Register Account

## Description

Allows the a user to create a new account in the system

## Actors

Customer

## Triggers

This use case is triggered when a new user account is needed

## Preconditions

- The customer is on the start page of the system (00-start)

## Postconditions

- The customer is taken to the main page of the system (01-main-customer)

## Courses of Events

### Basic Course of Events

1. The user enters the required details to create an account
2. The system displays the main page of the system (01-main-customer)

### Alternative Courses of Events - Account Already Exists

1. The user enters the required details to create an account
2. The system displays an error message indicating that the account already exists

### Extension Points

None

## Inclusions

None

## Data Outcomes
- **CREATE** - An object representing the new account will be created and added to the system
- **READ** - The details of the newly created account will be retrieved and displayed

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Start Page | ![Start Page](/01-requirements-solution/uisketches/00-start.png) |
| Main Customer Page | ![Main User Page](/01-requirements-solution/uisketches/03-main-customer.png) |
| Duplicate Email Message | ![Duplicate Email Message](/01-requirements-solution/uisketches/00-start-duplicate.png) |
