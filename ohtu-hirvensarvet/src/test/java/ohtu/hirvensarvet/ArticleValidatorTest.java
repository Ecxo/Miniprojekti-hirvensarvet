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
/*   
    @Test
    public void validateArticle_Valid() {
        testArticle = new Article("anyid");
        testArticle.setCitationType("article");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("publisher", "any publisher");
        testArticle.addField("year", "any year");
		assertEquals(0, ArticleValidator.validateArticle(testArticle));
	}
*/
/*
    @Test
    public void validateArticle_MissingMandatoryField() {
        testArticle = new Article("anyid");
        testArticle.setCitationType("article");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("publisher", "any publisher");
        //testArticle.addField("year", "any year");
		assertEquals(2, ArticleValidator.validateArticle(testArticle));
	}*/
/*
	@Test
	public void validateArticle_InvalidCitationType(){
        testArticle = new Article("anyid");
        //testArticle.setCitationType("article");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("publisher", "any publisher");
        testArticle.addField("year", "any year");
		assertEquals(1, ArticleValidator.validateArticle(testArticle));	
	}

	@Test
	public void validateArticle_InvalidFieldName(){
        testArticle = new Article("anyid");
        testArticle.setCitationType("article");
        testArticle.addField("author", "anybody");
        testArticle.addField("editor", "anybody");
        testArticle.addField("title", "any title");
        testArticle.addField("stick", "any publisher");
        testArticle.addField("year", "any year");
		assertEquals(3, ArticleValidator.validateArticle(testArticle));	
	}
*/
	@Test
	public void validateFieldNameValid(){
		BibliographyField field_1 = new BibliographyField("author", "anybody");
		BibliographyField field_2 = new BibliographyField("year", "any year");
		BibliographyField field_3 = new BibliographyField("title", "any title");
		assertEquals(true, ArticleValidator.isValidFieldName(field_1.name));
		assertEquals(true, ArticleValidator.isValidFieldName(field_2.name));
		assertEquals(true, ArticleValidator.isValidFieldName(field_3.name));
	}

	@Test
	public void validateFieldNameInvalid(){
		BibliographyField field_1 = new BibliographyField("rock", "any rock");
		BibliographyField field_2 = new BibliographyField("stick", "any stick");
		BibliographyField field_3 = new BibliographyField("stone", "any stone");
		assertEquals(false, ArticleValidator.isValidFieldName(field_1.name));
		assertEquals(false, ArticleValidator.isValidFieldName(field_2.name));
		assertEquals(false, ArticleValidator.isValidFieldName(field_3.name));
	}

	@Test 
	public void validateCitationTypeValid(){
		testArticle = new Article("any_id");
        testArticle.setCitationType("article");
		assertEquals(true, ArticleValidator.isValidCitationType(testArticle.getCitationType()));
	}

	@Test 
	public void validateCitationTypeInvalid(){
		testArticle = new Article("any_id");
        testArticle.setCitationType("trampoline");
		assertEquals(false, ArticleValidator.isValidCitationType(testArticle.getCitationType()));
	}
        
        @Test
        public void detectsIllegalCharacters() {
            BibliographyField bf1 = 
                    new BibliographyField("author", "K. J\\\"{a}rvela");
            BibliographyField bf2 =
                    new BibliographyField("title", "Åke ja Ääkköset");
            assertTrue(ArticleValidator.fieldValueContainsValidChars(bf1));
            assertTrue(!ArticleValidator.fieldValueContainsValidChars(bf2));
            
        }
}
