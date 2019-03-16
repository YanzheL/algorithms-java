package org.yanzhe.tree.traverser;

import org.yanzhe.tree.ImmutableBiTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class DefaultTraverser<V, B extends ImmutableBiTree<V>> implements Traverser<V, B> {

  @Override
  public void accept(B root, Consumer<V> consumer, Order order) {
    if (!isSupported(order)) throw new UnsupportedOperationException();
    Function<V, Boolean> op =
        o -> {
          consumer.accept(o);
          return true;
        };
    traverse(root, op, order, null);
  }

  @Override
  public <R> List<R> map(B root, Function<V, R> function, Order order) {
    if (!isSupported(order)) throw new UnsupportedOperationException();
    List<R> results = new ArrayList<>();
    traverse(root, function, order, results);
    return results;
  }

  protected <R> void applyToNode(B node, Function<V, R> function, List<R> results) {
    V data = node.getData();
    R r = data == null ? null : function.apply(data);
    if (results != null) results.add(r);
  }

  protected abstract <R> void traverse(
      B root, Function<V, R> function, Order order, List<R> results);
}
