/**
 * Name: Akhil Pillai
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains my implementation of a LinkedList, as well as the provided Node class.
 * This file also contains my implementation of an Iterator for the LinkedList.
 */

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/** 
 * Implementation of a LinkedList.
 * Functions similarly to a java.util.LinkedList,
 * but cannot store null values.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	//stores LL size
	int size;
	//sentinel/dummy head node of LL
	Node head;
	//sentinel/dummy tail node of LL
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
	/**
	* Initializes empty LinkedList with head and tail nodes.
	*/
	public MyLinkedList() {
		//initialize head and tail
		head = new Node(null);
		tail = new Node(null);
		//set head.prev to null (since it is the first node) and head.next to tail (since list is empty)
		head.setPrev(null);
		head.setNext(tail);
		//set tail.prev to head (since list is empty) and tail.next to null (since it is the last node)
		tail.setPrev(head);
		tail.setNext(null);
		/* Add your implementation here */
	}
	/**
	 * Returns the number of elements in the LinkedList.
	 */
	@Override
	public int size() {
		// return size variable that is updated by the methods
		return size;
	}
	/**
	 * Returns the element at a specific index.
	 */
	@Override
	public E get(int index) {
		//use getNth helper method to get Node at the given index
		//return the element at that specific Node
		return getNth(index).getElement();
	}
	/**
	 * Adds the provided data to a LinkedList
	 * at the provided index, adjusting the pointers of nearby
	 * nodes accordingly.
	 */
	@Override
	public void add(int index, E data) {
		//Only non-null data can be added
		if (data == null) throw new NullPointerException();
		//Get the node to the left of the new node, if index > 0.
		//Use the head otherwise.
		Node pNode;
		Node nNode;
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (index > 0) pNode = getNth(index-1);
		else pNode = head;
		if (index < size) nNode = getNth(index);
		else nNode = tail;
		//Create a new node with the provided data.
		Node newNode = new Node(data);
		//Change the pointers of the node on the left and right of the new node so they point
		//towards the new node.
		pNode.setNext(newNode);
		nNode.setPrev(newNode);
		newNode.setPrev(pNode);
		newNode.setNext(nNode);
		//Increment size by 1 since a new element was added.
		size++;
	}
	
	/**
	 * Appends a new element to the end of the LinkedList.
	 */
	public boolean add(E data) {
		//Use the above add(int, E), with the index parameter being the end of the list
		add(size, data);
		return true;
	}

	/**
	 * Sets the provided data into the node at the provided index, 
	 * and returns the overwritten data.
	 */
	public E set(int index, E data) {
		//Only non-null data may be inserted.
		if (data == null) throw new NullPointerException();
		//Get a reference to the node to be edited.
		Node setMe = getNth(index);
		//Get the value to return
		E output = setMe.getElement();
		//Change the value of the given node to the value of the new node.
		setMe.setElement(data);
		//Return the overwritten data.
		return output;
	}
	/**
	 * Removes the node at the provided index from the LinkedList,
	 * and returns its data.
	 */
	public E remove(int index) {
		//Get the element to return
		E output = getNth(index).getElement();
		//Get the node to the left of the node to be removed, if index > 0.
        //Use the head otherwise.
		Node pNode;
		Node nNode;
		if (index > 0) pNode = getNth(index-1);
		else pNode = head;	
		//Get the node to the right of the node to be removed.
		if (index < size - 1) nNode = getNth(index+1);
		else nNode = tail;
		//Modify the nodes to the left and right of the node to be removed 
		//so they point to each other, effectively putting
		//the node to be removed outside of the LL.
		pNode.setNext(nNode);
		nNode.setPrev(pNode);
		//Decrement size by 1 since a node was removed.
		size--;
		//Return the removed data.
		return output;
	}
	/**
	 * Removes all data from the LL.
	 */
	public void clear() {
		//Point the head and tail at each other, so all data 
		//in the list is effectively removed.
		head.setNext(tail);
		tail.setPrev(head);
		//Set size to 0 since list is now empty.
		size = 0;
	}
	/**
	 * Returns true if the LL has no elements, false otherwise.
	 */
	public boolean isEmpty() {
		//Return true if the size is 0, false otherwise.
		return size == 0;
	}
	/**
	 * Gets the node at the provided index.
	 * @param index The index of the LinkedList to
	 * get the node from.
	 */
	protected Node getNth(int index) {
		//Only existing elements may be accessed.
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		//Start at the first node with a value.
		Node curNode = head.next;
		//Traverse the LL to the requested index.
		for (int i = 0; i < index; i++) {
			//In each iteration, traverse the list by 1.
			curNode = curNode.next;
		}
		//Return the node found at the provided index.
		return curNode;
	}
	/**
	 * Implementation of a ListIterator for a MyLinkedList.
	 * This is a protected class, so it can only be declared
	 * via an instance of a MyLinkedList.
	 */
	protected class MyListIterator implements ListIterator<E> {
		/**
		 * The node on the MyListIterator's left.
		 */
		Node left;
		/**
		 * The node on the MyListIterator's right
		 */
		Node right;
		/**
		 * The index of the MyLinkedList that the
		 * MyListIterator is positioned at.
		 */
		int idx;
		/**
		 * True if the MyLinkedList is being traversed
		 * head to tail, false otherwise.
		 */
		boolean forward;
		/**
		 * True if the last modified element
		 * can be removed or set, false otherwise.
		 */
		boolean canRemoveOrSet;
		/**
		 * 0-arg constructor for the Iterator.
		 * Initializes all of the instance variables with data
		 * from the MyLinkedList used to instantiate it.
		 */
		public MyListIterator() {
			left = head;
			right = head.getNext();
			idx = 0;
			forward = true;
			canRemoveOrSet = false;
		}
		/**
		 * @return Whether the Iterator can traverse forward by 1.
		 */
		public boolean hasNext() {
			//if right == tail, the list is at its end
			//and we cannot traverse further.
			return right != tail;
		}

		/**
		 * Moves the iterator forward by 1 index, and shifts the 
		 * instance variables accordingly.
		 * @return The next element in the list.
		 */
		public E next() {
			//Only traverse forward if there are elements
			//to traverse to
			if (!hasNext()) throw new NoSuchElementException();
			//Shift all the instance variables to the right
			left = right;
			right = right.getNext();
			idx++;
			//Update traversion to forward
			forward = true;
			canRemoveOrSet = true;
			return left.getElement();
		}
		/**
		 * @return Whether the Iterator can traverse backwards by 1.
		 */
		public boolean hasPrevious() {
			//if left == head, the list is at its beginning
			//and we cannot traverse forward.
			return left != head;
		}
		/**
		 * Moves the iterator backward by 1 index, and shifts the 
		 * instance variables accordingly.
		 * @return The previous element in the list.
		 */
		public E previous() {
			//Only traverse backward if there are elements
			//to traverse to
			if (!hasPrevious()) throw new NoSuchElementException();
			//Shift all the instance variables to the left
			right = left;
			left = left.getPrev();
			idx--;
			//Update traversion to backward
			forward = false;
			canRemoveOrSet = true;
			return right.getElement();
		}

		/**
		 * @return The index of the object returned by {@link #next} if it exists,
		 * list size otherwise.
		 */
		public int nextIndex() {
			return idx;
		}

		/**
		 * @return The index of the object returned  by {@link #previous} if it exists,
		 * -1 otherwise.
		 */
		public int previousIndex() {
			return (!hasPrevious()) ? -1 : idx - 1;
		}

		/**
		 * Adds an element to the index immediately before the element returned by {@link #previous}.
		 * @param element The element to be inserted into the {@link MyLinkedList}
		 * @throws NullPointerException if you attempt to insert a null element
		 */
		public void add(E element) {
			//Only non-null elements may be added.
			if (element == null) throw new NullPointerException();
			//Wrap element in a node and adjust the pointers to 
			//integrate the node into the LL
			Node newNode = new Node(element);
			//Update the involved pointers
			left.setNext(newNode);
			right.setPrev(newNode);
			newNode.setPrev(left);
			newNode.setNext(right);
			//Update the iterator's instance variables
			left = newNode;
			idx++;
			canRemoveOrSet = false;
			//Update LL size
			size++;
		}

		/**
		 * Sets the latest element called by {@link #next} or {@link #previous}
		 * to a new {@link Node} with value element.
		 * @param element The value that will replace the original value
		 * of the selected {@link Node}.
		 * @throws IllegalStateException if a new value cannot currently be removed or set
		 * @throws NullPointerException if user attempts to set null
		 */
		public void set(E element) {
			//Throw exceptions if necessary
			if (!canRemoveOrSet) throw new IllegalStateException();
			if (element == null) throw new NullPointerException();
			//Wrap new value in node
			Node setThis = new Node(element);
			//Update list and pointers depending on
			//array traversal direction
			if (forward) {//set left
				Node previous = left.getPrev();
				left = setThis;
				left.setPrev(previous);
				left.setNext(right);
			}
			else {//set right
				Node next = left.getNext();
				right = setThis;
				right.setPrev(left);
				right.setNext(next);
			}
		}

		/**
		 * Removes the latest element called by {@link #next} or {@link #previous}.
		 * @throws IllegalStateException if a new value cannot currently be removed or set
		 */
		public void remove() {
			//Throw exception if necessary
			if (!canRemoveOrSet) throw new IllegalStateException();
			//Update list and pointers depending on array traversal
			//direction
			if (forward) {//remove left
				left = left.getPrev();
				left.setNext(right);
				right.setPrev(left);
			}
			else {//remove right
				right = right.getNext();
				left.setNext(right);
				right.setPrev(left);
			}
			//Update instance variables if necessary.
			if (forward) idx--;
			canRemoveOrSet = false;
			//Update LL size
			size--;
		}

		/**
		 * @return A new instance of a MyListIterator.
		 */
		public ListIterator<E> listIterator() {
			return new MyListIterator();
		}

		/**
		 * @return A new instance of a MyListIterator.
		 */
		public Iterator<E> iterator() {
			return new MyListIterator();
		}
	}
}