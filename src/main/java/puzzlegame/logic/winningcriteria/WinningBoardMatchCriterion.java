package puzzlegame.logic.winningcriteria;

import puzzlegame.logic.GameBoard;

import java.util.Arrays;

/**
 * Game is won if the board matches a winning board
 *
 * @param <BoardTile> Type of tiles the board consists of
 */
public class WinningBoardMatchCriterion<BoardTile> implements WinningCriterion<BoardTile> {
    private BoardTile[][] winningBoard;

    public WinningBoardMatchCriterion(BoardTile[][] winningBoard) {
        this.winningBoard = winningBoard;
    }

    @Override
    public boolean isGameWon(GameBoard<BoardTile> board) {
        return Arrays.deepEquals(board.getBoard(), winningBoard);
    }
}
