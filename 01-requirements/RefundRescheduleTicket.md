# Refund/Reschedule Ticket

**Use Case Name:** Refund/Reschedule Ticket

**Primary Actor:** User

**Brief Description:** This use case involves allowing the user to request a refund or reschedule a purchased ticket for an event. Users can choose to cancel their ticket and request a refund or select a different date/time if the event allows rescheduling. The system processes the request and updates the user's ticket status accordingly.

**Actors:**
- User

**Triggers:**
- The user wishes to cancel a ticket and request a refund.
- The user wishes to change the date/time of a ticketed event.

**Preconditions:**
- The user must be logged in to the system.
- The user must have purchased at least one ticket for an event.
- The event must allow rescheduling or refunds.

**Postconditions:**
- The system updates the ticket status to refunded or rescheduled.
- The system updates the availability of tickets for the event.

**Courses of Events**

### 01 - Basic course of events - Request Refund
#### Course of Events
1. User navigates to the "My Tickets" section of the application.
2. User selects the ticket they wish to refund.
3. The system checks if the ticket is eligible for a refund.
4. If eligible, the system provides a "Request Refund" option.
5. User selects the "Request Refund" option.
6. The system processes the refund and updates the ticket status to "Refunded."
7. The system notifies the user of the successful refund.

#### Related UI Prototypes
| Refund Ticket Page |
| ------------------- |
|                     |

### 02 - Basic course of events - Reschedule Ticket
#### Course of Events
1. User navigates to the "My Tickets" section of the application.
2. User selects the ticket they wish to reschedule.
3. The system checks if the ticket is eligible for rescheduling.
4. If eligible, the system provides a "Reschedule" option.
5. User selects the "Reschedule" option and chooses a new date/time from the available options.
6. The system processes the rescheduling and updates the ticket details.
7. The system notifies the user of the successful rescheduling.

#### Related UI Prototypes
| Reschedule Ticket Page |
| ----------------------- |
|                         |

### 03 - Alternate course of events - Not Eligible
#### Course of Events
1. User navigates to the "My Tickets" section of the application.
2. User selects the ticket they wish to refund or reschedule.
3. The system checks if the ticket is eligible for a refund or rescheduling.
4. If not eligible, the system displays a message indicating that the ticket cannot be refunded or rescheduled.

#### Related UI Prototypes
| Refund/Reschedule Ticket Page - Not Eligible |
| -------------------------------------------- |
|                                              |

**Special Requirements:**
- The system should clearly indicate which tickets are eligible for refunds or rescheduling.
- The system should be responsive, allowing users to comfortably manage their tickets on different devices.
- Refunds and rescheduling options may vary based on event policies.

**Inclusions:**
- Request Refund
- Reschedule Ticket

**Data Outcomes**
**READ** - The details of the selected ticket will be retrieved.
**UPDATE** - The ticket status will be updated to reflect the refund or new schedule.

**Primary Actor's Goal:** To cancel a ticket and receive a refund or to change the date/time of an event they have purchased a ticket for.

