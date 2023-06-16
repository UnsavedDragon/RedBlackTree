import java.util.Comparator;

public class RedBlackTree<T> {

    /**
     * The SentinelNode class represents a special node in the Red-Black Tree
     * that serves as a sentinel or null placeholder.
     * It inherits from the Node class and overrides some methods.
     */
    private static class SentinelNode<T> extends Node<T>{
        @Override
        public Node<T> getParent() {
            return this;
        }

        @Override
        public T getValue() {
            return null;
        }

        @Override
        public Color getColor() {
            return Color.BLACK;
        }

        @Override
        public Node<T> getLeft() {
            return this;
        }

        @Override
        public Node<T> getRight() {
            return this;
        }
    }
    private Node<T> root;       // The root node of the Red-Black Tree
    private final Node<T> sent;     // The root node of the Red-Black Tree
    private final Comparator<T> comparator;     // The comparator used to compare elements in the tree

    /**
     * Constructs a RedBlackTree with the given comparator.
     *
     * @param comparator the comparator used to compare elements in the tree.
     */
    public RedBlackTree(Comparator<T> comparator){
        sent = new RedBlackTree.SentinelNode<>();
        root = null;
        this.comparator = comparator;
    }



    /**
     * Rotates the given node to the left.
     *
     * @param node the node to rotate.
     */
    private void rotateLeft(Node<T> node){
        if(node == sent) return;
        var right = node.getRight();
        if(right == null) throw new RuntimeException();
        node.setRight(right.getLeft());
        if(right.getLeft() != null){
            right.getLeft().setParent(node);
        }
        right.setParent(node.getParent());
        if(node.getParent() == sent){
            root = right;
        }
        else{
            if(node == node.getParent().getLeft()) node.getParent().setLeft(right);
            else node.getParent().setRight(right);
        }
        right.setLeft(node);
        node.setParent(right);
    }

    /**
     * Rotates the given node to the right.
     *
     * @param node the node to rotate.
     */
    private void rotateRight(Node<T> node){
        if(node == sent) return;
        var left = node.getLeft();
        if(left == null) throw new RuntimeException();
        node.setLeft(left.getRight());
        if(left.getRight() != null){
            left.getRight().setParent(node);
        }
        left.setParent(node.getParent());
        if(node.getParent() == sent){
            root = left;
        }
        else{
            if(node == node.getParent().getRight()) node.getParent().setRight(left);
            else node.getParent().setLeft(left);
        }
        left.setRight(node);
        node.setParent(left);
    }

    /**
     * Inserts a node into the Red-Black Tree.
     *
     * @param node the node to insert.
     */
    public void insert(Node<T> node) {

        node.setLeft(null);
        node.setRight(null);
        Node<T> x = root;
        Node<T> px = sent;

        while (x != null) {
            px = x;
            if(comparator.compare(x.getValue(), node.getValue()) > 0){
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        node.setParent(px);

        if (px == sent) {
            root = node;
        } else {
            if(comparator.compare(px.getValue(), node.getValue()) > 0){
                px.setLeft(node);
            } else {
                px.setRight(node);
            }
        }

        node.setColor(Color.RED);
        fixColorsAfterInsertion(node);
    }

    /**
     * Fixes the colors after node insertion to maintain Red-Black Tree properties.
     *
     * @param node the newly inserted node.
     */
    private void fixColorsAfterInsertion(Node<T> node){
        while (node.getParent().getColor() == Color.RED) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                Node<T> y = node.getParent().getParent().getRight();
                if (y != null && y.getColor() == Color.RED) {
                    node.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    rotateRight(node.getParent().getParent());
                }
            }
            else {
                Node<T> y = node.getParent().getParent().getLeft();
                if (y != null && y.getColor() == Color.RED) {
                    node.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    rotateLeft(node.getParent().getParent());
                }
            }
        }
        root.setColor(Color.BLACK);
    }



    /**
     * Returns a string representation of the Red-Black Tree with nodes in pre-order traversal.
     *
     * @return the string representation of the tree.
     */
    public String getPrintingString() {
        if (root == null) {
            return "Empty Red-Black Tree";
        }
        StringBuilder sb = new StringBuilder();
        visualizeTree(root, "", true, sb);
        return sb.toString();
    }

    /**
     * Prints the Red-Black Tree with nodes in pre-order traversal.
     */
    public void printTree() {
        if (root == null) {
            System.out.println("Empty Red-Black Tree");
        }
        StringBuilder sb = new StringBuilder();
        visualizeTree(root, "", true, sb);
        System.out.println(sb);
    }

