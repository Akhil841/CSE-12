/**
 * TODO: Add file header
 * Name:
 * ID:
 * Email:
 * File description: 
 */
 
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * TODO: Add class header
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
     * TODO: Add test case description.
     */
    @Test
    public void testAdd(){
        populateDS();
        ds.add(23);
        assertEquals("Size should increase", 7, ds.size);
        assertSame("Node should be in correct location", 23, ds.root.children.get(0).children.get(0).data);
    }

    /**
     * TODO: Add test case description
     */
    @Test
    public void testContains(){
        populateDS();
        assertFalse("N-ary tree should not contain 15", ds.contains(15));
    }

    /**
     * TODO: Add test case description
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
}
