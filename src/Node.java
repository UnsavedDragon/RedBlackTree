/**
 * This is the Node class that represents a node in a Red-Black tree structure.
 * It is part of the implementation of a Red-Black tree data structure.
 * <p>
 * The Node class is generic, allowing it to store values of any type.
 * <p>
 * The class has private fields for storing references to the right child node, left child node, parent node,
 * the color of the node, and the value associated with the node.
 * <p>
 * Public getter and setter methods are provided to access and modify the private fields.
 * <p>
 * The class includes two constructors:
 * - The default constructor initializes the fields with default values.
 * - The parameterized constructor allows setting the value of the node.
 * <p>
 * Additionally, the class provides methods for performing tree traversal in different orders: pre-order, post-order, and in-order.
 * These traversal methods return a string representation of the nodes visited during traversal.
 * <p>
 * The toString() method is overridden to return a string representation of the node's value.
 *
 * @author UnsavedDragon
 * @param <T> the type of value stored in the node
 */
public class Node<T> {
    private Node<T> right;      // Reference to the right child node
    private Node<T> left;       // Reference to the left child node
    private Node<T> parent;     // Reference to the parent node
    private Color color;        // The color of the node
    private final T value;      // The value stored in the node

    /**
     * Returns the color of the node.
     *
     * @return the color of the node
     */
    public Color getColor() { return color; }

    /**
     * Returns the left child node.
     *
     * @return the left child node
     */
    public Node<T> getLeft() { return left; }

    /**
     * Returns the right child node.
     *
     * @return the right child node
     */
    public Node<T> getRight() { return right; }

    /**
     * Returns the parent node.
     *
     * @return the parent node
     */
    public Node<T> getParent() { return parent; }

    /**
     * Sets the color of the node.
     *
     * @param color the color to set for the node
     */
    public void setColor(Color color) { this.color = color; }

    /**
     * Sets the left child node.
     *
     * @param left the left child node to set
     */
    public void setLeft(Node<T> left) { this.left = left; }

    /**
     * Sets the parent node.
     *
     * @param parent the parent node to set
     */
    public void setParent(Node<T> parent) { this.parent = parent; }

    /**
     * Sets the right child node.
     *
     * @param right the right child node to set
     */
    public void setRight(Node<T> right) { this.right = right; }



    /**
     * Returns the value stored in the node.
     *
     * @return the value stored in the node
     */
    public T getValue() { return value; }

    /**
     * Default constructor for the Node class.
     * Initializes the fields with default values:
     * - right: null
     * - left: null
     * - parent: null
     * - color: Color.RED
     * - value: null
     */
    protected Node(){
        right = null;
        left = null;
        parent = null;
        color = Color.RED;
        value = null;
    }

    /**
     * Parameterized constructor for the Node class.
     * Initializes the fields with the provided values:
     * - right: null
     * - left: null
     * - parent: null
     * - color: Color.RED
     * - value: the specified value
     *
     * @param value the value to be stored in the node
     */
    public Node(T value){
        right = null;
        left = null;
        parent = null;
        color = Color.RED;
        this.value = value;
    }

    /**
     * Returns a string representation of the node and its descendants in pre-order traversal.
     * Each node is represented as: value.color (e.g., 5.R)
     *
     * @return the pre-order traversal string representation of the node and its descendants
     */
    public String preOrder(){
        return "  " + this + "." + (this.color == Color.RED ? "R" : "B")
                + (left != null ? left.preOrder() : "")
                + (right != null ? right.preOrder() : "");
    }
    /**
     * Returns a string representation of the node and its descendants in post-order traversal.
     * Each node is represented as: value.color (e.g., 5.R)
     *
     * @return the post-order traversal string representation of the node and its descendants
     */
    public String postOrder(){
        return ""
                + (left != null ? left.preOrder() : "")
                + (right != null ? right.preOrder() : "")
                + "  " + this + "." + (this.color == Color.RED ? "R" : "B");
    }
    /**
     * Returns a string representation of the node and its descendants in in-order traversal.
     * Each node is represented as: value.color (e.g., 5.R)
     *
     * @return the in-order traversal string representation of the node and its descendants
     */
    public String inOrder(){
        return ""
                + (left != null ? left.preOrder() : "")
                + "  " + this + "." + (this.color == Color.RED ? "R" : "B")
                + (right != null ? right.preOrder() : "");
    }

    /**
     * Returns a string representation of the value stored in the node.
     *
     * @return the string representation of the value stored in the node
     */
    public String toString() { return getValue().toString(); }
}
