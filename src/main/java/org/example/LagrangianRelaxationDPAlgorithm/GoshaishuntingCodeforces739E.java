package org.example.LagrangianRelaxationDPAlgorithm;

import java.io.*;
import java.util.*;

public class GoshaishuntingCodeforces739E {
    static int n, A, B;
    static double[] p, u;

    static double[] dp, next;
    static int[] cnt, nextCnt;

    static class Pair {
        double value;
        int used;

        Pair(double v, int u) {
            value = v;
            used = u;
        }
    }

    static Pair runDP(double penalty) {
        Arrays.fill(dp, -1e100);
        Arrays.fill(cnt, 0);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            System.arraycopy(dp, 0, next, 0, A + 1);
            System.arraycopy(cnt, 0, nextCnt, 0, A + 1);

            for (int j = 0; j <= A; j++) {
                if (dp[j] < -1e90) continue;

                double curr = dp[j];
                int used = cnt[j];

                update(j, curr + u[i] - penalty, used + 1);

                if (j + 1 <= A) {
                    update(j + 1, curr + p[i], used);
                    update(j + 1, curr + p[i] + u[i] - p[i] * u[i] - penalty, used + 1);
                }
            }

            double[] t1 = dp;
            dp = next;
            next = t1;

            int[] t2 = cnt;
            cnt = nextCnt;
            nextCnt = t2;
        }

        double best = -1e100;
        int used = 0;

        for (int j = 0; j <= A; j++) {
            if (dp[j] > best ||
                    (Math.abs(dp[j] - best) < 1e-12 && cnt[j] < used)
            ) {
                best = dp[j];
                used = cnt[j];
            }
        }

        return new Pair(best, used);
    }

    static void update(int idx, double val, int used) {
        if (val > next[idx] || (Math.abs(val - next[idx]) < 1e-12 && used < nextCnt[idx])) {
            next[idx] = val;
            nextCnt[idx] = used;
        }
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);

        n = fs.nextInt();
        A = fs.nextInt();
        B = fs.nextInt();

        p = new double[n];
        u = new double[n];

        for (int i = 0; i < n; i++) p[i] = fs.nextDouble();
        for (int i = 0; i < n; i++) u[i] = fs.nextDouble();

        dp = new double[A + 1];
        next = new double[A + 1];
        cnt = new int[A + 1];
        nextCnt = new int[A + 1];

        double low = 0, high = 1;

        for (int it = 0; it < 45; it++) {
            double mid = (low + high) / 2.0;

            Pair res = runDP(mid);

            if (res.used <= B) {
                high = mid;
            } else {
                low = mid;
            }
        }

        Pair res = runDP(high);

        double ans = res.value + high * B;

        PrintWriter out = new PrintWriter(System.out);
        out.printf("%.10f\n", ans);
        out.flush();
    }

    static class FastScanner {
        byte[] buf = new byte[1 << 16];
        int len = 0, ptr = 0;
        InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { s = -1; c = read(); }
            while (c > ' ') {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * s;
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String next() throws IOException {
            int c;
            StringBuilder sb = new StringBuilder();
            do { c = read(); } while (c <= ' ');
            while (c > ' ') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
    }
}
