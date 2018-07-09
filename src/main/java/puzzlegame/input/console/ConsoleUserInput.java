package puzzlegame.input.console;

import puzzlegame.input.UserInput;
import puzzlegame.logic.commands.CommandName;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUserInput implements UserInput {
    private final Scanner sc;
    private Map<String, CommandName> inputToCommandMap;

    public ConsoleUserInput(Map<String, CommandName> inputToCommandMap) {
        this.sc = new Scanner(System.in);
        this.inputToCommandMap = inputToCommandMap;
    }

    /**
     * Maps a user input string to a command
     */
    @Override
    public Optional<CommandName> read() {
        if (sc.hasNext()) {
            String userInput = sc.next();
            if (inputToCommandMap.containsKey(userInput)) {
                return Optional.of(inputToCommandMap.get(userInput));
            }
        }
        return Optional.empty();
    }
}
