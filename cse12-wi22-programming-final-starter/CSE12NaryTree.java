/**
 * TODO: Add file header
 * Name:
 * ID:
 * Email:
 * File description: 
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * TODO: Add class header
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
        if (element == null) throw new NullPointerException();
        if (root == null) {
            root = new Node(element);
            size++;
            return;
        }
        ArrayList<Node> allNodes = LOTraverse();
        for (Node node : allNodes) {
            if (node.getNumChildren() < N) {
                node.addChild(new Node(element));
                break;
            }
        }
        size++;
    }

    /**
     * Generates a level-order 
     * traversal of the N-ary tree
     * @return an ArrayList containing all the nodes in level order
     */
    private ArrayList<Node> LOTraverse() {
        ArrayList<Node> out = new ArrayList<>();
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (nodeQueue.size() != 0) {
            Node currNode = nodeQueue.poll();
            if (currNode != null) out.add(currNode);
            List<Node> children = currNode.getChildren();
            for (int i = 0; i < children.size(); i++) {
                nodeQueue.add(children.get(i));
            }
        }
        return out;
    }

    /**
     * Returns whether the N-ary tree contains element.
     * @return true if the N-ary contains the element, false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean contains(E element) {
        ArrayList<Node> allNodes = LOTraverse();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).data == element) return true;
        }
        return false;
    }

    /**
     * TODO: Add method header
     */
    public ArrayList<E> sortTree(){
        ArrayList<E> out = new ArrayList<>();
        ArrayList<Node> unsortedNodes = LOTraverse();
        PriorityQueue<E> pQueue = new PriorityQueue<E>();
        for (int i = 0; i < unsortedNodes.size(); i++) {
            pQueue.add(unsortedNodes.get(i).data);
        }
        while (pQueue.size() > 0) {
            out.add(pQueue.poll());
        }
        return out;
    }
}
