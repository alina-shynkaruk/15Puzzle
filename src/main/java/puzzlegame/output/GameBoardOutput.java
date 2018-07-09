package puzzlegame.output;

import puzzlegame.logic.GameBoard;

public interface GameBoardOutput<GameElement> {
    void printGame(GameBoard<GameElement> game);
}
