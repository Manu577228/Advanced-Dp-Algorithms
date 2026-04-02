package org.example.QuadrangleInequalityDPOptimizationAlgorithm;


import java.io.*;
import java.util.*;

public class NKLEAVESSPOJ {
    static int N, K;
    static long[] w;
    static long[] prefix;
    static long[] prefixPos;
    static long[] dp;
    static long[] ndp;
    static int[] opt;

    static long cost(int l, int r) {
        long sumW = prefix[r] - prefix[l];
        long sumWP = prefixPos[r] - prefixPos[l];
        return sumWP - (long) (l + 1) * sumW;
    }

    static void solve(int lo, int hi, int optLo, int optHi, long[] prev, long[] curr) {
        if (lo > hi) return;

        int mid = (lo + hi) >> 1;

        long best = Long.MAX_VALUE;
        int bestK = optLo;

        for (int k = optLo; k <= Math.min(mid - 1, optHi); k++) {
            long val = prev[k] + cost(k, mid);

            if (val < best) {
                best = val;
                bestK = k;
            }
        }

        curr[mid] = best;
        solve(lo, mid - 1, optLo, bestK, prev, curr);
        solve(mid + 1, hi, bestK, optHi, prev, curr);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w         = new long[N + 1];
        prefix    = new long[N + 1];
        prefixPos = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            w[i] = Long.parseLong(br.readLine().trim());
            prefix[i]    = prefix[i - 1] + w[i];
            prefixPos[i] = prefixPos[i - 1] + (long) i * w[i];
        }

        dp  = new long[N + 1];
        ndp = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = cost(0, i);
        }


        for (int k = 2; k <= K; k++) {
            Arrays.fill(ndp, Long.MAX_VALUE);
            solve(k, N, k - 1, N - 1, dp, ndp);
            long[] tmp = dp;
            dp = ndp;
            ndp = tmp;
        }

        System.out.println(dp[N]);
    }
}
