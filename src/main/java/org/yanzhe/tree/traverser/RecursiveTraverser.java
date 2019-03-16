package org.yanzhe.tree.traverser;

import org.yanzhe.tree.ImmutableBiTree;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class RecursiveTraverser<V, B extends ImmutableBiTree<V>> extends DefaultTraverser<V, B> {
  private static Collection<Order> supportedOrders =
      Arrays.asList(Order.PRE_ORDER, Order.IN_ORDER, Order.POST_ORDER);

  @Override
  protected <R> void traverse(B root, Function<V, R> function, Order order, List<R> results) {
    if (root == null) return;
    R r;
    V data;
    switch (order) {
      case PRE_ORDER:
        data = root.getData();
        r = data == null ? null : function.apply(data);
        if (results != null) results.add(r);
        traverse(root.getLeft(), function, order, results);
        traverse(root.getRight(), function, order, results);
        break;
      case IN_ORDER:
        traverse(root.getLeft(), function, order, results);
        data = root.getData();
        r = data == null ? null : function.apply(data);
        if (results != null) results.add(r);
        traverse(root.getRight(), function, order, results);
        break;
      case POST_ORDER:
        traverse(root.getLeft(), function, order, results);
        traverse(root.getRight(), function, order, results);
        data = root.getData();
        r = data == null ? null : function.apply(data);
        if (results != null) results.add(r);
        break;
    }
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
