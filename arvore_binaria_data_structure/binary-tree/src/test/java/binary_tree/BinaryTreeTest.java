package binary_tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void createANewTree() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals("root:6 (left:2 (left:1 right:4 (left:3 ))right:8 )", binaryTreeOps.toString(rootNode));
    }

    @Test
    void performASetOfInsertions() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Node<Integer> rootNode = binaryTreeOps.createTree(6);

        assertEquals("root:6 ", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 2);
        assertEquals("root:6 (left:2 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 8);
        assertEquals("root:6 (left:2 right:8 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 1);
        assertEquals("root:6 (left:2 (left:1 )right:8 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 1);
        assertEquals("root:6 (left:2 (left:1 )right:8 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 4);
        assertEquals("root:6 (left:2 (left:1 right:4 )right:8 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 3);
        assertEquals("root:6 (left:2 (left:1 right:4 (left:3 ))right:8 )", binaryTreeOps.toString(rootNode));

        binaryTreeOps.insert(rootNode, 3);
        assertEquals("root:6 (left:2 (left:1 right:4 (left:3 ))right:8 )", binaryTreeOps.toString(rootNode));
    }

    @Test
    void performASetOfRemovals() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{37, 20, 10, 30, 80, 100, 5, 180, 90};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals("root:37 (left:20 (left:10 (left:5 )right:30 )right:80 (right:100 (left:90 right:180 )))",
                binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 180);
        assertEquals("root:37 (left:20 (left:10 (left:5 )right:30 )right:80 (right:100 (left:90 )))",
                binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 80);
        assertEquals("root:37 (left:20 (left:10 (left:5 )right:30 )right:100 (left:90 ))",
                binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 10);
        assertEquals("root:37 (left:20 (left:5 right:30 )right:100 (left:90 ))",
                binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 20);
        assertEquals("root:37 (left:30 (left:5 )right:100 (left:90 ))", binaryTreeOps.toString(rootNode));

        binaryTreeOps.remove(rootNode, 37);
        assertEquals("root:100 (left:90 (left:30 (left:5 )))", binaryTreeOps.toString(rootNode));
    }

    @Test
    void getAnElement() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        Node<Integer> node = binaryTreeOps.getByElement(rootNode, 8);

        assertEquals(8, node.getValue());
    }

    @Test
    void dontFindRequiredElement() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        Node<Integer> node = binaryTreeOps.getByElement(rootNode, 10);

        assertNull(node);
    }

    @Test
    void checkNodeDegree() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals(0, binaryTreeOps.degree(rootNode, 8));
        assertEquals(2, binaryTreeOps.degree(rootNode, 2));
        assertEquals(1, binaryTreeOps.degree(rootNode, 4));
        assertNull(binaryTreeOps.degree(rootNode, 10));
    }

    @Test
    void getFather() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals(6, binaryTreeOps.getFather(rootNode, 8).getValue());
        assertEquals(2, binaryTreeOps.getFather(rootNode, 1).getValue());
        assertNull(binaryTreeOps.getFather(rootNode, 6));
    }

    @Test
    void getBrother() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals(2, binaryTreeOps.getBrother(rootNode, 8).getValue());
        assertNull(binaryTreeOps.getBrother(rootNode, 3));
    }

    @Test
    void calculateTreeDepths() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals(3, binaryTreeOps.calculateTreeDepth(rootNode));
    }

    @Test
    void calculateNodeLevel() {
        IBinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
        Integer[] elements = new Integer[]{6, 2, 8, 1, 4, 3};

        Node<Integer> rootNode = binaryTreeOps.createTree(elements);

        assertEquals(1, binaryTreeOps.calculateNodeLevel(rootNode, 8));
        assertEquals(2, binaryTreeOps.calculateNodeLevel(rootNode, 4));
        assertNull(binaryTreeOps.calculateNodeLevel(rootNode, null));
    }
}