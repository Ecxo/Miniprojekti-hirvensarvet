package ohtu.hirvensarvet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleValidatorTest {
    
    Article testArticle;
    
    public ArticleValidatorTest() {
    }
    
    @Before
    public void setUp() {
        testArticle = new Article("anyid");
        testArticle.addField("citation_type", "book");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("publisher", "any publisher");
        testArticle.addField("year", "any year");
    }
    
    @Test
    public void validateBook() {
		assertEquals(0, 0);
		//XXX:miksi javac/maven ei löydä symbolia validateArticle?
		//ArticleValidator.java:rivi78:
		//	public static int validateArticle(Article A);
        assertEquals(ArticleValidator.validateArticle(testArticle), 0);
    }
}
