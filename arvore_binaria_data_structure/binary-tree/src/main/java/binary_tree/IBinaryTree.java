package binary_tree;

public interface IBinaryTree<T extends Comparable<T>> {
  /**
   * Create a tree from a single element
   * 
   * @param element root element
   * @return root node
   */
  Node<T> createTree(T element);

  /**
   * This method creates a new tree returning the root Node. The elements should
   * be inserted from the first to last array element.
   * 
   * @param elements is an array of elements
   * @return root node
   */
  Node<T> createTree(T[] elements);

  /**
   * Calculates the degree of a single Node
   * 
   * @param rootNode
   * @param nodeElement
   * @return degree value
   */
  Integer degree(Node<T> rootNode, T nodeElement);

  /**
   * Given a root node, inserts an element. Duplicated elements are now allowed.
   * 
   * @param rootNode
   * @param element
   */
  void insert(Node<T> rootNode, T element);

  /**
   * Given a root node, removes an element
   * 
   * @param rootNode
   * @param nodeElement
   * @return return true if a node was removed and false if not
   */
  boolean remove(Node<T> rootNode, T nodeElement);

  /**
   * Get the father node
   * 
   * @param rootNode
   * @param nodeElement
   * @return returns the father node element or null
   */
  Node<T> getFather(Node<T> rootNode, T nodeElement);

  /**
   * Get the brother node
   * 
   * @param rootNode
   * @param nodeElement
   * @return returns the brother node or null
   */
  Node<T> getBrother(Node<T> rootNode, T nodeElement);

  /**
   * Find and return a Node by element
   * 
   * @param rootNode
   * @param element
   * @return return the Node that contains the element or null
   */
  Node<T> getByElement(Node<T> rootNode, T element);

  /**
   * Calculate the tree depth
   * 
   * @param rootNode
   * @return tree depth
   */
  Integer calculateTreeDepth(Node<T> rootNode);

  /**
   * Calculate the Node level
   * 
   * @param rootNode
   * @param nodeElement
   * @return node level
   */
  Integer calculateNodeLevel(Node<T> rootNode, T nodeElement);

  /**
   * Should use the String representation of a tree. This is an example for a
   * Node<Integer> tree:<br/>
   * root:6 (left:2 (left:1 right:4 (left:3 ))right:8 )
   * 
   * @param rootNode
   * @return String representation of a tree.
   */
  String toString(Node<T> rootNode);
}