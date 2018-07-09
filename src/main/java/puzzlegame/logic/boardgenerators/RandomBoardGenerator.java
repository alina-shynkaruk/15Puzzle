package puzzlegame.logic.boardgenerators;

import puzzlegame.logic.GameBoard;
import puzzlegame.logic.winningcriteria.WinningBoardMatchCriterion;
import puzzlegame.logic.commands.CommandFactory;
import puzzlegame.logic.commands.CommandName;
import puzzlegame.logic.commands.SymmetricGameCommad;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBoardGenerator<Tile> implements BoardGenerator<Tile> {
    private Tile[][] winningPuzzle;
    private final int minRandomSwaps;
    private final int maxRandomSwaps;
    private static final int DEFAULT_MIN = 3;
    private static final int DEFAULT_MAX = 50;
    private Tile emptyTile;
    private CommandFactory cf;

    public RandomBoardGenerator(Tile[][] winningPuzzle, Tile emptyTile, CommandFactory cf) {
        this(winningPuzzle, emptyTile, cf, DEFAULT_MIN, DEFAULT_MAX);
    }

    public RandomBoardGenerator(Tile[][] winningPuzzle, Tile emptyTile, CommandFactory cf,
                                int minRandomSwaps, int maxRandomSwaps) {
        this.winningPuzzle = winningPuzzle;
        this.emptyTile = emptyTile;
        this.cf = cf;
        this.minRandomSwaps = minRandomSwaps;
        this.maxRandomSwaps = maxRandomSwaps;
    }

    /**
     * This implementation guarantees that a resulted board will be solvable.
     * Algorithm is the following:
     * 1. Take a solution board
     * 2. Perform swap of an empty tile with a random neighbour tile.
     * 3. Repeat action for reasonable number of times (randomly generated within configurable bounds).
     * In order for this algorithm to be correct every command must have an opposite one (e.x. opposite for "up" is "down").
     *
     * @return solvable random board
     */
    @Override
    public GameBoard<Tile> generate() {
        GameBoard<Tile> board = new GameBoard<>(winningPuzzle, new WinningBoardMatchCriterion<>(winningPuzzle), emptyTile);
        int numberOfSwaps = generateRandomInt(minRandomSwaps, maxRandomSwaps);
        List<CommandName> availableCommandNames = cf.listCommands(command -> command instanceof SymmetricGameCommad);
        int commandsListSize = availableCommandNames.size();
        int direction;
        CommandName commandName;
        while (numberOfSwaps > 0) {
            direction = generateRandomInt(0, commandsListSize);
            commandName = availableCommandNames.get(direction);
            if (cf.isCommandAllowed(commandName, board)) {
                cf.executeCommand(commandName, board);
                --numberOfSwaps;
            }
        }
        return board;
    }

    private int generateRandomInt(int least, int bound) {
        return ThreadLocalRandom.current().nextInt(least, bound);
    }
}
