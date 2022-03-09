package sudoku;

public interface InterfaceSudokuSolver {
		
		/**
		* Tries to solve the sudokuboard if the board is valid, else return false.
		* 
		* @return true if the board was solved, else false
		*/
		boolean solve();

		/**
		 * Puts digit in the box row, col.
		 * 
		 * @param row   The row
		 * @param col   The column
		 * @param digit The digit to insert in box row, col
		 * 
		 * @throws IllegalArgumentException if row, col or digit is outside the range
		 *                                  [0..9]
		 */
		void add(int row, int col, int digit);

		/**
		 * Removes the number on the box(row, col) and returns the number that was removed.
		 * 
		 * @param row for the row of the box
		 * @param col for the column of the box
		 * 
		 * @throws IllegalArgumentException if row or col is outside 
		 * 			the range [0..9]
		 */
		void remove(int row, int col);

		/**
		 * Returns the number in the box(row, col) or if the box is empty return 0.
		 * 
		 * @param row for the row of the slot
		 * @param col for the column of the slot
		 * 
		 * @throws IllegalArgumentException if row or col is outside 
		 * 			the range [0..9]
		 *    
		 * @return the number on the slot(row, col), if empty return 0.
		 */
		int get(int row, int col);

		/**
		 * Checks that all filled in digits follows the the sudoku rules.
		 * 
		 * @return true if the board is valid, else false
		 */
		boolean isValid();

		/**
		 * Removes all the numbers on the board.
		 */
		void clear();

		/**
		 * Fills the grid with the digits in m. The digit 0 represents an empty box.
		 * 
		 * @param m the matrix with the digits to insert
		 * @throws IllegalArgumentException if m has the wrong dimension or contains
		 *                                  values outside the range [0..9]
		 */
		void setMatrix(int[][] m);

		/**
		 * returns the matrix
		 * 
		 * @return the matrix
		 */
		int[][] getMatrix();
	}
