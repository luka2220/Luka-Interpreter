package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.token.Token;

public class Identifier implements Expression {
    private Token token;
    private String value;

    public Identifier(Token token, String value) {
        this.token = token;
        this.value = value;
    }

    @Override
    public void expressionNode() {}

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    public String getValue() {
        return this.value;
    }

    public Token getToken() {
        return this.token;
    }
}
