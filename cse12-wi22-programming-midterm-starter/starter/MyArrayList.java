/**
 * 
 * 
 * Name: Akhil Pillai
 * ID: A16724533   
 * Email: avpillai@ucsd.edu
 * File description: 
 * This file contains an implementation of an ArrayList. There is also a new method,
 * reverseRegion, that reverses the positions of a set of objects within the
 * ArrayList.
 */

/**
 * This class contains the MyArrayList implementation.
 * It has less methods than a regular implementation
 * of ArrayList, but contains a new method that reverses the 
 * order of a set of objects within the ArrayList:
 * reverseRegion
 */
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
        }
    }

    /**
	 * Reverses values in the MyArrayList from fromIndex to toIndex, inclusive.
     * The list is unchanged if fromIndex >= toIndex
     * @param fromIndex The value to start reversing from. (inclusive)
     * @param toIndex The value to finish reversing from. (inclusive)
     * @throws IndexOutOfBoundsException if fromIndex or toIndex is not 
     * in the MyArrayList
	 */
    public void reverseRegion(int fromIndex, int toIndex) {
       //throw exception if attempting to access nonexistent parts of the ArrayList
       if (fromIndex < 0 || toIndex >= size) throw new IndexOutOfBoundsException();
       //do nothing if fromIndex is greater than or equal to toIndex
       if (fromIndex >= toIndex) return;
       //swap data from end to end and ending at the middle
       for (int i = 0; i < (toIndex - fromIndex)/2 + 1; i++) {
            Object temp = data[toIndex - i];
            data[toIndex - i] = data[fromIndex + i];
            data[fromIndex + i] = temp;
       }
    }

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
