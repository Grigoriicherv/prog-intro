package Algos;

import java.util.Arrays;
import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int h = sc.nextInt();
        int xl = x-h;
        int xr = x+h;
        int yl = y-h;
        int yr = y+h;
        while (--n > 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            h = sc.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        System.out.print((xl + xr) / 2 + " ");
        System.out.print((yl + yr) / 2 + " ");
        int max = Math.max(xr - xl, yr - yl);
        if (max % 2 == 0) {
            System.out.print(max / 2);
        } else {
            System.out.print(max / 2 + 1);
        }

    }
}
