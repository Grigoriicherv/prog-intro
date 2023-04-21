package game;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter m n k and number of win games");
        int m = 0;
        int n = 0;
        int k = 0;
        int numbOfWins = 0;
        do {
            try {
                m = sc.nextInt();
                n = sc.nextInt();
                k = sc.nextInt();
                numbOfWins = sc.nextInt();
            }
            catch (RuntimeException ex) {
                System.out.println("In correct format please");
                try {
                    sc.nextLine();
                }catch (NoSuchElementException e){
                    System.out.println("Goodbye");
                    System.exit(0);
                }
            }
        }while (m<=0 || n<=0 || k<=0);

        System.out.println(new Match(new HumanPlayer(sc),new UpgradeRandom(), numbOfWins, m, n, k).StartGame());

    }
}
