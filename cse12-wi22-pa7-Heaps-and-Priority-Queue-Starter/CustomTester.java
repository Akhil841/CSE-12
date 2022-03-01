/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains all of my custom JUnit tests.
 * None of these tests match what is in the public tester.
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains all of the JUnit tests I have written
 * to test MyMinHeap.
 * 
 * These tests are all unique from the public tester.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    
    /**
     * Test the constructor when the input data contains null
     */
    @Test(expected = NullPointerException.class)
    public void testMyMinHeapConstructor() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, null, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
    }

    /**
     * Test the getMinChildIdx method on a leaf
     */
    @Test
    public void testGetMinChildIdx() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        assertEquals("getMinChildIdx of a leaf should return -1", -1, heap.getMinChildIdx(4));
    }

    /**
     * Test the percolateUp method on the root
     */
    @Test
    public void testPercolateUp() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.percolateUp(0);
        Integer[] testHeap = new Integer[]{1, 3, 2, 5, 4};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("Array should be unchanged",
            testHeap[i],
            heap.data.get(i));
        }
    }

    /**
     * Test the percolateDown method on a leaf
     */
    @Test
    public void testPercolateDown() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.percolateDown(4);
        Integer[] testHeap = new Integer[]{1, 3, 2, 5, 4};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("Array should be unchanged",
            testHeap[i],
            heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method for a nonzero index
     */
    @Test
    public void testDeleteIndex() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.deleteIndex(2);
        Integer[] testHeap = new Integer[]{1, 3, 4, 5};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("Array should have been modified",
            testHeap[i],
            heap.data.get(i));
        }
    }

    /**
     * Test the deleteIndex method on a leaf
     */
    @Test
    public void testDeleteIndex2() {
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.deleteIndex(3);
        Integer[] testHeap = new Integer[]{1, 3, 2, 4};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("None of the array values should have shifted",
            testHeap[i],
            heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting a leaf
     */
    @Test
    public void testInsert(){
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4, 2, 5, 1}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.insert(22);
        Integer[] testHeap = new Integer[]{1, 3, 2, 5, 4, 22};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("None of the array values should have shifted",
            testHeap[i],
            heap.data.get(i));
        }
    }

    /**
     * Test the insert method when inserting into the middle
     * of the heap
     */
    @Test
    public void testInsert2(){
        ArrayList<Integer> inputValues = new ArrayList<Integer>(Arrays.asList(new Integer[]{6, 8, 4, 10, 2}));
        MyMinHeap<Integer> heap = new MyMinHeap<>(inputValues);
        heap.insert(3);
        Integer[] testHeap = new Integer[]{2, 6, 3, 10, 8, 4};
        for (int i = 0; i < testHeap.length; i++) {
            assertEquals("The heap should adjust to still be a heap after the insertion",
            testHeap[i],
            heap.data.get(i));
        }
    }

   
    /**
     * Test remove on an empty heap
     */
    @Test
    public void testRemove(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        assertEquals("Remove should return null", null, heap.remove());
    }

  
    /**
     * Test getMin on an empty heap
     */
    @Test
    public void testGetMin(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        assertEquals("getMin should return null", null, heap.getMin());
    }
}