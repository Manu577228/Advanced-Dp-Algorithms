package org.example.SubsetConvolutionAlgorithm;

//Subset Convolution computes a function over all subsets
//where each subset result is formed by combining
//values of all possible partitions of that subset.
//It is commonly used with bitmask DP and optimized
//using Fast Zeta Transform (FZT) to reduce complexity from O(3^n) to O(n^2 · 2^n).

import java.io.*;
import java.util.*;

public class SubsetConvolution {
    static int n = 3;
    static int N = 1 << n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long[] f = new long[N];
        long[] g = new long[N];
        long[] h = new long[N];

        for (int i = 0; i < N; i++) {
            f[i] = i;
            g[i] = 2 * i;
        }

        long[][] F = new long[n + 1][N];
        long[][] G = new long[n + 1][N];

        for (int mask = 0; mask < N; mask++) {
            int bits = Integer.bitCount(mask);
            F[bits][mask] = f[mask];
            G[bits][mask] = g[mask];
        }

        for (int i = 0; i < n; i++) {
            for (int mask = 0; mask < N; mask++) {
                if ((mask & (1 << i)) != 0) {
                    for (int k = 0; k <= n; k++) {
                        F[k][mask] += F[k][mask ^ (1 << i)];
                        G[k][mask] += G[k][mask ^ (1 << i)];
                    }
                }
            }
        }

        long[][] H = new long[n + 1][N];
        for (int mask = 0; mask < N; mask++) {
            for (int k = 0; k <= n; k++) {
                for (int j = 0; j <= k; j++) {
                    H[k][mask] += F[j][mask] * G[k - j][mask];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int mask = 0; mask < N; mask++) {
                if ((mask & (1 << i)) != 0) {
                    for (int k = 0; k <= n; k++) {
                        H[k][mask] -= H[k][mask ^ (1 << i)];
                    }
                }
            }
        }

        for (int mask = 0; mask < N; mask++) {
            int bits = Integer.bitCount(mask);
            h[mask] = H[bits][mask];
        }

        for (int i = 0; i < N; i++) {
            out.print(h[i] + " ");
        }

        out.close();
    }
}

