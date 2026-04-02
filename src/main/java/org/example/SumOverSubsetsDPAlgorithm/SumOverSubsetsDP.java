package org.example.SumOverSubsetsDPAlgorithm;

//Sum Over Subsets DP (SOS DP) is a technique used to efficiently
//compute values over all subsets of a bitmask.
//It transforms a naive O(3ⁿ) subset computation into O(n · 2ⁿ)
//by reusing previously computed results.

import java.util.*;
import java.io.*;

public class SumOverSubsetsDP {
    public static void main(String[] args) throws IOException {
        int n = 3;

        int size = 1 << n;

        int[] f = {1, 2, 3, 4, 5, 6, 7, 8};

        int[] dp = new int[size];

        for (int i = 0; i < size; i++) {
            dp[i] = f[i];
        }

        for (int bit = 0; bit < n; bit++) {
            for (int mask = 0; mask < size; mask++) {
                if ((mask & (1 << bit)) != 0) {
                    dp[mask] += dp[mask ^ (1 << bit)];
                }
            }
        }

        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < size; i++) {
            out.println("dp[" + i + "] = " + dp[i]);
        }

        out.flush();
    }
}
