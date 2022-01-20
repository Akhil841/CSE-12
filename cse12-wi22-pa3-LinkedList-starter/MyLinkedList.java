/**
 * TODO: Add your file header
 * Name:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * 
 * 2-4 sentence file description here
 */

import java.util.AbstractList;

/** 
 * TODO: Add class header here 
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		/* Add your implementation here */
		// TODO
	}

	@Override
	public int size() {
		// need to implement the size method
		return 0; // TODO
	}

	@Override
	public E get(int index) {
		return (E) null;  // TODO
	}

	@Override
	public void add(int index, E data) {
		/* Add your implementation here */
		// TODO
	}

	public boolean add(E data) {
		return true; // TODO
	}

	public E set(int index, E data) {
		return (E) null; // TODO
	}

	public E remove(int index) {
		return (E) null; // TODO
	}

	public void clear() {
		/* Add your implementation here */
	}

	public boolean isEmpty() {
		return true;  // TODO
	}

	protected Node getNth(int index) {
		return (Node) null;  // TODO
	}
}