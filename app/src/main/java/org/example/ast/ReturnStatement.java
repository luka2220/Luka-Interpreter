package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.token.Token;

/**
 * A Node data structure for representing return statements
 * */
public class ReturnStatement implements Statement {
    private Token token;
    private Expression returnValue;

    public ReturnStatement(Token token) {
        this.token = token;
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    @Override
    public String string() {
        StringBuilder out = new StringBuilder();

        out.append(this.tokenLiteral()).append(" ");

        if (this.returnValue != null) {
            out.append(this.returnValue.string());
        }

        out.append(";");

        return out.toString();
    }

    @Override
    public void statementNode() {}
}
