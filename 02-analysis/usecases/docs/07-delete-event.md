# Use Case 07 - Delete Event

## 01 - Basic Course of Events
1. The Administrator selects the remove events functionality of the event to be deleted 
2. The system asks the Administrator to confirm 
3. The Administrator selects **Yes**
4. The system updates the display to show the current events, with the selected event removed 

![Use Case Name - Basic Course of Events](/02-analysis/usecases/images/07-delete-event.png)

## 02 - Alternate course of events - Do Not Delete
1. The Administrator selects the remove events functionality of the event to be deleted 
2. The system asks the Administrator to confirm 
3. The Administrator selects **No**
4. The system returns to the List Events page of the application 

In this case, all interactions are completed within the javascript of the view majors page. No request is made to the server and no response is received.

## Related UI Prototypes
| Tile                                            | Page                                                                             |
|-------------------------------------------------|----------------------------------------------------------------------------------|
| 20 - List Events Page - Administrator           | ![List Events Page - Administrator](/01-requirements/ui/20-list-owner-events.png) |
| 16 - Delete Events - Confirmation Dialog        | ![Delete Events - Confirmation Dialog](/01-requirements/ui/16-delete-event.png)             |