package org.example.BitsetDPAlgorithm;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class CookingAtCoder {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        int[] t = new int[n];

        int total = 0;

        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(s[i]);
            total += t[i];
        }

        BigInteger dp = BigInteger.ONE;

        for (int x : t) {
            dp = dp.or(dp.shiftLeft(x));
        }

        int ans = total;

        for (int i = 0; i <= total; i++) {
            if (dp.testBit(i)) {
                int other = total - i;
                int curr = Math.max(i, other);
                ans = Math.min(ans, curr);
            }
        }

        out.println(ans);
        out.flush();
    }
}
