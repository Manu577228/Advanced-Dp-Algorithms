package org.example.BitsetDPAlgorithm;

//Bitset DP is an optimization technique where we
//use bits to represent DP states instead of arrays.
//Each bit position indicates whether a state is achievable,
//enabling fast transitions using bitwise operations like shift (<<) and OR (|).
//It significantly reduces time and memory compared to traditional DP.

import java.io.*;
import java.util.*;

public class BitsetDP {
    public static void main(String[] args) throws Exception {
        int[] arr = {3, 1, 5};
        int maxSum = 10;
        BitSet dp = new BitSet(maxSum + 1);

        dp.set(0);

        for (int num : arr) {
            BitSet shifted = new BitSet(maxSum + 1);

            for (int i = 0; i <= maxSum; i++) {
                if (dp.get(i)) {
                    if (i + num <= maxSum) {
                        shifted.set(i + num);
                    }
                }
            }

            dp.or(shifted);
        }

        for (int i = 0; i <= maxSum; i++) {
            if (dp.get(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
