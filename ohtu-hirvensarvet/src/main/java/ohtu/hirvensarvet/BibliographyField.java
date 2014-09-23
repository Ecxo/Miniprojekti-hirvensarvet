/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

import java.util.ArrayList;
/** Field object for Article objects.
 *
 * @author Juha Kivekas
 */
public class BibliographyField {
	/** Name of the BibTex field */
	public String name;
	/** Value of the BibTex field */
	public String value;

	BibliographyField(String name, String value){
		this.name  = name;
		this.value = value;
	}
	
	@Override
	public String toString(){
		return (this.name + ": " + this.value);
	}
}
