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
            catch (NoSuchElementException e){
                System.out.println("Goodbye");
                System.exit(0);
            }
            catch (RuntimeException ex) {
                System.out.println("In correct format please");
                sc.nextLine();
            }
        }while (m<=0 || n<=0 || k<=0);

        int countWins1 = 0;
        int countWins2 = 0;
        int numbOfGames = 0;
        boolean swapPlayers = true;
        while(true) {
            final int result = new TwoPlayerGame(
                    new MNKBoard(m, n, k),
                    new UpgradeRandom(),
                    //new UpgradeRandom(),
                    //new Nullplayer(),
                    new HumanPlayer(sc),
                    swapPlayers
            ).play(true);
            boolean giveUp = false;
            switch (result) {
                case 1:

                    countWins1++;
                    System.out.println((numbOfGames+1) + " game won player1");
                    swapPlayers = !swapPlayers;
                    break;
                case 2:

                    countWins2++;
                    System.out.println((numbOfGames+1) + " game won player2");
                    swapPlayers = !swapPlayers;
                    break;
                case 0:
                    System.out.println("Draw");
                    swapPlayers = !swapPlayers;
                    break;

                case -2:
                    System.out.println("First player won, because second player give up");
                    giveUp = true;
                    break;
                case -3:
                    System.out.println("Second player won, because first player give up");
                    giveUp = true;
                    break;
                default:
                    throw new AssertionError("Unknown result " + result);
            }
            if (giveUp){
                break;
            }
            numbOfGames++;
            if (countWins1 == numbOfWins){
                System.out.println("First player won " + numbOfWins + " games !!! And Second player won "
                        + countWins2 + " games");

                break;
            }
            if (countWins2 == numbOfWins){
                System.out.println("Second player won " + numbOfWins + " games !!! And First player won " +
                         countWins1 +  " games");
                break;
            }
        }
    }
}
