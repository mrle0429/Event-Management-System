# Use Case 03 - View Account

## Description

Allows the administrator to view the details of an account in the system

## Actors

Administrator, Organizer, User

## Triggers

This use case is triggered when the administrator needs to view the details of an account in the system

## Preconditions

- The administrator is on the list accounts page (04-list-accounts)
- The administrator, organiser or user is the main page for their account type (01-main-admin, 02-main-organiser, 03-main-user)

## Postconditions

- The user is shown the view account page (06-view-account) which shows the details of the selected account

## Courses of Events

### Basic Course of Events - Administrator Viewing Other Accounts

1. The user selects the view account functionality adjacent to the account to view
2. The system displays the view account page (06-view-account) which shows the details of the selected account

### Basic Course of Events - Any User Viewing Their Account

1. The user selects the my account functionality on the main page for their account type
2. The system displays the view account page (06-view-account) which shows the details of the selected account

### Extension Points

None

## Inclusions

None

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Main Admin Page | ![Main Admin Page](/01-requirements-solution/uisketches/01-main-admin.png) |
| Main Organiser Page | ![Main Organiser Page](/01-requirements-solution/uisketches/02-main-organiser.png) |
| Main User Page | ![Main User Page](/01-requirements-solution/uisketches/03-main-user.png) |
| List Accounts Page | ![List Accounts Page](/01-requirements-solution/uisketches/04-list-accounts.png) |
| View Account Page | ![View Account Page](/01-requirements-solution/uisketches/06-view-account.png) |


## Data Outcomes
**READ** - The details of the selected account will be read and displayed

