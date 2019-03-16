package org.yanzhe.tree;

public interface BiTree<T> extends ImmutableBiTree<T> {

  void setLeft(BiTree<T> left);

  void setRight(BiTree<T> right);

  void setData(T data);
}
