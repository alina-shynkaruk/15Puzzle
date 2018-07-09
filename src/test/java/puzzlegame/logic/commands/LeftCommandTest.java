package puzzlegame.logic.commands;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LeftCommandTest {
    @DataProvider
    Object[][] invalidBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 11, 2, 3}, {4, 5, 6, 7}, {0, 9, 10, 8}, {12, 13, 14, 15}}
                , (b) -> false, 0)}};
    }

    @DataProvider
    Object[][] validBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {8, 0, 10, 11}, {12, 13, 14, 15}}
                , (board) -> false, 0)}};
    }

    @Test(dataProvider = "validBoards")
    public void shouldAllowToMoveLeft(GameBoard<Integer> actualBoard) throws Exception {
        LeftCommand leftCommand = new LeftCommand();
        assertTrue(leftCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "invalidBoards")
    public void shouldNotAllowToMoveLeft_WhenMoveLeftFromZeroRow(GameBoard<Integer> actualBoard) throws Exception {
        LeftCommand leftCommand = new LeftCommand();
        assertFalse(leftCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "validBoards")
    public void shouldMoveEmptyTileLeft(GameBoard<Integer> actualBoard) throws Exception {
        // When
        LeftCommand leftCommand = new LeftCommand();
        leftCommand.execute(actualBoard);
        // Then
        GameBoard<Integer> expectedBoard = new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {0, 8, 10, 11}, {12, 13, 14, 15}},
                (board) -> false, 0);
        assertTrue(Arrays.deepEquals(actualBoard.getBoard(), expectedBoard.getBoard()));
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "invalidBoards")
    public void shouldThrowException_WhenMoveLeftFromZeroRow(GameBoard<Integer> actualBoard) {
        LeftCommand leftCommand = new LeftCommand();
        leftCommand.execute(actualBoard);
    }
}
