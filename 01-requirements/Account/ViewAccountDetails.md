# View Account Details

## Description

This use case allows users and administrators to view the details of a specific account.

## Actors

- Administrator
- User
- Organizer

## Triggers

This use case is triggered when a user/administrator/organizer wants to view account information.

## Preconditions
- User/Organizer/Admin is log in.
- The account must exist in the system.

## Postconditions

- The account details are retrieved and displayed.

## Courses of Events

### 01 - Basic Course of Events (User/Organizer/Admin)

#### Course of Events
1. The user navigates to the account settings page and selects button. . **(01-Dashboard Page)**
2. The system displays the account details.**(02 - Account Settings Page)**

#### Related UI Prototypes
| 01- Dashboard Page|
|:-----------------------------------------:|
|![User Dashboard Page](../ui/User_DashBoard.png)|
|![Organizer Dash Page](../ui/Organizer_DashBoard.png)|
|![Admin Dash Page](../ui/Admin_Dashboard.png)|

|         02 - Account Details Page         |
|:-----------------------------------------:|
| ![Account Settings](../ui/User_info.png)  |
|  ![Account Settings](../ui/Org_info.png)  |
| ![Account Settings](../ui/Admin_info.png) |


### 02 - Administrator Views Another User's Account

#### Course of Events
1. The administrator navigates to the account management page 
2. The administrator selects a user account to view.**(01 - Account Management Page)**.
3. The system retrieves and displays the user's account details.**(02 - User Account )**

#### Related UI Prototypes
|             01 - Account Management Page              | 02 - User Account Details |
|:-----------------------------------------------------:|:--:|
| ![Account Management](../ui/Admin_Manage_Account.png) | ![Account Settings](../ui/User_info.png)  |
|                                                       |  ![Account Settings](../ui/Org_info.png)  |
|                                                       |       ![Account Settings](../ui/Admin_info.png)       |
                                                        