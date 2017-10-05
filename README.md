# Sliding-block-puzzle-solver
The goal of this project is to create a program, Solver.java, that will solve a Klotski puzzle (if a solution exists) with a give goal baord configuration in as little time as possible.

Process
----------
In our process for solving this game, we designed a few sample boards and goals ourselves and created a rudimentary Solver test class that would simply print our hand-written solutions so that we could test whether or not our intuition with regards to the program setup was correct.
 
### steps:
    (1) 
    - read configuration files
    
    (2) 
    - intialize boards & store configuration
    
      (2.a)
      - validate size. i.e. do pieces "fit" on the board
    
    (3)
    - check if goal is reached; if not, 
    - manipulate the initial configuration of the board by making a legal move & keep track of the moves
    
### Board
    - each individual piece on the board is stored as an array
    - dimensions of the board
    - is board valid
    - what are the possible moves? (as an iterator?)
    - baord is stingified?
    
    
### Solver
    - create hashset to keep track of seen boards
    - create main iteration method to traverse through all possible configureation
    - isGoal to check if a given config is "done"
    - keeping track of different pathways to be able to present solution
    - consideration of various "special" scenarios
    
