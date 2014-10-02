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
    }
   
    @Test
    public void validateArticleValid() {
        testArticle = new Article("anyid");
        testArticle.setCitationType("article");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("publisher", "any publisher");
        testArticle.addField("year", "any year");
		assertEquals(0, ArticleValidator.validateArticle(testArticle));
	}

/*	public void validateArticleNoCitationType(){

	}*/
}
