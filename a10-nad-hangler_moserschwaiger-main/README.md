# A10_WS2020

### _Preamble_

* Submission deadline: 12.01.2021, 12:00 (Noon)
* Begin your work by carefully reading the assignment below as well as the starter code (main and test), including the comments therein.
* You may add your own classes and packages. The root package must always be `a<x>se2020ws`, where `<x>` is the assignment number.
* You are expected to add code to some existing methods in the starter code, in particular to auto-generated stub methods. 
* You may add new attributes and methods to existing classes.
* You may not change definitions of existing classes, attributes, and methods in the starter code.
* Do not modify the contents of the `.github` folder and `pom.xml` file in your repo.
* All tests will fail at the beginning, which is normal.
* If you want to run all tests locally, make sure that you have Java 14 (at least) installed, and execute in the repo folder the command: `mvn clean test`
* You can run selected test classes and methods by executing `mvn -Dtest=TestClassToRun test` or `mvn -Dtest=TestClass#TestMethodToRun test`
* The test cases that are run automatically through github can be seen in the file `.github/classroom/autograding.json`.

--------------------------------

## Assignment 10: Text Encoding  (with Decorator and Singleton design patterns)
--------------------------------

Implement a small framework for reading and writing encoded text.

Write two implementations of the interface `Translator`:
- MorseTranslator, that provides decoding of the Morse code to letters [a-z]. Assume that letters in the Morse code are separated by one space and words are separated by the character '|' (with no spaces around it). See also [3,4].
- ROT13Translator, which provides ROT13 encoding. Only characters representing letters in the Latin alphabet should be encoded, with their case preserved. All other characters remain unchanged.

Implement your own translation algorithms, without using an existing Map implementation. 

Implement the interface `A10Encoding` by using the Decorator design pattern on existing classes from the `java.io` package. Use also your implementations of the Translator interface. 
For example, a file containing the Morse code ``.... . .-.. .-.. ---|-.. .- -..`` will be read as ``hello dad``, and the string ``Why me?`` should be written in ROT13 as ``Jul zr?``.

Can you spot other implementations of the Decorator pattern in the `java.io` package?

Moreover, use the Singleton design pattern to enforce the rule that all reader objects returned by the implementation of `A10Encoding` employ the same MorseTranslator object, and all writer objects employ the same ROT13Translator object.

The Decorator and Singleton design patterns can be found in [1]. For an overview of the ``java.io`` package, see [2]. Make sure that each one of your two decorator classes decorates an existing Java class from the ``java.io`` package. Note: the assignment is about CHARACTER streams.

Testing: Implement the methods specified in the test classes, and add more test methods as you see fit. Do not modify the existing methods `testMorse_1` and `testROT13_1`. Note that other (remote) test cases will be run in addition to the ones in your local test folder.

Be prepared for questions: UML, pros/cons, requirements, ...

Bonus: Provide an efficient implementation of the Translator (avoid excessive use of string comparisons). 

[1] Design Patterns. Elements of Reusable Object-Oriented Software; Gamma et al.  
[2] https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/io/package-summary.html  
[3] https://en.wikipedia.org/wiki/Morse_code  
[4] https://raw.githubusercontent.com/jvcleave/example-ofFile-MorseCode/master/bin/data/morse.csv  

------------------------------------------
### _Evaluation_

* The Reader part (`MorseTranslator` and implementations of the Reader methods in `A10Encoding`) weights  at most 60%.

* The Writer part (`ROT13Translator` and implementations of the Writer methods in `A10Encoding`) weights at most 40%.

* Testing is worth 5% per part. For example, the Reader part without testing gets at most 55%.
