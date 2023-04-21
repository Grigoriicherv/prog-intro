package Algos;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {

        Scanner mysc = new Scanner(System.in);

        int lengthofSmall = mysc.nextInt();
        int lengthofBig = mysc.nextInt();
        int lengthofOurModel = mysc.nextInt();

        if ((lengthofBig - lengthofSmall) * ((lengthofOurModel - lengthofBig) / (lengthofBig - lengthofSmall))
                != (lengthofOurModel - lengthofBig)) {
            lengthofSmall = ((lengthofOurModel - lengthofBig) / (lengthofBig - lengthofSmall)) + 1;
        } else {
            lengthofSmall = (lengthofOurModel - lengthofBig) / (lengthofBig - lengthofSmall);
        }
        lengthofSmall = lengthofSmall * 2 + 1;

        System.out.print(lengthofSmall);
        mysc.close();
    }

}
