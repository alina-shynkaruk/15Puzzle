package puzzlegame.logic.commands;

import puzzlegame.logic.GameBoard;

public class LeftCommand implements GameCommand, SymmetricGameCommad {
    @Override
    public boolean isCommandAllowed(GameBoard gameBoard) {
        return gameBoard.getEmptyJ() > 0;
    }

    @Override
    public void execute(GameBoard gameBoard) {
        gameBoard.moveEmptyTile(0, -1);
    }

    @Override
    public GameCommand opposite() {
        return new RightCommand();
    }
}
