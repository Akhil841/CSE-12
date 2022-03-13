/**
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * File description: 
 * Contains an implementation of an "N-ary" tree, 
 * as described in the final's specification.
 * An N-ary tree is a generalized form of a binary tree 
 * where a node can have up to N children.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Contains the N-ary tree implementation.
 * The tree has add, contains, and sortTree methods.
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Adds element to the N-ary tree at the correct location.
     * @throws NullPointerException if element is null
     */
    public void add(E element) {
        //only non-null elements may be added
        if (element == null) throw new NullPointerException();
        //if the the tree is empty
        if (root == null) {
            //make the root a new node containing
            //the element
            root = new Node(element);
            //update size
            size++;
            //break from method
            return;
        }
        //get all nodes in level order
        ArrayList<Node> allNodes = LOTraverse();
        //add a new node with element
        //to the first node that can take new nodes
        for (Node node : allNodes) {
            if (node.getNumChildren() < N) {
                node.addChild(new Node(element));
                break;
            }
        }
        //increment size
        size++;
    }

    /**
     * Generates a level-order 
     * traversal of the N-ary tree
     * @return an ArrayList containing all the nodes in level order
     */
    private ArrayList<Node> LOTraverse() {
        //instantiate ArrayList to store level-order traversal
        //(is empty to account for an empty tree)
        ArrayList<Node> out = new ArrayList<>();
        //Create a queue to traverse the data in level order
        Queue<Node> nodeQueue = new LinkedList<>();
        //enqueue the root to begin the level-order traversal
        nodeQueue.add(root);
        //while there is still elements in the queue
        while (nodeQueue.size() > 0) {
            //dequeue once
            Node currNode = nodeQueue.poll();
            //if a node was dequeued
            if (currNode != null) {
                //add it to the list
                out.add(currNode);
                //and enqueue all of its children
                List<Node> children = currNode.getChildren();
                for (Node child : children) {
                    nodeQueue.add(child);
                }
            }
        }
        //after it's all done, the list should be traversed
        //in level order
        return out;
    }

    /**
     * Returns whether the N-ary tree contains element.
     * @return true if the N-ary contains the element, false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean contains(E element) {
        //get all nodes in list
        ArrayList<Node> allNodes = LOTraverse();
        //if any node contains the element return true
        for (Node node : allNodes) {
            if (node.data == element) return true;
        }
        //none of them do, so return false
        return false;
    }

    /**
     * Uses a PriorityQueue to return the contents of the tree
     * in sorted order.
     * @return An ArrayList that contains the contents of the
     * N-ary tree, in sorted order.
     */
    public ArrayList<E> sortTree(){
        //Store output of sorting in this list
        ArrayList<E> out = new ArrayList<>();
        //Get all nodes
        ArrayList<Node> unsortedNodes = LOTraverse();
        //Create priority queue to sort the data
        PriorityQueue<E> pQueue = new PriorityQueue<E>();
        //add all the nodes to the priority queue
        for (Node node : unsortedNodes) {
            pQueue.add(node.data);
        }
        //dequeue all elements from the priority queue
        //and add them to the array list
        while (pQueue.size() > 0) {
            out.add(pQueue.poll());
        }
        //return the sorted data
        return out;
    }
}
