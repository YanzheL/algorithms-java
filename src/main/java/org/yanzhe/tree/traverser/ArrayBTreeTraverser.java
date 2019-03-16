package org.yanzhe.tree.traverser;

import org.yanzhe.tree.ArrayBiTree;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ArrayBTreeTraverser<V, B extends ArrayBiTree<V>> extends DefaultTraverser<V, B> {
  private static Collection<Order> supportedOrders = Arrays.asList(Order.IN_ORDER);

  @Override
  protected <R> void traverse(B root, Function<V, R> function, Order order, List<R> results) {
    inOrderTraverse(root, function, results);
  }

  protected <R> void inOrderTraverse(B root, Function<V, R> function, List<R> results) {
    R r;
    for (V data : root.getArray()) {
      r = data == null ? null : function.apply(data);
      if (results != null) results.add(r);
    }
  }

  @Override
  public Order getBestOrder() {
    return Order.IN_ORDER;
  }

  @Override
  public Collection<Order> getSupportedOrders() {
    return supportedOrders;
  }
}
