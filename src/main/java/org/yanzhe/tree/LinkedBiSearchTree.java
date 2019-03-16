package org.yanzhe.tree;

import java.util.Stack;

public class LinkedBiSearchTree<K extends Comparable<K>, V> extends LinkedBiTree<V>
    implements BiSearchTree<K, V> {
  protected K key;

  //  public LinkedBiSearchTree() {
  //    this(null, null);
  //  }

  public LinkedBiSearchTree(K key, V data) {
    this.key = key;
    this.data = data;
  }

  public void insert(K key, V data) {
    insert(this, key, data);
  }

  public V get(K key) {
    return getNode(this, key).data;
  }

  protected void insert(BiSearchTree<K, V> node, K key, V data) {
    LinkedBiSearchTree<K, V> root = (LinkedBiSearchTree<K, V>) node;
    Stack<LinkedBiSearchTree<K, V>> s = new Stack<>();
    s.push(root);
    LinkedBiSearchTree<K, V> pParent = root, p;
    while (!s.empty()) {
      p = s.pop();
      if (p == null) {
        LinkedBiSearchTree<K, V> newNode = new LinkedBiSearchTree<>(key, data);
        if (key.compareTo(pParent.key) > 0) pParent.right = newNode;
        else pParent.left = newNode;
        break;
      }
      if (p.key == null) {
        p.key = key;
        p.data = data;
        break;
      }
      int cmp = key.compareTo(p.key);
      if (cmp == 0) {
        p.data = data;
        break;
      } else if (cmp < 0) s.push((LinkedBiSearchTree<K, V>) p.left);
      else s.push((LinkedBiSearchTree<K, V>) p.right);
      pParent = p;
    }
  }

  protected LinkedBiSearchTree<K, V> getNode(BiTree<V> node, K key) {
    LinkedBiSearchTree<K, V> root = (LinkedBiSearchTree<K, V>) node;
    Stack<LinkedBiSearchTree<K, V>> s = new Stack<>();
    s.push(root);
    LinkedBiSearchTree<K, V> p;
    while (!s.empty()) {
      p = s.pop();
      if (p == null) continue;
      int cmp = key.compareTo(p.key);
      if (cmp == 0) return p;
      else if (cmp < 0) s.push((LinkedBiSearchTree<K, V>) p.left);
      else s.push((LinkedBiSearchTree<K, V>) p.right);
    }
    return null;
  }
}
