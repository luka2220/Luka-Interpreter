package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.token.Token;

/**
 * Node data structure for representing let statements
 * */
public class LetStatement implements Statement {
    private Token token;    // the token.LET token
    private Identifier name;
    private Expression value;

    public LetStatement(Token token) {
        this.token = token;
    }

    public LetStatement(Token token, Identifier name, Expression value) {
        this.token = token;
        this.name = name;
        this.value = value;
    }

    public Identifier getName() {
        return this.name;
    }

    public void setName(Identifier name) {
        this.name = name;
    }

    @Override
    public void statementNode() {}

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    /**
     * Builds an expression assigned a let statement
     * */
    @Override
    public String string() {
        StringBuilder out = new StringBuilder();

        out.append(this.tokenLiteral()).append(" ");
        out.append(this.name.string());
        out.append(" = ");

        if (this.value != null) {
            out.append(this.value.string());
        }

        out.append(";");

        return out.toString();
    }
}
