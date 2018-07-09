package puzzlegame.output.console;

import puzzlegame.output.ErrorOutput;
import puzzlegame.output.GameBoardOutput;
import puzzlegame.output.InfoOutput;
import puzzlegame.output.OutputFactory;

public class ConsoleOutputFactory<GameElement> implements OutputFactory<GameElement> {

    private GameBoardOutput<GameElement> gameBoardOutput;
    private InfoOutput infoOutput;
    private ErrorOutput errorOutput;

    public ConsoleOutputFactory() {
        this.gameBoardOutput = new ConsoleGameBoardOutput<>();
        this.infoOutput = new ConsoleInfoOutput();
        this.errorOutput = new ConsoleErrorOutput();
    }

    @Override
    public GameBoardOutput<GameElement> getGameBoardOutput() {
        return this.gameBoardOutput;
    }

    @Override
    public InfoOutput getInfoOutput() {
        return this.infoOutput;
    }

    @Override
    public ErrorOutput getErrorOutput() {
        return this.errorOutput;
    }
}
