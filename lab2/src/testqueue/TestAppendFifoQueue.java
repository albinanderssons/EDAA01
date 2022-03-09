package testqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> intQueue;
	private FifoQueue<Integer> intQueue2;
	

	@BeforeEach
	public void setUp() throws Exception {
		intQueue = new FifoQueue<Integer>();
		intQueue2 = new FifoQueue <Integer>();
	}

	@AfterEach
	public void tearDown() throws Exception {
		intQueue = null;
		intQueue2 = null;
	}
	
	@Test
	public void twoEmpty() {
		assertTrue("The queue should be empty", intQueue.isEmpty());
		assertTrue("The queue should be empty", intQueue2.isEmpty());
		intQueue.append(intQueue2);
	}
	
	@Test
	public void emptyAppendNoneEmpty() {
		intQueue2.offer(1);
		intQueue2.offer(2);
		intQueue.append(intQueue2);
		assertEquals("Size should be 2", 2, intQueue.size());
		assertEquals("First int should be 1", 1 ,(int) intQueue.poll());
		assertEquals("Second int should be 2", 2 ,(int) intQueue.peek());
		assertTrue("This queue should be empty", intQueue2.isEmpty());
	}
	
	@Test
	public void noneEmptyAppendEmpty() {
		intQueue.offer(4);
		intQueue.offer(5);
		intQueue.append(intQueue2);
		assertEquals("Size should be 2", 2, intQueue.size());
		assertEquals("First int should be 4", 4 ,(int) intQueue.poll());
		assertEquals("Second int should be 5", 5 ,(int) intQueue.peek());
		assertTrue("The queue should be empty", intQueue2.isEmpty());
	}
	
	@Test
	public void appendTwo() {
		intQueue.offer(5);
		intQueue.offer(6);
		intQueue2.offer(7);
		intQueue2.offer(8);
		intQueue.append(intQueue2);
		assertEquals("Size should be 4", 4, intQueue.size());
		assertEquals("First int should be 5", 5 ,(int) intQueue.poll());
		assertEquals("First int should be 6", 6 ,(int) intQueue.poll());
		assertEquals("First int should be 7", 7 ,(int) intQueue.poll());
		assertEquals("First int should be 8", 8 ,(int) intQueue.poll());
		assertTrue("The queue to append should be empty", intQueue2.isEmpty());
	}
	
	@Test
	public void appendSame() {
		try {
			intQueue.offer(1);
			intQueue.offer(1);
			intQueue.append(intQueue);
			fail("");
		}catch(IllegalArgumentException e){
			
		}
	}
	
}
