package puzzlegame.logic.commands;

import puzzlegame.logic.GameBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A placeholder for all available game commands.
 */
public class CommandFactory {
    private final Map<CommandName, GameCommand> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
    }

    public CommandFactory(Map<CommandName, GameCommand> commands) {
        this.commands = commands;
    }

    public void addCommand(final CommandName name, final GameCommand command) {
        commands.put(name, command);
    }

    public Optional<GameCommand> getCommand(CommandName commandName) {
        return commands.containsKey(commandName) ?
                Optional.of(commands.get(commandName)) : Optional.empty();
    }

    public boolean isCommandAllowed(CommandName commandName, GameBoard board) {
        return getCommand(commandName)
                .orElseThrow(IllegalArgumentException::new)
                .isCommandAllowed(board);
    }

    public void executeCommand(CommandName commandName, GameBoard board) {
        getCommand(commandName)
                .orElseThrow(IllegalArgumentException::new)
                .execute(board);
    }

    public List<CommandName> listCommands() {
        return new ArrayList<>(commands.keySet());
    }

    public List<CommandName> listCommands(Predicate<GameCommand> filterPredicate) {
        return commands.entrySet().stream()
                .filter(entry -> filterPredicate.test(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
