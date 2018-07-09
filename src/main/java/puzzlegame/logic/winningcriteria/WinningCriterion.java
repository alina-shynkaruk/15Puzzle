package puzzlegame.logic.winningcriteria;

import puzzlegame.logic.GameBoard;

@FunctionalInterface
public interface WinningCriterion<Tile> {
    boolean isGameWon(GameBoard<Tile> board);
}
