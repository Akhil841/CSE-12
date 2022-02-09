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
public class AlexReverseLinkedListTester {

    private Object[] listLen5, linkedListData;
    private MyLinkedList emptyList, linkedLen5; 
    private boolean eCaught;
    /**
     * Run before every test
     */
    @Before
    public void setUp(){

        eCaught = false;

        listLen5 = new Integer[]{1,2,3,4,5};

        emptyList = new MyLinkedList(null);
        linkedLen5 = new MyLinkedList(listLen5);

    }

    public void getLinkedListdata(MyLinkedList l){
        linkedListData = new Object[l.size];

        MyLinkedList.Node currNode = l.getNth(0);

        for(int i = 0; i < l.size; i++){

            linkedListData[i] = currNode.getElement();
            currNode = currNode.getNext();
            
        }
    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        
        //fromIndex out of bounds
        try{
            linkedLen5.reverseRegion(-5, 0);
        }
        catch(IndexOutOfBoundsException e){
            eCaught = true;
        }

        assertTrue(eCaught);
        eCaught = false;

        //toIndex out of bounds
        try{
            linkedLen5.reverseRegion(0, 5);
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
        

        linkedLen5.reverseRegion(3, 2);

        getLinkedListdata(linkedLen5);
        
        assertArrayEquals(new Object[]{1,2,3,4,5}, linkedListData);

    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds
    */
    @Test
    public void testReverseIndexWithinBounds(){

       //Reverse middle
       linkedLen5.reverseRegion(2, 3);
       getLinkedListdata(linkedLen5);
       assertArrayEquals(new Object[]{1,2,4,3,5}, linkedListData);

       setUp();
       //reverse from index 0
       linkedLen5.reverseRegion(0, 1);
       getLinkedListdata(linkedLen5);
       assertArrayEquals(new Object[]{2,1,3,4,5}, linkedListData);

       setUp();
       //reverse from end
       linkedLen5.reverseRegion(3, 4);
       getLinkedListdata(linkedLen5);
       assertArrayEquals(new Object[]{1,2,3,5,4}, linkedListData);

    }

    /**
     *  fromIndex and toIndex are equal
    */
    @Test
    public void testReverseCustom(){
       
        try{
            emptyList.reverseRegion(0, 0);
        }
        catch(IndexOutOfBoundsException e){
            eCaught = true;
        }

        assertTrue(eCaught);


        linkedLen5.reverseRegion(2, 2);
        getLinkedListdata(linkedLen5);
        assertArrayEquals(new Object[]{1,2,3,4,5}, linkedListData);



    }


}
