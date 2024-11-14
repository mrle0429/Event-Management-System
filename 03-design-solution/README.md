# Design Sample Solution

This currently contains only 4 realised sequence diagrams. These are examples of each of the core CRUD operations that are happening in the system. 

## Example Seqquence Diagrams

### 05 - Register Account - CREATE Operation

![Register Account Sequence Diagram](/03-design-solution/usecases/images/05-register-account-basic.png)

- Note how the newly created account object is added to the model object so that the information it contains can be used on the main page of the application.

### 03 - View Account - READ Operation

![View Account Sequence Diagram](/03-design-solution/usecases/images/03-view-account-basic.png)

- This example is somewhat complicated by the fact that there are three different types of accounts. This results in multiple different repositories being queried to find the account object. 
- This object is then added to the model so that the information it contains can be displayed on the view account page.

### 04 - Modify Account - UPDATE Operation

![Modify Account Sequence Diagram](/03-design-solution/usecases/images/04-modify-account-basic.png)

- This is the most complex sequence diagram because it involves multiple interactions. The first interaction is used to show the page for editing the account. The second interaction is used to save the changes made to the account.
- A consequence of using a DTO in the second part of the sequence diagram is that it must be constructed and added to the model during the first part of the sequence diagram. If you are not planning to use a DTO, then this step can be omitted.

### 20 - Delete Account - DELETE Operation

![Delete Account Sequence Diagram](/03-design-solution/usecases/images/20-delete-account-basic.png)

- Again, this is somewhat complicated by the fact that there are three different types of accounts. 