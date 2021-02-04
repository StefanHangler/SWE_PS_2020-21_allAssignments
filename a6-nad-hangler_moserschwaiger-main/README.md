# A6_WS2020

Assignment 6: The Strategy Design Pattern
----------------------------------------------

Read the provided starter code.

Implement the given interfaces as described in the comments therein.

Use the Strategy Design Pattern [1]. 

The following path types must be supported:
* **min**: a shortest path with a minimum number of mines on it
* **max**: a shortest path with a maximum number of mines on it

(**Bonus**) Additionally, the following path type shall be supported: 
* **safest**: a shortest path, that is 'safest'. A path is safest if every other path gets closer to some mine (or at least as close), including diagonal distance.

See the provided .pdf file for examples.

It is expected that more path types will be added in the future.


[1] Design Patterns - Elements of Reusable Object-Oriented Software; Erich Gramma et al.; Addison-Wesley

Example
------------------
Input:
 ```
6 7
..*.*..
..*..*.
.......
.......
.......
....*..

 ```
Code snippet:

 ```java
        MineField mField = yourFieldGeneratorImpl.loadFieldFromFile(infile);
        Cell c00 = mField.getCell(0,0);
        Cell c14 = mField.getCell(1,4);
        Cell c36 = mField.getCell(3,6);
               
        int distance1 = mField.getShortestDistance(c00, c14); // should return 4
        int distance2 = mField.getShortestDistance(c00, c36); // should return 8

        List<Cell> path;
        
        //TODO: instruct mField that we are interested in 'min' paths.
        
        path = mField.getShortestPath(c00, c14); 
        // (1,0)->(1,1)->(1,2)->(1,3) has one mine
        // any shortest path with one mine on it is ok
        
        path = mField.getShortestPath(c14, c36); 
        // (2,4)->(3,4)->(3,5) has no mine. Others have also no mine.
        
        //TODO: instruct mField that we are interested in 'max' paths.
        
        path = mField.getShortestPath(c00, c14); 
        // (0,1)->(0,2)->(0,3)->(0,4) is one of the shortest paths with two mines
        
        path = mField.getShortestPath(c00, c36);
        // there are several paths with 3 mines, one of them is
        // (0,1)->(0,2)->(0,3)->(0,4)->(0,5)->(1,5)->(1,6)->(2,6)
       
       
        // for the bonus part:
        
        //TODO: instruct mField that we are interested in 'safest' paths.
        
        path = mField.getShortestPath(c00, c36);
        // only one path is two steps away from any mine:
        // (1,0)->(2,0)->(3,0)->(3,1)->(3,2)->(3,3)->(3,4)->(3,5)
        // any other shortest path has a cell that is at most 1 step from a mine

```
