package puzzlegame.logic.commands;

import puzzlegame.logic.GameBoard;

public interface GameCommand {
    boolean isCommandAllowed(GameBoard gameBoard);

    void execute(GameBoard gameBoard);
}
