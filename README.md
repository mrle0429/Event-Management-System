# Feedback Legend

## Part 1
The included pdf has been annotated with the notes I made while I was grading. The following is a legend for the annotations I made.

### Use Case Diagram
* UD1: Hierarchical connections in use case diagram

### Use Case Naming
* UN1: Some of the use case names could have been shortened or simplified

### Use Cases
* UC1: The use of includes relationships in the diagram was not necessary
* UC1: The includes relationships were oriented in the wrong direction

### Courses of Events
* CEN1: An action performed by the system was missing from the course of events
* CEN2: The course of events had multiple consecutive steps performed by the either the user or the system
* CEN3: A step in your course of events described what is happening internally within the system. All descriptions here should be only about the outcome, not how something happened.
* CEN4: When describing an alternate or exceptional course of events, all of the steps should be included, not just the steps that are different
* CEN5: It is not clear how the user got to the first point in this use case
* CEN6: This is not an alternate course of events, it should be a separate use case.
* CEN7: The course of events is discussing interactions that take place outside of the software
* CEN8: The course of events ends without completion

### Data Outcomes
* DO1: Data outcomes should also have included the READ outcome
* DO2: This use case should have resulted in an object being created and remembered by the system and as such should have had the CREATE outcome
* DO3: Data outcomes were not included in the use case description
* DO4: This should have resulted in an object being modified and as such should have included the UPDATE outcome

### Domain Model:
* DM1: You have included associations between objects that are not related to the data that these concepts remember, but instead is related to the functionality of the system. This should not be in the domain model.
* DM2: You have included attribute(s) that are objects within the system, these should only be shown through the use of associations. 
* DM3: You have used composition incorrectly
* DM4: Associations should have included role names
* DM5: This object is representing multiple contradictory responsibilities
* DM6: Including attributes like ids should not be done at this point in the design process. These are not an aspect of the concepts we are representing, instead they are a technical requirement we may require later if we intend to use a relational database to remember our data.
* DM7: Associations should have included multiplicities