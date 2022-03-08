import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;



public class AlexCustomTester {
    MyBST<Integer, Integer> completeTree;
    MyBST<Integer, Integer> rSkewTree;

    @Before
    public void setup(){

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


        MyBST.MyBSTNode<Integer, Integer> rootr = 
            new MyBST.MyBSTNode<>(1, 1, null);
        MyBST.MyBSTNode<Integer, Integer> twor = 
            new MyBST.MyBSTNode<>(2, 1, rootr);
         MyBST.MyBSTNode<Integer, Integer> sixr = 
            new MyBST.MyBSTNode<>(6, 1, twor);

        this.rSkewTree = new MyBST<>();
        this.rSkewTree.root = rootr;
        rootr.setRight(twor);
        twor.setRight(sixr);
        this.rSkewTree.size = 3;

    }


    /**
     * Testing predecessor method on tree
     * 1
     *  \
     *   2
     *     \
     *      6
     */
    @Test
    public void predecessorTest(){

        MyBST.MyBSTNode<Integer, Integer> root = rSkewTree.root;

        assertEquals(null, root.predecessor());
        assertEquals(root, root.getRight().predecessor());
        assertEquals(root.getRight(), root.getRight().getRight().predecessor());

        

    }
    /**
     * Testing predeccsor method when tree
     *          4
     *         /  \
     *       2     6
     *     /  |   /
     *    1   3  5
     */
    @Test
    public void predecessorTest2(){
        
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;

        MyBST.MyBSTNode<Integer,Integer> curr = root.getLeft().getLeft();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> successor = new ArrayList<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> predecessor = new ArrayList<>();
        
        while(curr.successor() != null){
            successor.add(curr);
            curr = curr.successor();
        }
        successor.add(curr);
        
       
        
        while(curr != null){
            predecessor.add(curr);
            curr = curr.predecessor();
        }
        
        

        for(int i = 0; i < 6; i++){
            assertEquals(successor.get(i), predecessor.get(5 - i));
        }
        
    }

    /**
     * Test for insert if exisiting key
     */
    @Test
    public void insertTest(){
        
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;

        completeTree.insert(5, 999);
        
        assertEquals((Integer)999, root.getRight().getLeft().getValue());

    }

    /**
     * Test for insert on empty tree
     */
    @Test
    public void insertTest2(){
        
        MyBST<Integer, Integer> emptyTree = new MyBST<>();
        emptyTree.insert(5, 50);

        assertSame(emptyTree.root.getKey(), 5);
        assertEquals(emptyTree.size(), 1);

    }



    /**
     * Test remove root of tree
     */
    @Test
    public void removeTest(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root;
        assertEquals((Integer)1, completeTree.remove(4));

        assertNull(root.getRight().getLeft());
        assertEquals((Integer)5, root.getKey());
        assertEquals((Integer)50, root.getValue());
        assertEquals(5, completeTree.size());
    }

     /**
     * Test remove root null key
     */
    @Test
    public void removeTest2(){
       

        assertNull(completeTree.remove(null));
        //size remains the same
        assertEquals(completeTree.size(), 6);
        
    }

    /**
     * Test remove last element in tree
     */
    @Test
    public void removeTest3(){
       
        MyBST<Integer, Integer> tree = new MyBST<>();
        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<Integer, Integer>(1, 1, null);
       tree.root = root;
       tree.size = 1;

       assertSame(1,tree.remove(1));
       assertEquals(0, tree.size());
       assertEquals(null, tree.root);
        
        
    }

    /**
     * Test remove when element doesnt exisit
     */
    @Test
    public void removeTest4(){
       
       

       assertEquals(null,completeTree.remove(999));
       assertEquals(6, completeTree.size());
       
        
        
    }
    /**
     * Test inorder method on skewed tree
     */
    @Test
    public void inorderTest(){
        MyBST.MyBSTNode<Integer, Integer> root = rSkewTree.root;
        
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expected = new ArrayList<>();
        expected.add(root);
        expected.add(root.getRight());
        expected.add(root.getRight().getRight());

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actual = rSkewTree.inorder();

        for(int i = 0; i < 3; i++){
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    /**
     * Test inorder method on empty tree
     */
    @Test
    public void inorderTest2(){
        MyBST<Integer, Integer> emptyTree = new MyBST<>();
       
        
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expected = new ArrayList<>();

        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actual = emptyTree.inorder();

        assertEquals(expected, actual);

    }

    


    

}
