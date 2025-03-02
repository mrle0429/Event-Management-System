# View Details Event

## Description

This use case includes viewing the details of a specific events

## Actors

Administrators
Organisers

## Triggers

The need to check the details of a specific events

## Preconditions

- The list events use case was executed
- The event exists in the system

## Postconditions

After the use case is completed, the organiser/administrator will see all relevant details about the event

## Course of Events

### 01 - Basic Course of Events

#### Course of Events
1. The organiser/administrator selects the event he wants to view **(01 - List Events Page - Organiser)** **(02 - List Events Page - Administrator)**
2. The system retrieves and displays the event details, including the name, description, date, time, venue, organiser, and ticket information(Details about tickets include the type, price, number available, and number sold for each type of ticket) **(03 - View Details Event Page)**

#### Related UI Prototypes
| 01 - List Events Page - Organiser                             | 02 - List Events Page - Administrator| 03 - View Details Event Page                         |
|---------------------------------------------------------------|--------------------------------------|------------------------------------------------------|
| ![List Events Page - Organiser](../ui/ListEvents(Organiser).png) | ![List Events Page - Administrator](../ui/ListEvents(Administrator).png)| ![ View Details Event Page](../ui/ViewDetailsEvent.png) |

## Inclusions
List Events

## Data Outcomes
**READ** - The system retrieves and displays the details of the selected event
