package org.yanzhe.tree;

public class LinkedBiTree<T> implements BiTree<T> {
  protected T data;
  protected BiTree<T> left;
  protected BiTree<T> right;

  public BiTree<T> getLeft() {
    return left;
  }

  public void setLeft(BiTree<T> left) {
    this.left = left;
  }

  public BiTree<T> getRight() {
    return right;
  }

  public void setRight(BiTree<T> right) {
    this.right = right;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    T d = getData();
    return d == null ? null : d.toString();
  }
}
