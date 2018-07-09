package puzzlegame.logic.commands;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DownCommandTest {
    @DataProvider
    Object[][] validBoards() {
        return new Object[][]{{new GameBoard<>(new Character[][]{{'1', '9', '2', '3'}, {'4', '5', '6', '7'},
                {'8', '*', 'a', 'b'}, {'c', 'd', 'f', 'g'}}
                , (b) -> false, '*')}};
    }

    @DataProvider
    Object[][] invalidBoards() {
        return new Object[][]{{new GameBoard<>(new Integer[][]{{1, 13, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 0, 14, 15}}
                , (board) -> false, 0)}};
    }

    @Test(dataProvider = "validBoards")
    public void shouldAllowToMoveDown(GameBoard<Character> actualBoard) throws Exception {
        DownCommand downCommand = new DownCommand();
        assertTrue(downCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "invalidBoards")
    public void shouldNotAllowToMoveDown_WhenMoveDownFromZeroRow(GameBoard<Integer> actualBoard) throws Exception {
        DownCommand downCommand = new DownCommand();
        assertFalse(downCommand.isCommandAllowed(actualBoard));
    }

    @Test(dataProvider = "validBoards")
    public void shouldMoveEmptyTileDown(GameBoard<Character> actualBoard) throws Exception {
        // When
        DownCommand downCommand = new DownCommand();
        downCommand.execute(actualBoard);
        // Then
        GameBoard<Character> expectedBoard = new GameBoard<>(new Character[][]
                {{'1', '9', '2', '3'}, {'4', '5', '6', '7'},
                        {'8', 'd', 'a', 'b'}, {'c', '*', 'f', 'g'}},
                (board) -> false, '*');
        assertTrue(Arrays.deepEquals(actualBoard.getBoard(), expectedBoard.getBoard()));
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "invalidBoards")
    public void shouldThrowException_WhenMoveDownFromZeroRow(GameBoard<Integer> actualBoard) {
        DownCommand downCommand = new DownCommand();
        downCommand.execute(actualBoard);
    }

}