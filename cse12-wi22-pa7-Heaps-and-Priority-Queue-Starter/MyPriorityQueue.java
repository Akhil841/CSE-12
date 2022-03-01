/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * Contains an implementation of a priority queue based on
 * my implementation of a min heap. 
 * That implementation uses a java.util.ArrayList, which
 * in turn is implemented with an array. Funny how ADTs work.
 */

import java.util.Collection;

/**
 * Contains an implementation of a priority queue
 * using a min heap.
 * As you might expect, a lower priority means it goes higher on the list.
 */
public class MyPriorityQueue<E extends Comparable<E>>
{

    //"heap" is of a generic MyMinHeap type
    MyMinHeap<E> heap;
    
    /**
     * Constructor that creates an empty priority queue
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Constructor that creates a priority queue from a collection
     * @param collection The collection used to intialize priority queue
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Adds an element to the priority queue
     * @param element the element to be added
     */
    public void push(E element){
        heap.insert(element);
    }

    /**
     * Removes the element with the highest priority from the priority queue 
     * @return the element with the highest priority
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * Sees the element with the highest priority from the priority queue
     * @return the element with the highest priority
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Finds the number of elements in the priority queue
     * @return the number of elements in the priority queue
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Remove all the elements from the priority queue.
     */
    public void clear(){
        heap.clear();
    }
}