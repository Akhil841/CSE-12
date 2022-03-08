/**
 * Name: Akhil Pillai
 * PID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: PublicTester
 * 
 * Contains custom tests for MyBST, MyBSTIterator,
 * and MyCalendar. No tests are copied from the public tester.
 */
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Contains custom tests for MyBST, MyBSTIterator,
 * and MyCalendar. No tests are copied from the public tester.
 */
public class CustomTester {
    //copying test data structures
    //from public tester
    MyBST<Integer, Integer> completeTree;
    MyCalendar cal;

    // 
    /**
     * The setup method create a complete tree with height three
     * The tree has following structure and will be used as testing purpose
     *           4
     *         /  \
     *       2     6
     *     /  |   /
     *    1   3  5
     */
    @Before
    public void setup(){
        cal = new MyCalendar();
        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = 
            new MyBST.MyBSTNode<>(5, 50, six);

        this.completeTree = new MyBST<>();
        this.completeTree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        this.completeTree.size = 6;
    }

    /**
     * Test when a null key is inserted into BST
     */
    @Test(expected = NullPointerException.class)
    public void testInsertNullKey() {
        completeTree.insert(null, 5);
    }

    /**
     * Test wehn a null value is inserted into BST
     */
    @Test
    public void testInsertNullValue() {
        completeTree.insert(7, null);
        assertEquals("The node with key 7 should have value null", 
            null, completeTree.search(7));
        assertEquals("The size of the tree should have increased",
            7, completeTree.size);
    }

    /**
     * Test when BST is searched for null
     */
    @Test
    public void testSearchNull() {
        assertEquals("Searching null should return null", null, completeTree.search(null));
    }

    /**
     * Test when we attempt to remove a node with 2 children
     * from BST
     */
    @Test
    public void testRemove2ChildNode() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertSame("Rmove should work as usual", 1, completeTree.remove(2));
        assertSame("Successor should have shifted into removed node's position", 3, root.getLeft().getKey());
    }

    /**
     * Test when we remove a node that is not
     * in the BST
     */
    @Test
    public void testRemoveBadKey() {
        assertEquals("Remove should return null if we try to remove a key" + 
        " that is not in the BST", null, completeTree.remove(7));
    }

    /**
     * Test when we remove null from the BST
     */
    @Test
    public void testRemoveNull() {
        assertEquals("remove(null) should return null", null, completeTree.remove(null));
    }

    /**
     * Test inorder traversal of empty BST
     */
    @Test
    public void testInorderEmpty() {
        MyBST<Integer, Integer> emptyBST = new MyBST<>();
        assertEquals("Inorder on an empty BST should return an empty list", 
        new ArrayList<MyBST.MyBSTNode<Integer, Integer>>(), emptyBST.inorder());
    }

    /**
     * Test iteration past the last element of BST
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorOver() {
        MyBSTIterator<Integer, Integer>.MyBSTValueIterator vi = new MyBSTIterator<Integer, Integer>()
            .new MyBSTValueIterator(completeTree.root);
        while (vi.hasNext()) {
            vi.nextNode();
        }
        vi.nextNode();
    }

    /**
     * Check if calendar is storing data in a MyTreeMap and not a BST
     */
    @Test
    public void testCalendarConstructor() {
        assertTrue("Calendar should be a MyTreeMap and not a BST", cal.getCalendar() instanceof MyTreeMap);
    }
    /**
     * Test various exceptions that calendar should handle
     */
    @Test
    public void testCalendarExceptionHandling() {
        try {
            cal.book(-1, 6);
        }
        catch (IllegalStateException e) {
            assertEquals("Size should be unchanged after bad booking", 
                0, cal.calendar.size());
        }
        try {
            cal.book(6, 5);
        }
        catch (IllegalStateException e) {
            assertEquals("Size should be unchanged after bad booking", 
                0, cal.calendar.size());
        }
    }
}
