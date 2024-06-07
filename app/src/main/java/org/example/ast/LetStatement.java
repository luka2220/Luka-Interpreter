package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.token.Token;

// NOTE: Node data structure for representing let statements
public class LetStatement implements Statement {
    private Token token;    // the token.LET token
    private Identifier name;
    private Expression value;

    public LetStatement(Token token) {
        this.token = token;
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
}
