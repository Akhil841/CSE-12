/**
 * Name: Alex Oshima
 * ID: A16959817
 * Email: aoshima@ucsd.edu
 * File description: File containing test methods for NaryTree class
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * Test Class for CSE12NaryTree.java
 */
public class AlexFinalTester {
    
    CSE12NaryTree<Integer> fiveNary;
    CSE12NaryTree<Integer> emptyTriNary;

    @Before
    public void setup(){
        fiveNary = new CSE12NaryTree<Integer>(5);
        emptyTriNary = new CSE12NaryTree<Integer>(3);
        CSE12NaryTree<Integer>.Node root = fiveNary.new Node(0);
        fiveNary.root = root;
        fiveNary.size = 1;

    }
    
    /**
     * Tests the add method on a 5-ary tree that already contains only a root node and its 5 children nodes.
     */
    @Test
    public void testAdd(){

        for(int i = 1; i <= 5; i++){
            fiveNary.root.getChildren().add(fiveNary.new Node(i));
        }

        fiveNary.size = 6;

        fiveNary.add(6);

        assertEquals(7, fiveNary.size);
        assertTrue(fiveNary.contains(6));
        assertEquals(fiveNary.root.getChildren().get(0).getChildren().get(0).getData(),(Integer)6);


    }

    /**
     * Tests the contains method on a 5-ary tree when the element is not present in it.
     */
    @Test
    public void testContains(){

        assertFalse(fiveNary.contains(-1));
        
    }

    /**
     * Tests the sortTree method on a 5-ary tree that contains only the root node.
     */
    @Test
    public void testSortTree(){

        ArrayList<Integer> actual = fiveNary.sortTree();
        
        assertEquals(1, actual.size());
        assertEquals((Integer)0, actual.get(0));
        
    }

    /**
     * Test add, contains and sortTree method on an empty tree
     */
    @Test
    public void testCustom(){

        emptyTriNary.add(5);
        assertEquals(1, emptyTriNary.size);
        assertTrue(emptyTriNary.contains(5));

        setup();
        emptyTriNary.contains(0);
        assertEquals(0, emptyTriNary.size);
        
        setup();
        ArrayList<Integer> actual = emptyTriNary.sortTree();

        assertTrue(actual.isEmpty());
    }

    /**
     * Test contains on trees of varying size
     */
    @Test
    public void customContainsTest(){
        
    }

    /**
     * Test sortTree on unsorted tree
     */
    @Test
    public void customSortTree(){
        emptyTriNary.add(100);
        emptyTriNary.add(10);
        emptyTriNary.add(0);
        emptyTriNary.add(50);
        assertEquals(emptyTriNary.root.getData(), (Integer)100);
        assertEquals(emptyTriNary.root.getNumChildren(), 3);
        
        emptyTriNary.add(15); 
        emptyTriNary.add(75);
        assertEquals(emptyTriNary.root.getChildren().get(0).getNumChildren(), 2);

        
        ArrayList<Integer> actual = emptyTriNary.sortTree();
        assertEquals(6, actual.size());

        ArrayList<Integer> expected = new ArrayList<Integer>();

        expected.add(0);
        expected.add(10);
        expected.add(15);
        expected.add(50);
        expected.add(75);
        expected.add(100);


        for(int i = 0; i < actual.size(); i++){
            assertEquals(expected.get(i), actual.get(i));
        }
        

    }
}
