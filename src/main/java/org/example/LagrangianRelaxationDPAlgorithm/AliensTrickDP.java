package org.example.LagrangianRelaxationDPAlgorithm;

//Alien Trick (Lagrangian Relaxation DP) is an optimization technique
//used when a DP has a constraint (like “at most K segments”).
//We convert the constraint into a penalty (λ)
//and solve the DP without constraint, then use binary search on λ
//to satisfy the original constraint.

import java.io.*;
import java.util.*;

public class AliensTrickDP {
    static int n, k;
    static long[] arr, pref;

    static long[] solve(long lambda) {
        long[] dp = new long[n + 1];
        int[] cnt = new int[n + 1];

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                long sum = pref[i] - pref[j];
                long cost = dp[j] + sum * sum + lambda;

                if (cost < dp[i]) {
                    dp[i] = cost;
                    cnt[i] = cnt[j] + 1;
                }
            }
        }

        return new long[]{
                dp[n], cnt[n]
        };
    }

    public static void main(String[] args) {

        n = 4;
        k = 2;
        arr = new long[]{1, 2, 3, 4};


        pref = new long[n + 1];


        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + arr[i];
        }

        long low = 0, high = (long)1e14;
        long ans = 0;


        while (low <= high) {

            long mid = (low + high) / 2;

            long[] res = solve(mid);

            long cost = res[0];
            long segments = res[1];


            if (segments > k) {
                low = mid + 1;
            } else {
                ans = cost - mid * k;
                high = mid - 1;
            }
        }


        System.out.println(ans);
    }
}
