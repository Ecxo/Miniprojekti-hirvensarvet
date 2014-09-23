package ohtu.hirvensarvet;

import java.util.Iterator;

public class ArticleExporter {
    
    private Article article;
    
    public ArticleExporter(Article article) {
        this.article = article;
    }
    
    public String exportArticle() {
        
        String bibtex = "@article{" + article.getName() + ",\n";
        bibtex += formatFields() + "\n}";
        
        return bibtex;
    }
    
    //kyllä = padding 6
    //jaa   =
    
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
        
        result += "= " + "\"" + field.value + "\"";
        
        return result;
    }
}
