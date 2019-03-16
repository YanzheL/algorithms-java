package org.yanzhe.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayBiTree<T> implements BiTree<T> {
  protected List<T> array;
  protected int height;
  protected int capacity;

  public ArrayBiTree(int height) {
    this(height, new ArrayList<>(Collections.nCopies((1 << height) - 1, null)));
  }

  public ArrayBiTree(int height, List<T> array) {
    this.height = height;
    capacity = (1 << height) - 1;
    this.array = array;
  }

  @Override
  public BiTree<T> getRight() {
    int begin = capacity / 2 + 1;
    int end = capacity;
    List<T> sub = array.subList(begin, end);

    return height <= 1 || getData() == null ? null : new ArrayBiTree<>(height - 1, sub);
  }

  @Override
  public void setRight(BiTree<T> right) {
    setRange(capacity / 2 + 1, capacity, right);
  }

  @Override
  public BiTree<T> getLeft() {
    int begin = 0;
    int end = capacity / 2;
    List<T> sub = array.subList(begin, end);
    return height <= 1 || getData() == null ? null : new ArrayBiTree<>(height - 1, sub);
  }

  @Override
  public void setLeft(BiTree<T> left) {
    setRange(0, capacity / 2, left);
  }

  @Override
  public T getData() {
    return capacity == 0 ? null : array.get(capacity / 2);
  }

  @Override
  public void setData(T t) {
    array.set(capacity / 2, t);
  }

  @Override
  public boolean isLeaf() {
    return height == 1;
  }

  private void setRange(int begin, int end, BiTree<T> tree) {
    List<T> d;
    if (tree == null) {
      return;
    }
    ArrayBiTree<T> t = (ArrayBiTree<T>) tree;
    if (t.capacity != end - begin)
      throw new UnsupportedOperationException(
          String.format(
              "Cannot copy source array of size = %d to destination of size = %d",
              t.capacity, end - begin));
    d = t.array;
    for (int i = begin; i < end; ++i) {
      array.set(i, d.get(i - begin));
    }
  }

  public List<T> getArray() {
    return array;
  }

  @Override
  public String toString() {
    T d = getData();
    return d == null ? "null" : d.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ArrayBiTree)) return false;
    ArrayBiTree<T> other = (ArrayBiTree<T>) obj;
    return other.height == height && other.capacity == capacity && other.array.equals(array);
  }
}
