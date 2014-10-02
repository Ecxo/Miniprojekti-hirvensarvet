/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

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
public class PrinterTest {
    
    private Printer printer;
    
    public PrinterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        printer = new Printer();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test if printer remembers lines with println and print.
     */
    @Test
    public void testPrintln() {
        printer.print("hey lets print");
        printer.println("but add a line break");
        assertTrue(printer.historyContainsLine("hey lets print"));
        assertTrue(printer.historyContainsLine("but add a line break"));

    }

}