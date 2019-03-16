package org.yanzhe.tree;

public interface ImmutableBiTree<T> {

  <R extends ImmutableBiTree<T>> R getLeft();

  <R extends ImmutableBiTree<T>> R getRight();

  T getData();

  default boolean isLeaf() {
    return getLeft() == null && getRight() == null;
  }
}
