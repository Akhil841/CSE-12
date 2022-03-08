import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for the BST.
 * Name: Akhil Pillai
 * PID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * Contains an iterator for my implementation of a binary search tree.
 * The iterator can iterate through the tree, as well as remove nodes.
 */

/**
 * Contains an abstract class for an iterator for a binary search tree.
 * Also contains a key iterator and a value iterator that implement this class.
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Returns the inorder next node in this BST.
         * @return the inorder next node in this BST
         * @throws NoSuchElementException if there is no
         * next node
         */
        MyBSTNode<K, V> nextNode() {
            //throw exception if the iterator cannot go forward
            if (!hasNext()) throw new NoSuchElementException();
            //move the iterator forward by one
            //store the next node in a variable
            //to store the output
            MyBSTNode<K, V> output = next;
            //shift next to its successor
            next = next.successor();
            //set the last visited node to
            //what we will output
            //since it is the lastest node returned
            lastVisited = output;
            //return the found value
            return output;
        }

        /**
         * This method removes the last visited node from the tree.
         * @throws IllegalStateException if their is no last visited node;
         * if the iterator has not been used
         */
        public void remove() {
            //if lastVisited is null throw an exception
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            //move the iterator back by one node if lastVisited is a leaf
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
                next = lastVisited;
            }
            //remove the last visited node from the array
            MyBSTIterator.this.remove(lastVisited.getKey());
            //set lastVisited to null since no nodes were visited since
            //the removal
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}