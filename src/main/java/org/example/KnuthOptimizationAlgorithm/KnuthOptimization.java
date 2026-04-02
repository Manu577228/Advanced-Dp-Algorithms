package org.example.KnuthOptimizationAlgorithm;

//Knuth Optimization is a DP optimization technique used
//when the optimal partition point (opt[i][j]) is monotonic.
//It reduces transition complexity from O(N³) to O(N²) by
//restricting the search range of the partition index.

import java.io.*;
import java.util.*;

public class KnuthOptimization {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n][n];
        int[][] opt = new int[n][n];

        long[] pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + a[i];
        }

        for (int i = 0; i < n; i++) {
            opt[i][i] = i;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                dp[i][j] = Long.MAX_VALUE;

                int start = opt[i][j - 1];
                int end = opt[i + 1][j];

                if (end < start) end = start;

                for (int k = start; k <= end && k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j] + (pref[j + 1] - pref[i]);

                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        opt[i][j] = k;
                    }
                }
            }
        }

        out.println(dp[0][n - 1]);
        out.flush();
    }
}
