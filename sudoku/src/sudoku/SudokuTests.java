package sudoku;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SudokuTests {
	private InterfaceSudokuSolver sudoku;
	private int test[][] = { {  0, 0, 8, 0, 0, 9, 0, 6, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
        { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
        { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
        { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
        { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, 
        { 8, 6, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 4, 0, 0 }};
	
	@BeforeEach
	public void setUp() throws Exception {
		sudoku = new SudokuSolver();
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		sudoku = null;
	}

	/*Testing to solve an empty board*/
	
	@Test
	public void testEmpty() {
		assertTrue(this.sudoku.solve());
	}
	
	/*Testing to solve a specific board*/
	
	@Test
	public void testBoard(){
		sudoku.setMatrix(test);
		assertTrue(sudoku.isValid());
		assertTrue(sudoku.solve());
	}
	
	/*Testing to solve a sudoku with an unvalid row*/
	
	@Test
	public void testUnvalidRow() {
		sudoku.add(0,0,7);
		sudoku.add(0,5,7);
		assertFalse(sudoku.solve());
		
		sudoku.add(0,5,0);
		assertTrue(sudoku.solve());
	}
	
	/*Testing to solve a sudoku with an unvalid column*/
	
	@Test
	public void testUnvalidColumn() {
		sudoku.add(0,0,7);
		sudoku.add(5,0,7);
		assertFalse(sudoku.solve());
		
		sudoku.add(5,0,0);
		assertTrue(sudoku.solve());
	}
	
	/*Testing to solve a sudoku with an unvalid region*/
	
	@Test
	public void testUnvalidRegion() {
		sudoku.add(0,0,7);
		sudoku.add(2,0,7);
		assertFalse(sudoku.solve());
		
		sudoku.add(2,0,0);
		assertTrue(sudoku.solve());
	}
	
	/* Testing to solve an empty board, clear it and ensure that all boxes are empty*/
	
	@Test
	public void testClear() {
		assertTrue(sudoku.solve());	
		sudoku.clear();
		
		for(int r=0; r<9; r++) {
			for(int c=0; c<9; c++) {
				assertEquals(sudoku.get(r,c),0);
			}
		}
	}
	
	/* Testing to add unvalid numbers to the sudoku and expects a exception to be thrown*/
	
	@Test
	public void testIsValid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    sudoku.add(0,0,-1);
		  });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    sudoku.add(0,0,999);
		  });
	}
	
	/* Testing to set a temp matrix equals to a initialized matrix and ensure that all boxes are the same */
	
	@Test
	public void testGetMatrix() {
		sudoku.setMatrix(test);
		int [][] temp = sudoku.getMatrix();
		for(int r=0; r<9; r++) {
			for(int c=0; c<9; c++) {
				assertEquals(temp[r][c], test[r][c]);
			}
		}
	}
	
	/* Testing to add digits and remove them to make sure they have been removed*/
	
	@Test
	public void testRemove() {
		sudoku.add(0,5,2);
		sudoku.add(5,8,4);
		
		assertEquals(sudoku.get(0,5), 2);
		assertEquals(sudoku.get(5,8), 4);
		
		sudoku.remove(0,5);
		sudoku.remove(5,8);
		
		assertEquals(sudoku.get(0,5), 0);
		assertEquals(sudoku.get(5,8), 0);
	}
	
	
	
	
	
	
	
	
	

}
