package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
  
  	public static void main(String[] args) {
		BSTVisualizer visualizer = new BSTVisualizer("BST", 500, 500);
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(1);
		bst.add(5);
		bst.add(10);
		bst.add(11);
		bst.add(12);
		bst.add(15);
		
		bst.rebuild();
		bst.printTree();
		visualizer.drawTree(bst);
		
	}
  
  
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		comparator = (e1, e2) -> ((Comparable <E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(x, root); 	
	}
	
	private boolean add(E x, BinaryNode<E> node) {
		BinaryNode<E> newNode = new BinaryNode<> (x);
		if(root == null) {
			root = newNode;
			size ++;
		}else if(node.element == x) {
			return false;
		}else if(comparator.compare(x, node.element) > 0) {
			if(node.right == null) {
				node.right = newNode;
				size++;
			} else {
				return add(x, node.right);
			}
		}else {
			if(node.left == null) {
				node.left = newNode;
				size++;
			}else {
				return add(x, node.left);
			}
		}
		return true; 
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) {
		if(n==null) {
			return 0;
		}else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> n) {
		if(n != null) {
			printTree(n.left);
			System.out.println(" " + n.element);
			printTree(n.right);
		}
		
	}
	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		root = buildTree(sorted, 0, sorted.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n!=null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first>last) {
			return null;
		}
		
		int mid  = first + ((last-first) / 2);
		BinaryNode<E> node = new BinaryNode<> (sorted.get(mid));
		
		node.left = buildTree(sorted, first, mid-1);
		node.right = buildTree(sorted, mid+1, last);
		return node;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
