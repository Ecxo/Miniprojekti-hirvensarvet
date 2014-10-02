package ohtu.hirvensarvet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandLineUITest {

    CommandLineUI ui;
    /*see: 
     * http://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
     * */
    private CommandReader cmd;
    private Printer printer;

    public CommandLineUITest() {
    }

    @Before
    public void setUp() {
        this.cmd = new CommandReader(new Scanner(System.in));
        this.printer = new Printer();
        ui = new CommandLineUI(cmd, printer);

        /**
         * Creating a valid article used in tests
         */
        String testname = "testname";
        cmd.setNextLine("add test");
        cmd.setNextLine("0");
        cmd.setNextLine("Hessu");
        cmd.setNextLine(testname);
        cmd.setNextLine("aku ankka");
        cmd.setNextLine("1999");
        cmd.setNextLine("done");

    }

    @After
    public void cleanUp() {
    }

    @Test
    public void addArticleBasicCase() {

        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        testUi.getArticles().get(0).toString();
        assertEquals(testUi.getArticles().get(0).getFieldByName("title").value, "testname");
    }

    @Test
    public void corretlyPrintedListOfArticles() {

        cmd.setNextLine("list");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("author  = \"Hessu\","));
        assertTrue(printer.historyContainsLine("aku ankka"));
        assertTrue(printer.historyContainsLine("1999"));
    }
}
