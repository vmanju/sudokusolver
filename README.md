sudokusolver
============

SudokuSolver solves a classic 9 x 9 Sudoku board by 
assigning numbers one by one after validating
if the same number is not present in current row/col/3X3 subgrid. 
If the assignment does not lead to a solution, then we backtrack 
and next next number for the current cell. 
If none of the numbers (1-9) work then we return false.


Main class:
===========
SudokuSolver.java

Command line: 
=============
./SudokuSolver input.csv output.csv

Command line args:
==================
args[0]: input csv filepath

args[1]: output csv filepath


Additional classes:
===================
SudokuValidator.java

SudokuUtility.java

Reference:
==========
Peter Norvig's Sudoku page: http://norvig.com/sudoku.html
