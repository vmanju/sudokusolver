package com.insightdata.sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Main class for Sudoku Solver
 * Solves a classic 9 x 9 Sudoku board by 
 * assigning numbers one by one after validating
 * if the same number is not present in current row/col/3X3 subgrid.
 * If the assignment does not lead to a solution, then we backtrack
 * and next next number for the current cell
 * If none of the numbers (1-9) work then we return false.
 *
 */
public class SudokuSolver {

	/**
	 * Solve the partially filled Sudoku board
	 * @param grid Sudoku board
	 * @return true if the board can be solved, false otherwise
	 */
	public static int[][] solveSudoku(int[][] grid) {
		if (!SudokuValidator.isValidSudoku(grid)) {
			System.out.println("Initial configuration violates constraints.");
		}

		if (solveSudokuHelper(grid, 0, 0)) {
			for (int[] element : grid) {
				System.out.println(Arrays.toString(element));
			}		
		} else {
			System.out.println("No solution exists.");
		}
		
		return grid;
	}

	/**
	 * Helper method to solve the sudoku
	 * Uses backtracking technique to fill the board with valid entries
	 * @param grid Sudoku board
	 * @param row a row in the sudoku board
	 * @param col a col in the sudoku board
	 * @return
	 */
	private static boolean solveSudokuHelper(int[][] grid, int row, int col) {
		if (row == grid.length) {
			row = 0; // Starts a new row
			if (++col == grid[row].length) {
				return true; // Entire grid has been filled without conflict
			}
		}

		// Skips nonempty entries
		if (grid[row][col] != 0) {
			return solveSudokuHelper(grid, row + 1, col);
		}

		for (int val = 1; val <= grid.length; ++val) {
			// If value looks potentially right, tentatively assign
			if (SudokuUtility.validToAdd(grid, row, col, val)) {
				grid[row][col] = val;
				// Return if success
				if (solveSudokuHelper(grid, row + 1, col)) {
					return true;
				}
			}
		}

		// If unsuccessful, undo assignment
		grid[row][col] = 0;
		
		return false;
	}

	
	/**
	 * Parse the input csv file into a grid
	 * @param filename input csv file path
	 * @return
	 */
	private static int[][] parseGrid(String filename){
	
		BufferedReader fileReader = null;
        final String DELIMITER = ",";
        int[][] grid = new int[9][9];
        
		try {
			String line = "";
            fileReader = new BufferedReader(new FileReader(filename));
            int row = 0, col = 0;
            //Read the file line by line
            while ((line = fileReader.readLine()) != null && row < 9) {
            	col = 0;
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                for(String token : tokens) {
                    grid[row][col++] = Integer.parseInt(token.trim());
                }
                row++;
            }
            fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception: " + filename);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception with file: " + filename);
			e.printStackTrace();
		}
		return grid;
		
	}
	
	/**
	 * Write the solved sudoku grid to the output csv file
	 * @param filename output csv file path
	 * @return
	 */
	/*private static int[][] writeGridToOutput(int[][] grid, String filename){
		
	}*/
	
	
	public static void main(String[] args) {
		int[][] inputGrid = parseGrid(args[0]);
		int[][] outputGrid = solveSudoku(inputGrid);
		//writeGridToOutput(outputGrid, args[1]);
	}

}
