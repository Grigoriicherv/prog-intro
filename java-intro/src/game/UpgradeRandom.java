package game;

import java.util.Random;

public class UpgradeRandom implements Player{

    private final Random random = new Random();

    public UpgradeRandom() {}
    @Override
    public Move makeMove(Position position){
        Move move;
        do {
            move = new Move(
                    random.nextInt(position.getInts()[0]),
                    random.nextInt(position.getInts()[1]),
                    position.getTurn()

            );
        } while (!position.isValid(move));
        return move;
    }
}
