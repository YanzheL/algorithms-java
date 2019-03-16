import org.yanzhe.tree.*;
import org.yanzhe.tree.traverser.ArrayBTreeTraverser;
import org.yanzhe.tree.traverser.Traverser;
import org.yanzhe.util.Benchmark;

public class Main {

  public static void main(String[] args) {
    String input = "A,B,D,#,#,F,H,#,#,#,C,#,G,#,#";

    try {

      BiTree<String> linkedBiTree = new LinkedBiTree<>();
      BiTree<String> arrayBiTree = new ArrayBiTree<>(4);
      BTreeFactory.fromString(input, ",", "#", linkedBiTree);
      BTreePrinter.print(linkedBiTree);
      BTreeFactory.fromString(input, ",", "#", arrayBiTree);
      BTreePrinter.print(arrayBiTree);

      //      Traverser<String, BiTree<String>> traverser = new MorrisTraverser<>();
      //      Traverser traverser = new MorrisTraverser();
      Traverser traverser = new ArrayBTreeTraverser();
      //      Traverser traverser = new RecursiveTraverser();
      long avg =
          Benchmark.timeIt(
              () -> traverser.accept(arrayBiTree, o -> {}, Traverser.Order.IN_ORDER),
              100000000,
              10);
      //      new ArrayBTreeTraverser()
      //          .accept(arrayBiTree, o -> System.out.print(o), Traverser.Order.PRE_ORDER);

      //            linkedBiTree.setRight(arrayBiTree);
      //      BTreePrinter.print(linkedBiTree);
      //      System.out.println(String.format("Avg = %d", avg));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
