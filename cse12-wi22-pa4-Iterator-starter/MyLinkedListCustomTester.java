
/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains tests that I wrote to test the efficacy of my 
 * implementation of a ListIterator. It tests cases that are different
 * from those of the public tester.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This class all the tests I have written for MyListIterator's various methods.
 * It also contains a setUp method to streamline initialization
 * of MyLinkedLists for testing.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList<String> listLen0, listLen1, listLen2;
    private MyLinkedList<String>.MyListIterator listLen0Iter, listLen1Iter, listLen2Iter;

    /**
     * Initializes up the LinkedLists we will use before execution.
     * These are the same LinkedLists as are used in the public tester.s
     */
    @Before
    public void setUp() throws Exception {
        listLen0 = new MyLinkedList<>();
        listLen0Iter = listLen0.new MyListIterator();

        listLen1 = new MyLinkedList<>();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList<>();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Test the hasNext method on an empty list
     */
    @Test
    public void testHasNext() {
        //This should return false since listLen0Iter.right = listLen0.tail
        //as per the constructor
        assertFalse("hasNext() should return false for an empty list", listLen0Iter.hasNext());
    }

    /**
     * Test the next method on an empty list
     */
    @Test(expected = NoSuchElementException.class)
    public void testNext() {
        //This should throw a NoSuchElementException since there is no element 
        //in the list to return
        String out = listLen0Iter.next();
    }

    /**
     * Test the hasPrevious method on an empty list
     */
    @Test
    public void testHasPrevious() {
        //This should return false since listLen0Iter.left = listLen0.head
        //as per the constructor
        assertFalse("hasNext() should return false for an empty list", listLen0Iter.hasPrevious());
    }

    /**
     * Test the previous method on an empty list
     */
    @Test(expected = NoSuchElementException.class)
    public void testPrevious() {
        //This should throw a NoSuchElementException since there is no element 
        //in the list to return
        String out = listLen0Iter.previous();
    }

    /**
     * Test the nextIndex method on the middle of a list
     */
    @Test
    public void testNextIndex() {
        //Add another element to the list to test in the middle
        listLen2.add("CSE12");
        //Iterate to the next element in the list
        //(We know that the next method works from the previous)
        //tests so we use it here
        listLen2Iter.next();
        listLen2Iter.next();
        //nextIndex should be 2 at this point since that 
        //is the index of the final value in the 
        //LinkedList.
        assertEquals(2, listLen2Iter.nextIndex());
    }

    /**
     * Test the previousIndex method on an empty list
     */
    @Test
    public void testPreviousIndex() {
        //Since there is no element to go backwards to,
        //this should return -1.
        assertEquals(-1, listLen0Iter.previousIndex());
    }

    /**
     * Test the set method before using next or previous
     */
    @Test(expected = IllegalStateException.class)
    public void testSet() {
        //This should return an IllegalStateException since
        //the Iterator doesn't know which value to set to
        listLen1Iter.set("Professor");
    }

    /**
     * Test the remove method before using next or previous
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveTestOne() {
        //This should return an IllegalStateException since
        //the Iterator doesn't know which value to remove
        listLen1Iter.remove();
    }

    /**
     * Test the remove method on a list with only one item
     */
    @Test
    public void testRemoveTestTwo() {
        //Use next() to update canRemoveOrSet
        listLen1Iter.next();
        //canRemoveOrSet should be true
        assertEquals(true, listLen1Iter.canRemoveOrSet);
        //Use remove();
        listLen1Iter.remove();
        //List should be empty
        assertEquals(listLen1.size, 0);
        //canRemoveOrSet should be false
        assertEquals(false, listLen1Iter.canRemoveOrSet);
    }

    /**
     * Attempt to add a null value
     */
    @Test(expected = NullPointerException.class)
    public void testAdd() {
        listLen0Iter.add(null);
    }

}