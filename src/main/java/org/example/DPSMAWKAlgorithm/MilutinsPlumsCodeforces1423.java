package org.example.DPSMAWKAlgorithm;

import java.io.*;
import java.util.*;

public class MilutinsPlumsCodeforces1423 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static int n, m;

    static HashMap<Long, Integer> memo = new HashMap<>();

    static long key(int i, int j) {
        return (((long) i) << 32) | (j & 0xffffffffL);
    }

    static int query(int i, int j) throws Exception {
        long k = key(i, j);

        if (memo.containsKey(k)) return memo.get(k);

        out.println("? " + (i + 1) + " " + (j + 1));
        out.flush();

        int val = Integer.parseInt(br.readLine());

        memo.put(k, val);
        return val;
    }

    static int[] opt;
    static int[] dp;

    static int get(int i, int j) throws Exception {
        return query(i, j);
    }

    static void smawk(int[] rows, int rsz, int[] cols, int csz) throws Exception {
        if (rsz == 0) return;

        if (csz > rsz) {
            int[] st = new int[csz];
            int top = 0;

            for (int j = 0; j < csz; j++) {
                while (top > 0) {
                    int i = rows[top - 1];

                    if (get(i, cols[st[top - 1]]) >= get(i, cols[j])) top--;
                    else break;
                }

                if (top < rsz) st[top++] = j;
            }

            int[] newCols = new int[top];
            for (int i = 0; i < top; i++) newCols[i] = cols[st[i]];

            smawk(rows, rsz, newCols, top);
            return;
        }

        int[] odd = new int[(rsz + 1) / 2];
        int idx = 0;

        for (int i = 1; i < rsz; i += 2) {
            odd[idx++] = rows[i];
        }

        smawk(odd, idx, cols, csz);

        int ptr = 0;

        for (int i = 0; i < rsz; i += 2) {
            int row = rows[i];

            int bestCol = cols[ptr];
            int bestVal = get(row, bestCol);

            int limit = (i + 1 < rsz) ? opt[rows[i + 1]] : cols[csz - 1];

            while (ptr < csz && cols[ptr] <= limit) {
                int val = get(row, cols[ptr]);

                if (val < bestVal) {
                    bestVal = val;
                    bestCol = cols[ptr];
                }

                ptr++;
            }

            ptr--;

            opt[row] = bestCol;
            dp[row] = bestVal;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        int[] rows = new int[n];
        int[] cols = new int[m];

        for (int i = 0; i < n; i++) rows[i] = i;
        for (int j = 0; j < m; j++) cols[j] = j;

        opt = new int[n];
        dp = new int[n];

        smawk(rows, n, cols, m);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[i]);
        }

        out.println("! " + ans);
        out.flush();
    }
}
