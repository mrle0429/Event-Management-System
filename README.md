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

## Part 2
The included pdf has been annotated with the notes I made while I was grading. While I did not have the time to be as thorough as the last part of the assignment, there are a number of notes that I have made that require the detailed explanation that I have provided in the legend below.

* SD When a new object is created, the lifeline of the object should have only started at this point
* SD This activation should have ended when the method returned
* SD This arrow should have been dashed to show that the method was returning
* SD It seems unlikely that this could have been detected and handled in Javascript alone
* SD All of the necessary data was not retreived to be shown on the page
* SD This sequence diagram shows multiple interactions that appear to indicate that the UI is being updated, but there is no user action carried out between them
* SD This method call does not make any sense, you are asking the object to get itself. If you already have the object, then you don't need to ask it, if you don't have the object then you can't ask it…
* SD If you are going to modify/delete an object, first you have to find that object!
* SD  The positioning of the object seems to show that a new object is being created, but the action supposed to be performed is to modify the object
* SD The typical naming for methods like this would be setVariableName
* SD The method related to the display should indicate what page is being shown, in your examples you use the same name
* SD This use case stopped without the method returning
* SD When an object is being constructed, the message name should match the name of the class
* SD This message should have used a dashed arrow to indicate that it was returning, not had the name of another method
* SD When updating the details of an object the individual attributes or associations being changed should each have been shown as a single message
* SD The interactions in this use case would not have taken place until after the user has clicked the submit button. Before this point everything is only happening in the browser and does not need to be shown in the sequence diagram.
* SD This is a very poorly named method
* SD Why is this method returning the data that was passed as a parameter when it was called?
* SD When an object is being constructed, it should appear at that point in the diagram with it's lifeline extending from there downwards.
* SD This method call is never returned by the system
* SD If an object is being created by the system, then you need to show that process in the sequence diagram
* SD you have used the same initial method in different use cases and shown a different result

In addition, there is also an included class file that was generated based on the sequence diagrams that you created. This serves to allow you to compare the methods in the class diagram you submitted against the methods in the generated diagram for reference. 
