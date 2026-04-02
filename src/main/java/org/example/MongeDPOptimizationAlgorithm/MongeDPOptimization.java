package org.example.MongeDPOptimizationAlgorithm;

//Monge DP Optimization is used when a DP transition cost matrix
//satisfies the Monge property (quadrangle inequality).
//This property ensures the optimal decision points
//are monotonic, allowing us to reduce transitions
//from O(n²) to O(n log n) or even O(n).

import java.io.*;

public class MongeDPOptimization {
    static int n = 5;
    static long[] dp = new long[n];
    static long[] prev = new long[n];

    static long cost(int j, int i) {
        return (long) (i - j) * (i - j);
    }

    static void compute(int l, int r, int optL, int optR) {
        if (l > r) return;

        int mid = (l + r) >> 1;

        long bestVal = Long.MAX_VALUE;

        int bestK = -1;

        for (int k = optL; k <= Math.min(mid, optR); k++) {
            long val = prev[k] + cost(k, mid);

            if (val < bestVal) {
                bestVal = val;
                bestK = k;
            }
        }

        dp[mid] = bestVal;
        compute(l, mid - 1, optL, bestK);
        compute(mid + 1, r, bestK, optR);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < n; i++) prev[i] = i;

        compute(0, n - 1, 0, n - 1);

        for (int i = 0; i < n; i++) {
            out.print(dp[i] + " ");
        }

        out.flush();
    }
}
