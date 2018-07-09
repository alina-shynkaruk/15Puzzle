package puzzlegame.output.console;

import puzzlegame.logic.GameBoard;
import puzzlegame.output.GameBoardOutput;

public class ConsoleGameBoardOutput<GameElement> implements GameBoardOutput<GameElement> {
    @Override
    public void printGame(GameBoard<GameElement> game) {
        System.out.println(getBoardRepresentation(game));
    }

    private String getBoardRepresentation(GameBoard<GameElement> game) {
        GameElement[][] board = game.getBoard();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < game.getDimensionI(); i++) {
            for (int j = 0; j < game.getDimensionJ(); j++) {
                GameElement boardVal = board[i][j];
                if (game.getEmptyTile().equals(boardVal)) {
                    sb.append("* ");
                } else {
                    sb.append(boardVal).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
