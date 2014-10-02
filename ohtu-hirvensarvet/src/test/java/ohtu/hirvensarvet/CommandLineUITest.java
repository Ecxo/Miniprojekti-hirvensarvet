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

    public CommandLineUITest() {
        
    }


    @Before
    public void setUp() {
        this.cmd = new CommandReader(new Scanner(System.in));
        ui = new CommandLineUI(cmd);
    }

    @After
    public void cleanUp() {

    }


    @Test
    public void addArticleBasicCase() {

        String testname = "testname";
        cmd.setNextLine("add test");
        cmd.setNextLine("0");
        cmd.setNextLine("Hessu");
        cmd.setNextLine(testname);
        cmd.setNextLine("aku ankka");
        cmd.setNextLine("1999");
        cmd.setNextLine("done");
        cmd.setNextLine("quit");

        BibtexMaker testUi = new BibtexMaker(ui);
        testUi.run();
        testUi.getArticles().get(0).toString();
        assertEquals(testUi.getArticles().get(0).getFieldByName("title").value, testname);
    }

}
