# Use Case 04 - Modify Account

## Description

Allows the users to change the details of an account in the system. Note that the email address cannot be changed.

## Actors

User

## Triggers

This use case is triggered when the details of an account need to be changed

## Preconditions

- The administrator is on the list accounts page (04-list-accounts)
- Any user is on the view account page (06-view-account)

## Postconditions

- The user is shown the view account page (06-view-account) with the updated details of the account

## Courses of Events

### Basic Course of Events - Administrator Modifying Other Accounts

1. The administrator selects the modify account functionality adjacent to the account to view on the list accounts page (04-list-accounts) 
2. The system displays the modify account page (07-modify-account) which shows the details of the selected account as editable fields
3. The administrator enters the updated details of the account
4. The system displays the view account page (06-view-account) with the updated details of the account

### Basic Course of Events - Any User Modifying Their Account

1. The user selects the modify account functionality on the view account page (06-view-account)
2. The system displays the modify account page (07-modify-account) which shows the details of the selected account as editable fields
3. The user enters the updated details of the account
4. The system displays the view account page (06-view-account) with the updated details of the account

### Alternate Course of Events - Invalid Password

1. The administrator or user selects the modify account functionality
2. The system displays the modify account page (07-modify-account) which shows the details of the selected account as editable fields
3. The administrator or user enters the updated details of the account
4. The system displays a warning message indicating that the password is invalid
5. The user selects OK and returns to step 2 in the basic course of events


### Extension Points

None

## Inclusions

None

## Data Outcomes
- **READ** - The details of the selected account will be retrieved and displayed
- **UPDATE** - The details of the selected account will be changed


## Relevant UI Sketches
| Page Name | Image |
|----|------|
| List Accounts Page | ![List Accounts Page](/01-requirements-solution/uisketches/04-list-accounts.png) |
| View Account Page | ![View Account Page](/01-requirements-solution/uisketches/06-view-account.png) |
| Modify Account Page | ![Modify Account Page](/01-requirements-solution/uisketches/07-modify-account.png) |
| Modify Account Page - Error | ![Modify Account Page - Error](/01-requirements-solution/uisketches/07-modify-account-password.png) |


