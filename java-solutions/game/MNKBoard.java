package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );
    private final int m;
    private final int n;
    private final int k;
    private final Cell[][] field;
    private Cell turn;
    private int col;
    private int row;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }
    @Override
    public int[] getInts() {
        return new int[]{m, n, k};
    }
    @Override
    public Position getPosition() {
        return this;
    }
    @Override
    public GameResult makeMove(Move move) {
        if(!isValid(move)){
           return GameResult.LOOSE;
        }

        row = move.getRow();
        col = move.getCol();

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(k)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }
        if (!checkWin(4)) {
                    turn = turn == Cell.X ? Cell.O : Cell.X;
                }
        else{
            return GameResult.REPEAT;
        }
        return GameResult.UNKNOWN;


    }
    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        return count == 0;
    }
    private boolean check(int k, int row, int col, Cell turn, int rstep, int cstep){
        int count = 0;
        int c = col - cstep;
        int r = row - rstep;
        while (r<= field.length-1 && c<= field[row].length-1 && (r >= 0) && (c >= 0) && (field[r][c] == turn)) {
            count++;
            c -= cstep;
            r -= rstep;
        }
        c = col + cstep;
        r = row + rstep;
        while (r>=0 && c<= field[row].length-1 && (r<= field.length-1) && (c >= 0) && field[r][c]  == turn ){
            count++;
            c += cstep;
            r += rstep;
        }
        return count + 1 >= k;
    }
    private boolean checkWin(int k) {
        return check(k, row, col , turn, 1, 0) || check (k, row, col , turn, 0, 1)
                || check(k, row, col , turn, 1, 1) || check(k, row, col , turn, 1, -1);
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E;
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("   ");
        int len = String.valueOf(n).length();
        for (int i = 0; i< n ; i++){
            sb.append(i+1);
            sb.append(" ".repeat(len));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            int i =0;
            if (r+1>9){
                sb.append(r + 1).append(" ");
            }
            else{
                sb.append(" ").append(r + 1).append(" ");
            }

            for (Cell cell : field[r]) {
                ++i;
                if (i>9){
                    sb.append(CELL_TO_STRING.get(cell));
                    sb.append(" ".repeat(len+1));
                }
                else {
                    sb.append(CELL_TO_STRING.get(cell));
                    sb.append(" ".repeat(len));
                }


            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
