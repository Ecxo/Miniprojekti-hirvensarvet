package ohtu.hirvensarvet;

import java.util.Collection;
import java.util.List;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map3;
import org.codehaus.jparsec.functors.Map5;
import org.codehaus.jparsec.pattern.CharPredicates;

public class CitationParser {
    
    private static Parser<String> parseWhiteSpaceSurrounded(Parser<String> parser) {

        return Parsers.sequence(skipWhite, parser, skipWhite,
            new Map3<Void, String, Void, String>() {
                @Override
                public String map(Void v1, String s, Void v2) {
                    return s;
                }
            });
    }
    
    /*copypaste only because java is fucking retarded and doesn't allow
     *generic return types 
     */
    private static Parser<Article> parseWhiteSpaceSurroundedArticle(Parser<Article> parser) {

        return Parsers.sequence(skipWhite, parser, skipWhite,
            new Map3<Void, Article, Void, Article>() {
                @Override
                public Article map(Void v1, Article a, Void v2) {
                    return a;
                }
            });
    }

    static final Parser<Void> skipWhite =
            Scanners.WHITESPACES.skipMany();
    
    static final Parser<Void> quotOrBracket =
            Scanners.among("\"{");
    
    static final Parser<Void> quotEndOrBracket =
            Scanners.among("\"}");
    
    static final Parser<String> fieldName =
            Scanners.isChar(CharPredicates.IS_ALPHA).many1().source();
    
    static final Parser<String> anyString =
            parseWhiteSpaceSurrounded(Scanners
            .isChar(CharPredicates.ALWAYS)
            .many().source());
    
    static final Parser<String> fieldValue =
            parseWhiteSpaceSurrounded(Scanners
            .quoted(quotOrBracket, quotEndOrBracket, anyString));
   
    static final Parser<BibliographyField> field =
            Parsers.sequence(fieldName, Scanners.isChar('='), fieldValue,
            new Map3<String, Void, String, BibliographyField>() {
                @Override
                public BibliographyField map(String name, Void v, String value) {
                    return new BibliographyField(name, value);
                }
            });
    
    static final Parser<List<BibliographyField>> fields =
            field.sepBy(Scanners.isChar(','));
    
    static final Parser<String> citationType =
            Parsers.sequence(skipWhite, Scanners.isChar('@'), fieldName);
    
    static final Parser<Void> endOfEntry =
            Parsers.sequence(skipWhite, Scanners.isChar('}'), skipWhite);
    
    static final Parser<String> articleId =
            parseWhiteSpaceSurrounded(Scanners
            .isChar(CharPredicates.not(CharPredicates.IS_WHITESPACE))
            .many1().source());
    
    static final Parser<Article> wholeArticle =
            Parsers.sequence(citationType, Scanners.isChar('{'), articleId,
            fields, endOfEntry, 
            new Map5<String, Void, String, List<BibliographyField>, Void, Article>() {
                
                @Override
                public Article map(String type,
                Void v1, String id,
                List<BibliographyField> bibs, Void v2) {
                    
                    Article article = new Article(id);
                    article.setAllFields(bibs);
                    article.setCitationType(type);
                    
                    return article;
                }
            });
    
    static final Parser<List<Article>> manyArticles =
            parseWhiteSpaceSurroundedArticle(wholeArticle).many();
    
    public static List<Article> parseBibtexFile(String bibtexFile) {
        return manyArticles.parse(bibtexFile);
    }
}