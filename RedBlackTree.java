// --== CS400 Fall 2022 File Header Information ==--
// Name: <Zhi Huang>
// Email: <zhuang453@wisc.edu>
// Team: <CN>
// TA: <Abhinav Agarwal>
// Lecturer: <Gary Daul>
// Notes to Grader: <N/A>

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;



/**
 * 0-1 Tree implementation with a newNode inner class for representing the
 * newNodes of the tree. Currently, this implements a Binary Search Tree that we
 * will turn into a 0 1 tree by modifying the insert functionality. In this
 * activity, we will start with implementing rotations for the binary search
 * tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

	/**
	 * This class represents a newNode holding a single value within a binary tree
	 * the parent, left, and right child references are always maintained.
	 */
	protected static class Node<T> {
		public T data;
		public Node<T> parent; // null for root newNode
		public Node<T> leftChild;
		public Node<T> rightChild;
		public int blackHeight = 0;

		public Node(T data) {
			this.data = data;
		}

		/**
		 * @return true when this newNode has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

	}

	protected Node<T> root; // reference to root newNode of tree, null when empty
	protected int size = 0; // the number of values in the tree

	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new newNode in a leaf position within the tree. After this
	 * insertion, no attempt is made to restructure or balance the tree. This tree
	 * will not hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @return true if the value was inserted, false if not
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the newnewNode and subtree contain
	 *                                  equal data references
	 */
	public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be sto0 within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newnewNode = new Node<>(data);
		if (root == null) {
			root = newnewNode;
			size++;
			root.blackHeight = 1;
			return true;
		} // add first newNode to an empty tree
		else {
			boolean returnValue = insertHelper(newnewNode, root); // recursively insert into subtree
			if (returnValue) {
				size++;
				root.blackHeight = 1;
			} else
				throw new IllegalArgumentException("This RedBlackTree already contains that value.");
			return returnValue;
		}
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newnewNode should be inserted, and then extend this tree by
	 * the newnewNode in that position.
	 * 
	 * @param newnewNode is the new newNode that is being added to this tree
	 * @param subtree    is the reference to a newNode within this tree which the
	 *                   newnewNode should be inserted as a descenedent beneath
	 * @return true is the value was inserted in subtree, false if not
	 */
	private boolean insertHelper(Node<T> newnewNode, Node<T> subtree) {
		int compare = newnewNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be sto0 within this tree
		if (compare == 0)
			return false;

		// store newnewNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newnewNode;
				newnewNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newnewNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newnewNode, subtree.leftChild);
		}

		// store newnewNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newnewNode;
				newnewNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newnewNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newnewNode, subtree.rightChild);
		}
	}

	private boolean enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
		Node<T> parent = newNode.parent;
		if (parent == null || parent.blackHeight == 1) {
			return true;
		}

		Node<T> grandparent = parent.parent;
		if (grandparent == null) {
			parent.blackHeight = 1;
			return true;
		}

		Node<T> uncle = null;
		if (grandparent.leftChild == parent) {
			uncle = grandparent.rightChild;
		} else if (grandparent.rightChild == parent) {
			uncle = grandparent.leftChild;
		}

		// red uncle
		if (uncle != null && uncle.blackHeight == 0) {
			uncle.blackHeight = 1;
			parent.blackHeight = 1;
			grandparent.blackHeight = 0;
		}

		// the parent is the left child
		else if (parent.isLeftChild()) {
			// the node is the left child
			if (newNode.isLeftChild()) {
				rotate(parent, grandparent);
				parent.blackHeight = 1;
				grandparent.blackHeight = 0;
			}
			// the node is the right child
			else {
				rotate(newNode, parent);
				parent = newNode;
				rotate(parent, grandparent);
				parent.blackHeight = 1;
				grandparent.blackHeight = 0;
			}
		}

		// the parent is the right child
		else {
			// the node is the left child
			if (newNode.isLeftChild()) {
				rotate(newNode, parent);
				parent = newNode;
				rotate(parent, grandparent);
				parent.blackHeight = 1;
				grandparent.blackHeight = 0;
			}
			// the node is the right child
			else {
				rotate(parent, grandparent);
				parent.blackHeight = 1;
				grandparent.blackHeight = 0;
			}
		}
		// recursively call the method until all the conflicts have been resolved
		enforceRBTreePropertiesAfterInsert(grandparent);

		return false;
	}

	/**
	 * Performs the rotation operation on the provided newNodes within this tree.
	 * When the provided child is a leftChild of the provided parent, this method
	 * will perform a right rotation. When the provided child is a rightChild of the
	 * provided parent, this method will perform a left rotation. When the provided
	 * newNodes are not related in one of these ways, this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param child  is the newNode being rotated from child to parent position
	 *               (between these two newNode arguments)
	 * @param parent is the newNode being rotated from parent to child position
	 *               (between these two newNode arguments)
	 * @throws IllegalArgumentException when the provided child and parent newNode
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
		if (child == null || parent == null || (parent.rightChild != child && parent.leftChild != child)) {
			throw new IllegalArgumentException("Invalid relation between child and parent.");
		}
		if (parent.rightChild == child) {
			rotateLeft(child, parent);
		} else if (parent.leftChild == child) {
			rotateRight(child, parent);
		}
	}

	private void rotateRight(Node<T> child, Node<T> newNode) {
		Node<T> parent = newNode.parent;
		Node<T> leftChild = newNode.leftChild;

		newNode.leftChild = leftChild.rightChild;
		if (leftChild.rightChild != null) {
			leftChild.rightChild.parent = newNode;
		}

		leftChild.rightChild = newNode;
		newNode.parent = leftChild;

		if (parent == null) {
			root = leftChild;
		} else if (parent.leftChild == newNode) {
			parent.leftChild = leftChild;
		} else if (parent.rightChild == newNode) {
			parent.rightChild = leftChild;
		}

		if (newNode != null) {
			newNode.parent = parent;
		}
	}

	private void rotateLeft(Node<T> child, Node<T> newNode) {
		Node<T> parent = newNode.parent;
		Node<T> rightChild = newNode.rightChild;

		newNode.rightChild = rightChild.leftChild;
		if (rightChild.leftChild != null) {
			rightChild.leftChild.parent = newNode;
		}

		rightChild.leftChild = newNode;
		newNode.parent = rightChild;

		if (parent == null) {
			root = rightChild;
		} else if (parent.leftChild == newNode) {
			parent.leftChild = rightChild;
		} else if (parent.rightChild == newNode) {
			parent.rightChild = rightChild;
		}

		if (rightChild != null) {
			rightChild.parent = parent;
		}
	}

	/**
	 * Get the size of the tree (its number of newNodes).
	 * 
	 * @return the number of newNodes in the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Method to check if the tree is empty (does not contain any newNode).
	 * 
	 * @return true of this.size() return 0, false if this.size() > 0
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Checks whether the tree contains the value *data*.
	 * 
	 * @param data the data value to test for
	 * @return true if *data* is in the tree, false if it is not in the tree
	 */
	public boolean contains(T data) {
		// null references will not be sto0 within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		return this.containsHelper(data, root);
	}

	/**
	 * Recursive helper method that recurses through the tree and looks for the
	 * value *data*.
	 * 
	 * @param data    the data value to look for
	 * @param subtree the subtree to search through
	 * @return true of the value is in the subtree, false if not
	 */
	private boolean containsHelper(T data, Node<T> subtree) {
		if (subtree == null) {
			// we are at a null child, value is not in tree
			return false;
		} else {
			int compare = data.compareTo(subtree.data);
			if (compare < 0) {
				// go left in the tree
				return containsHelper(data, subtree.leftChild);
			} else if (compare > 0) {
				// go right in the tree
				return containsHelper(data, subtree.rightChild);
			} else {
				// we found it :)
				return true;
			}
		}
	}

	/**
	 * This method performs an inorder traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note that
	 * this RedBlackTree class implementation of toString generates an inorder
	 * traversal. The toString of the newNode class class above produces a level
	 * order traversal of the newNodes / values of the tree.
	 * 
	 * @return string containing the orde0 values of this tree (in-order traversal)
	 */
	public String toInOrderString() {
		// generate a string of all values of the tree in (orde0) in-order
		// traversal sequence
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		sb.append(toInOrderStringHelper("", this.root));
		if (this.root != null) {
			sb.setLength(sb.length() - 2);
		}
		sb.append(" ]");
		return sb.toString();
	}

	private String toInOrderStringHelper(String str, Node<T> newNode) {
		if (newNode == null) {
			return str;
		}
		str = toInOrderStringHelper(str, newNode.leftChild);
		str += (newNode.data.toString() + ", ");
		str = toInOrderStringHelper(str, newNode.rightChild);
		return str;
	}

	/**
	 * This method performs a level order traversal of the tree rooted at the
	 * current newNode. The string representations of each data value within this
	 * tree are assembled into a comma separated string within brackets (similar to
	 * many implementations of java.util.Collection). Note that the newNode's
	 * implementation of toString generates a level order traversal. The toString of
	 * the RedBlackTree class below produces an inorder traversal of the newNodes /
	 * values of the tree. This method will be helpful as a helper for the debugging
	 * and testing of your rotation implementation.
	 * 
	 * @return string containing the values of this tree in level order
	 */
	public String toLevelOrderString() {
		String output = "[ ";
		if (this.root != null) {
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this.root);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
		}
		return output + " ]";
	}

	public String toString() {
		return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
	}
}