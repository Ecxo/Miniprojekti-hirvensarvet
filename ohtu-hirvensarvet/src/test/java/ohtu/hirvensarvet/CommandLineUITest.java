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
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private ByteArrayInputStream input;
    
    public CommandLineUITest() {
    }
    
    private CommandLineUI makeCmdLineUI(String message) {
        return new CommandLineUI(new CommandReader(new Scanner(message)));
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }
    
    @After
    public void cleanUp() {
        
        System.setOut(null);
        System.setIn(System.in);
    }
    
    @Test
    public void getCommandCorrect() {
        
        String message = "add 2";
        ui = makeCmdLineUI(message);
        
        String[] result = ui.getCommand(">");
        assertEquals(output.toString(), "> ");
        assertEquals(result[0], "add");
        assertEquals(result[1], "2");
        
    }
    
    @Test
    public void addArticleBasicCase() {
        ui = makeCmdLineUI("dOnE");
        String name = "ldskfjlfdskjfsljsa";
        Article article = ui.addArticle(name);
        
        assertTrue(output.toString().toLowerCase().contains("or done"));
        assertTrue(output.toString().toLowerCase().contains("add"));
        assertTrue(output.toString().contains(">"));
        
        assertEquals(article.getName(), name);
    }
    
    @Test
    public void addArticleForReal() {
        ui = makeCmdLineUI("test1 test2 test3\ndone");
        Article article = ui.addArticle("test");
        
        assertEquals(article.getFields().get(0).name, "test1");
        assertEquals(article.getFields().get(0).value, "test2 test3");
    }
}
