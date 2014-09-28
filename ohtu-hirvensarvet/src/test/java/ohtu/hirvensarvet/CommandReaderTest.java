/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author petri
 */
public class CommandReaderTest {
    
    CommandReader cmd;
    
    public CommandReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cmd = new CommandReader(new Scanner(System.in));
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test if commands are saved to the command que
     */
    @Test
    public void commandsCanBeQeued() {
        cmd.setNextLine("hello");
        cmd.setNextLine("world");
        assertEquals(cmd.nextLine(),"hello");
        assertEquals(cmd.nextLine(), "world");
        

    }

}