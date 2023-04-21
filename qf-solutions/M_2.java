package Algos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int quantofTest = sc.nextInt();
        while (quantofTest-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];

            for (int k = 0; k < n; k++) {
                a[k] = sc.nextInt();
            }

            Map<Integer, Integer> diffAndNumber = new HashMap<>();
            int quantofk = 0;
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    int key = 2 * a[j] - a[i];
                    if (diffAndNumber.containsKey(key)) {
                        quantofk += diffAndNumber.get(key);
                    }
                }
                diffAndNumber.merge(a[j], 1, Integer::sum);
            }
            System.out.println(quantofk);
        }
    }
}
