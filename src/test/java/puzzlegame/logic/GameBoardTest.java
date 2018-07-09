package puzzlegame.logic;

import org.testng.annotations.Test;
import puzzlegame.logic.winningcriteria.WinningBoardMatchCriterion;

import java.util.Arrays;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GameBoardTest {

    @Test
    public void shouldMoveEmptyCellUp() {
        // Given
        GameBoard<Integer> actualBoard = new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {8, 0, 10, 11}, {12, 13, 14, 15}}
        , (board) -> false, 0);
        GameBoard<Integer> expectedBoard = new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 0, 6, 7}, {8, 5, 10, 11}, {12, 13, 14, 15}},(board) -> false, 0 );
        // When

        actualBoard.moveEmptyTile(-1, 0);
        // Then
        assertTrue(Arrays.deepEquals(actualBoard.getBoard(), expectedBoard.getBoard()));
    }

    @Test
    public void shouldReturnTrue_WhenBoardMatchesWinningBoard() {
        // Given
        Integer[][] winningBoard = new Integer[][] {{0, 1}, {2, 3}};
        Integer[][] board = new Integer[][] {{0, 1}, {2, 3}};
        GameBoard<Integer> gameBoard = new GameBoard<>(board, new WinningBoardMatchCriterion<>(winningBoard), 1);

        assertTrue(gameBoard.isGoal());
    }

    @Test
    public void shouldReturnFalse_WhenBoardDiffersFromWinningBoard() {
        // Given
        Integer[][] winningBoard = new Integer[][] {{0, 1}, {2, 3}};
        Integer[][] board = new Integer[][] {{0, 1}, {5, 6}};
        GameBoard<Integer> gameBoard = new GameBoard<>(board, new WinningBoardMatchCriterion<>(winningBoard), 1);

        assertFalse(gameBoard.isGoal());
    }
}