package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.token.TokenTypes;

// NOTE: A Node data structure for representing return statements
public class ReturnStatement implements Expression {
    private TokenTypes token;
    private Expression returnValue;

    public ReturnStatement() {}

    @Override
    public void expressionNode() {

    }

    @Override
    public String tokenLiteral() {
        return "";
    }
}
