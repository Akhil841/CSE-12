/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains my implementation of a Deque, MyDeque.
 * This deque has methods for adding, removing, and peeking
 * to both front and back, as well as methods to expand capacity and 
 * check size.
 */

/**
 * This class contains the MyDeque implementation.
 * It implements all the methods stated above.
 */
 public class MyDeque<E> implements DequeInterface<E> {
    //default capacity of a MyDeque, if
    //capacity is expanded from 0
    final int DEFAULT_CAPACITY = 10;
    //the array which stores the deque's
    //data
    Object[] data;
    //The number of non-null objects in the array
    int size;
    //The index of the last non-null object in the array
    int rear;
    //The index of the first non-null object in the array
    int front;
    /**
     * Constructor of a MyDeque.
     * @param initialCapacity The initial capacity of the
     * MyDeque's data
     */
    public MyDeque(int initialCapacity) {
        //must store at least 0 objects
        if (initialCapacity < 0) throw new IllegalArgumentException();
        //initialize data as an array of size initial capacity
        data = new Object[initialCapacity];
        //set all other instance variables to 0
        size = 0;
        rear = 0;
        front = 0;
    }

    /**
     * Gets the number of non-null objects in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Expands the size of the deque to 10 objects if
     * it is currently 0, double the current size otherwise.
     */
    public void expandCapacity() {
        //if data length is 0 set the data array
        //to an array of 10 objects
        if (data.length == 0) {
            data = new Object[DEFAULT_CAPACITY];
            return;
        }
        //Otherwise double the data array's size and
        //copy over all of the old datas
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Adds an object to the head of the deque.
     * @param element The object to add to the head of the deque.
     * @throws NullPointerException if you attempt to add a null object
     */
    public void addFirst(E element) {
        //Only non-null objects may be added
        if (element == null) throw new NullPointerException();
        //Expand capacity if necessary
        if (size == data.length) expandCapacity();
        //If the array has no objects in it
        if (size == 0) {
            //set the first value to element
            data[0] = element;
            //increase size
            size++;
            return;
        }
        //if the array is populated
        //set the object to the frontmost
        //free space
        int index = (front - 1) % data.length;
        //circular behavior
        if (index < 0) index += data.length;
        data[index] = element;
        //update instance variables
        size++;
        front = index;
    }

    /**
     * Adds an object to the tail of the deque.
     * @param element The object to add to the tail of the deque.
     * @throws NullPointerException if you attempt to add a null object
     */
    public void addLast(E element) {
        //Only non-null objects may be added
        if (element == null) throw new NullPointerException();
        //Expand capacity if necessary
        if (size == data.length) expandCapacity();
        //If the array has no objects in it
        if (size == 0) {
            //set the first value to element
            data[0] = element;
            //increase size
            size++;
            return;
        }
        //if the array is populated
        //set the object to the frontmost
        //free space
        data[(rear + 1) % data.length] = element;
        //update instance variables
        size++;
        //circular behavior
        rear = (rear + 1) % data.length;
    }

    /**
     * Removes the frontmost element of the deque. Returns null if
     * the deque has no elements.
     */
    public E removeFirst() {
        //store output here
        E output;
        //return null if array is empty
        if (size == 0) return null;
        //get return data
        output = (E) data[front];
        //remove value from array
        data[front] = null;
        //update instance variables
        front = (front + 1) % data.length;
        size--;
        //return removed value
        return output;
    }

    /**
     * Removes the rearmost element of the deque. Returns null if
     * the deque has no elements.
     */
    public E removeLast() {
        //store output here
        E output;
        //return null if array is empty
        if (size == 0) return null;
        //get return data
        output = (E) data[rear];
        //remove value from array
        data[rear] = null;
        //update instance variables
        rear = (rear - 1) % data.length;
        if (rear < 0) rear += data.length;
        size--;
        //return removed value
        return output;
    }

    /**
     * Peeks at the frontmost object of the deque.
     * Returns null if the deque is empty.
     */
    public E peekFirst() {
        //return null if array has no elements
        if (size == 0) return null;
        //return the requested value
        return (E) data[front];
    }

    /**
     * Peeks at the rearmost object of the deque.
     * Returns null if the deque is empty.
     */
    public E peekLast() {
        //return null if array has no elements
        if (size == 0) return null;
        //return the requested value
        return (E) data[rear];
    }
}
