package org.example.SumOverSubsetsDPAlgorithm;

import java.io.*;
import java.util.*;

public class CompatibleNumbersCodeforces165E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] a = new int[n];
        int MAXV = 1 << 22;
        int[] who = new int[MAXV];
        Arrays.fill(who, -1);

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            who[a[i]] = a[i];
        }

        for (int i = 0; i < 22; i++) {
            for (int mask = 0; mask < MAXV; mask++) {
                if ((mask & (1 << i)) != 0) {
                    if (who[mask] == -1) {
                        if (who[mask ^ (1 << i)] != -1) {
                            who[mask] = who[mask ^ (1 << i)];
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int compliment = (MAXV - 1) ^ a[i];
            if (i > 0) sb.append(' ');
            sb.append(who[compliment]);
        }

        pw.println(sb);
        pw.flush();
        pw.close();
    }
}
