package org.yanzhe.tree;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree printer
 *
 * @author MightyPork
 */
public class BTreePrinter {
  /** Node that can be printed */

  /**
   * Print a tree
   *
   * @param root tree root node
   */
  public static void print(BiTree root) {
    print(root, System.out);
  }

  public static void print(ImmutableBiTree root, PrintStream out) {
    List<List<String>> lines = new ArrayList<>();
    List<ImmutableBiTree> level = new ArrayList<>();
    List<ImmutableBiTree> next = new ArrayList<>();

    level.add(root);
    int nn = 1;
    int widest = 0;

    while (nn != 0) {
      List<String> line = new ArrayList<>();
      nn = 0;
      for (ImmutableBiTree n : level) {
        if (n == null) {
          line.add(null);

          next.add(null);
          next.add(null);
        } else {
          String aa = n.toString();
          line.add(aa);
          if (aa.length() > widest) widest = aa.length();

          next.add(n.getLeft());
          next.add(n.getRight());

          if (n.getLeft() != null) nn++;
          if (n.getRight() != null) nn++;
        }
      }

      if (widest % 2 == 1) widest++;

      lines.add(line);

      List<ImmutableBiTree> tmp = level;
      level = next;
      next = tmp;
      next.clear();
    }

    int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
    for (int i = 0; i < lines.size(); i++) {
      List<String> line = lines.get(i);
      int hpw = (int) Math.floor(perpiece / 2f) - 1;

      if (i > 0) {
        for (int j = 0; j < line.size(); j++) {

          // split node
          char c = ' ';
          if (j % 2 == 1) {
            if (line.get(j - 1) != null) {
              c = (line.get(j) != null) ? '┴' : '┘';
            } else {
              if (j < line.size() && line.get(j) != null) c = '└';
            }
          }
          out.print(c);

          // lines and spaces
          if (line.get(j) == null) {
            for (int k = 0; k < perpiece - 1; k++) {
              out.print(" ");
            }
          } else {
            for (int k = 0; k < hpw; k++) {
              out.print(j % 2 == 0 ? " " : "─");
            }
            out.print(j % 2 == 0 ? "┌" : "┐");
            for (int k = 0; k < hpw; k++) {
              out.print(j % 2 == 0 ? "─" : " ");
            }
          }
        }
        out.println();
      }
      // print line of numbers
      for (String f : line) {
        if (f == null) f = "";
        double gap = perpiece / 2f - f.length() / 2f;
        int gap1 = (int) Math.ceil(gap);
        int gap2 = (int) Math.floor(gap);
        // a number
        for (int k = 0; k < gap1; k++) {
          out.print(" ");
        }
        out.print(f);
        for (int k = 0; k < gap2; k++) {
          out.print(" ");
        }
      }
      out.println();
      perpiece /= 2;
    }
  }
}
