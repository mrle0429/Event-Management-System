# Use Case 03 - Update Account

## Courses of Events

### Basic Course of Events - Any User Modifying Their Account

1. The user clicks "edit" to update their account information on the account info page. (10-Admin-information) (22-organizer-information)  (26-user-info)
2. The system displays the account update form.
3. The user enters the new details (username, password, email, etc.)
4. System displays the view account page with updated details


![](/03-design/usecases/images/03-update-account(User).png)

#### Related UI Prototypes
|                     01 - Account info Page                      |
|:-------------------------------------------------------------------:|
| ![](/01-requirements/ui/10-Admin-infomation.png)         |
| ![](/01-requirements/ui/22-organizer-infomation.png)     |
| ![](/01-requirements/ui/26-user-info.png)                |



### Basic Course of Events - Administrator Updates Another User's Account

#### Course of Events
1. The administrator clicks 'edit" to selects a user to update. **(06 - manage-account)**.
2. The System displays the account update page
3. The administrator enters the new details.
4. The system dispkay account info page.(26-user-info)

   ![](/03-design/usecases/images/03-update-account(Admin).png)
#### Related UI Prototypes
|             01 - Account Management Page              |
|:-----------------------------------------------------:|
| ![Account Management](/01-requirements/ui/06-manage-account.png) |