package org.example.SubsetConvolutionAlgorithm;

import java.io.*;
import java.util.*;

public class SubsetConvolutionLibraryChecker {
    static final int MOD = 998244353;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int N = 1 << n;

        long[] a = new long[N];
        long[] b = new long[N];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) a[i] = Long.parseLong(s[i]);

        s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) b[i] = Long.parseLong(s[i]);

        int[] pc = new int[N];

        for (int i = 1; i < N; i++) pc[i] = pc[i >> 1] + (i & 1);

        long[][] fa = new long[n + 1][N];
        long[][] fb = new long[n + 1][N];

        for (int mask = 0; mask < N; mask++) {
            fa[pc[mask]][mask] = a[mask];
            fb[pc[mask]][mask] = b[mask];
        }

        for (int bit = 0; bit < n; bit++) {
            for (int mask = 0; mask < N; mask++) {
                if ((mask & (1 << bit)) != 0) {
                    int prev = mask ^ (1 << bit);
                    for (int k = 0; k <= n; k++) {
                        fa[k][mask] = (fa[k][mask] + fa[k][prev]) % MOD;
                        fb[k][mask] = (fb[k][mask] + fb[k][prev]) % MOD;
                    }
                }
            }
        }

        long[][] fc = new long[n + 1][N];

        for (int mask = 0; mask < N; mask++) {
            for (int i = 0; i <= n; i++) {
                if (fa[i][mask] == 0) continue;
                for (int j = 0; j + i <= n; j++) {
                    if (fb[j][mask] == 0) continue;
                    fc[i + j][mask] = (fc[i + j][mask] + fa[i][mask] * fb[j][mask]) % MOD;
                }
            }
        }

        for (int bit = 0; bit < n; bit++) {
            for (int mask = 0; mask < N; mask++) {
                if ((mask & (1 << bit)) != 0) {
                    int prev = mask ^ (1 << bit);
                    for (int k = 0; k <= n; k++) {
                        fc[k][mask] = (fc[k][mask] - fc[k][prev] + MOD) % MOD;
                    }
                }
            }
        }

        long[] c = new long[N];

        for (int mask = 0; mask < N; mask++) {
            c[mask] = fc[pc[mask]][mask];
        }

        for (int i = 0; i < N; i++) {
            out.print(c[i] + " ");
        }

        out.println();
        out.flush();
    }
}
