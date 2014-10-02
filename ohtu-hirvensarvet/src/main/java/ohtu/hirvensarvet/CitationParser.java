package ohtu.hirvensarvet;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.pattern.CharPredicates;

public class CitationParser {
    
    static final Parser<Void> skipWhite =
            Scanners.WHITESPACES.skipMany();
    
    static final Parser<Void> quotOrBracket =
            Scanners.among("\"{");
    
    static final Parser<Void> quotEndOrBracket =
            Scanners.among("\"}");
    
    static final Parser<String> fieldName =
            Scanners.isChar(CharPredicates.IS_ALPHA).many1().source();
    
    static final Parser<String> anyString =
            Scanners.isChar(CharPredicates.ALWAYS).many().source();
    
    static final Parser<String> fieldValue =
            Scanners.quoted(quotOrBracket, quotEndOrBracket, anyString);
   
    static final Parser<BibliographyField> field =
            Parsers.sequence(skipWhite, fieldName,
            skipWhite, Scanners.isChar('='), skipWhite, fieldValue,
            Scanners.isChar(','), skipWhite);
}