package org.example.ast;

import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.token.Token;

public class ExpressionStatement implements Statement {
    private Token token;
    private Expression expression;

    public ExpressionStatement(Token token) {
        this.token = token;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void statementNode() {}

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    @Override
    public String string() {
        if (this.expression != null) {
            return this.expression.string();
        }

        return "";
    }
}
