package puzzlegame.logic.winningcriteria;

import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WinningBoardMatchCriterionTest {

    @Test
    public void shouldReturnTrue_WhenBoardMatchesWinningBoard() {
        // Given
        Integer[][] winningBoard = new Integer[][] {{0, 1}, {2, 3}};
        Integer[][] board = new Integer[][] {{0, 1}, {2, 3}};
        WinningBoardMatchCriterion<Integer> winningCriterion = new WinningBoardMatchCriterion<>(winningBoard);
        GameBoard<Integer> gameBoard = new GameBoard<>(board, winningCriterion, 1);

       assertTrue(winningCriterion.isGameWon(gameBoard));
    }

    @Test
    public void shouldReturnFalse_WhenBoardDiffersFromWinningBoard() {
        // Given
        Integer[][] winningBoard = new Integer[][] {{0, 1}, {2, 3}};
        Integer[][] board = new Integer[][] {{0, 1}, {5, 6}};
        WinningBoardMatchCriterion<Integer> winningCriterion = new WinningBoardMatchCriterion<>(winningBoard);
        GameBoard<Integer> gameBoard = new GameBoard<>(board, winningCriterion, 1);

        assertFalse(winningCriterion.isGameWon(gameBoard));
    }
}