# Create Account

## Description

This use case includes the creation of a new account by. Administrator could create new User/Organiser/Administrator accounts. Users can register a new User account. This includes entering user details such as username, password, email, and other necessary information.

## Actors

- Administrator
- User

## Triggers

This use case is triggered by the following situations:

- The need to create a new account in the system

## Preconditions

- None

## Postconditions

- For User: After the use case is completed, the new account will be created and stored in the system, and the user will be logged in and redirected to their dashboard.
- For Administrator: After the use case is completed, the new account will be created and stored in the system, and the administrator will be notified of the successful creation.
## Course of Events

### 01 - Basic Course of Events

#### Course of Events
1. The user navigates to the account creation page **(01 - Account Creation Page)**
2. The user enters the required account details (username, password, email, etc.)
3. The system validates the entered details
4. The system creates the new account and logs the user in
5. The system redirects the user to their dashboard **(02 - User Dashboard)**

#### Related UI Prototypes
| 01 - Account Creation Page        | 02 - User Dashboard |
|---| ---|
| ![Account Creation Page](ui/.png) | ![User Dashboard](ui/.png) |

### 02  - Administrator Creates Account for Another User

#### Course of Events

1. The administrator navigates to the account creation page and choose the account type **(01 - Account Creation Page)**
2. The administrator enters the required account details for the new user (username, password, email, etc.)
3. The system validates the entered details
4. The system creates the new account
5. The system notifies the administrator that the account has been successfully created

#### Related UI Prototypes
| 01 - Account Creation Page        | 03 - Account Created Notification |
|---| ---|
| ![Account Creation Page](ui/account_creation.png) | ![Account Created Notification](ui/account_created_notification.png) |



## Inclusions
Account Creation

## Data Outcomes
**CREATE** - A new object will be added to the system representing the new account

**READ** - The details of the newly created account will be retrieved