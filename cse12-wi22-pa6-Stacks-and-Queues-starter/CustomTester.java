/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains my custom tests for MyDeque, MyQueue and MyStack.
 * MyDeque has 8 tests, MyStack has 1, and MyDeque has 1.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains all of my JUnit tests for MyDeque, MyStack
 * and MyQueue. These tests are all different from the public tester's
 * tests.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initialCapacity < 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMyDequeConstructor() {
        MyDeque<Integer> deque = new MyDeque<>(-10);
    }

    /**
     * Test the expandCapacity method when deque has no data
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(0);
        deque.expandCapacity();
        assertEquals("Deque should have size of 10", 10, deque.data.length);
        assertTrue("Deque should have no elements", deque.size == 0);
    }

    /**
     * Test the addFirst method when the elements are at the begining of the deque.
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] preAddFirst = { 1, 2, 3, null, null};
        deque.size = 3;
        deque.front = 0;
        deque.data = preAddFirst;
        deque.addFirst(0);
        assertEquals("Size should have increase by 1", 4, deque.size);
        assertEquals("Data capacity should not have changed", 5, deque.data.length);
        assertEquals("The value at index 4 should be 0", 0, deque.data[4]);
        assertEquals("The value at index 3 should still be null", null, deque.data[3]);
    }

    /**
     * Test the addLast method when the elements are at the end of the deque.
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] preAddFirst = { null, null, 1, 2, 3};
        deque.size = 3;
        deque.data = preAddFirst;
        deque.rear = 4;
        deque.addLast(0);
        assertEquals("Size should have increase by 1", 4, deque.size);
        assertEquals("Data capacity should not have changed", 5, deque.data.length);
        assertEquals("The value at index 0 should be 0", 0, deque.data[0]);
        assertEquals("The value at index 1 should still be null", null, deque.data[1]);
    }

    /**
     * Test the removeFirst method on an empty deque
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer out = deque.removeFirst();
        assertEquals("null should be returned", null, out);
        assertEquals("Size should be unchanged", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be 0", 0, deque.rear);
    }

    /**
     * Test the removeLast method on an empty deque
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer out = deque.removeLast();
        assertEquals("null should be returned", null, out);
        assertEquals("Size should be unchanged", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be 0", 0, deque.rear);
    }

    /**
     * Test the peekFirst method on an empty deque
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer out = deque.peekFirst();
        assertEquals("null should be returned", null, out);
        assertEquals("Size should be unchanged", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be 0", 0, deque.rear);
    }

    /**
     * Test the peekLast method on an empty deque
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer out = deque.peekLast();
        assertEquals("null should be returned", null, out);
        assertEquals("Size should be unchanged", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be 0", 0, deque.rear);
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack.empty() on a populated stack
     */
    @Test
    public void testMyStack(){
        MyStack<Integer> stack = new MyStack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse("Stack should not be empty", stack.empty());
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue.enqueue(E) on a populated queue
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(5);
        queue.theQueue.data = new Integer[]{1, 2, 3, null, null};
        queue.theQueue.size = 3;
        queue.theQueue.front = 0;
        queue.theQueue.rear = 2;
        queue.enqueue(4);
        assertArrayEquals("The queue should contain {1, 2, 3, 4, null}", new Integer[]{1, 2, 3, 4, null}, queue.theQueue.data);
    }
}
