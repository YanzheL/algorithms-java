package org.yanzhe.tree;

public interface SearchTree<K extends Comparable<K>, V> {
  void insert(K key, V data);

  V get(K key);
}
