package ohtu.hirvensarvet;

import java.io.FileWriter;
import java.util.Iterator;

public class ArticleFormatter {
    
    private Article article;
    
    public ArticleFormatter(Article article) {
        this.article = article;
    }
    
    /**
     * Converts an article object to a Bibtex citation string.
     * @return String which should be valid Bibtex syntax
     */
    public String exportArticle() {
        
        String bibtex = "@" + article.getCitationType() + "{" + article.getName() + ",\n";
        bibtex += formatFields() + "\n}";
        
        return bibtex;
    }
    
    private String formatFields() {
        
        String bibtexfields = "";
        
        int maxfieldnamelen = BibliographyField
                .getMaxNameLength(article.getEntries());
        int thisManySpaces = maxfieldnamelen + 1;

        Iterator<BibliographyField> fields = article.getEntries().iterator();
        
        while(fields.hasNext()) {
            
            BibliographyField field = fields.next();
            bibtexfields += singleFieldLine(field, thisManySpaces);
            
            if(fields.hasNext()) {
                bibtexfields += ",\n";
            }
        }
        
        return bibtexfields;
    }
    
    private String singleFieldLine(BibliographyField field, int padding) {
        
        String result = "    ";
        result += field.name.toLowerCase();
        
        for(int i = 0; i < padding - field.name.length(); i++) {
            result += " ";
        }
        
        result += "= " + "\"" + escapeNonAngloChars(field.value) + "\"";
        
        return result;
    }
    
    /**
     * Writes a Bibtex citation string corresponding to an article to a file
     * @param writer writer object used for writing to a file
     */
    public void writeToFile(FileWriter writer) {
        
        try {
            writer.write(exportArticle());
        } catch(Exception e) {
            System.out.println("Saving to disk failed");
        }
    }
    
    /**
     * Escapes non-anglo characters (å, ä, ö) according to Bibtex
     * standard, replacing them with (\aa, \"{a}, \"{o}) correspondingly.
     * @param input string to escape
     * @return string which has the non-anglo characters replaced
     * with escape sequences
     */
    
    public String escapeNonAngloChars(String input) {
        
        String result = "";
        
        for(int i = 0; i < input.length(); i++) {
            
            char curchar = input.charAt(i);
            
            if(curchar == 'ä') {
                result += "\\\"{a}";
            } else if(curchar == 'ö') {
                result += "\\\"{o}";
            } else if(curchar == 'å') {
                result += "\\aa";
            } else if(curchar == 'Ä') {
                result += "\\\"{A}";
            } else if(curchar == 'Ö') {
                result += "\\\"{O}";
            } else if(curchar == 'Å') {
                result += "\\AA";
            } else {
                result += curchar;
            }
        }
        
        return result;
    }
}
