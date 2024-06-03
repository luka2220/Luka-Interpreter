package org.example.token;

public class Token {
    private TokenTypes type;
    private String literal;

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
}
