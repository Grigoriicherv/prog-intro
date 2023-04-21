package game;

public interface Position {
    Cell getTurn();

    int[] getInts();

    boolean isValid(Move move);

    Cell getCell(int row, int column);
}
