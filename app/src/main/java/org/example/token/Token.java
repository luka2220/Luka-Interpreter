package org.example.token;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private TokenTypes type;
    private String literal;
    private final Map<String, TokenTypes> keywords = new HashMap<>();
    {
        keywords.put("fn", TokenTypes.FUNCTION);
        keywords.put("let", TokenTypes.LET);
    };

    public Token() {}

    public Token(TokenTypes type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    public TokenTypes getType() {
        return this.type;
    }

    public String getLiteral() {
        return this.literal;
    }

    public void setType(TokenTypes type) {
        this.type = type;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    // NOTE: Checks whether an identifier is a reserved keyword
    public TokenTypes LookupIdent(String ident) {
        if (keywords.containsKey(ident)) {
            return keywords.get(ident);
        }

        return TokenTypes.IDENT;
    }
}
