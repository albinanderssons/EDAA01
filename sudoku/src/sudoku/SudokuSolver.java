package sudoku;

public class SudokuSolver implements InterfaceSudokuSolver {

	private int grid[][];

	public SudokuSolver() {
		grid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	/**
	 * Tries to solve the sudokuboard if the board is valid, else return false.
	 * 
	 * @return true if the board was solved, else false
	 */
	
	@Override
	public boolean solve() {
		if(isValid()) {
			return solve(0,0);
		}else {
			return false;
		}
	}

	private boolean solve(int r, int c) {
		if(r == 8 && c == 9) {
			return true;
		}
		if (c == 9) {
			r++;
			c = 0;
		}
		
		if(grid[r][c] == 0) {
			for(int number = 1; number < 10; number++) {	
				if(isValidNumber(r, c, number)) {	
					grid[r][c] = number;	
					if(solve(r,c+1)) {
						return true;
					}
					grid[r][c] = 0;	
				}
			}
			return false;
		}
		
		return solve(r,c+1);
		
	}

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
	
	@Override
	public void add(int row, int col, int digit) {
		if (digit < 0 || digit > 9) {
			throw new IllegalArgumentException(digit + " is not a valid nbr");
		}
		if(isValidBox(row,col)) {
			grid[row][col] = digit;
		}
	}

	/**
	 * Removes the number on the box(row, col) and returns the number that was removed.
	 * 
	 * @param row for the row of the box
	 * @param col for the column of the box
	 * 
	 * @throws IllegalArgumentException if row or col is outside 
	 * 			the range [0..9]
	 */
	@Override
	public void remove(int row, int col) {
		if (isValidBox(row, col)) {
			grid[row][col] = 0;
		}
	}

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
	
	@Override
	public int get(int row, int col) {
		if (isValidBox(row, col)) {
			return grid[row][col];
		} else {
			return 0;
		}
	}

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if the board is valid, else false
	 */
	
	@Override
	public boolean isValid() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (!isValidNumber(r, c, grid[r][c])) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Removes all the numbers on the board.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	@Override
	public void setMatrix(int[][] m) {
		if(isValidMatrix(m)){
			grid = m.clone();
		}
	}
	
	/**
	 * returns the matrix
	 * 
	 * @return the matrix
	 */
	@Override
	public int[][] getMatrix() {
		return grid.clone();
		
	}
	
	/*Checks that a number is valid for a box in the matrix */
	
	private boolean isValidNumber(int row, int col, int number) {
		int v = grid[row][col];
		grid[row][col] = 0;
		
		boolean res = isValidRow(number, row) && isValidColumn(number, col) && isValidRegion(number, row, col);
		
		grid[row][col] = v;
		
		return res;
	}
	
	/* Checks if the box is valid, i.e -1< row&col <9 */
	
	private boolean isValidBox(int row, int col) {
		if (row < 0 || row > 8 || col < 0 || col > 8) {
			throw new IllegalArgumentException(row + "," + col + "is not a valid box");
		}
		return true;
	}

	/* Checks that the matrix is a 9x9 matrix */
	
	private boolean isValidMatrix(int m[][]) {
		if (m.length < 0 || m.length > 9 || m[0].length < 0 || m[0].length > 9 || m.length != m[0].length) {
			throw new IllegalArgumentException("The matrix " + m + "is not valid");
		}
		
		for(int r = 0; r<9; r++) {
			for(int c = 0; c<9; c++) {
				if (m[r][c] < 0 || m[r][c] > 9) {
					throw new IllegalArgumentException(m[r][c] + " is not a valid nbr");
				}
			}
		}
		return true;
	}
	
	/* Checks that the row is valid */
	
	private boolean isValidRow(int digit, int row) {
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == digit && digit != 0) {
				return false;
			}
		}
		return true;

	}

	/* Checks that the column is valid */
	private boolean isValidColumn(int digit, int col) {
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == digit && digit != 0) {
				return false;
			}
		}
		return true;
	}
	
	/* Checks that the region is valid */

	private boolean isValidRegion(int digit, int row, int col) {
		int firstRow = (row / 3) * 3;
		int firstCol = (col / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[firstRow + i][firstCol + j] == digit  && digit != 0) {
					return false;
				}
			}
		}
		return true;

	}

}
