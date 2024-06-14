package org.example.ast;

import org.example.ast.contracts.Statement;

import java.util.List;

/**
 * The Program Node is going to be the root node of every AST the parser produces.
 * Every valid program is a series of statements which are stored in the instance variable
 * below.
 * */
public class Program {
    private List<Statement> statements;

    public Program() {}

    public Program(List<Statement> statements) {
        this.statements = statements;
    }

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

    /**
     * Creates a string of all the statements string() values.
     * */
    public String string() {
        StringBuilder out = new StringBuilder();
        for (Statement s : statements) {
            out.append(s.string());
        }
        return out.toString();
    }
}
