# List Accounts

## Description

This use case allows administrators to view a list of all user accounts in the system.

## Actors

- Administrator

## Triggers

This use case is triggered when an administrator wants to view all user accounts.

## Preconditions

- The Administrator has login its account.

## Postconditions

- A list of user accounts is displayed.

## Courses of Events

### 01 - Basic Course of Events

#### Course of Events
1. The administrator navigates to the account management page .**(01- Admin Dash Page)**
2. The system retrieves and displays a list of all user accounts.**(02 - Account Management Page)**

#### Related UI Prototypes
| 01 - Admin Dash Page|
| ---|
| ![Admin Dash Page](../ui/Admin_Dashboard.png)

| 01 - Account Management Page              |
|-----------------------------------------------------|
| ![Account Management](../ui/Admin_Manage_Account.png) |

---

## Inclusions

Account Creation, Account Update, Account Deletion

## Data Outcomes

**READ** - A list of user accounts is retrieved from the database.
