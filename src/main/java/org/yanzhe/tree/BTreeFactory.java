package org.yanzhe.tree;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;

public class BTreeFactory {
  public static void fromString(
      String string, String delimiter, String nullToken, BiTree<String> root)
      throws InstantiationException, InvocationTargetException, IllegalAccessException,
          InvalidParameterException {
    if (root == null) throw new InvalidParameterException("Root node cannot be null");
    fromTokens(Arrays.asList(string.split(delimiter)).iterator(), nullToken, root);
  }

  public static <T> void fromTokens(Iterator<T> tokens, T nullToken, BiTree<T> root)
      throws InstantiationException, InvocationTargetException, IllegalAccessException {
    Constructor<? extends BiTree> constructor = null;
    try {
      constructor = root.getClass().getConstructor();
    } catch (NoSuchMethodException ignore) {
    }
    fromTokens(tokens, nullToken, root, constructor);
  }

  @SuppressWarnings("unchecked")
  private static <T> BiTree<T> fromTokens(
      Iterator<T> tokens, T nullToken, BiTree<T> root, Constructor<? extends BiTree> constructor)
      throws InstantiationException, InvocationTargetException, IllegalAccessException {
    if (tokens.hasNext()) {
      T token = tokens.next();
      if (!token.equals(nullToken)) {
        if (root == null) root = constructor.newInstance();
        root.setData(token);
        root.setLeft(fromTokens(tokens, nullToken, root.getLeft(), constructor));
        root.setRight(fromTokens(tokens, nullToken, root.getRight(), constructor));
      }
    }
    return root;
  }
}
