package ohtu.hirvensarvet;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class is to allow easier testing buy passing commands without having to
 * use scanner.
 *
 * @author petri
 */
public class CommandReader {

    private Scanner scanner;
    private LinkedList<String> nextLine;

    CommandReader(Scanner scan) {
        this.scanner = scan;
        this.nextLine = new LinkedList<>();
        
    }

    /**
     * Sets the next line to return, used for testing.
     *
     * @param command
     */
    public void setNextLine(String command) {
        this.nextLine.add(command);
    }

    /**
     * Returns a command set in setNextLine or reads scanner.
     *
     * @param command
     */
    public String nextLine() {
        if (nextLine.isEmpty()) {
            this.nextLine.add(scanner.nextLine());
        }

        return nextLine.poll();
    }
}
