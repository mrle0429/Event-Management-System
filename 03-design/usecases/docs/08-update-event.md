# Use Case 08 - Update Event

## 01 - Basic course of events
1. The organiser/administrator selects the relevant event from the display to be updated 
2. The system displays a dialog to enter the event details  
3. The organiser/administrator updates the details of the event and clicks "Save"
4. The system updates the view to show the current events for the specified organiser/administrator, including the newly changed event 

![Use Case Name - Basic Course of Events](/03-design/usecases/images/08-update-event.png)

## 02 - Alternate Course of Events - Do not update
1. The organiser/administrator selects the relevant event from the display to be updated 
2. The system displays a dialog to enter the event details  
3. The organiser/administrator updates the details of the event and clicks "Cancel"
4. The system returns to the List Events page of the application  

In this case, all interactions are completed within the javascript of the view majors page. No request is made to the server and no response is received.

## Related UI Prototypes
| Tile                                            | Page                                                                             |
|-------------------------------------------------|----------------------------------------------------------------------------------|
| 19 - List Events Page - Organiser               | ![List Events Page - Organiser](/01-requirements/ui/19-list-all-events.png)      |
| 20 - List Events Page - Administrator           | ![List Events Page - Administrator](/01-requirements/ui/20-list-owner-events.png) |
| 25 - Update Event Page                          | ![Update Event Page](/01-requirements/ui/25-update-event.png)             |
| 25 - Update Event Dialog - Time and Venue Clash | ![Update Event Dialog - Time and Venue Clash](/01-requirements/ui/25-update-event-timeAndVenueClash.png)                        |