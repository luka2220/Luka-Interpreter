package org.example.ast;

import org.example.ast.contracts.Statement;

import java.util.List;

// NOTE: The Program Node is going to be the root node of every AST the parser produces.
// Every valid program is a series of statements which are stored in the instance variable
// below.
public class Program {
    private List<Statement> statements;

    public Program() {}

    public String tokenLiteral() {
        if (!this.statements.isEmpty()) {
            return this.statements.get(0).tokenLiteral();
        } else {
            return "";
        }
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
