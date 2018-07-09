package puzzlegame.output;

public interface OutputFactory<GameElement> {
    GameBoardOutput<GameElement> getGameBoardOutput();
    InfoOutput getInfoOutput();
    ErrorOutput getErrorOutput();
}
