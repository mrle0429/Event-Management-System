# Use Case 01 - Create Account

## 01 - Basic Course of Events - user
1. The user clicks the "Sign Up" button on the login page. (01- Login Page)
2. The system navigates to the account creation page. (02 - register page)
3. The user enters the required account details (username, password, email, etc.) 
4. The system redirects the user to their dashboard. (03 - User Dash Page)

![Use Case Name - Basic Course of Events](/02-analysis/usecases/images/01-create-account-user-basic.png)

## 02 - Alternative Courses of Events - Account Already Exists

1. The user clicks the "Sign Up" button on the login page. 
2. The system navigates to the account creation page. (02 - register page)
3. The user enters the required account details (username, password, email, etc.) 
4. The system displays an error message indicating that the account already exists (02 - register account exist)
5. The user clicks ok.
6. The sysrem return to the account creation page. (02 - register page)

![Use Case Name - Basic Course of Events](/02-analysis/usecases/images/01-alternate-account-exist.png)

## 03 - Basic Course of Events - Admin
1. The administrator select create account button(3 types). **(06-manage account)**
2. The system navigates to the account creation page.  **(07-create admin/ 08-create user / 09-create organiser )**
3. The administrator enters the required account details for the new user/Organizer/admin. 
4. The system renturn to account manage page.

## 04 - Alternative Courses of Events - Account Already Exists
1. The administrator select create account button(3 types). **(06-manage account)**
2. The system navigates to the account creation page. **(07-create admin/ 08-create user / 09-create organiser )**
3. The administrator enters the required account details for the new user/Organizer/admin. 
4. The system displays an error message indicating that the account already exists. **(08-create user exists)**
5. The administrator clicks OK
6. The system returns to the create account page. 
## Related UI Prototypes
| Tile | Page |
| --- | --- |
| 01 - login Page | ![Login Page](/01-requirements/ui/01-login-page.png) |
| 02 - register Page | ![Account Creation Page](/01-requirements/ui/Register_Page.png) |
| 02 - register account exist| ![account exist](/01-requirements/ui/register-page-account-exist.png)|
| 03 - user Dash Page | ![User Dashboard](/01-requirements/ui/User_DashBoard.png) |
| 06 - manage account    |  ![Account Management Page](/01-requirements/ui/06-manage-account.png) | 
| 07 - createAdmin Page    | ![CreateAdmin Page](/01-requirements//ui/07-create-admin.png) | 
| 08 - createUser Page|![CreateUser Page](/01-requirements/ui/08-create-user.png)|
| 08 - createUser exists| ![](/01-requirements/ui/08-create-user-exists.png)|
| 09 - createOrganizer Page| ![CreateOrganizer Page](/01-requirements/ui/09-create-organiser.png)|