    /**
     * Recursively visualizes the tree starting from the given node.
     *
     * @param node   the current node being visualized.
     * @param prefix the prefix used for indentation.
     * @param isTail specifies if the current node is a tail node.
     * @param sb     the StringBuilder used for constructing the string representation.
     */
    private void visualizeTree(Node<?> node, String prefix, boolean isTail, StringBuilder sb) {
        if (node == null) return;

        String colorString = (node.getColor() == Color.RED) ? "R" : "B";
        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(node.getValue())
                .append(" (")
                .append(colorString)
                .append(")")
                .append("\n");

        if (node.getLeft() != null) {
            visualizeTree(node.getLeft(), prefix + (isTail ? "    " : "│   "), node.getRight() == null, sb);
        }

        if (node.getRight() != null) {
            visualizeTree(node.getRight(), prefix + (isTail ? "    " : "│   "), true, sb);
        }
    }

    /**
     * Returns a string representation of the Red-Black Tree with nodes in pre-order traversal.
     *
     * @return the string representation of the tree.
     */
    public String toString(){
        return getPreOrder();
    }

    /**
     * Returns a string representation of the Red-Black Tree with nodes in pre-order traversal.
     *
     * @return the string representation of the tree.
     */
    public String getPreOrder(){
        return root == null ? "Empty Rad-Black Tree" : root.preOrder();
    }

    /**
     * Returns a string representation of the Red-Black Tree with nodes in post-order traversal.
     *
     * @return the string representation of the tree.
     */
    public String getPostOrder(){
        return root == null ? "Empty Rad-Black Tree" : root.postOrder();
    }

    /**
     * Returns a string representation of the Red-Black Tree with nodes in in-order traversal.
     *
     * @return the string representation of the tree.
     */
    public String getInOrder(){
        return root == null ? "Empty Rad-Black Tree" : root.inOrder();
    }



    /**
     * Searches for the presence of a node containing the specified value in the Red-Black Tree.
     *
     * @param value the value to search for.
     * @return true if the value is found, false otherwise.
     */
    public boolean search(T value) {
        Node<T> current = root;

        while (current != null) {
            int comparison = comparator.compare(value, current.getValue());

            if (comparison == 0) {
                // Found the node with the specified value
                return true;
            } else if (comparison < 0) {
                // Value is smaller, move to the left child
                current = current.getLeft();
            } else {
                // Value is larger, move to the right child
                current = current.getRight();
            }
        }

        // Node with the specified value not found
        return false;
    }

    /**
     * Searches for a node containing the specified value in the Red-Black Tree.
     *
     * @param value the value to search for.
     * @return the node containing the value, or null if not found.
     */
    private Node<T> searchNode(T value) {
        Node<T> current = root;

        while (current != null) {
            int comparison = comparator.compare(value, current.getValue());

            if (comparison == 0) {
                // Found the node with the specified value
                return current;
            } else if (comparison < 0) {
                // Value is smaller, move to the left child
                current = current.getLeft();
            } else {
                // Value is larger, move to the right child
                current = current.getRight();
            }
        }

        // Node with the specified value not found
        return null;
    }

    /**
     * Deletes a node with the specified value from the Red-Black Tree.
     *
     * @param value the value to delete.
     * @return true if the node was successfully deleted, false otherwise.
     */
    public boolean delete(T value) {
        Node<T> node = searchNode(value);
        if (node == null) {
            // Node with the specified value not found
            return false;
        }

        // Perform the deletion
        deleteNode(node);
        return true;
    }

    /**
     * Deletes the given node from the Red-Black Tree.
     *
     * @param node the node to delete.
     */
    private void deleteNode(Node<T> node) {
        // Case 1: If the node has no children (leaf node)
        if(node.getLeft() == null && node.getRight() == null){
            if(node.getColor() == Color.BLACK) throw new RuntimeException("Unexpected color of the node to be removed");
            if(root == node) { root = null; return; }
            if(node.getParent().getRight() == node) node.getParent().setRight(null);
            else node.getParent().setLeft(null);
        }
        // Case 2: If the node has only one child
        else if(node.getLeft() != null || node.getRight() != null){
            Node<T> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();

            if (node == root) {
                // Deleting the root (replace with the child)
                root = child;
                child.setParent(null);
            }
            else{
                // TODO: Implement
            }

        }
        // Case 3: If the node has two children
        else {
            // TODO: Implement
        }
    }

}
