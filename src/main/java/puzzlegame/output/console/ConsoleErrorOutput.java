package puzzlegame.output.console;

import puzzlegame.output.ErrorOutput;

public class ConsoleErrorOutput implements ErrorOutput {
    @Override
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
