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
 * @author Juha Kivekäs
 */
public class ArticleTest {

    public ArticleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addFieldTest() {
        Article A = new Article("name");
        A.addField("author", "Juha Kivekäs");
        A.addField("title", "Esoteric languages in electronic music production");

        assertEquals(A.toString(),
                "citation name: name\n"
                + "author: Juha Kivekäs\n"
                + "title: Esoteric languages in electronic music production\n");
    }

}
