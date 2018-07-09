package puzzlegame;

import puzzlegame.input.UserInput;
import puzzlegame.input.console.ConsoleUserInput;
import puzzlegame.logic.GameBoard;
import puzzlegame.logic.boardgenerators.BoardGenerator;
import puzzlegame.logic.commands.*;
import puzzlegame.output.ErrorOutput;
import puzzlegame.output.GameBoardOutput;
import puzzlegame.output.InfoOutput;
import puzzlegame.output.OutputFactory;
import puzzlegame.output.console.ConsoleOutputFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ConsolePuzzle15Game {
    private static final String COMMAND_IS_NOT_ALLOWED = "Command is not allowed.";
    private static final String COMMAND_IS_NOT_SUPPORTED = "Command is not supported.";

    private UserInput userInput;
    private CommandFactory cf;
    private OutputFactory<Integer> output;

    public ConsolePuzzle15Game() {
        userInput = new ConsoleUserInput(getInputToCommandsMapping());
        cf = initCommandFactory();
        output = new ConsoleOutputFactory<>();
    }

    public CommandFactory getCommandFactory() {
        return cf;
    }

    public void play(BoardGenerator<Integer> randomBoardGenerator) {
        InfoOutput infoOutput = output.getInfoOutput();
        ErrorOutput errorOutput = output.getErrorOutput();
        GameBoardOutput<Integer> gameBoardOutput = output.getGameBoardOutput();

        GameBoard<Integer> board = randomBoardGenerator.generate();

        infoOutput.printInfo(getRules(
                getInputToCommandsMapping()));
        gameBoardOutput.printGame(board);

        while (!board.isGoal()) {
            Optional<CommandName> commandName = userInput.read();
            if (commandName.isPresent()) {
                if (cf.isCommandAllowed(commandName.get(), board)) {
                    cf.executeCommand(commandName.get(), board);
                    gameBoardOutput.printGame(board);
                } else {
                    errorOutput.printError(COMMAND_IS_NOT_ALLOWED);
                }
            } else {
                errorOutput.printError(COMMAND_IS_NOT_SUPPORTED);
            }
        }

        infoOutput.printInfo("Congratulations! You have won :)");
    }

    private String getRules(Map<String, CommandName> inputToCommandMap) {
        StringBuilder rules = new StringBuilder("Welcome to the Puzzle15 game. To move the empty cell type:\n");
        inputToCommandMap.forEach((key, command) -> rules.append(key).append(" - for ").append(command).append("\n"));
        return rules.toString();
    }

    private CommandFactory initCommandFactory() {
        CommandFactory cf = new CommandFactory();
        cf.addCommand(DirectionCommandName.UP, new UpCommand());
        cf.addCommand(DirectionCommandName.DOWN, new DownCommand());
        cf.addCommand(DirectionCommandName.LEFT, new LeftCommand());
        cf.addCommand(DirectionCommandName.RIGHT, new RightCommand());
        return cf;
    }

    private Map<String, CommandName> getInputToCommandsMapping() {
        Map<String, CommandName> inputToCommandMap = new LinkedHashMap<>();
        inputToCommandMap.put("w", DirectionCommandName.UP);
        inputToCommandMap.put("a", DirectionCommandName.LEFT);
        inputToCommandMap.put("s", DirectionCommandName.DOWN);
        inputToCommandMap.put("d", DirectionCommandName.RIGHT);
        return inputToCommandMap;
    }
}
