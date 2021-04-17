package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;

  @Override
  public void add(T t) {
    BSTNode<T> newNode = new BSTNode<T>(t, null, null);
    upperBound++;
    super.add(t);
    if (this.height() > Math.log(upperBound) / Math.log(1.5)) {
      BSTNode<T> scapeGoatNode = getParentNode(root, newNode);
      while (3 * subtreeSize(scapeGoatNode) <= (2 * subtreeSize(getParentNode(root, scapeGoatNode))) + 1) {
        scapeGoatNode = getParentNode(root, scapeGoatNode);
      }
      BSTInterface<T> subTree = new BinarySearchTree<T>(scapeGoatNode);
      subTree.balance();
      getParentNode(root, scapeGoatNode).setRight(subTree.getRoot());
    }
  }

  @Override
  public boolean remove(T element) {
    boolean removed = super.remove(element);
    if (upperBound > 2 * this.size()) {
      this.balance();
      upperBound = this.size();
    }
    return removed;
  }
}
