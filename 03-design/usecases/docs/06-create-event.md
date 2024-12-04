# Use Case 06 - Create Event

## 01 - Basic Course of Events

1. User navigates to create event page and enters event details
2. System displays updated event list including the new event

![Use Case Name - Basic Course of Events](/03-design/usecases/images/06-create-event.png)

## 02 - Alternate Course of Events - Booking Clash

1. The organiser/administrator enters the event details and clicks "Create"
2. The System return create event page with errorMessage

![Use Case Name - Basic Course of Events](/03-design/usecases/images/06-create-event-bookingClash.png)

## 03 - Alternate Course of Events - Do not create
1. The organiser/administrator selects the create event functionality 
2. The system displays a dialog to enter the event details 
3. The organiser/administrator enters the event details (or not) and clicks "Cancel"
4. The system returns to the List Events page of the application  

In this case, all interactions are completed within the javascript of the view majors page. No request is made to the server and no response is received.

## Related UI Prototypes
| Tile                                  | Page                                                                             |
|---------------------------------------|----------------------------------------------------------------------------------|
| 19 - List Events Page - Organiser     | ![List Events Page - Organiser](/01-requirements/ui/19-list-all-events.png)      |
| 20 - List Events Page - Administrator | ![List Events Page - Administrator](/01-requirements/ui/20-list-owner-events.png) |
| 13 - Create Event Dialog           | ![Create Event Dialog](/01-requirements/ui/13-create-event.png)             |
| 13 - Create Event Dialog - Time and Venue Clash                   | ![Create Event Dialog - Time and Venue Clash](/01-requirements/ui/13-create-event-timeAndVenueClash.png)                        |