package org.yanzhe.tree.traverser;

import org.yanzhe.tree.BiTree;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MorrisTraverser<V, B extends BiTree<V>> extends DefaultTraverser<V, B> {
  private static Collection<Order> supportedOrders =
      Arrays.asList(Order.PRE_ORDER, Order.IN_ORDER, Order.POST_ORDER);

  // 负责把逆序之后的每个节点从下往上访问一遍
  private <R> void visitEdge(B root, Function<V, R> function, List<R> results) {
    B tail = reverseEdge(root); // 逆序，使每个节点的右指针指向各自的父节点
    B cur = tail;
    while (cur != null) {
      applyToNode(cur, function, results);
      cur = cur.getRight(); // 此时每个节点的right指针都指向各自的父节点，从而实现从右下往上遍历
    }
    reverseEdge(tail); // 再调用一次reverseEdge(tail)调整回来，恢复原来的指针状态
  }

  // 把root右子树每个节点的右指针的方向颠倒，也就是说往上指，返回最下方的节点prev，那么逆序的指针将从最下方的prev依次往上指
  private B reverseEdge(B root) {
    B prev = null;
    B next;
    while (root != null) {
      // 使右指针指向各自的父节点，逆序
      next = root.getRight();
      root.setRight(prev);
      prev = root;
      root = next;
    }
    return prev;
  }

  @Override
  protected <R> void traverse(B root, Function<V, R> function, Order order, List<R> results) {
    if (root == null) return;
    // 设置两个指针cur和mostRight, cur作为当前节点，mostRight作为当前节点的左子树根
    B cur = root;
    B mostRight;
    while (cur != null) {
      mostRight = cur.getLeft();
      if (mostRight != null) {
        // cur移动到p的左子树的最右节点
        while (mostRight.getRight() != null && !mostRight.getRight().equals(cur))
          mostRight = mostRight.getRight();
        // 如果最右节点的右指针为空，则最右节点的右指针指向cur
        if (mostRight.getRight() == null) {
          mostRight.setRight(cur);
          if (order == Order.PRE_ORDER) { // 前序遍历时访问的时刻
            applyToNode(cur, function, results);
          }
          cur = cur.getLeft(); // 对p的左节点再次进行如上操作
          continue;
        } else {
          // 如果最右节点的右指针不为空，则最右节点的右指针设为null，也就是说把上述的操作调整回来
          // 通过此操作也可确保遍历前后整棵树的结构不发生变化
          mostRight.setRight(null);
          if (order == Order.POST_ORDER)
            visitEdge(cur.getLeft(), function, results); // 后序遍历需要额外增加逆序调整的过程
        }
      } else if (order == Order.PRE_ORDER) {
        applyToNode(cur, function, results);
      }
      if (order == Order.IN_ORDER) {
        applyToNode(cur, function, results);
      }
      cur = cur.getRight();
    }
    if (order == Order.POST_ORDER) visitEdge(root, function, results);
  }

  @Override
  public Collection<Order> getSupportedOrders() {
    return supportedOrders;
  }

  @Override
  public Order getBestOrder() {
    return Order.IN_ORDER;
  }
}
