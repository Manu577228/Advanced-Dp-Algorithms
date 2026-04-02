package org.example.DPSMAWKAlgorithm;

//DP + SMAWK is an optimization technique used when a
//DP transition involves finding a minimum over a monotonic matrix.
//It reduces the complexity of row-wise minimum queries
//from O(n²) to O(n) using the SMAWK algorithm on totally monotone matrices.

import java.io.*;
import java.util.*;

public class DPSMAWK {
    static int n = 5;
    static long[] dp = new long[n];

    static long cost(int j, int i) {
        return (long) (i - j) * (i - j);
    }

    static void computeDP() {
        int[] opt = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Long.MAX_VALUE;

            for (int j = opt[i - 1]; j < i; j++) {
                long val = dp[j] + cost(j, i);

                if (val < dp[i]) {
                    dp[i] = val;
                    opt[i] = j;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        computeDP();

        for (int i = 0; i < n; i++) {
            out.print(dp[i] + " ");
        }

        out.flush();
    }
}
