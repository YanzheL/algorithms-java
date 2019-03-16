package org.yanzhe.tree.traverser;

import org.yanzhe.tree.ImmutableBiTree;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Traverser<V, B extends ImmutableBiTree<V>> {

  void accept(B root, Consumer<V> consumer, Order order);

  <R> List<R> map(B root, Function<V, R> function, Order order);

  Order getBestOrder();

  Collection<Order> getSupportedOrders();

  default boolean isSupported(Order order) {
    return getSupportedOrders().contains(order);
  }

  default void accept(B root, Consumer<V> consumer) {
    accept(root, consumer, getBestOrder());
  }

  default <R> List<R> map(B root, Function<V, R> function) {
    return map(root, function, getBestOrder());
  }

  enum Order {
    PRE_ORDER,
    IN_ORDER,
    POST_ORDER
  }
}
