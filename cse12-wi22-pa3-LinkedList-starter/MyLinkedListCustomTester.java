/**
 * Name: Akhil Pillai
 * Email: avpillai@ucsd.edu
 * 
 * Contains tests I have written to test my LinkedList
 * implementation. All methods in LinkedList are tested.
 * except isEmpty().
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains the methods that do the actual testing
 * of MyLinkedList
 * 
 * IMPORTANT: Do not change the method headers and points are awarded 
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	MyLinkedList<Integer> list1;
	MyLinkedList<String> list2;
	MyLinkedList<Integer>.Node node1;
	MyLinkedList<Integer>.Node node2;
	MyLinkedList<Integer>.Node node3;
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		list1 = new MyLinkedList<>();
		list2 = new MyLinkedList<>();
	}
	
	private void populateLinkedList() {
		this.node1 = this.list1.new Node(1);
		this.node2 = this.list1.new Node(2);
		this.node3 = this.list1.new Node(3);
		this.list1.head.setNext(this.node1);
		this.node1.setPrev(this.list1.head);
		this.node1.setNext(this.node2);
		this.node2.setPrev(this.node1);
		this.node2.setNext(this.node3);
		this.node3.setPrev(this.node2);
		this.node3.setNext(this.list1.tail);
		this.list1.tail.setPrev(this.node3);
		this.list1.size = 3;
	}

	/**
	 * add(E) should always return true
	 */
	@Test
	public void testAdd() {
		populateLinkedList();
		boolean value = list1.add(5);
		assertEquals(value, true);
	}

	/**
	 * You should not be able to add elements to the LL
	 * with index less than 0
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddWithIndexTestOne() {
		list2.add(-1, "Hello");
	}

	/**
	 * You should not be able to add null elements
	 * to the LL.
	 */	
	@Test(expected = NullPointerException.class)
	public void testAddWithIndexTestTwo() {
		populateLinkedList();
		list1.add(2, null);
	}

	/**
	 * You should not be able to get at an index greater than size.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet() {
		list2.get(5);
	}

	/**
	 * Test getting the last node in a list.
	 */
	@Test
	public void testGetNth() {
		populateLinkedList();
		int out = this.list1.get(this.list1.size()-1);
		assertSame(out, this.node3.getElement());
	}

	/**
	 * You should not be able to set to an index less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSet() {
		list2.set(-4, "testtest");
	}

	/**
	 * You should not be able to remove from an index less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveTestOne() {
		populateLinkedList();
		list1.remove(-6);
	}
	
	/**
	 * You should not be able to remove from an index greater than size.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveTestTwo() {
		populateLinkedList();
		list1.remove(5);
	}

	/**
	 * Test clearing an empty list.
	 */
	@Test
	public void testClear() {
		list2.clear();
		//size should be 0
		assertEquals(list2.size, 0);
		//only head and tail node should be in the list
		//this will happen if list2.get(0) is null.
		assertNull(list2.get(0));
	}

	/**
	 * Check size of a non-empty list.
	 */
	@Test
	public void testSize() {
		populateLinkedList();
		assertEquals(this.list1.size(), 3);
	}
}