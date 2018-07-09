package puzzlegame.logic.boardgenerators;

import puzzlegame.logic.GameBoard;

public interface BoardGenerator<Tile> {
    GameBoard<Tile> generate();
}