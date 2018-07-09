package puzzlegame;

import puzzlegame.logic.boardgenerators.BoardGenerator;
import puzzlegame.logic.boardgenerators.RandomBoardGenerator;

/**
 * Console app for playing Puzzle 15 game.
 */
public class ConsolePuzzle15App {

    private static final int DIMENSION_I = 4;
    private static final int DIMENSION_J = 4;
    private static final Integer EMPTY_TILE = 0;

    public static void main(String[] args) {
        ConsolePuzzle15Game puzzleGame = new ConsolePuzzle15Game();
        BoardGenerator<Integer> boardGenerator = new RandomBoardGenerator<>(
                getWinningBoard(),
                EMPTY_TILE,
                puzzleGame.getCommandFactory());

        puzzleGame.play(boardGenerator);
    }

    private static Integer[][] getWinningBoard() {
        Integer[][] winningBoard = new Integer[DIMENSION_I][DIMENSION_J];
        int val = 1;
        for (int i = 0; i < DIMENSION_I; ++i) {
            for (int j = 0; j < DIMENSION_J; ++j, ++val) {
                winningBoard[i][j] = val;
            }
        }
        winningBoard[DIMENSION_I - 1][DIMENSION_J - 1] = EMPTY_TILE; // empty tile is the last element
        return winningBoard;
    }
}
