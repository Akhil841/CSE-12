public class Testing {
    public static void main(String[] args) {
        MyBST<Integer, Integer> bst = new MyBST<Integer, Integer>();
        bst.insert(6, 6);
        bst.insert(2, 2);
        bst.insert(8, 8);
        bst.insert(5, 5);
        bst.insert(7, 7);
        bst.insert(9, 9);
        System.out.println(bst.inorder());
    }
}
