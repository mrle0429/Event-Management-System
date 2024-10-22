# Use Case 01 - Create Account

## Description

Allows the administrator to create a new administrator or organiser account

## Actors

Administrator

## Triggers

This use case is triggered when a new user account is needed

## Preconditions

- The is on the administrator main page (01-main-admin) or the list accounts page (04-list-accounts)

## Postconditions

- The user is returned to the view account page of the system (06-view-account) which shows the details of the created account

## Courses of Events

### Basic Course of Events

1. The user selects the create account functionality
2. The system displays an input form to get the required details (details required are based on the type of account being created) - 05-create-account
3. The user enters the details of the account
4. The system displays the view account page with the details of the created account shown - 06-view-account

### Alternative Courses of Events - Account Already Exists

1. The user selects the create account functionality
2. The system displays an input form to get the required details (details required are based on the type of account being created) - 05-create-account
3. The user enters the details of the account
4. The system displays an error message indicating that the account already exists
5. The user clicks OK
6. The system returns to the create account page - 05-create-account

### Alternative Courses of Events - Missing Details

1. The user selects the create account functionality
2. The system displays an input form to get the required details (details required are based on the type of account being created) - 05-create-account
3. The user enters the details of the account
4. The system displays an error message indicating that some details are missing
5. The user clicks OK
6. The system returns to the create account page - 05-create-account

### Extension Points

None

## Inclusions

None

## Relevant UI Sketches
| Page Name | Image |
|----|------|
| Admin Main Page | ![Admin Main Page](/01-requirements-solution/uisketches/01-main-admin.png) |
| Create Account Page | ![Create Account Page](/01-requirements-solution/uisketches/05-create-account.png) |
| View Account Page | ![View Account Page](/01-requirements-solution/uisketches/06-view-account.png) |
| Duplicate Email Message | ![Duplicate Email Message](/01-requirements-solution/uisketches/00-start-duplicate.png) |
| Missing Details Message | ![Missing Details Message](/01-requirements-solution/uisketches/05-create-account-missing.png) |

## Data Outcomes
**CREATE** - A new account will be created and added to the system

**READ** - The details of the newly created account will be read and displayed
