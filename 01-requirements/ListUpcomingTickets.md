# List Upcoming Tickets

**Use Case Name:** List Upcoming Tickets

**Primary Actor:** User

**Brief Description:** This use case includes the process of displaying all upcoming tickets that a user has purchased for future events. It provides an organized overview of purchased tickets, including relevant event details such as event name, date, time, venue, and ticket type. Additionally, users can filter tickets based on different criteria for easier management.

**Actors:**
- User

**Triggers:**
- The user wishes to view their upcoming event tickets.
- The user navigates to the "My Tickets" section of the application.

**Preconditions:**
- The user must be logged in to the system.
- The user must have purchased at least one ticket for an upcoming event.

**Postconditions:**
- The system displays a list of all upcoming tickets purchased by the user, including relevant event details.

**Courses of Events**

### 01 - Basic course of events
#### Course of Events
1. User navigates to the "My Tickets" section of the application.
2. The system retrieves all upcoming tickets associated with the user's account.
3. The system displays a list of upcoming tickets, including the event name, date, time, venue, and ticket type.
4. User can apply filters to view specific tickets based on event type, venue, or date.

#### Related UI Prototypes
| My Tickets Page |
| --- |
| ![My Tickets Page](ui/mytickets.png) |

### 02 - Alternate course of events - No Upcoming Tickets
#### Course of Events
1. User navigates to the "My Tickets" section of the application.
2. The system checks if there are any upcoming tickets associated with the user's account.
3. If no upcoming tickets are found, the system displays a message indicating that there are no upcoming tickets.

#### Related UI Prototypes
| My Tickets Page - No Tickets |
| --- |
| ![My Tickets Page - No Tickets](ui/notickets.png) |

**Special Requirements:**
- The system should display the tickets in chronological order based on the event date.
- The user interface should allow the user to filter tickets based on event type or venue to easily manage their upcoming events.
- The system should be responsive, allowing users to comfortably view and manage their tickets on different devices.

**Inclusions:**
- Display Upcoming Tickets
- Filter Tickets

**Data Outcomes**
**READ** - The details of all upcoming tickets for the user will be retrieved.
**FILTER** - The list of tickets can be filtered by the user to show only specific categories.

**Primary Actor's Goal:** To view and manage upcoming events for which the user has purchased tickets.