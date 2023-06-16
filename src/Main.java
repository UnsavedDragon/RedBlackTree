import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var tree = new RedBlackTree<>(Integer::compareTo);
        var list = Arrays.asList(3, 5, 10, 11, 2, 4, 8, 7, 1, 6, 9);
        for (Integer integer : list) {
            tree.insert(new Node<>(integer));
            tree.printTree();
        }
        System.out.println(tree);
    }
}