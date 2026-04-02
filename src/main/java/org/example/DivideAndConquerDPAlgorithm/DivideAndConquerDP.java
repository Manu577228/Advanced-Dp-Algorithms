package org.example.DivideAndConquerDPAlgorithm;

//Divide & Conquer DP is an optimization technique used when a
//DP transition has a monotonic property, allowing us to compute
//states faster by recursively dividing the range instead of checking all possibilities.
//It reduces complexity from O(n²) to O(n log n) (or similar).

import java.util.*;
import java.io.*;

public class DivideAndConquerDP {
    static int n = 5;
    static int[] arr = {0, 11, 2, 3, 4, 5};

    static int[] dp = new int[6];
    static int[] newDp = new int[6];

    static int cost(int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static void compute(int left, int right, int optL, int optR) {
        if (left > right) return;

        int mid = (left + right) >> 1;

        int bestIdx = -1;
        newDp[mid] = Integer.MAX_VALUE;

        for (int j = optL; j <= Math.min(mid, optR); j++) {
            int val = dp[j] + cost(j + 1, mid);

            if (val < newDp[mid]) {
                newDp[mid] = val;
                bestIdx = j;
            }
        }

        compute(left, mid - 1, optL, bestIdx);
        compute(mid + 1, right, bestIdx, optR);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        compute(1, n, 0, n);

        for (int i = 1; i <= n; i++) {
            System.out.print(newDp[i] + " ");
        }
    }
}
