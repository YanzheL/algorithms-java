package org.yanzhe.util;

public class Benchmark {
  public static long timeIt(Runnable runnable, int nCases, int nSuites) {
    long begin, end;
    long total = 0;
    for (int i = 0; i < nSuites; ++i) {
      begin = System.currentTimeMillis();
      for (int j = 0; j < nCases; ++j) {
        runnable.run();
      }
      end = System.currentTimeMillis();
      long duration = end - begin;
      System.out.println(String.format("Time = %d", duration));
      total += duration;
    }
    return total / nSuites;
  }
}
