/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This contains my implementation of a min heap, as specified in PA7. 
 * It implements all the methods in MinHeapInterface, since it implements
 * that interface.
 */

import java.util.ArrayList;
import java.util.Collection;
/**
 * A generic implementation of a min heap.
 * Contains insert method, getMin method, remove method,
 * size method, and clear method.
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{
    /** 
     * The contents of the heap.
    */
    ArrayList<E> data;
    /**
     * Zero-element constructor for a min heap.
     */
    public MyMinHeap() {
        data = new ArrayList<E>();
    }

    /**
     * Constructs a min heap based on provided input data.
     * @param collection The collection to sort into a heap
     * @throws NullPointerException if any of the data in the collection is null
     */
    public MyMinHeap(Collection<? extends E> collection) {
        data = new ArrayList<E>(collection);
        for (int i = data.size() - 1; i >= 0; i--) {
            if (data.get(i) == null) throw new NullPointerException();
            percolateDown(i);
        }
    }

    /**
     * Inserts an element into its correct position in the heap.
     * @param element The value to insert
     * @throws NullPointerException if element is null
     */
    public void insert(E element) {
        //only non-null elements may be added
        if (element == null) throw new NullPointerException();
        //add the new element to the last position on the heap
        data.add(element);
        //percolate it up if necessary
        percolateUp(data.size() - 1);
    }

    /**
     * Returns the smallest value in the heap.
     * @return null if the heap is empty,
     * the smallest value in the heap otherwise
     */
    public E getMin() {
        //return null if the heap is empty
        if (data.size() == 0) return null;
        //return root otherwise
        return data.get(0);
    }

    /**
     * Removes the root from the heap, and returns it.
     * @return null if the heap is empty,
     * the root otherwise
     */
    public E remove() {
        //return null if the heap is empty
        if (data.size() == 0) return null;
        //delete root and return it otherwise
        return deleteIndex(0);
    }

    /**
     * @return The size of the heap.
     */
    public int size() {
        //return the size of the heap
        return data.size();
    }

    /**
     * Clears out the entire heap, and expunges all
     * of its data.
     */
    public void clear() {
        //set the heap to an empty ArrayList
        data = new ArrayList<E>();
    }

    /**
     * Swaps two elements in the heap.
     * @param from The index of the value to swap from
     * @param to The index of the value to swap to
     */
    protected void swap(int from, int to) {
        //swap algorithm
        E temp = data.get(to);
        data.set(to, data.get(from));
        data.set(from, temp);
    }

    /**
     * @return The parent of the provided value.
     * @param index The index of the value whose parent
     * you want to know
     */
    protected int getParentIdx(int index) {
        return (index - 1) / 2;
    }

    /**
     * @return The left child of the provided value.
     * @param index The index of the value whose left child
     * you want to know
     */
    protected int getLeftChildIdx(int index) {
        return 2 * index + 1;
    }

    /**
     * @return the right child of the provided value.
     * @param index The index of the value whose right child
     * you want to know
     */
    protected int getRightChildIdx(int index) {
        return 2 * index + 2;
    }

    /**
     * @return the left child of the input node if it's smaller or equal to
     * the right, the right child otherwise.
     * @param index The index of the node whose 
     * min child you want to know
     */
    protected int getMinChildIdx(int index) {
        //if the node is a leaf, return -1
        if (getLeftChildIdx(index) >= data.size() && getRightChildIdx(index) >= data.size()) return -1;
        //if the node has only one child, return the left child
        if (getRightChildIdx(index) >= data.size()) return getLeftChildIdx(index);
        //if the right child is less than the left child, return the right child
        if (data.get(getRightChildIdx(index)).compareTo(data.get(getLeftChildIdx(index))) == -1) 
            return getRightChildIdx(index);
        //if the left child is less than the right child or if they are equal,
        //return the left child
        return getLeftChildIdx(index);
    }

    /**
     * Percolates a given value as far up as it goes
     * as long as it meets heap rules.
     * @param index The value that you want to percolate
     */
    protected void percolateUp(int index) {
        //do nothing if we are attempting to up-percolate
        //the root
        if (index == 0) return;
        //do nothing if we cannot up-percolate the data
        //without violating heap rules
        if (data.get(getParentIdx(index)).compareTo(data.get(index)) == -1) return;
        //up-percolate the data once
        swap(getParentIdx(index), index);
        //up-percolate the data again if possible
        percolateUp(getParentIdx(index));
    }

    /**
     * Percolates a given value as far down as it goes
     * as long as it meets heap rules.
     * Swaps with the left child if it is smaller or equal
     * to the right child, the right child otherwise.
     * @param index The index of the value that you want to percolate
     */
    protected void percolateDown(int index) {
        //do nothing if we are attempting to down-percolate
        //a leaf
        if (getMinChildIdx(index) == -1) return;
        //do nothing if we cannot down-percolate the data
        //without violating heap rules
        if (data.get(getLeftChildIdx(index)).compareTo(data.get(index)) == 1 &&
        data.get(getRightChildIdx(index)).compareTo(data.get(index)) == 1) return;
        //store index for next down-percolation here
        int nextPD = getMinChildIdx(index);
        //down-percolate the data once with the smaller of the 
        //left and right children
        swap(nextPD, index);
        //down-percolate again if possible
        percolateDown(nextPD);
    }

    /**
     * Deletes the value from the heap, returns it,
     * and resets the heap.
     * @param index The index of the value you want to
     * delete
     */
    protected E deleteIndex(int index) {
        //Do nothing special if the value to delete is
        //the last value in the heap
        if (index == data.size() - 1) {
            //remove and get the value to be returned
            E output = data.remove(index);
            //delete it
            return output;
        }
        //Delete the value, swap it with the heap's last
        //value, and percolate that value up or down
        //until it's a heap again
        else {
            //remove and get the output value
            E output = data.remove(index);
            //set the last value to the index that
            //the removed value used to occupy
            data.add(index, data.get(data.size() - 1));
            //remove the extra copy of the last value
            data.remove(data.size() -1);
            //if the swapped data is less than the value 
            //it replaced
            if (output.compareTo(data.get(index)) == 1) {
                //percolate it up
                percolateUp(index);
            }
            //otherwise
            else {
                //percolate it down
                percolateDown(index);
            }
            //return the removed value
            return output;
        }
    }
}