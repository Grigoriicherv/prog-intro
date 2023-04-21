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

    private String countingWins(int result, int countWins1, int countWins2, String playerX, String playerO){
        switch (result) {
            case 1:
                countWins1++;
                System.out.println((numbOfGames + 1) + " game won "+ playerX);
                break;
            case 2:
                countWins2++;
                System.out.println((numbOfGames + 1) + " game won "+ playerO);
                break;
            case 0:
                System.out.println("Draw");
                break;

            case -2:
                return(playerX+" won, because " +playerO+ " give up");

            case -3:
                return (playerO+" won, because " +playerX+ " give up");

            default:
                throw new AssertionError("Unknown result " + result);
        }
        if (swapPlayers){
            this.countWins1 = countWins1;
            this.countWins2 = countWins2;
        }
        else{
            this.countWins2 = countWins1;
            this.countWins1 = countWins2;
        }
        swapPlayers = !swapPlayers;
        numbOfGames++;
        if (this.countWins1 == numbOfWins) {
            return ("player1 won " + numbOfWins + " games !!! And player2 won "
                    + this.countWins2 + " games");
        }
        else if (this.countWins2 == numbOfWins) {
            return ( "player2 won " + numbOfWins + " games !!! And player1 won " +
                    this.countWins1 + " games");
        }
        else{return null;}
    }

    private void swaping(){
        Player temp;

        temp = player1;
        player1 = player2;
        player2 = temp;

    }
    public String StartGame() {
        String temp;
        do {
            final int result = new TwoPlayerGame(
                    new MNKBoard(m,n,k),
                    player1,
                    player2
            ).play(true);

            if (swapPlayers){
                temp = countingWins(result, countWins1, countWins2, "player1", "player2");
            }
            else{
                temp = countingWins(result, countWins2, countWins1, "player2", "player1");
            }
            if (temp == null){
                swaping();
            }
        }while (temp == null);
        return temp;
    }

}
