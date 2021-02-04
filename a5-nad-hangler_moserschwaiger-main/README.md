# A5_WS2020
A5 (Testing, Junit)

Assignment 5: Unit testing, code coverage
----------------------------------------------

## **JUnit and some background**
### Task 1 (40 %)
1.  What is test driven development (TDD), unit testing?
1.  Get familiar with JUnit 5 (www.junit.org); (use version 5, which is based on Java annotations).
1.  Test your stack (from Assignment 3).
	* Extend your stack implementation by a getSize() method, also make sure to throw appropriate exceptions when you perform a pop() on an empty stack, for example.
   	* Write JUnit tests to test all your stacks. Use the special features for testing for expected exceptions in JUnit. Make sure that all your `public` and `protected` methods get tested.
    * Note: You have two stack classes, but you must not duplicate your test code in order to test both stacks! Find out a suitable strategy for tesing both stacks without duplicating code (there are junit features for this).

### Task 2 (30 %)
1.	Test the calculator (from Assignment 3).
    * Write several tests for the RPN calculator.
    * Write also tests to check the behavior on invalid input.

## **Code coverage analysis**
### Task 3 (30 %)
1. Try to have your whole code of Assignment 3 covered by unit tests.
1. Find and try a code coverage analysis tool (e.g. Eclipse plug-in).
1. Analyze the coverage of your code and improve your tests if necessary.
1. Make a screenshot of the coverage analysis tool that documents your efforts and submit the image (jpeg).

**Required submissions (git):**
* the .java file(s) for the stacks and the calculator:
 ```
 src/main/assignment3_int/Calculator.java  
         /stud1_stud2/YourStackClass1.java, ...
```
* the .java file(s) for the junit-tests (note: not in ``main``, but in ``test``):
 ```
src/test/stud1_stud2/YourTestClass1.java, ...
```
* the screenhot of the coverage analysis:
```
stud1_stud2.jpg
```
