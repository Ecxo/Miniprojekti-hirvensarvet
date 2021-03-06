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
		"book",
		"inproceedings"
	};
	//XXX:mandatory fileds are in same order as citation_type
	// mandatory fileds for <type> are:
	// mandatory_fields[citation_types.indexOf(<type>)];
	public static String[][] mandatory_fields = {
		{"author", "title", "journal", "year"},				//article
		{"author", "editor", "title", "publisher", "year"}, //book
		{"author", "title", "booktitle", "year"}			//inproceedings
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
	
	/** Validates all field contents of this article.
	* @param A Article for which the contents of the fields will be validated.
	* @return An error code or message
	*/
	public static int validateArticleFields(Article A){
		//TODO: checkataan onko vuosiluku järkeenkäyvä, Nimet alkavat isolla kirjaimella, jne...
		//kuuluuko kakkos sprinttiin?
		return 0;
	}

	/** Checks whether a field is a valid BibTex field or not.
	* @param field_name the name of the field to be validated
	* @return true if field name is valid, false otherwise
	*/
	public static boolean isValidFieldName(String field_name){
		for(String S : valid_fields){
			if(field_name.equals(S)){
				return true;
			}
		}
		return false;
	}

	/** Checks whether the citation_type is a valid BibTex type or not.
	* @param field_name the citation_type to be validated
	* @return true if type name is valid, false otherwise
	*/
	public static boolean isValidCitationType(String field_name){
		if(field_name == null) return false;
		for(String S : citation_types){
			if(field_name.equals(S)){
				return true;
			}
		}
		return false;
	}
	
	
	/* Validates that the article contains all the mandatory fields
	* and that all optional field names are valid, does not validate 
	* field contents.
	* @param A The article to be validated
	* @return An error type or message, 0:valid, 1:citation type missing, 2:mandatory field(s) missing, 3:has an invalid field
	*/
	/*
	public static int validateArticle(Article A){
		//does the Article contain a citation type?
		if(!isValidCitationType(A.getCitationType())){
			return 1;
		}
		
		//does the Article contain any invalid bibtex fields?
		for(BibliographyField B : A.getFields()){
			if(!isValidFieldName(B.name)){
				return 3;
			}
                        
                        if(!fieldValueContainsValidChars(B)) {
                            return 4;
                        }
		}
		return 0;
	}
*/  
        public static boolean 
                fieldValueContainsValidChars(BibliographyField bf) {
            
            return !bf.value.matches(".*[öäåÖÄÅ].*");
        }
        
}
