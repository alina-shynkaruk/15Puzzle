package puzzlegame.logic.boardgenerators;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import puzzlegame.logic.GameBoard;
import puzzlegame.logic.commands.CommandFactory;
import puzzlegame.logic.commands.DirectionCommandName;
import puzzlegame.logic.commands.LeftCommand;
import puzzlegame.logic.commands.RightCommand;
import puzzlegame.logic.commands.UpCommand;

import static org.testng.Assert.assertNotEquals;

public class RandomBoardGeneratorTest {

    Integer[][] winningPuzzle;
    Integer emptyTile;
    CommandFactory cf;
    RandomBoardGenerator<Integer> generator;

    @BeforeClass
    public void initFields() {
        winningPuzzle = new Integer[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        emptyTile = 0;
        cf = new CommandFactory();
        cf.addCommand(DirectionCommandName.UP, new UpCommand());
        cf.addCommand(DirectionCommandName.LEFT, new LeftCommand());
        cf.addCommand(DirectionCommandName.RIGHT, new RightCommand());

        generator = new RandomBoardGenerator<>(winningPuzzle, emptyTile, cf, 20, 40);
    }

    @Test
    public void shouldGenerateDifferentBoards() throws Exception {
        GameBoard<Integer> firstPuzzle = generator.generate();
        GameBoard<Integer> secondPuzzle = generator.generate();
        assertNotEquals(firstPuzzle.getBoard(), secondPuzzle.getBoard());
    }
}