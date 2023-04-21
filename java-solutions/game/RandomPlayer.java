package game;

import java.util.Random;


public class RandomPlayer implements Player {
    private final Random random = new Random();

    public RandomPlayer() {}
    @Override
    public Move makeMove(Position position){

        Move move = new Move(
                random.nextInt(position.getInts()[0]),
                random.nextInt(position.getInts()[1]),
                position.getTurn()

        );
        return move;

    }
}
