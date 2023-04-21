
import java.util.Arrays;

public class ReverseAbc {
    public static void main(String[] args) {
        int rows = 0;
        String[][] twodarray = new String[50][50];
        MyScanner in = new MyScanner(System.in);
        while (in.hasNextLine("str")) {
            String to = in.nextLine();
            int columns = 0;
            String[] arrayforline = new String[50];
            try (MyScanner linesc = new MyScanner(to)) {
                while (linesc.hasNext("str")) {
                    arrayforline[columns] = linesc.nextLetter();
                    columns++;
                    while (columns >= arrayforline.length - 1) {
                        arrayforline = Arrays.copyOf(arrayforline, (arrayforline.length * 2));
                    }
                }
            }
            twodarray[rows++] = Arrays.copyOf(arrayforline, columns);
            while (rows >= twodarray.length - 1) {
                twodarray = Arrays.copyOf(twodarray, (twodarray.length * 2));
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = twodarray[i].length - 1; j >= 0; j--) {
                System.out.print(twodarray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
