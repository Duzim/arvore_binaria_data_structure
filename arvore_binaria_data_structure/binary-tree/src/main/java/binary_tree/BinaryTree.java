package binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
    @Override
    public Node<T> createTree(T element) {
        Node<T> rootNode = new Node<T>();
        rootNode.setValue(element);
        return rootNode;
    }

    @Override
    public Node<T> createTree(T[] elements) {
        Node<T> rootNode = createTree(elements[0]);

        if (elements.length > 1) {
            for (int i = 1; i < elements.length; i++) {
                insert(rootNode, elements[i]);
            }
        }

        return rootNode;
    }

    @Override
    public Integer degree(Node<T> rootNode, T nodeElement) {
        if (rootNode == null) {
            return null;
        }

        Node<T> node = getByElement(rootNode, nodeElement);
        if (node == null) {
            return null;
        }

        Integer nodeDegree = 0;

        if (node.getLeft() == null && node.getRight() == null) {
            return nodeDegree;
        }

        if (node.getLeft() != null) {
            nodeDegree++;
        }

        if (node.getRight() != null) {
            nodeDegree++;
        }

        return nodeDegree;
    }

    @Override
    public void insert(Node<T> rootNode, T element) {
        if (element.compareTo(rootNode.getValue()) == 0) {
            return;
        }

        if (element.compareTo(rootNode.getValue()) < 0) {
            if (rootNode.getLeft() == null) {
                rootNode.setLeft(createTree(element));
            } else {
                insert(rootNode.getLeft(), element);
            }
        } else {
            if (rootNode.getRight() == null) {
                rootNode.setRight(createTree(element));
            } else {
                insert(rootNode.getRight(), element);
            }
        }
    }

    @Override
    public boolean remove(Node<T> rootNode, T nodeElement) {
        Node<T> nodeElementFather = getFather(rootNode, nodeElement);
        return removeRecursive(rootNode, nodeElement, nodeElementFather);
    }

    public boolean removeRecursive(Node<T> rootNode, T nodeElement, Node<T> father) {
        if (rootNode == null) {
            return false;
        }

        //Remove nó raíz com filhos
        if (rootNode.getValue().compareTo(nodeElement) == 0 && degree(rootNode, nodeElement) > 0 && father == null) {
            Node<T> leftTree = rootNode.getLeft();
            Node<T> rightTree = rootNode.getRight();

            rootNode.setValue(rightTree.getValue());
            rootNode.setRight(rightTree.getRight());

            if (rightTree.getLeft() == null) {
                rootNode.setLeft(leftTree);
                return true;
            }

            Node<T> successorParent = rightTree;
            Node<T> successor = rightTree.getLeft();
            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            successorParent.setLeft(successor.getRight());
            successor.setLeft(leftTree);
            successor.setRight(rootNode.getRight());
            rootNode.setLeft(successor);

            return true;
        }

        //Remove nó folha
        if (rootNode.getValue().compareTo(nodeElement) == 0 && degree(rootNode, nodeElement) == 0 && father != null) {
            if (father.getLeft() != null && father.getLeft().getValue().compareTo(rootNode.getValue()) == 0) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }

            return true;
        }

        //Remove nó com filhos apenas para a direita
        if (rootNode.getValue().compareTo(nodeElement) == 0 && rootNode.getLeft() == null && father != null) {
            if (rootNode.getValue().compareTo(father.getLeft().getValue()) == 0) {
                father.setLeft(rootNode.getRight());
            } else {
                father.setRight(rootNode.getRight());
            }

            return true;
        }

        //Remove nó com filhos apenas para a esquerda
        if (rootNode.getValue().compareTo(nodeElement) == 0 && rootNode.getRight() == null && father != null) {
            if (rootNode.getValue().compareTo(father.getLeft().getValue()) == 0) {
                father.setLeft(rootNode.getLeft());
            } else {
                father.setRight(rootNode.getLeft());
            }

            return true;
        }

        //Remove nó com filhos para esquerda e para a direita
        if (rootNode.getValue().compareTo(nodeElement) == 0 && degree(rootNode, nodeElement) == 2 && father != null) {
            if (father.getLeft().getValue().compareTo(rootNode.getValue()) == 0) {
                father.setLeft(rootNode.getRight());
                father.getLeft().setLeft(rootNode.getLeft());
                //rootNode.getRight().setLeft(rootNode.getLeft());
            } else {
                father.setRight(rootNode.getRight());
                father.getRight().setRight(rootNode.getRight());
            }

            return true;
        }

        if (nodeElement.compareTo(rootNode.getValue()) < 0) {
            return removeRecursive(rootNode.getLeft(), nodeElement, father);
        } else {
            return removeRecursive(rootNode.getRight(), nodeElement, father);
        }
    }

    @Override
    public Node<T> getFather(Node<T> rootNode, T nodeElement) {
        if (rootNode == null || nodeElement.compareTo(rootNode.getValue()) == 0) {
            return null;
        }

        if ((rootNode.getLeft() != null && rootNode.getLeft().getValue().compareTo(nodeElement) == 0) || (rootNode.getRight() != null && rootNode.getRight().getValue().compareTo(nodeElement) == 0)) {
            return rootNode;
        }

        if (nodeElement.compareTo(rootNode.getValue()) < 0) {
            return getFather(rootNode.getLeft(), nodeElement);
        } else {
            return getFather(rootNode.getRight(), nodeElement);
        }
    }

    @Override
    public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
        if (rootNode == null) {
            return null;
        }

        Node<T> father = getFather(rootNode, nodeElement);
        if (father == null) {
            return null;
        }


        if (father.getLeft() != null && father.getLeft().getValue().compareTo(nodeElement) == 0) {
            return father.getRight();
        }

        if (father.getRight() != null && father.getRight().getValue().compareTo(nodeElement) == 0) {
            return father.getLeft();
        }

        return null;
    }

    @Override
    public Node<T> getByElement(Node<T> rootNode, T element) {
        if (rootNode == null) {
            return null;
        }

        if (rootNode.getValue().compareTo(element) == 0) {
            return rootNode;
        }

        if (element.compareTo(rootNode.getValue()) < 0) {
            return getByElement(rootNode.getLeft(), element);
        } else {
            return getByElement(rootNode.getRight(), element);
        }
    }

    @Override
    public Integer calculateTreeDepth(Node<T> rootNode) {
        return calculateTreeDepthRecursive(rootNode) - 1;
    }

    private Integer calculateTreeDepthRecursive(Node<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }

        Integer leftDepth = calculateTreeDepthRecursive(rootNode.getLeft());
        Integer rightDepth = calculateTreeDepthRecursive(rootNode.getRight());

        return 1 + Math.max(leftDepth, rightDepth);
    }

    @Override
    public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
        if (nodeElement == null) {
            return null;
        }

        return calculateNodeLevelRecursive(rootNode, nodeElement, 0);
    }

    private Integer calculateNodeLevelRecursive(Node<T> rootNode, T nodeElement, Integer level) {
        if (rootNode == null || rootNode.getValue().compareTo(nodeElement) == 0) {
            return level;
        }

        level++;

        if (nodeElement.compareTo(rootNode.getValue()) < 0) {
            return calculateNodeLevelRecursive(rootNode.getLeft(), nodeElement, level);
        } else {
            return calculateNodeLevelRecursive(rootNode.getRight(), nodeElement, level);
        }
    }

    @Override
    public String toString(Node<T> rootNode) {
        if (rootNode == null) {
            return "";
        }

        return "root:" + rootNode.getValue() + " " + treeToString(rootNode);
    }

    private String treeToString(Node<T> rootNode) {
        StringBuilder sb = new StringBuilder();

        if (rootNode.getLeft() != null) {
            sb.append("(");
            sb.append("left:");
            sb.append(rootNode.getLeft().getValue());
            sb.append(" ");

            sb.append(treeToString(rootNode.getLeft()));
        }

        if (rootNode.getLeft() == null && rootNode.getRight() != null) {
            sb.append("(");
        }

        if (rootNode.getRight() != null) {
            sb.append("right:");
            sb.append(rootNode.getRight().getValue());
            sb.append(" ");

            sb.append(treeToString(rootNode.getRight()));
            sb.append(")");
        }

        if (rootNode.getLeft() != null && rootNode.getRight() == null) {
            sb.append(")");
        }

        return sb.toString();
    }
}