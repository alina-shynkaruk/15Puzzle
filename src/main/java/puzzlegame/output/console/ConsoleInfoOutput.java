package puzzlegame.output.console;

import puzzlegame.output.InfoOutput;

public class ConsoleInfoOutput implements InfoOutput {
    @Override
    public void printInfo(String info) {
        System.out.println(info);
    }
}
