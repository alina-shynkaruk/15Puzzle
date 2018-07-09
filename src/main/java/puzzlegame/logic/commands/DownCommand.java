package puzzlegame.logic.commands;

import puzzlegame.logic.GameBoard;

public class DownCommand implements GameCommand, SymmetricGameCommad {
    @Override
    public boolean isCommandAllowed(GameBoard gameBoard) {
        return gameBoard.getEmptyI() < gameBoard.getDimensionI() - 1;
    }

    @Override
    public void execute(GameBoard gameBoard) {
        gameBoard.moveEmptyTile(1, 0);
    }

    @Override
    public GameCommand opposite() {
        return new UpCommand();
    }
}
