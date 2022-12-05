package game;


public class Match {
    private int countWins1 = 0;
    private int countWins2 = 0;

    private int numbOfGames = 0;

    final int numbOfWins;
    private boolean swapPlayers = true;
    Player player1;
    Player player2;
    final int m;
    final int n;
    final int k;

    public Match (Player player1, Player player2, int numbOfWins, int m, int n, int k){
        this.player1 = player1;
        this.player2 = player2;

        this.numbOfWins = numbOfWins;
        this.m = m;
        this.n = n;
        this.k = k;
    }
    public String StartGame() {
        while (true) {
            final int result = new TwoPlayerGame(
                    new MNKBoard(m,n,k),
                    player1,
                    player2,
                    swapPlayers
            ).play(true);
            switch (result) {
                case 1:

                    countWins1++;
                    System.out.println((numbOfGames + 1) + " game won player1");
                    swapPlayers = !swapPlayers;
                    break;
                case 2:

                    countWins2++;
                    System.out.println((numbOfGames + 1) + " game won player2");
                    swapPlayers = !swapPlayers;
                    break;
                case 0:
                    System.out.println("Draw");
                    swapPlayers = !swapPlayers;
                    break;

                case -2:
                    return("First player won, because second player give up");
                case -3:
                    return("Second player won, because first player give up");

                default:
                    throw new AssertionError("Unknown result " + result);
            }
            numbOfGames++;
            if (countWins1 == numbOfWins) {
                return ("First player won " + numbOfWins + " games !!! And Second player won "
                        + countWins2 + " games");
            }
            if (countWins2 == numbOfWins) {
                return ("Second player won " + numbOfWins + " games !!! And First player won " +
                        countWins1 + " games");
            }
        }
    }

}
