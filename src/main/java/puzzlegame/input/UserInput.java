package puzzlegame.input;


import puzzlegame.logic.commands.CommandName;

import java.util.Optional;

public interface UserInput {
    /**
     * Maps user action to a command
     */
    Optional<CommandName> read();
}
