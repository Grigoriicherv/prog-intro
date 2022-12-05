package game;

import java.util.Map;

public enum Cell {
    E, X, O, T, F;

    static final Map<Integer, Cell> fillCells = Map.of(
            1, Cell.X,
            2, Cell.O,
            3, Cell.T,
            4, Cell.F
    );
}
