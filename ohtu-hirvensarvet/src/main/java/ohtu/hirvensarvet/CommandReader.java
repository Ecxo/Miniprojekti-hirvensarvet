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
     * @param command command to be added to que
     */
    public void setNextLine(String command) {
        this.nextLine.add(command);
    }

    /**
     * Returns a command set in setNextLine or reads scanner.
     *
     * @return the returned line from que or scanner.
     */
    public String nextLine() {
        if (nextLine.isEmpty()) {
            this.nextLine.add(scanner.nextLine());
        }

        return nextLine.poll();
    }

    /**
     * Have to return int also
     * With pre-loaded list of commands, parses integer.
     * @return next int from scanner
     */
    public int nextInt() {
        if (nextLine.isEmpty()) {
            this.nextLine.add(scanner.nextLine());
        }
        
        return Integer.parseInt(this.nextLine.poll());
    }
}
