package puzzlegame.logic.commands;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UpCommandTest {

    @DataProvider
    Object[][] invalidBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 0, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}}
                , (b) -> false, 0)}};
    }

    @DataProvider
    Object[][] validBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {8, 0, 10, 11}, {12, 13, 14, 15}}
                , (board) -> false, 0)}};
    }

    @Test(dataProvider = "validBoards")
    public void shouldAllowToMoveUp(GameBoard<Integer> actualBoard) throws Exception {
        UpCommand upCommand = new UpCommand();
        assertTrue(upCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "invalidBoards")
    public void shouldNotAllowToMoveUp_WhenMoveUpFromZeroRow(GameBoard<Integer> actualBoard) throws Exception {
        UpCommand upCommand = new UpCommand();
        assertFalse(upCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "validBoards")
    public void shouldMoveEmptyTileUp(GameBoard<Integer> actualBoard) throws Exception {
        // When
        UpCommand upCommand = new UpCommand();
        upCommand.execute(actualBoard);
        // Then
        GameBoard<Integer> expectedBoard = new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 0, 6, 7}, {8, 5, 10, 11}, {12, 13, 14, 15}},
                (board) -> false, 0);
        assertTrue(Arrays.deepEquals(actualBoard.getBoard(), expectedBoard.getBoard()));
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "invalidBoards")
    public void shouldThrowException_WhenMoveUpFromZeroRow(GameBoard<Integer> actualBoard) {
        UpCommand upCommand = new UpCommand();
        upCommand.execute(actualBoard);
    }
}