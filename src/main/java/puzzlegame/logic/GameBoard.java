package puzzlegame.logic;

import puzzlegame.logic.winningcriteria.WinningCriterion;

import java.util.Arrays;

public class GameBoard<Tile> {
    private final int dimensionI;
    private final int dimensionJ;
    private final Tile[][] board;
    private final Tile emptyTile;
    private final WinningCriterion<Tile> winningCriterion;
    private int emptyI;
    private int emptyJ;

    /**
     * @throws IllegalArgumentException when given board is empty or given tile is null.
     */
    public GameBoard(Tile[][] board, WinningCriterion<Tile> winningCriterion, Tile emptyTile) {
        if (board.length == 0 || board[0].length == 0) {
            throw new IllegalArgumentException("Board should contain elements");
        }
        if (emptyTile == null) {
            throw new IllegalArgumentException("Empty tile can't be null.");
        }
        this.dimensionI = board.length;
        this.dimensionJ = board[0].length;
        this.board = deepCopy(board);
        this.winningCriterion = winningCriterion;
        this.emptyTile = emptyTile;
        calculateEmptyTileIndexes();
    }

    private Tile[][] deepCopy(Tile[][] source) {
        Tile[][] target = (Tile[][]) new Object[dimensionI][dimensionJ];
        for (int i = 0; i < source.length; i++) {
            target[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return target;
    }

    /**
     * @throws IllegalArgumentException when initial board doesn't contain a given empty tile
     */
    private void calculateEmptyTileIndexes() {
        for (int i = 0; i < dimensionI; i++) {
            for (int j = 0; j < dimensionJ; j++) {
                if (emptyTile.equals(board[i][j])) {
                    this.emptyI = i;
                    this.emptyJ = j;
                    return;
                }
            }
        }
        // element was not found
        throw new IllegalArgumentException("Initial board should contain the empty tile element " + emptyTile);
    }

    /**
     * Returns true if the game is won
     */
    public boolean isGoal() {
        return winningCriterion.isGameWon(this);
    }

    /**
     * Swaps the empty tile with the tile which has position
     * vertically {@code emptyI} + {@code stepsDown} and horizontally {@code emptyJ} + {@code stepsRight}
     *
     * @param stepsDown  Number of rows an empty tile will be moved to. Positive for down, negative for up.
     * @param stepsRight Number of columns an empty tile will be moved to. Positive for right, negative for left.
     * @throws IllegalStateException if new indexes for empty tile are out of board bounds.
     */
    public void moveEmptyTile(int stepsDown, int stepsRight) {
        if (emptyI + stepsDown > dimensionI - 1
                || emptyI + stepsDown < 0
                || emptyJ + stepsRight > dimensionI - 1
                || emptyJ + stepsRight < 0) {
            throw new IllegalStateException(String.format("Illegal move by (%d, %d)", stepsDown, stepsRight));
        }

        board[emptyI][emptyJ] = board[emptyI + stepsDown][emptyJ + stepsRight];
        emptyI += stepsDown;
        emptyJ += stepsRight;
        board[emptyI][emptyJ] = emptyTile;
    }

    public Tile[][] getBoard() {
        return deepCopy(board);
    }

    /**
     * @return a row number of empty tile on a board
     */
    public int getEmptyI() {
        return emptyI;
    }

    /**
     * @return a column number of empty tile on a board
     */
    public int getEmptyJ() {
        return emptyJ;
    }

    public Tile getEmptyTile() {
        return emptyTile;
    }

    /**
     * @return number of rows in a board
     */
    public int getDimensionI() {
        return dimensionI;
    }

    /**
     * @return number of columns in a board
     */
    public int getDimensionJ() {
        return dimensionJ;
    }
}
