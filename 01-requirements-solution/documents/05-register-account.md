# Use Case 05 - Register Account

## Description

Allows the a user to create a new account in the system

## Actors

User

## Triggers

This use case is triggered when a new user account is needed

## Preconditions

- The user is on the start page of the system (00-start)

## Postconditions

- The user is taken to the main page of the system (01-main-user)

## Courses of Events

### Basic Course of Events

1. The user enters the required details to create an account
2. The system displays the main page of the system (01-main-user)

### Alternative Courses of Events - Account Already Exists

1. The user enters the required details to create an account
2. The system displays an error message indicating that the account already exists

### Extension Points

None

## Inclusions

None

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Start Page | ![Start Page](/01-requirements-solution/uisketches/00-start.png) |
| Main User Page | ![Main User Page](/01-requirements-solution/uisketches/03-main-user.png) |
| Duplicate Email Message | ![Duplicate Email Message](/01-requirements-solution/uisketches/00-start-duplicate.png) |

## Data Outcomes
**CREATE** - A new account will be created and added to the system

**READ** - The details of the newly created account will be read and displayed
