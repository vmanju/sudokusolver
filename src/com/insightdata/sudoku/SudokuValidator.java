package com.insightdata.sudoku;

/**
 * Validator class to validate the initial 
 * configuration of the Sudoku board
 *
 */
public class SudokuValidator {
	
	/**
	 * Check if a partially filled Sudoku board has any conflicts
	 * @param grid Sudoku board
	 * @return
	 */
	public static boolean isValidSudoku(int[][] grid) {
		// Check row constraints.
		for (int[] row : grid) {
			boolean[] isPresent = new boolean[grid.length + 1];
			for (int j = 0; j < grid.length; ++j) {
				if (row[j] != 0 && isPresent[row[j]]) {
					return false;
				} else {
					isPresent[row[j]] = true;
				}
			}
		}

		// Check column constraints.
		for (int col = 0; col < grid.length; ++col) {
			boolean[] isPresent = new boolean[grid.length + 1];
			for (int[] row : grid) {
				if (row[col] != 0 && isPresent[row[col]]) {
					return false;
				} else {
					isPresent[row[col]] = true;
				}
			}
		}

		// Check region constraints.
		int regionSize = (int) Math.sqrt(grid.length);
		for (int I = 0; I < regionSize; ++I) {
			for (int J = 0; J < regionSize; ++J) {
				boolean[] isPresent = new boolean[grid.length + 1];
				for (int i = 0; i < regionSize; ++i) {
					for (int j = 0; j < regionSize; ++j) {
						if (grid[regionSize * I + i][regionSize * J + j] != 0
								&& isPresent[grid[regionSize * I + i][regionSize
										* J + j]]) {
							return false;
						} else {
							isPresent[grid[regionSize * I + i][regionSize * J + j]] = true;
						}
					}
				}
			}
		}
		return true;
	}
}
