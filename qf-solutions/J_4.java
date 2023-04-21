package Algos;

import java.util.Scanner;

public class J {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] myGraph = new int[n][n];
        for (int i =0; i<n; i++){
            String line;
            line = sc.next();
            for (int j =0; j<n; j++){
                myGraph [i][j]= line.charAt(j)- 48;
            }
        }
        for (int k = 0; k<n; k++){

            for (int j = 0; j<n;j++){

                if (myGraph[k][j]==1){
                    System.out.print(1);
                }
                else if (myGraph[k][j]==0){
                    System.out.print(0);
                    continue;
                }

                for (int i =j+1; i<n; i++){
                    myGraph[k][i] = myGraph[k][i]-myGraph[j][i];
                    while (myGraph[k][j] < 0) {
                        myGraph[i][k] += 10;
                    }
                }

            }
            System.out.println();
        }
    }
}
