package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public BinarySearchTree() {
  }

  public BinarySearchTree(BSTNode<T> root) {
    this.root = root;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    if (t == null) {
      throw new NullPointerException("The target element is null");
    }
    boolean found = false;
    BSTNode<T> currNode = root;
    while (!found && currNode != null) {
      if (t.compareTo(currNode.getData()) < 0) {
        currNode = currNode.getLeft();
      } else if (t.compareTo(currNode.getData()) > 0) {
        currNode = currNode.getRight();
      } else {
        found = true;
      }
    }
    return found;
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException("Target element is null");
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    if (contains(t)) {
      return t;
    } else {
      return null;
    }
  }

  public void add(T t) {
    if (t == null) {
      throw new NullPointerException("The target element in null");
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    if (root == null) {
      return null;
    }
    BSTNode<T> currNode = root;
    while (currNode.getLeft() != null) {
      currNode = currNode.getLeft();
    }
    return (currNode.getData());
  }

  @Override
  public T getMaximum() {
    if (root == null) {
      return null;
    }
    BSTNode<T> currNode = root;
    while (currNode.getRight() != null) {
      currNode = currNode.getRight();
    }
    return (currNode.getData());
  }

  @Override
  public int height() {
    if (root == null) {
      return -1;
    }
    return heightCalc(root);
  }

  public int heightCalc(BSTNode<T> currNode) {
    if (currNode == null) {
      return -1;
    }
    return 1 + Math.max(heightCalc(currNode.getLeft()), heightCalc(currNode.getRight()));
  }

  public Iterator<T> preorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    preorderTraverse(queue, root);
    return queue.iterator();
  }

  private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      queue.add(node.getData());
      preorderTraverse(queue, node.getLeft());
      preorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }

  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    postorderTraverse(queue, root);
    return queue.iterator();
  }

  private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      postorderTraverse(queue, node.getLeft());
      postorderTraverse(queue, node.getRight());
      queue.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    BSTNode<T> rootA = this.root;
    BSTNode<T> rootB = other.getRoot();
    if (this == null || other == null) {
      throw new NullPointerException("Either one or both of the trees is/are null");
    }
    return equalsCheck(rootA, rootB);
  }

  public boolean equalsCheck(BSTNode<T> nodeA, BSTNode<T> nodeB) {
    if (nodeA == null && nodeB == null) {
      return true;
    } else if (nodeA != null && nodeB != null) {
      boolean currNodeCheck = nodeA.getData() == nodeB.getData();
      boolean leftNodeCheck = equalsCheck(nodeA.getLeft(), nodeB.getLeft());
      boolean rightNodeCheck = equalsCheck(nodeA.getRight(), nodeB.getRight());
      return (currNodeCheck && leftNodeCheck && rightNodeCheck);
    } else {
      return false;
    }
  }

  public BSTNode<T> getParentNode(BSTNode<T> root, BSTNode<T> childNode) {
    if (root == null) {
      return null;
    }
    BSTNode<T> parentNode = null;
    while (root != null) {
      if (childNode.getData().compareTo(root.getData()) < 0) {
        parentNode = root;
        root = root.getLeft();
      } else if (childNode.getData().compareTo(root.getData()) > 0) {
        parentNode = root;
        root = root.getRight();
      } else {
        break;
      }
    }
    return parentNode;
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    if (other == null) {
      throw new NullPointerException("The other tree is null");
    }
    Queue<T> queueA = new LinkedList<T>();
    Queue<T> queueB = new LinkedList<T>();
    BSTNode<T> rootA = this.root;
    BSTNode<T> rootB = other.getRoot();
    insertToQueue(rootA, queueA);
    insertToQueue(rootB, queueB);
    return (queueA.equals(queueB));
  }

  public void insertToQueue(BSTNode<T> currNode, Queue<T> queue) {
    if (currNode == null)
      return;
    insertToQueue(currNode.getLeft(), queue);
    queue.add(currNode.getData());
    insertToQueue(currNode.getRight(), queue);
  }

  @Override
  public boolean isBalanced() {
    return Math.pow(2, heightCalc(root)) <= size() && size() < Math.pow(2, heightCalc(root) + 1);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
    Iterator<T> inOrdeIter = this.inorderIterator();
    int treeSize = size();
    T[] valuesOfTree = (T[]) new Comparable[treeSize];
    for (int i = 0; i < treeSize; i++) {
      valuesOfTree[i] = inOrdeIter.next();
    }
    this.root = null;
    balanceUtilMethod(valuesOfTree, 0, treeSize - 1);
  }

  public void balanceUtilMethod(T[] values, int lowerBound, int upperBound) {
    if (lowerBound == upperBound) {
      this.add(values[lowerBound]);
    } else if ((lowerBound + 1) == upperBound) {
      this.add(values[lowerBound]);
      this.add(values[upperBound]);
    } else {
      int mid = (lowerBound + upperBound) / 2;
      this.add(values[mid]);
      balanceUtilMethod(values, lowerBound, mid - 1);
      balanceUtilMethod(values, mid + 1, upperBound);
    }
  }

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(toDotFormat(tree.getRoot()));
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    System.out.println("Main Completed");
  }

}
