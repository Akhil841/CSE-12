/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * This file contains the tests I wrote for various use cases of MyArrayList. The ideas were provided by the teachers of the CSE 12 
 * course, Christine Alvarado and Paul Cao.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test */
    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * Aims to test the capacity argument constructor when the input
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        MyArrayList<String> list = new MyArrayList<>(-1);
    }

    /**
     * Aims to test the Array argument constructor when the input
     * is null
     */
    @Test
    public void testConstructorNullArg(){
        MyArrayList<String> list = new MyArrayList<>(null);
        assertEquals(list.getCapacity(), 5);
    }

    /**
     * Aims to test the append method when an element is appended to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        MyArrayList<String> list = new MyArrayList<>(5);
        list.append("a");
        list.append("b");
        list.append("c");
        list.append("d");
        list.append("e");
        list.append("f");
        assertEquals(list.size(), 6);
        assertEquals(list.getCapacity(), 10);
    }

    /**
     * Aims to test the prepend method when a null element is appended
     * Checks reflection on size and capacity
     * Checks whether null was appended successfully
     */
    @Test
    public void testPrependNull(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.prepend(null);
        assertEquals(list.size(), 4);
        assertEquals(list.getCapacity(), 5);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBound(){
       MyArrayList<String> list = new MyArrayList<>();
       list.append("a");
       list.append("b");
       list.append("c");
       list.insert(20, "d");
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        for (int i = 0; i < 1000; i++) {
            list.insert(0, i);
        }
        assertEquals(list.size(), 1003);
        assertEquals(list.getCapacity(), 1280);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        list.get(3);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        String output = list.set(3, "s");
    }


    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        list.remove(3);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.expandCapacity(2);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than double(2x) the current capacity
     */
    @Test
    public void testExpandCapacityExplode(){
        MyArrayList<String> list = new MyArrayList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.expandCapacity(500);
        assertEquals(list.getCapacity(), 500);
    }

}
