# A11_WS2020

### _Preamble_

* Submission deadline: 26.01.2021, 12:00 (Noon)
* Begin your work by carefully reading the assignment below as well as the starter code (main and test), including the comments therein.
* You may add your own classes and packages. The root package must always be `a<x>se2020ws`, where `<x>` is the assignment number.
* You are expected to add code to some existing methods in the starter code, in particular everything marked with TODO.
* You may add new attributes and methods to existing classes.
* Do not modify the contents of the `.github` folder and `pom.xml` file in your repo.
* To start the provided demo application, run ```mvn clean javafx:run```.

Assignment 11: MVC,Observer (Book-Manager)
----------------------------------------------

Carefully read the Observer chapter in [1].

Implement a small Book-Manager application that has a graphical-user-inferface (GUI) based on JavaFX [2].
The application manages a list of books (title, author, year, isbn, ...). It supports adding, removing, editing of books.

The application shall follow the Model-View-Controller (MVC) architecture.

Particularly, the model of your application must not depend on any JavaFX-specific class.
Use the Observer design pattern to decouple the logic of your application (Model) from the View.

## Task 1 (Model and View) -- 50%

The **model** of your application is responsible for managing a collection of books.
A book comprises at least the following information (attributes): title, author, year, isbn-number.
Supported operations on the data must include
* adding a new book
* removing an existing book
* editing book attributes
* listing all books

Books must have unique isbn-numbers, i.e., there must not be two books with the same isbn.

Write a GUI for the model. This **view** shall be based on JavaFX. 
Implement a list (or table) that displays all the books (with all their properties).

On every change in the model, the view must be updated! Use the observer pattern to decouple the model and the view.
Go for the push-approach; encapsulate the broadcasted details.

Separate the model classes from the remaining classes by using different java packages.

Note: The model part of your application must be able to exist without any GUI components. 
Therefore, the actual list of books is part of the model, not part of a class that relates to the GUI.
Also, make sure to have no JavaFX imports in the whole model package.

## Task 2 (Controller) -- 30%

Implement the controller part of the application and properly integrate all the components to form a complete application.

The GUI shall provide a toolbar containing at least the buttons 'Add', 'Remove' to add and remove a book, respectively.
Take care of the state of the buttons (e.g., 'Remove' shall be disabled when no book is selected).
Also add support for editing (either via a button in the toolbar, or with another mechanism).

* 'Add' asks for the data of a new book and adds it (use some simple input mechanism, e.g., a dialog).  
* 'Remove' removes the book that is currently selected in the list/table.
* 'Edit' shall change, for example, the title; you may also allow the other properties to be changed.

Note that you might require exception handling in the button-handlers.


## Task 3 (Tests) -- 20%

Write unit tests for your application.




To start the provided demo application, run ```mvn clean javafx:run```.


[1] Design Patterns. Elements of Reusable Object-Oriented Software; Gamma et al.  
[2] https://openjfx.io/


