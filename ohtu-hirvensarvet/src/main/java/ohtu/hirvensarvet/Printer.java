/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

import java.util.LinkedList;

/**
 * This class can be used in place of print, when it is desired that printed
 * lines can be easily checked later.
 *
 */
public class Printer {

    private LinkedList<String> printedLines;

    public Printer() {
        this.printedLines = new LinkedList<>();
    }

    public void println(String p) {
        this.printedLines.add(p.toLowerCase().trim());
        System.out.println(p);
    }

    public void print(String p) {
        this.printedLines.add(p.toLowerCase().trim());
        System.out.print(p);
    }

    public boolean historyContainsLine(String searchedString) {
        for (String line : this.printedLines) {
            if (line.contains(searchedString.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }
}
