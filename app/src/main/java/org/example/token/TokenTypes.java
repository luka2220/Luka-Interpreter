package org.example.token;

// NOTE:
// All possible token types
public enum TokenTypes {
    ILLEGAL("ILLEGAL"),
    EOF("EOF"),
    IDENT("IDENT"),
    INT("INT"),
    ASSIGN("="),
    PLUS("+"),
    COMMA(","),
    SEMICOLON(";"),
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    FUNCTION("FUNCTION"),
    LET("LET"),
    MINUS("-"),
    BANG("!"),
    ASTERISK("*"),
    SLASH("/"),
    LT("<"),
    GT(">"),
    IF("IF"),
    ELSE("ELSE"),
    RETURN("RETURN"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    EQ("=="),
    NOT_EQ("!=");

    private final String literal;

    TokenTypes(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }
}
