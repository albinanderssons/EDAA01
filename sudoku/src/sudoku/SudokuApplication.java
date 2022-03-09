package sudoku;

public class SudokuApplication {
	
	public static void main(String[] args) {
		SudokuSolver sudoku = new SudokuSolver();
		new SudokuGUI(sudoku);
		
		//Used before the GUI was implemented
		
		/*int grid1[][] = { { 1, 2, 3, 0, 0, 0, 0, 0, 0 },
                {4, 5, 6, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
                
		/*sudoku.setMatrix(grid1);
		
		if(sudoku.solve()) {
			printGrid(sudoku);
		}
		else {
			System.out.println("no solution");
		}*/
	}
	
	/* Method used before GUI was implemented to prints the current grid in the console*/
	
	private static void printGrid(SudokuSolver sudoku) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (sudoku.get(r, c) == -1) {
					System.out.print(" " + sudoku.get(r, c));
				} else {
				System.out.print(" " + sudoku.get(r, c));
				}
				if (c == 8) {
					System.out.println();
				}
			}
		}
	}

}
