package com.insightdata.sudoku;

/**
 * Utility methods for Sudoku Solver
 *
 */
public class SudokuUtility {
	
	/**
	 * Check if value is a valid value for the cell
	 * @param grid Sudoku board
	 * @param row a row in the board
	 * @param col a column in the board
	 * @param val potential value (1-9)
	 * @return true if value is valid, else false
	 */
	public static boolean validToAdd(int[][] grid, int row, int col, int val) {
		return validInRow(grid, col, val) 
				&& validInCol(grid, row, val)
				&& validInBox(grid, row, col, val);
	}

	/**
	 * Check region (3 x 3 box) constraints
	 * @param grid Sudoku board
	 * @param row a row in the board
	 * @param col a column in the board
	 * @param val potential value (1-9)
	 * @return true if value is valid, else false
	 */
	private static boolean validInBox(int[][] grid, int row, int col, int val) {
		int regionSize = (int) Math.sqrt(grid.length);
		int i = row / regionSize, j = col / regionSize;
		for (int a = 0; a < regionSize; ++a) {
			for (int b = 0; b < regionSize; ++b) {
				if (val == grid[regionSize * i + a][regionSize * j + b]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check row constraints
	 * @param grid Sudoku board
	 * @param col a column in the board
	 * @param val potential value (1-9)
	 * @return true if value is valid, else false
	 */
	private static boolean validInRow(int[][] grid, int col, int val) {
		for (int[] row : grid) {
			if (val == row[col]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check column constraints
	 * @param grid Sudoku board
	 * @param row a row in the board
	 * @param val potential value (1-9)
	 * @return true if value is valid, else false
	 */
	private static boolean validInCol(int[][] grid, int row, int val) {
		for (int col = 0; col < grid.length; ++col) {
			if (val == grid[row][col]) {
				return false;
			}
		}
		return true;
	}
	
}
