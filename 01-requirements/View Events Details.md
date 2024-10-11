# View Event Details

## Description

This use case allows users and organizers to view detailed information about a specific event.

## Actors

Users, Organizers

## Triggers

This use case is triggered when a user or organizer selects an event to view more details.

## Preconditions

- The event must exist in the system.

## Postconditions

None

## Courses of Events

### 01 - Basic course of events
#### Course of Events
1. The user selects a specific event from the event list.
2. The system fetches detailed information about the event.
3. The user sees the event details, including name, description, date, time, venue, organizer information, and ticket details.
4. The user can choose to buy a ticket or share the event.

#### Related UI Prototypes
| 02 - Event Details Page |
|:--:|
| ![Event Details](ui/EventDetails.png) |

### 02 - Alternate course of events - Event Not Found

#### Course of Events
1. The user selects an event from the event list.
2. The system detects that the event no longer exists.
3. The system displays an error message informing the user that the event is not found. The use case ends.

#### Related UI Prototypes
| 02 - Error Page |
|:--:|
| ![Error](ui/Error.png) |

## Inclusions

Buy Ticket

## Data Outcomes

**READ** - Event details are retrieved from the database.
