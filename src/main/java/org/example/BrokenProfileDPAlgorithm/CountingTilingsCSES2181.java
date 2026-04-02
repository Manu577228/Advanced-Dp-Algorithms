package org.example.BrokenProfileDPAlgorithm;

import java.io.*;
import java.util.*;

public class CountingTilingsCSES2181 {
    static final int MOD = 1000000007;
    static int n, m;

    static void generate(int row, int currMask, int nextMask, int[] dpNext, int val) {
        if (row == n) {
            dpNext[nextMask] = (dpNext[nextMask] + val) % MOD;
            return;
        }

        if ((currMask & (1 << row)) != 0) {
            generate(row + 1, currMask, nextMask, dpNext, val);
        } else {
            if (row + 1 < n && (currMask & (1 << (row + 1))) == 0) {
                generate(row + 2, currMask, nextMask, dpNext, val);
            }

            generate(row + 1, currMask, nextMask | (1 << row), dpNext, val);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        int maxMask = 1 << n;
        int[] dp = new int[maxMask];
        dp[0] = 1;

        for (int col = 0; col < m; col++) {
            int[] next = new int[maxMask];

            for (int mask = 0; mask < maxMask; mask++) {
                if (dp[mask] == 0) continue;
                generate(0, mask, 0, next, dp[mask]);
            }

            dp = next;
        }

        out.println(dp[0]);
        out.flush();
    }
}
