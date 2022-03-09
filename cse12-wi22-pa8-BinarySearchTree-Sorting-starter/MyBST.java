import java.util.ArrayList;
/**
 * Implementation of a binary search tree.
 * Name: Akhil Pillai
 * PID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * 
 * Contains an implementation of a binary search tree. Contains
 * insertion and removal methods, as well as a method for inorder
 * traversal.
 */

 /**
  * Contains binary tree implementation. Also has an
  * inner class for the nodes of the binary tree.
  */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * @return The size of the binary search tree
     */
    public int size(){
        return size;
    }

    /**
     * Inserts an element into its correct location on
     * the binary tree.
     * @param key The key to associate with the value to insert.
     * @param value The value to insert.
     * @return The value that was overwritten, if applicable. Returns null
     * if the list is empty or if no values are overwritten.
     * @throws NullPointerException if key is null
     */
    public V insert(K key, V value){
        //exception handling
        if (key == null) throw new NullPointerException();
        //if the list is empty
        if (size == 0) {
            //set the root to a new node containing the
            //value and key, with a null parent
            root = new MyBSTNode<K, V>(key, value, null);
            //increase size by 1 since a new value was added
            size++;
            //return null since nothing was replaced
            return null;
        }
        //if the value will replace another value
        if (search(key) != null) {
            //find the value that it will replace
            V output = search(key);
            //set the node that it will replace
            //to a new node with the inserted value
            MyBSTNode<K, V> replace = nodeSearch(key);
            replace.setValue(value);
            //return the overwritten value
            return output;
        }
        //if we are inserting a new node into the tree
        //find the node to insert it at
        MyBSTNode<K, V> insertNodeParent = insertParent(key);
        //create the node that will store the inserted value
        MyBSTNode<K, V> add = new MyBSTNode<K, V>(key, value, insertNodeParent);
        //if the key is smaller than its parent's key, set it on the left
        if (insertNodeParent.getKey().compareTo(key) == 1) insertNodeParent.setLeft(add);
        //otherwise set it on the right
        else insertNodeParent.setRight(add);
        //increase size since a new element was added
        size++;
        //return null since nothing was replaced
        return null;
    }

    /**
     * Searches the BST for the given key, and
     * returns the value associated with that key
     * @param key The key to search the BST for.
     * @return The value that the system found. Returns null
     * if key is null or the value is not found.
     */
    public V search(K key){
        //search is just a wrapper for searchHelper
        //at the root
        return searchHelper(key, this.root);
    }

    /**
     * Searches all nodes in the BST lower than or equal to node
     * for the given key, and returns the value it finds
     * at that key.
     * @param key The key to search for.
     * @param node The node to start searching at.
     * @return The value that is associated with key, or null
     * if key is null or not in the BST.
     */
    private V searchHelper(K key, MyBSTNode<K, V> node) {
        //return null if key is null
        if (key == null) return null;
        //if this node is the one we're looking for, return
        //its value.
        if (node.getKey().equals(key)) return node.getValue();
        //search the nodes on the left and store the
        //results in an instance variable
        V left = null;
        if (node.left != null) left = searchHelper(key, node.left);
        //search the nodes on the right and store the
        //results in an instance variable
        V right = null;
        if (node.right != null) right = searchHelper(key, node.right);
        //Return left if it's not null. Otherwise, return right, regardless
        //of whether it's null or not. If it's not null, then great! if it is
        //null, then the element is not in the BST
        V output = left != null ? left : right;
        //return the element we found
        return output;
    }

    /**
     * Similar to search, but returns the entire node instead
     * of just the value.
     * @param key The key to search the BST for.
     * @return The node that is associated with key, or null
     * if key is null or not in the BST.
     */
    private MyBSTNode<K, V> nodeSearch(K key) {
        //nodeSearch is just a wrapper for nodeSearchHelper
        //at the root
        return nodeSearchHelper(key, this.root);
    }

    /**
     * Searches all the nodes lower than or equal to
     * node in the BST for key, and returns the node
     * associated with that key.
     * @param key The key to search the BST for.
     * @param node The node to start searching at.
     * @return The node whose key is equal to key, or null
     * if key is null or there is no such node in the BST
     */
    private MyBSTNode<K, V> nodeSearchHelper(K key, MyBSTNode<K, V> node) {
        //return null if key is null
        if (key == null) return null;
        //if the starting node has the right key, return it.
        if (node.getKey().equals(key)) return node;
        //search the nodes on the left and store the
        //results in an instance variable
        MyBSTNode<K, V> left = null;
        if (node.left != null) left = nodeSearchHelper(key, node.getLeft());
        //search the nodes on the right and store the
        //results in an instance variable
        MyBSTNode<K, V> right = null;
        if (node.right != null) right = nodeSearchHelper(key, node.getRight());
        //Return left if it's not null. Otherwise, return right, regardless
        //of whether it's null or not. If it's not null, then great! if it is
        //null, then the element is not in the BST
        MyBSTNode<K, V> output = left != null ? left : right;
        //return the node we found
        return output;
    }

    /**
     * Returns the correct node in the BST
     * to insert the given key at.
     * @param key The key to place into the BST
     * @return The best node to insert key at,
     * or null if the list is empty.
     */
    private MyBSTNode<K, V> insertParent(K key) {
        //insertParent is just a wrapper for
        //insertParentHelper at the root
        return insertParentHelper(key, this.root);
    }
    
    /**
     * Returns the correct node in the BST
     * to insert the given key at, starting from
     * node. Assumes that node and key are not null.
     * @param key The key to place into the BST
     * @param node The node to start from
     * @return The best node to insert key at,
     * or null if the list is empty.
     */
    private MyBSTNode<K, V> insertParentHelper(K key, MyBSTNode<K, V> node) {
        //if the node's key is bigger than the key
        if (node.getKey().compareTo(key) == 1) {
            //and the node has a predecessor
            if (node.predecessor() != null) {
                //that is less than key, return this node.
                if (node.predecessor().getKey().compareTo(key) == -1) return node;
            }
            //if the node has no predecessor, but is bigger than the key,
            //then node is the smallest node in the BST and key is even smaller,
            //so node should be returned.
            if (node.predecessor() == null) return node;
        }
        //if the node's key is less than key
        if (node.getKey().compareTo(key) == -1) {
            //and the node has a successor
            if (node.successor() != null) {
                //that is greater than key, return this node.
                if (node.successor().getKey().compareTo(key) == 1) return node;
            }
            //if the node has no successor, but is smaller than the key,
            //then node is the biggest node in the BST and key is even bigger,
            //so node should be returned.
            if (node.successor() == null) return node;
        }
        //if key is less than node, and node has a left child,
        //check the node's left for a suitable key.
        if (key.compareTo(node.getKey()) == -1 && node.left != null)
            return insertParentHelper(key, node.left);
        //if key is greater than node, and node has a right child,
        //check the node's right for a suitable key.
        if (key.compareTo(node.getKey()) == 1 && node.right != null)
            return insertParentHelper(key, node.right);
        //if list is empty, return null.
        return null;
    }

    /**
     * Removes the node associated with this key
     * from the BST, and shifts the list accordingly.
     * @param key The key associated with the node to remove.
     * @return The value of the node removed, or null if the BST
     * does not contain a node with key equal to key, or if key is null.
     */
    public V remove(K key){
        //return null if key is null
        if (key == null) return null;
        //return null if the list does not contain key
        if (nodeSearch(key) == null) return null;
        //get the node to remove
        MyBSTNode<K, V> removeMe = nodeSearch(key);
        //instantiate variable to store removed value
        V output = null;
        //if this node is a leaf
        if (removeMe.getLeft() == null && removeMe.getRight() == null) {
            //if this node is not the last node in the tree
            if (removeMe.getParent() != null) {
                //determine whether this node is a left child
                //or right child, and set the parent's corresponding
                //child to null
                if (removeMe.getParent().getLeft() != null) {
                    if (removeMe.getParent().getLeft().equals(removeMe)) {
                        removeMe.getParent().setLeft(null);
                    }
                }
                if (removeMe.getParent().getRight() != null) {
                    if (removeMe.getParent().getRight().equals(removeMe)) {
                        removeMe.getParent().setRight(null);
                    }
                }
                //do nothing if the parent is somehow a leaf
                //store value of the removed node in output
                output = removeMe.getValue();
            }
            //if this node is the last node in the tree
            else {
                //store value of the removed node in output
                output = removeMe.getValue();
                //set root to null
                this.root = null;
            }
        }
        //if removed node has a left child and no right child
        if (removeMe.getLeft() != null && removeMe.getRight() == null) {
            //store value of removed node
            output = removeMe.getValue();
            //if the removed node is a left child
            if (removeMe.getParent().getLeft().equals(removeMe)) {
                //set parent's left child to removed node's
                //left child
                removeMe.getParent().setLeft(removeMe.getLeft());
            }
            //if the removed node is a right child,
            //set parent's right child to removed node's
            //left child
            else removeMe.getParent().setRight(removeMe.getLeft());
        }
        //if removed node has a right child and no left child
        if (removeMe.getLeft() == null && removeMe.getRight() != null) {
            //store value of removed node
            output = removeMe.getValue();
            //if the removed node is a left child
            if (removeMe.getParent().getLeft().equals(removeMe)) {
                //set parent's left child to removed node's
                //right child
                removeMe.getParent().setLeft(removeMe.getRight());
            }
            //if the removed node is a right child,
            //set parent's right child to removed node's
            //right child
            else removeMe.getParent().setRight(removeMe.getRight());
        }
        //if the removed node has a left and right child
        if (removeMe.getLeft() != null && removeMe.getRight() != null) {
            //get the removed node's value
            output = removeMe.getValue();
            //get and remove successor node
            MyBSTNode<K, V> successor = removeMe.successor();
            K successorKey = successor.getKey();
            V successorValue = successor.getValue();
            remove(removeMe.successor().getKey());
            //set removed node to its successor
            removeMe.setKey(successorKey);
            removeMe.setValue(successorValue);
            //return the removed value
            return output;
        }
        //decrement size since a node was removed
        size--;
        //return the removed value
        return output;
    }
    
    /**
     * Returns an ArrayList of nodes that form
     * an inorder traversal of the BST.
     * @return an ArrayList of nodes that form
     * an inorder traversal of the BST
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        //this method is just a wrapper of inorderHelper at the roop
        return inorderHelper(this.root);
    }

    /**
     * Returns an ArrayList of nodes that form
     * an inorder traversal of the BST, starting at node.
     * @param node The node to start the inorder traversal
     * at.
     * @return an ArrayList of nodes that form
     * an inorder traversal of the BST, starting at node
     */
    private ArrayList<MyBSTNode<K, V>> inorderHelper(MyBSTNode<K, V> node) {
        //if the list is empty return an empty list of nodes
        if (this.size == 0) return new ArrayList<MyBSTNode<K, V>>();
        //instantiate ArrayList to store the output
        ArrayList<MyBSTNode<K, V>> output = new ArrayList<>();
        //do inorder traversal of all nodes left of this ndoe,
        //and add it to the output
        if (node.left != null) output.addAll(inorderHelper(node.left));
        //add this node to the output
        output.add(node);
        //do inorder traversal of all nodes right of this ndoe,
        //and add it to the output 
        if (node.right != null) output.addAll(inorderHelper(node.right));
        //return the output
        return output;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            //if this node has a right child
            if(this.getRight() != null){
                //go right, then go as far left as you can
                //this will return the smallest node that
                //is greater than the given node
                MyBSTNode<K,V> curr = this.getRight();
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                //return the node found
                return curr;
            }
            //if this node has no right child,
            //go backwards through the BST
            //(go through the node's parent)
            //until th node is a left node or
            //you have reached the root. This is
            //the smallest node greater than this node.
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * This method returns the in order predecessor of current node object.
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            //if this node has a left child
            if (this.getLeft() != null) {
                //go left, then go as far right as you can
                //this will return the biggest node that
                //is smaller than the given node
                MyBSTNode<K,V> curr = this.getLeft();
                while (curr.getRight() != null) {
                    curr = curr.getRight();
                }
                //return the node found
                return curr;
            }
            //if this node has no left child,
            //go backwards through the BST
            //(i.e. go through the node's parent)
            //until th node is a right node or
            //you have reached the root. This is
            //the biggest node smaller than this node.
            else {
                MyBSTNode<K, V> parent = this.getParent();
                MyBSTNode<K, V> curr = this;
                while (parent != null && curr == parent.getLeft()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}