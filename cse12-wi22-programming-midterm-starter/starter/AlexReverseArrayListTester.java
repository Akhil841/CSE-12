/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements

import static org.junit.Assert.*;
import org.junit.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class AlexReverseArrayListTester {

    private Object[] listLen5;
    private MyArrayList emptyArrList, arrLen5; 
    private boolean eCaught;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){

        eCaught = false;

        listLen5 = new Integer[]{1,2,3,4,5};

        emptyArrList = new MyArrayList(null);
        arrLen5 = new MyArrayList<>(listLen5);

    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        
        //fromIndex out of bounds
        try{
            arrLen5.reverseRegion(-5, 0);
        }
        catch(IndexOutOfBoundsException e){
            eCaught = true;
        }

        assertTrue(eCaught);
        eCaught = false;

        //toIndex out of bounds
        try{
            arrLen5.reverseRegion(0, 5);
        }
        catch(IndexOutOfBoundsException e){
            eCaught = true;
        }

        assertTrue(eCaught);
        
        
        
    }

    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        

        arrLen5.reverseRegion(3, 2);
        assertArrayEquals(new Object[]{1,2,3,4,5}, arrLen5.data);

    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){

       //Reverse middle
       arrLen5.reverseRegion(2, 3);
       assertArrayEquals(new Object[]{1,2,4,3,5}, arrLen5.data);

       setUp();
       //reverse from index 0
       arrLen5.reverseRegion(0, 1);
       assertArrayEquals(new Object[]{2,1,3,4,5}, arrLen5.data);

       setUp();
       //reverse from end
       arrLen5.reverseRegion(3, 4);
       assertArrayEquals(new Object[]{1,2,3,5,4}, arrLen5.data);

    }

    /**
     *  fromIndex and toIndex are equal
    */
    @Test
    public void testReverseCustom(){
       
        try{
            emptyArrList.reverseRegion(0, 0);
        }
        catch(IndexOutOfBoundsException e){
            eCaught = true;
        }

        assertTrue(eCaught);


        arrLen5.reverseRegion(2, 2);
        assertArrayEquals(new Object[]{1,2,3,4,5}, arrLen5.data);



    }

  

}
