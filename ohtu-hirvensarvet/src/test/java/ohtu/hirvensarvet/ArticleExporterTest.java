package ohtu.hirvensarvet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleExporterTest {
    
    String articleid;
    String authorname;
    String articletitle;
    Article testArticle;
    ArticleExporter exporter;
    String testOutput;
    String[] splitTestOutput;
    
    
    public ArticleExporterTest() {
    }
    
    @Before
    public void setUp() {
        articleid = "Landin:1966:NPL:365230.365257";
        authorname = "Landin, P. J.";
        articletitle = "The Next 700 Programming Languages";
        testArticle = new Article(articleid);
        testArticle.addField("author", authorname);
        testArticle.addField("title", articletitle);
        
        exporter = new ArticleExporter(testArticle);
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
}