/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

import java.util.ArrayList;
/** Container object for all the necessary information for creating
 * a BibTex bibliography entry.
 *
 * @author Juha Kivekas
 */
public class Article{
	/** A unique identifier for the article for use in LaTex files and
	  * equality checking.
	  */
	private String article_id;

	/** The list of fields and values that will go to the BibTex file. 
	  */
	private ArrayList<BibliographyField> Fields;

	/** Constructs a new Article with no fields.
	  *
	  * @param article_id	A unique identifier for this article.
	  */
	Article(String article_id){
		this.Fields = new ArrayList<BibliographyField>();
		this.article_id = article_id;
	}

	String getName(){
		return this.article_id;
	}

	ArrayList<BibliographyField> getEntries(){
		return this.Fields;
	}

	/** Gets a field value by its name.
	  * @param field_name The name of the field of which we want the value
	  * @return pointer to the matching field, null if not found.
	*/
	public BibliographyField getFieldByName(String field_name){
		for(BibliographyField b : this.Fields){
			if(b.name.equals(field_name)){
				return b;
			}
		}
		return null;
	}


	/** Adds a field to the article.
	  *
	  * @param field_name The name of the field to be added.
	  * @param filed_value The value of the field to be added
	  * @return True if the filed name complies to BibTex standards.
	  */
	boolean addField(String field_name, String field_value){
		//TODO:check that field_name is compliant to the bibtex standard?
		//https://en.wikipedia.org/wiki/BibTeX#Entry_types
		this.Fields.add(new BibliographyField(field_name, field_value));
		return true;
	}

	@Override
	public String toString(){
		String S = "citation name: " + article_id + "\n";
		for(BibliographyField f : this.Fields){
			S += f.toString() + "\n";
		}
		return S;
	}

	/** Formats the Article into a BibTex formatted article entry.
	  *
	  * @return A very long string of formatted data.
	  */
	public String toBibtextEntry(){
		//format the Article data into bibtex format
		return "";
	}
}
