package ohtu.hirvensarvet;

import java.io.FileWriter;
import java.util.Iterator;

public class ArticleFormatter {
    
    private Article article;
    

    public ArticleFormatter(Article article) {
        this.article = article;
    }
    

    /**
     * Static version of the exportArticle()-method.
     * @param article article to format
     * @return String which should be valid Bibtex syntax
     */
    public static String exportArticle(Article article) {
        
        String bibtex = "@" + article.getCitationType() + "{" + article.getName() + ",\n";
        bibtex += formatFields(article) + "\n}";
        
        return bibtex;
    }
    
    /**
     * Converts an article object to a Bibtex citation string.
     * Non-static verion of the exportArticle(Article article)-method.
     * @return String which should be valid Bibtex syntax
     */
    public String exportArticle() {
        return exportArticle(article);
    }
    
    private static String formatFields(Article article) {
        
        String bibtexfields = "";
        
        int maxfieldnamelen = BibliographyField
                .getMaxNameLength(article.getFields());
        int thisManySpaces = maxfieldnamelen + 1;

        Iterator<BibliographyField> fields = article.getFields().iterator();
        
        while(fields.hasNext()) {
            
            BibliographyField field = fields.next();
            bibtexfields += singleFieldLine(field, thisManySpaces, article);
            
            if(fields.hasNext()) {
                bibtexfields += ",\n";
            }
        }
        
        return bibtexfields;
    }
    
    private static String singleFieldLine(BibliographyField field,
            int padding, Article article) {
        
        String result = "    ";
        result += field.name.toLowerCase();
        
        for(int i = 0; i < padding - field.name.length(); i++) {
            result += " ";
        }
        
        result += "= " + "\"" + escapeNonAngloChars(field.value) + "\"";
        
        return result;
    }
    
    /**
     * Escapes non-anglo characters (å, ä, ö) according to Bibtex
     * standard, replacing them with (\aa, \"{a}, \"{o}) correspondingly.
     * @param input string to escape
     * @return string which has the non-anglo characters replaced
     * with escape sequences
     */
    
    public static String escapeNonAngloChars(String input) {
        
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
