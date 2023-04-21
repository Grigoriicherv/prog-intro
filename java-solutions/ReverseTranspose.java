

import java.util.Scanner;
import java.util.Arrays;

public class ReverseTranspose {

    public static void main(String[] args) {
        int rows = 0;
        Scanner in = new Scanner(System.in);
        int[][] twodarray = new int[10][];

        int maxj = 0;
        while (in.hasNextLine()) {

            Scanner sc = new Scanner(in.nextLine());

            int[] row = new int[5];
            int columns = 0;
            while (sc.hasNextInt()) {
                row[columns] = sc.nextInt();
                columns++;
                while (columns >= row.length) {
                    row = Arrays.copyOf(row, row.length * 2);
                }
            }

            if (columns > maxj) {
                maxj = columns;
            }
            sc.close();
            if (row.length == 0) {
                continue;
            }
            twodarray[rows] = Arrays.copyOf(row, columns);

            rows++;
            while (rows >= twodarray.length - 1) {
                twodarray = Arrays.copyOf(twodarray, twodarray.length * 2);
            }
        }

        in.close();

        boolean p = true;
        for (int j = 0; j <= maxj; j++) {
            for (int i = 0; i < rows; i++) {
                if (j >= twodarray[i].length) {
                    if (i == rows - 1 && j != maxj) {
                        System.out.println();
                        p = false;
                    }
                    continue;
                }
                System.out.print(twodarray[i][j] + " ");
            }
            if (j != maxj && p) {
                System.out.println();
            }
        }
    }
}
