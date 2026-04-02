package org.example.DivideAndConquerDPAlgorithm;

import java.io.*;
import java.util.*;

public class OI00P5PostOffice {
    static int V, P;
    static int[] pos;
    static long[][] dp;
    static int[][] opt;
    static long[][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        pos = new int[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        long[] pre = new long[V + 1];

        for (int i = 1; i <= V; i++) pre[i] = pre[i - 1] + pos[i];

        cost = new long[V + 2][V + 2];

        for (int l = 1; l <= V; l++) {
            for (int r = l; r <= V; r++) {
                int m = (l + r) / 2;

                long left = (long) pos[m] * (m - l) - (pre[m - 1] - pre[l - 1]);
                long right = (pre[r] - pre[m]) - (long) pos[m] * (r - m);

                cost[l][r] = left + right;
            }
        }

        final long INF = Long.MAX_VALUE;

        dp = new long[P + 1][V + 1];
        opt = new int[P + 1][V + 1];

        for (long[] row : dp) Arrays.fill(row, INF);

        dp[0][0] = 0;

        for (int v = 1; v <= V; v++) {
            dp[1][v] = cost[1][v];
            opt[1][v] = 0;
        }

        for (int p = 2; p <= P; p++) {
            solve(p, 1, V, p - 1, V - 1);
        }

        System.out.println(dp[P][V]);

        int[] offices = new int[P];

        int end = V;

        for (int p = P; p >= 1; p--) {
            int split = opt[p][end];
            int m = (split + 1 + end) / 2;
            offices[p - 1] = pos[m];
            end = split;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < P; i++) {
            if (i > 0) sb.append(' ');
            sb.append(offices[i]);
        }

        System.out.println(sb);
    }

    static void solve(int p, int lo, int hi, int optLo, int optHi) {
        if (lo > hi) return;

        int mid = (lo + hi) / 2;

        long best = Long.MAX_VALUE;
        int bestJ = optLo;

        int bound = Math.min(mid - 1, optHi);

        for (int j = optLo; j <= bound; j++) {
            if (dp[p - 1][j] == Long.MAX_VALUE / 2) continue;
            long val = dp[p - 1][j] + cost[j + 1][mid];

            if (val < best) {
                best = val;
                bestJ = j;
            }
        }

        dp[p][mid] = best;
        opt[p][mid] = bestJ;

        solve(p, lo, mid - 1, optLo, bestJ);
        solve(p, mid + 1, hi, bestJ, optHi);
    }
}
