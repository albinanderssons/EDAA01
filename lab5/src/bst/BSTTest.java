package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BSTTest {
	
	private BinarySearchTree<Integer> tree1;
	private BinarySearchTree<String> tree2;
	private BinarySearchTree<Integer> tree3;
	
	@BeforeEach
	public void setup() throws Exception{
		tree1 = new BinarySearchTree<Integer>();
		tree2 = new BinarySearchTree<String>();
		tree3 = new BinarySearchTree<Integer>((e1,e2) -> e1-e2);
	}

	@AfterEach
	public void tearDown() throws Exception {
		tree1 = null;
		tree2 = null;
		tree3 = null;
	}
		
	@Test
	public void testHeightEmpty(){		
		assertEquals(tree1.height(), 0);
		assertEquals(tree2.height(), 0);
		assertEquals(tree3.height(), 0);
	}
	
	@Test
	public void testSizeEmpty(){		
		assertEquals(tree1.size(), 0);
		assertEquals(tree2.size(), 0);
		assertEquals(tree3.size(), 0);
	}
	
	@Test
	public void testHeight(){
		//Tree1
		assertTrue(tree1.add(3));
		assertTrue(tree1.add(1));
		assertTrue(tree1.add(7));
		assertTrue(tree1.add(8));
		assertTrue(tree1.add(2));
		
		assertFalse(tree1.add(7));
		
		assertEquals(tree1.height(), 3);
		//Tree2
		assertTrue(tree2.add("Hej"));
		assertTrue(tree2.add("Hejdå"));
		
		assertFalse(tree2.add("Hej"));
		
		assertEquals(tree2.height(), 2);
		//Tree3
		assertTrue(tree3.add(2));
		assertTrue(tree3.add(6));
		assertTrue(tree3.add(7));
		
		assertFalse(tree3.add(6));
		
		assertEquals(tree3.height(), 3);
	}
	
	@Test
	public void testSize() {
		//Tree1
		assertTrue(tree1.add(5));
		assertTrue(tree1.add(1));
		assertTrue(tree1.add(10));
		assertTrue(tree1.add(7));
		
		assertFalse(tree1.add(10));
		
		assertEquals(tree1.size(), 4);
		//Tree2
		assertTrue(tree2.add("Hej"));
		assertTrue(tree2.add("Hejdå"));
		assertFalse(tree2.add("Hejdå"));
		assertTrue(tree2.add("Hejsan"));
		
		assertEquals(tree2.size(), 3);
		
		//Tree3
		assertTrue(tree3.add(7));
		assertTrue(tree3.add(8));
		assertTrue(tree3.add(1));
		
		assertFalse(tree3.add(8));
		
		assertEquals(tree3.size(), 3);
	}
	
	@Test
	public void testClear() {
		//Tree1
		assertTrue(tree1.add(3));
		assertTrue(tree1.add(1));
		assertTrue(tree1.add(6));
		assertTrue(tree1.add(8));
		
		tree1.clear();
		
		assertEquals(tree1.size(), 0);
		//Tree2
		assertTrue(tree2.add("Hej"));
		assertTrue(tree2.add("Hejsan"));
		
		tree2.clear();
		
		assertEquals(tree2.size(), 0);
		//Tree3
		assertTrue(tree3.add(3));
		assertTrue(tree3.add(1));
		assertTrue(tree3.add(6));
		assertTrue(tree3.add(8));
		
		tree3.clear();
		
		assertEquals(tree3.size(), 0);

	}
	
		
}
