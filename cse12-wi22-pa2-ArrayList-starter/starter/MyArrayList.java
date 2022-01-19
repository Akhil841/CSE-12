/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This contains a custom implementation of an ArrayList that uses an array to store data.
 */

public class MyArrayList<E> implements MyList<E> {
    public static final int DEFAULT_CAPACITY = 5;
    public static final int DEFAULT_SIZE = 0;
	//store data in instance variable
    public Object[] data;
    //store size in instance variable
    public int size;
    //no-arg constructor
    public MyArrayList() {
    	//data array should have default capacity and size since it has nothing
        data = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
    }
    //constructor with initial capacity argument
    public MyArrayList(int initialCapacity) {
    	//report exception if the initial capacity is less than 0, which cannot be done
        if (initialCapacity < 0) throw new IllegalArgumentException();
        //data should have capacity of the input capacity and size 0 since it has nothing
        data = new Object[initialCapacity];
        size = DEFAULT_SIZE;
    }
    //constructor with initial array argument
    public MyArrayList(E[] arr) {
    	//if the array is null use the default constructor
        if (arr == null) {
        	data = new Object[DEFAULT_CAPACITY];
            size = DEFAULT_SIZE;
        }
        //data should have the array's contents and have the same size as the array since it has
        //all of the array's contents
        else {
            data = arr;
            size = arr.length;
        }
    }
    //expands capacity by double if requiredCapacity < capacity * 2,
    //and by requiredCapacity otherwise
    public void expandCapacity(int requiredCapacity) {
    	//if the user provides a value less than the original capacity,
    	//throw an exception to let them know they shouldn't do that
        if (requiredCapacity < data.length) throw new IllegalArgumentException();
        //store the new capacity here
        int len = data.length;
        //if the data set isn't empty (i.e. if capacity isn't 0)
        if (len != 0) {
        	//set len to the maximum of capacity * 2 and requiredCapacity
            len = Math.max(requiredCapacity, len * 2);
            //create shallow copy of the data to add after increasing capacity
            Object[] oldData = data;
            //set data to an array with length equal to the new capacity
            data = new Object[len];
            //transfer over the shallow-copied elements to the new array
            for (int i = 0; i < oldData.length; i++) {
                data[i] = oldData[i];
            }
        }
        //if the data set is empty
        else {
        	//make data a new array of size being the maximum
        	//of requiredCapacity and the default capacity
            data = new Object[Math.max(requiredCapacity, DEFAULT_CAPACITY)];
        }
    }
    //get the capacity of the ArrayList
    public int getCapacity() {
    	//return the size of the ArrayList
        return data.length;
    }
    //Inserts a new element at the index position and shifts the elements after it to the right by 1.
    public void insert(int index, E element) {
    	//if the index is 0, use the prepend method instead because it gets kind of mucky otherwise
    	if (index == 0) prepend(element);
    	//if the index is not 0
    	else {
    		//throw an IndexOutOfBoundsException if the user attempts
    		//to write to an inaccessible part of the ArrayList
    		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            else {
            	//Expand capacity to double if the ArrayList is at full capacity
            	if (size == getCapacity()) expandCapacity(data.length * 2);
            	//shallow copy the data for re-writing purposes
                Object[] oldData = data;
                //all data from 0 to the given index is the same so we do not need to rewrite it
                //set the value at the index point of data to the provided element
                data[index] = element;
                //shift all the other values by 1
                for (int i = index; i < size; i++) {
                    data[i+1] = oldData[i];
                }
                //increment size by 1 since one element was added
                size++;
            }
    	}
    }
    //Appends an element to the end of the list
    public void append(E element) {
    	//Expand capacity by double if the list is fully packed
        if (size == getCapacity()) expandCapacity(data.length * 2);
        //since we are adding an element to the end of the list no data needs to be shifted
        //add the given element to the end of the list
        data[size] = element;
        //increment size by 1 since one element was added
        size++;
    }
    //Appends an element to the the beginning of the list
    public void prepend(E element) {
    	//Expand capacity by double if the list is fully packed
        if (size == getCapacity()) expandCapacity(data.length * 2);
        //Make a shallow copy of the data for rewriting purposes
        Object[] oldData = data;
        //set the first index of data to the given element
        data[0] = element;
        //shift the original data in the array by 1
        for (int i = 0; i < size; i++) {
            data[i+1] = oldData[i];
        }
      //increment size by 1 since one element was added
        size++;
    }
    //Return the value at a given index
    public E get(int index) {
    	//throw an IndexOutOfBoundsException if the user attempts
		//to write to an inaccessible part of the ArrayList
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        //return the value found at the given index, but converted to the generic type
        return (E) data[index];
    }
    //Set the value at a given index to the given element, and return the pre-overwrite value
    //at that index
    public E set(int index, E element) {
    	//get the pre-overwrite value
    	//Using the get method allows us to do exception handling without repeating a line of code
        E output = get(index);
        //overwrite the given index with the given element
        data[index] = element;
        //return the pre-overwrite value
        return output;
    }
    //Remove the element at a given index from the ArrayList, and return the removed value
    public E remove(int index) {
    	//Get the value that is to be removed
    	//Using the get method allows us to do exception handling without repeating a line of code
        E output = get(index);
        //make a shallow copy of the data for rewriting purposes
        Object[] oldData = data;
        //all the data from 0 to the given index does not need to be shifted
        //shift all the data from the index to the last data left by 1
        for (int i = index; i < oldData.length - 1; i++) {
            data[i] = oldData[i+1];
        }
        //decrement size by 1 since a value was removed
        size--;
        return output;
    }
    //get the size of the ArrayList (i.e. the number of actual items in the ArrayList
    public int size() {
    	//return the size value
    	return size;
    }
}
 // Hint: the 'capacity' (length of data array) is not the same as the 'size'
 // Size is the number of elements in the ArrayList, i.e., the number of valid elements in the array
