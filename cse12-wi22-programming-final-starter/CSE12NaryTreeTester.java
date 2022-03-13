/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * File description: Contains various tests for the N-ary
 * tree's methods.
 * Three of the tests are provided and one is custom.
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * This class contains tests for the N-ary tree.
 * It tests the add, contains, and sortTree methods.
 */
public class CSE12NaryTreeTester {
    CSE12NaryTree<Integer> ds;
    @Before
    public void init() {
        ds = new CSE12NaryTree<Integer>(5);
    }

    public void populateDS() {
        ds.root = ds.new Node(5);
        ds.root.children.add(ds.new Node(7));
        ds.root.children.add(ds.new Node(3));
        ds.root.children.add(ds.new Node(12));
        ds.root.children.add(ds.new Node(21));
        ds.root.children.add(ds.new Node(2));
        ds.size = 6;
    }
    /**
     * Tests the add method on a 5-ary tree that already 
     * contains only a root node and its 5 children nodes.
     */
    @Test
    public void testAdd(){
        populateDS();
        ds.add(23);
        assertEquals("Size should increase", 7, ds.size);
        assertSame("Node should be in correct location", 23, ds.root.children.get(0).children.get(0).data);
    }

    /**
     * Tests the contains method on a 5-ary tree when 
     * the element is not present in it.
     */
    @Test
    public void testContains(){
        populateDS();
        assertFalse("N-ary tree should not contain 15", ds.contains(15));
    }

    /**
     * Tests the sortTree method on a 5-ary tree 
     * that contains only the root node.
     */
    @Test
    public void testSortTree(){
        ds.root = ds.new Node(5);
        ds.size = 1;
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        ArrayList<Integer> actual = ds.sortTree();
        assertEquals("Tree should be sorted", expected, actual);
    }

    /**
     * Test when we attempt to add null to a 5-ary tree
     * 
     * This method checks whether the N-ary tree uses exception
     * handling to prevent null elements from being added to the 
     * tree.
     * 
     * It differs from the existing add test because that method
     * merely tests if add puts the element in the correct location.
     * 
     * It differs from the contains and sortTree tests because it only
     * tests the add method.
     */
    @Test(expected = NullPointerException.class)
    public void testCustom(){
        populateDS();
        ds.add(null);
    }

    /**
     * Test the add method on the following tree:
     * 
     *                                      5
     *                 7          3         12          21        2
     *      13 28 55 37 40
     * The size should increase and the added node should be in the correct location
     * (i.e. it should be the first child of 3)
     */
    @Test
    public void testAddExpanded() {
        populateDS();
        CSE12NaryTree<Integer>.Node childNode = ds.root.children.get(0);
        childNode.addChild(ds.new Node(13));
        childNode.addChild(ds.new Node(28));
        childNode.addChild(ds.new Node(55));
        childNode.addChild(ds.new Node(37));
        childNode.addChild(ds.new Node(40));
        ds.size = 11;
        ds.add(90);
        assertEquals("Size should increase", 12, ds.size);
        assertSame("Node should be in correct location", 90, ds.root.children.get(1).children.get(0).data);
    }

    /**
     * Tests sortTree on a populated list.
     */
    @Test
    public void testSortPopulated() {
        populateDS();
        ArrayList<Integer> actual = ds.sortTree();
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        expected.add(12);
        expected.add(21);
        assertEquals(expected, actual);
    }
}
