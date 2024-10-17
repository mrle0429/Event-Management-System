# View Account Details

## Description

This use case allows users and administrators to view the details of a specific account.

## Actors

- Administrator
- User

## Triggers

This use case is triggered when a user or administrator wants to view account information.

## Preconditions

- The account must exist in the system.

## Postconditions

- The account details are retrieved and displayed.

## Courses of Events

### 01 - Basic Course of Events

#### Course of Events
1. The user navigates to the account settings page **(01 - Account Settings Page)**.
2. The user selects the option to view their account details.
3. The system retrieves and displays the account details.

#### Related UI Prototypes
|         01 - Account Details Page         |
|:-----------------------------------------:|
| ![Account Settings](../ui/User_info.png)  |
|  :-------------------------------------:  |
|  ![Account Settings](../ui/Org_info.png)  |
|  :-------------------------------------:  |
| ![Account Settings](../ui/Admin_info.png) |


### 02 - Administrator Views Another User's Account

#### Course of Events
1. The administrator navigates to the account management page **(01 - Account Management Page)**.
2. The administrator selects a user account to view.
3. The system retrieves and displays the user's account details.

#### Related UI Prototypes
|             01 - Account Management Page              | 02 - User Account Details |
|:-----------------------------------------------------:|:--:|
| ![Account Management](../ui/Admin_Manage_Account.png) | ![Account Settings](../ui/User_info.png)  |
|                                                       |  ![Account Settings](../ui/Org_info.png)  |
|                                                       |       ![Account Settings](../ui/Admin_info.png)       |
                                                        