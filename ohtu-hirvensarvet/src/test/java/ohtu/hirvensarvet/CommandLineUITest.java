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

    }

    @After
    public void cleanUp() {
    }

    @Test
    public void addArticleBasicCase() {
        cmd.setNextLine("done");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        testUi.getArticles().get(0).toString();
        assertEquals(testUi.getArticles().get(0).getFieldByName("title").value, "testname");
    }

    @Test
    public void correctlyPrintedListOfArticles() {
        cmd.setNextLine("done");
        cmd.setNextLine("list");

        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("author  = \"Hessu\","));
        assertTrue(printer.historyContainsLine("aku ankka"));
        assertTrue(printer.historyContainsLine("1999"));
    }

    @Test
    public void helpIsPrinted() {
        cmd.setNextLine("done");
        cmd.setNextLine("help");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("Available commands:"));
    }

    @Test
    public void savingToFileUI() {
        cmd.setNextLine("done");
        cmd.setNextLine("save");
        cmd.setNextLine("test");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("Please specify the name of the file to save into, or 'cancel' to cancel the operation:"));
        assertTrue(printer.historyContainsLine("Entries successfully saved"));
    }

    @Test
    public void userDoesNotSpecifyWhatToAdd() {
        cmd.setNextLine("done");
        cmd.setNextLine("add");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("Please enter article id (add somearticle)"));

    }

    @Test
    public void userTriesToAddInvalidFields() {

        cmd.setNextLine("lazerturbo man");
        cmd.setNextLine("done");
        cmd.setNextLine("add");
        cmd.setNextLine("quit");
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("is not a valid BibTex field."));

    }

    @Test
    public void userGetsHelpWhenAddingFields() {
        cmd.setNextLine("help");
        cmd.setNextLine("done");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("Valid types:"));

    }

    @Test
    public void userCanAddValidOptionalField() {
        cmd.setNextLine("chapter IV");
        cmd.setNextLine("done");
        cmd.setNextLine("list");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("chapter"));

    }

    @Test
    public void userCanRemoveReference() {
        cmd.setNextLine("done");
        cmd.setNextLine("remove test");
        cmd.setNextLine("list");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        //list should not print the reference name, because it was removed.
        assertTrue(!printer.historyContainsLine("test"));
    }

    @Test
    public void userCanEditArticle() {
        cmd.setNextLine("done");
        cmd.setNextLine("edit test");
        cmd.setNextLine("author");
        cmd.setNextLine("heikki");
        cmd.setNextLine("done");
        cmd.setNextLine("list");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("author  = \"heikki \""));
    }

    @Test
    public void userCanRemoveArticleFromMemory() {
        cmd.setNextLine("done");
        cmd.setNextLine("remove test");
        cmd.setNextLine("edit test");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        assertTrue(printer.historyContainsLine("No article found"));
    }
}
