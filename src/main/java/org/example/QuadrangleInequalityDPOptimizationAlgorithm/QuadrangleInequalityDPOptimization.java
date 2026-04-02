package org.example.QuadrangleInequalityDPOptimizationAlgorithm;

//Quadrangle Inequality DP Optimization is used to speed
//up certain dynamic programming problems where the
//cost function satisfies a special inequality (quadrangle inequality).
//This property ensures the optimal partition points
//are monotonic, allowing us to reduce transitions from O(n³) → O(n²).


import java.io.*;
import java.util.*;

public class QuadrangleInequalityDPOptimization {
    static int n;
    static long[] a, pre;
    static long[][] dp;
    static int[][] opt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        a = new long[n + 1];
        pre = new long[n + 1];

        String[] s = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(s[i - 1]);
            pre[i] = pre[i - 1] + a[i];
        }

        dp = new long[n + 1][n + 1];
        opt = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            opt[i][i] = i;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;

                int start = opt[i][j - 1];
                int end = opt[i + 1][j];

                for (int k = start; k <= end && k < j; k++) {
                    long val = dp[i][k] + dp[k + 1][j] + cost(i, j);

                    if (val < dp[i][j]) {
                        dp[i][j] = val;
                        opt[i][j] = k;
                    }
                }
            }
        }

        out.println(dp[1][n]);
        out.flush();
    }

    static long cost(int l, int r) {
        return pre[r] - pre[l - 1];
    }
}
