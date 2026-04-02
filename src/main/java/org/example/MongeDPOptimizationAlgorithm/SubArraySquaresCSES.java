package org.example.MongeDPOptimizationAlgorithm;

import java.util.*;
import java.io.*;

class SubArraySquaresCSES {

    static long[] pre, dpPrev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        pre = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            pre[i] = pre[i - 1] + Long.parseLong(st.nextToken());

        dpPrev = new long[n + 1];
        for (int i = 1; i <= n; i++)
            dpPrev[i] = sq(pre[i]);

        for (int t = 2; t <= k; t++) {
            int sz = n - t + 1;
            int[] rows = new int[sz];
            int[] cols = new int[sz];
            for (int i = 0; i < sz; i++) {
                rows[i] = i + t;
                cols[i] = i + (t - 1);
            }
            int[] argmin = new int[n + 1];
            smawk(rows, cols, argmin);

            long[] dpCurr = new long[n + 1];
            for (int i = t; i <= n; i++)
                dpCurr[i] = dpPrev[argmin[i]] + sq(pre[i] - pre[argmin[i]]);
            dpPrev = dpCurr;
        }

        System.out.println(dpPrev[n]);
    }

    static long sq(long x) {
        return x * x;
    }

    static long entry(int row, int col) {
        if (col >= row) return Long.MAX_VALUE / 2;
        return dpPrev[col] + sq(pre[row] - pre[col]);
    }

    static void smawk(int[] rows, int[] cols, int[] argmin) {
        smawkRec(rows, 0, rows.length, cols, 0, cols.length, argmin);
    }

    static void smawkRec(int[] rows, int rlo, int rhi,
                         int[] cols, int clo, int chi, int[] argmin) {
        int m = rhi - rlo;
        if (m == 0) return;

        if (m == 1) {
            long best = Long.MAX_VALUE / 2;
            int bestCol = cols[clo];
            for (int p = clo; p < chi; p++) {
                long v = entry(rows[rlo], cols[p]);
                if (v < best) {
                    best = v;
                    bestCol = cols[p];
                }
            }
            argmin[rows[rlo]] = bestCol;
            return;
        }

        int[] stack = new int[m];
        int top = -1;
        for (int p = clo; p < chi; p++) {
            int c = cols[p];
            while (top >= 0 && entry(rows[top], stack[top]) >= entry(rows[top], c))
                top--;
            if (top < m - 1)
                stack[++top] = c;
        }
        int rLen = top + 1;


        int evenCount = (m + 1) / 2;
        int[] evenRows = new int[evenCount];
        for (int i = 0; i < evenCount; i++) evenRows[i] = rows[rlo + 2 * i];
        smawkRec(evenRows, 0, evenCount, stack, 0, rLen, argmin);


        int lo = 0;
        for (int i = 1; i < m; i += 2) {
            int oddRow = rows[rlo + i];
            int lowerCol = argmin[rows[rlo + i - 1]];
            int upperCol = (i + 1 < m) ? argmin[rows[rlo + i + 1]] : stack[rLen - 1];
            while (lo < rLen && stack[lo] < lowerCol) lo++;
            long best = Long.MAX_VALUE / 2;
            int bestCol = stack[lo];
            for (int p = lo; p < rLen && stack[p] <= upperCol; p++) {
                long v = entry(oddRow, stack[p]);
                if (v < best) {
                    best = v;
                    bestCol = stack[p];
                }
            }
            argmin[oddRow] = bestCol;
        }
    }
}