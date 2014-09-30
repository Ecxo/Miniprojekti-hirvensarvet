/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

import java.util.ArrayList;
/** A validator for bibliography entries.
  * @author Juha Kivekas
  */
public class ArticleValidator{
	//TODO: jonkunlainen tapa lukea nämä tiedot conffitiedostosta?
	public static String[] citation_types = {
		"article",
		"book"
	};
	//XXX:mandatory fileds are in same order as citation_type
	// mandatory fileds for <type> are:
	// mandatory_fields[citation_types.indexOf(<type>)];
	public static String[][] mandatory_fields = {
		{"author", "title", "journal", "year"},
		{"author", "editor", "title", "publisher", "year"} //only one of "author" and "editor" are required, needs fixing
	};

	public static String[] valid_fields = {
		"address",
		"annote",
		"author",
		"booktitle",
		"chapter",
		"crossref",
		"edition",
		"editor",
		"eprint",
		"howpublished",
		"institution",
		"journal",
		"key",
		"month",
		"note",
		"number",
		"organization",
		"pages",
		"publisher",
		"school",
		"series",
		"title",
		"type",
		"url",
		"volume",
		"year"
	};

	/** Constructs an article validator, given configurations.
	*/
	public ArticleValidator(/*configuration source*/){
		//read lots of strings from a configuration file
		//when we get this far lets make all functions non-static
	}
	
	/** Validates all field contents of this article.
	* @param A Article for which the contents of the fields will be validated.
	* @return An error code or message
	*/
	public static int validateArticleFields(Article A){
		//TODO: ihan vapaasti saa tehdä
		return 0;
	}
	
	/** Validates that the article contains all the mandatory fields
	* and that all optional field names are valid, does not validate 
	* field contents.
	* @param A The article to be validated
	* @return An error type or message, this will come later
	*/
	public static int validateArticle(Article A){
		//does the bibliography entry contain a citation type?
		BibliographyField f = A.getFieldByName("citation_type");
		if(f == null) return 1;

		//does the bibliography entry contain all mandatory fields for its type
		//XXX: this has to be refactored, arrays suck for this
		int i;
		String[] mandatory = mandatory_fields[0];//hardcoded 0 (book) for testing purposes
		for(i=0; i<mandatory.length; i++){
			if(A.getFieldByName(mandatory[i]) == null) return 2;
		}
		return 0;
	}
}
