package org.example.BrokenProfileDPAlgorithm;

//Broken Profile DP is a dynamic programming
//technique used for grid tiling problems where we
//process the grid column-by-column (or row-by-row) while
//maintaining a bitmask (profile) representing the current column’s state.
//It helps efficiently track partially filled configurations
//and ensures valid placements.

import java.io.*;
import java.util.*;

public class BrokenProfileDP {
    static int n = 3;
    static int[][] dp;

    static void dfs(int row, int currMask, int nextMask, int col) {
        if (row == 2) {
            dp[col + 1][nextMask] += dp[col][currMask];
            return;
        }

        if ((currMask & (1 << row)) != 0) {
            dfs(row + 1, currMask, nextMask, col);
        } else {
            if (row + 1 < 2 && (currMask & (1 << (row + 1))) == 0) {
                dfs(row + 2, currMask, nextMask, col);
            }

            dfs(row + 1, currMask, nextMask | (1 << row), col);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        dp = new int[n + 1][1 << 2];

        dp[0][0] = 1;


        for (int col = 0; col < n; col++) {
            for (int mask = 0; mask < (1 << 2); mask++) {
                if (dp[col][mask] == 0) continue;
                dfs(0, mask, 0, col);
            }
        }

        out.println(dp[n][0]);
        out.flush();
    }
}
