package ohtu.hirvensarvet;

import java.util.Collection;
import java.util.List;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map2;
import org.codehaus.jparsec.functors.Map3;
import org.codehaus.jparsec.functors.Map5;
import org.codehaus.jparsec.pattern.CharPredicates;
import org.codehaus.jparsec.pattern.Patterns;

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

    private static final Parser<Void> skipWhite =
            Parsers.or(Scanners.WHITESPACES, Scanners.isChar('\n')).skipMany();
    
    private static final Parser<Void> quotOrBracket =
            Scanners.among("\"{");
    
    private static final Parser<Void> quotEndOrBracket =
            Scanners.among("\"}");
    
    private static final Parser<String> fieldName =
            parseWhiteSpaceSurrounded(
            Scanners.isChar(CharPredicates.IS_ALPHA).many1().source().map(
            new Map<String, String>() {
                @Override
                public String map(String s) {
                    return s.toLowerCase();
                }
            }));
    
    
    //ks. nestableBlockComment
    private static final Parser<String> anyString = 
            Parsers.or(Scanners.isChar(CharPredicates.notAmong("\"}")),
            Parsers.sequence(Scanners.isChar('\\'), Scanners.ANY_CHAR)).many().source();
    
    private static final Parser<String> fieldValue =
            parseWhiteSpaceSurrounded(Parsers.or(Parsers
            .between(quotOrBracket, anyString, quotEndOrBracket),
            Scanners.INTEGER.source()));
    
    private static final Parser<Void> optionalTrailingComma =
            Scanners.isChar(',').optional();
   
    private static final Parser<BibliographyField> field =
            Parsers.sequence(fieldName,
            Scanners.isChar('='), fieldValue,
            new Map3<String, Void, String, BibliographyField>() {
                @Override
                public BibliographyField map(String name, Void v, String value) {
                    return new BibliographyField(name, value);
                }
            }).followedBy(skipWhite);
    
    private static final Parser<List<BibliographyField>> fields =
            Parsers.sequence(field.atomic().sepEndBy(Scanners.isChar(','))
            .followedBy(skipWhite), Scanners.among("})"),
            new Map2<List<BibliographyField>, Void, List<BibliographyField>>() {
                @Override
                public List<BibliographyField> 
                        map(List<BibliographyField> ls, Void v) {
                    return ls;
                }
            });
    
    private static final Parser<String> citationType =
            Parsers.sequence(skipWhite, Scanners.isChar('@'), fieldName);
    
    private static final Parser<Void> endOfEntry = skipWhite;
            //Parsers.sequence(skipWhite, Scanners.isChar('}'), skipWhite);
    
    private static final Parser<String> citationId =
            parseWhiteSpaceSurrounded(Scanners
            .isChar(CharPredicates.not(CharPredicates.IS_WHITESPACE))
            .many1().source());
    
    private static final Parser<Article> wholeArticle =
            Parsers.sequence(citationType, Scanners.among("{("),
            citationId,
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
    
    private static final Parser<List<Article>> manyArticles =
            wholeArticle.many().followedBy(Parsers.EOF);
    
    public static List<Article> parseBibtexFile(String bibtexFile) {
        return manyArticles.parse(bibtexFile);
    }

    /*
    private static Parser<String> parseValue(Parser<String> quotedCell) {

        Parser.Reference<String> ref = Parser.newReference();
        Parser<String> textinside = ref
                .lazy().between(quotOrBracket, quotOrEndBracket).or(quotedCell);
        Parser<String> parseCell = new ;
    }
    */
}