package game;

import java.util.Scanner;

public class HumanPlayer implements User {
    final private Scanner in;
    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position){

        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        System.out.println("Please enter, in correct format and on empty cell, if you want give up finish typing");

        Move move;
        do {
            try {
                move = new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
                if (position.isValid(move)){
                    break;
                }
                else{
                    System.out.println("This move was not valid");
                }
            }
            catch (RuntimeException e) {
                in.nextLine();
                System.out.println("It was not correct format");
            }
        } while(true);
        return move;
    }

}
