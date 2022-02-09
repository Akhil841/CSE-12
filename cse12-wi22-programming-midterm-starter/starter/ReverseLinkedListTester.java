/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements

import org.junit.*;
import static org.junit.Assert.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseLinkedListTester {
    public String[] LLToStringArr(MyLinkedList<String> list) {
        String[] out = new String[list.size];
        for (int i = 0; i < out.length; i++) {
            out[i] = list.getNth(i).data;
        }
        return out;
    }
    MyLinkedList<String> test;
    String[] values = { "word0", "word1", "word2", "word3", "word4"};
    /**
     * Run before every test
     */
    @Before
    public void setUp() {
        test = new MyLinkedList<String>(values);
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReverseIndexOutOfBounds(){
        test.reverseRegion(-1, 7);
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        test.reverseRegion(3, 2);
        assertArrayEquals(values, LLToStringArr(test));
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){
        test.reverseRegion(1, 3);
        String[] out = {"word0", "word3", "word2", "word1", "word4"};
        assertArrayEquals(out, LLToStringArr(test));
    }

    /**
    * Tests reverseRegion across an entire ArrayList
    * 
    * This is different from the other tests because it actually
    * tests for a working output from the reverseRegion method,
    * and is checking across an entire ArrayList. This is important 
    * because the method may break if it has to begin from the first
    * index or end at the last index.
    */
    @Test
    public void testReverseCustom(){
        test.reverseRegion(0, 4);
        String[] out = {"word4", "word3", "word2", "word1", "word0"};
        assertArrayEquals(out, LLToStringArr(test));
    }
}
