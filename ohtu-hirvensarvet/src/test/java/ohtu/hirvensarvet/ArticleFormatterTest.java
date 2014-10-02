package ohtu.hirvensarvet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleFormatterTest {
    
    String articleid;
    String authorname;
    String articletitle;
    Article testArticle;
    ArticleFormatter exporter;
    String testOutput;
    String[] splitTestOutput;
    
    
    public ArticleFormatterTest() {
    }
    
    @Before
    public void setUp() {
        articleid = "Landin:1966:NPL:365230.365257";
        authorname = "Landin, P. J.";
        articletitle = "The Next 700 Programming Languages";
        testArticle = new Article(articleid);
        testArticle.addField("author", authorname);
        testArticle.addField("title", articletitle);
        testArticle.setCitationType("article");
               
        
        exporter = new ArticleFormatter(testArticle);
        testOutput = exporter.exportArticle();
        splitTestOutput = splitByLine(testOutput);
    }
    
    private String[] splitByLine(String multiLineInput) {
        
        return multiLineInput.split("\n");
    }
    
    @Test
    public void correctNumberOfLines() {
        assertEquals(splitTestOutput.length, 4);
    }
    
    @Test
    public void firstLineIsCorrect() {
        
        assertEquals(splitTestOutput[0], "@article{" + articleid + ",");
    }
    
    @Test
    public void fieldsAreIndented() {
        assertTrue(splitTestOutput[1].matches("    .*"));
        assertTrue(splitTestOutput[2].matches("    .*"));
    }
    
    @Test
    public void secondLineIsCorrect() {
        assertEquals(splitTestOutput[1],
                "    author = \"" + authorname + "\",");
    }
    
    @Test
    public void thirdLineIsCorrect() {
        assertEquals(splitTestOutput[2],
                "    title  = \"" + articletitle + "\"");
    }
    
    @Test
    public void initialBracketIsClosed() {
        assertTrue(splitTestOutput[3].contains("}"));
    }
    
    @Test
    public void nonAngloEscapingNeutral() {
        String test = "Autokoulun leima ja allekirjoitus";
        assertEquals(exporter.escapeNonAngloChars(test), test);
    }
    
    @Test
    public void nonAngloEscapingWorks1() {
        String test1 = "Detta intyg måste förevisas till Polisen";
        String result1= "Detta intyg m\\aaste f\\\"{o}revisas till Polisen";
        
        assertEquals(exporter.escapeNonAngloChars(test1), result1);
    }
    
    @Test
    public void nonAngloEscapingWorks2() {
        String test2 = "Åke veti Överit";
        String result2 = "\\AAke veti \\\"{O}verit";
        
        assertEquals(exporter.escapeNonAngloChars(test2), result2);
    }

    @Test
    public void nonAngloEscapingWorks3() {
        String test3 = "käärme kååmassa";
        String result3 = "k\\\"{a}\\\"{a}rme k\\aa\\aamassa";
        
        System.out.println(exporter.escapeNonAngloChars(test3));
        
        assertEquals(exporter.escapeNonAngloChars(test3), result3);
    }
    
    @Test
    public void nonAngloEscapingWorks4() {
        String test4 = "ÄÄÖÅäöåÄÅå";
        String result4 = 
                "\\\"{A}\\\"{A}\\\"{O}\\AA\\\"{a}\\\"{o}\\aa\\\"{A}\\AA\\aa";
        
        assertEquals(exporter.escapeNonAngloChars(test4), result4);
    }
}