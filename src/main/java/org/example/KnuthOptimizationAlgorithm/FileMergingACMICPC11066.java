package org.example.KnuthOptimizationAlgorithm;

import java.io.*;
import java.util.*;

public class FileMergingACMICPC11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");

            int[] a = new int[n + 1];
            long[] pref = new long[n + 1];

            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(s[i - 1]);
                pref[i] = pref[i - 1] + a[i];
            }

            long[][] dp = new long[n + 2][n + 2];
            int[][] opt = new int[n + 2][n + 2];

            for (int i = 1; i <= n; i++) {
                opt[i][i] = i;
            }

            for (int len = 2; len <= n; len++) {
                for (int i = 1; i + len - 1 <= n; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Long.MAX_VALUE;

                    long sum = pref[j] - pref[i - 1];

                    int start = opt[i][j - 1];
                    int end = opt[i + 1][j];

                    for (int k = start; k <= end; k++) {
                        long val = dp[i][k] + dp[k + 1][j] + sum;

                        if (val < dp[i][j]) {
                            dp[i][j] = val;
                            opt[i][j] = k;
                        }
                    }
                }
            }

            out.println(dp[1][n]);
        }

        out.flush();
    }
}
