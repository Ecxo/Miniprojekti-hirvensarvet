/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CitationParserTest {
    
    public CitationParserTest() {
    }
    
    String testString1;
    
    @Before
    public void setUp() throws FileNotFoundException {
        
        File f = new File("test.bib");
        Scanner s = new Scanner(f);
        
        testString1 = "";
        
        while(s.hasNextLine()) {
            String nextline = s.nextLine();
            testString1 += nextline;
            testString1 += "\n";
        }
    }
    
    @Test
    public void testCorrectNumber() {
        
        assertEquals(CitationParser.parseBibtexFile(testString1).size(), 17);
    }
    
    @Test
    public void testRandomCorrect() {
        
        assertEquals(CitationParser.parseBibtexFile(testString1).get(5)
                .getFieldByName("title").value,
                "Infusing active learning into introductory programming courses");
    }
    
    @Test
    public void testRandomCorrect2() {
        assertEquals(CitationParser.parseBibtexFile(testString1).get(16)
                .getFieldByName("author").value, 
                "Hassinen, Marko and M\\\"{a}yr\\\"{a}, Hannu");
    }
    
    @Test
    public void testRandomCorrect3() {
        assertEquals(CitationParser.parseBibtexFile(testString1).get(6)
                .getFieldByName("year").value,
                "1978");
    }
        
    @Test
    public void testRandomCorrect4() {
        assertEquals(CitationParser.parseBibtexFile(testString1).get(7)
                .getFieldByName("title").value,
                "{A}{A}{A} and {C}{S} 1: the applied apprenticeship approach to {C}{S} 1");
    }
}