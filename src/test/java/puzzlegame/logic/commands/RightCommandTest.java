package puzzlegame.logic.commands;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class RightCommandTest {
    @DataProvider
    Object[][] invalidBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 11, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 0}, {12, 13, 14, 15}}
                , (b) -> false, 0)}};
    }

    @DataProvider
    Object[][] validBoards() {
        return new Object[][] {{new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {8, 0, 10, 11}, {12, 13, 14, 15}}
                , (board) -> false, 0)}};
    }

    @Test(dataProvider = "validBoards")
    public void shouldAllowToMoveRight(GameBoard<Integer> actualBoard) throws Exception {
        RightCommand rightCommand = new RightCommand();
        assertTrue(rightCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "invalidBoards")
    public void shouldNotAllowToMoveRight_WhenMoveRightFromZeroRow(GameBoard<Integer> actualBoard) throws Exception {
        RightCommand rightCommand = new RightCommand();
        assertFalse(rightCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "validBoards")
    public void shouldMoveEmptyTileRight(GameBoard<Integer> actualBoard) throws Exception {
        // When
        RightCommand rightCommand = new RightCommand();
        rightCommand.execute(actualBoard);
        // Then
        GameBoard<Integer> expectedBoard = new GameBoard<>(new Integer[][]{{1, 9, 2, 3}, {4, 5, 6, 7}, {8, 10, 0, 11}, {12, 13, 14, 15}},
                (board) -> false, 0);
        assertTrue(Arrays.deepEquals(actualBoard.getBoard(), expectedBoard.getBoard()));
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "invalidBoards")
    public void shouldThrowException_WhenMoveRightFromZeroRow(GameBoard<Integer> actualBoard) {
        RightCommand rightCommand = new RightCommand();
        rightCommand.execute(actualBoard);
    }
}
