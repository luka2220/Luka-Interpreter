package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.token.TokenTypes;

public class IntegerLiteral implements Expression {
    private TokenTypes token;
    private long value;

    public IntegerLiteral(TokenTypes token) {
        this.token = token;
    }

    public TokenTypes getToken() {
        return token;
    }

    public void setToken(TokenTypes token) {
        this.token = token;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public void expressionNode() {}

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    @Override
    public String string() {
        return this.token.getLiteral();
    }
}
