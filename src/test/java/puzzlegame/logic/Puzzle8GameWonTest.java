package puzzlegame.logic;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import puzzlegame.logic.commands.CommandFactory;
import puzzlegame.logic.commands.DirectionCommandName;
import puzzlegame.logic.commands.DownCommand;
import puzzlegame.logic.commands.LeftCommand;
import puzzlegame.logic.commands.RightCommand;
import puzzlegame.logic.commands.UpCommand;
import puzzlegame.logic.winningcriteria.WinningBoardMatchCriterion;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Puzzle8GameWonTest {
    Integer[][] winningPuzzle;
    Integer emptyTile;
    CommandFactory cf;

    @BeforeClass
    public void initFields() {
        winningPuzzle = new Integer[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        emptyTile = 0;
        cf = new CommandFactory();
        cf.addCommand(DirectionCommandName.UP, new UpCommand());
        cf.addCommand(DirectionCommandName.DOWN, new DownCommand());
        cf.addCommand(DirectionCommandName.LEFT, new LeftCommand());
        cf.addCommand(DirectionCommandName.RIGHT, new RightCommand());
    }

    @Test
    public void shouldFinishGame_WhenWinningBoardIsReached() {
        // Given
        Integer[][] board = new Integer[][] {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
        GameBoard<Integer> gameBoard = new GameBoard<>(board, new WinningBoardMatchCriterion<>(winningPuzzle), emptyTile);
        //When
        cf.executeCommand(DirectionCommandName.DOWN, gameBoard);
        cf.executeCommand(DirectionCommandName.RIGHT, gameBoard);
        // Then
        assertTrue(gameBoard.isGoal());
    }

    @Test
    public void shouldNotFinishGame_WhenWinningBoardIsNotReached() {
        // Given
        Integer[][] board = new Integer[][] {{1, 2, 3}, {0, 4, 6}, {7, 5, 8}};
        GameBoard<Integer> gameBoard = new GameBoard<>(board, new WinningBoardMatchCriterion<>(winningPuzzle), emptyTile);
        // When
        cf.executeCommand(DirectionCommandName.DOWN, gameBoard);
        cf.executeCommand(DirectionCommandName.RIGHT, gameBoard);
        //Then
        assertFalse(gameBoard.isGoal());
    }
}
