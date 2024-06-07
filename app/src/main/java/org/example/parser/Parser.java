package org.example.parser;

import org.example.ast.Identifier;
import org.example.ast.LetStatement;
import org.example.ast.Program;
import org.example.ast.contracts.Statement;
import org.example.lexer.Lexer;
import org.example.token.Token;
import org.example.token.TokenTypes;

import java.util.ArrayList;

public class Parser {
    private Lexer l;
    private Token curToken;
    private Token peekToken;

    public Parser(Lexer l) {
        this.l = l;
        // Read two tokens, so curToken and peekToken are both set
        this.nextToken();
        this.nextToken();
    }

    public void nextToken() {
        this.curToken = peekToken;
        this.peekToken = l.NextToken();
    }

    public Program parseProgram() {
        Program program = new Program();
        program.setStatements(new ArrayList<Statement>());

        while (this.curToken.getType() != TokenTypes.EOF) {
            Statement stmt = parseStatement();
            if (stmt != null) {
                program.getStatements().add(stmt);
            }

            this.nextToken();
        }

        return program;
    }

    private Statement parseStatement() {
        switch (this.curToken.getType()) {
            case LET -> {
                return this.parseLetStatement();
            }
            default -> {
                return null;
            }
        }
    }

    private LetStatement parseLetStatement() {
        LetStatement stmt = new LetStatement(curToken);

        if (!this.expectPeek(TokenTypes.IDENT)) {
            return null;
        }

        stmt.setName(new Identifier(this.curToken, this.curToken.getLiteral()));

        if (!this.expectPeek(TokenTypes.ASSIGN)) {
            return null;
        }


        // TODO: We're skipping the expressions until we encounter a semicolon
        while (!this.curTokenIs(TokenTypes.SEMICOLON)) {
            this.nextToken();
        }

        return stmt;
    }

    private boolean curTokenIs(TokenTypes token) {
        return this.curToken.getType().equals(token);
    }

    private boolean peekTokenIs(TokenTypes token) {
        return this.peekToken.getType().equals(token);
    }

    private boolean expectPeek(TokenTypes token) {
        if (this.peekTokenIs(token)) {
            this.nextToken();
            return true;
        } else {
            return false;
        }
    }

    public Token getCurToken() {
        return this.curToken;
    }

    public Token getPeekToken() {
        return this.peekToken;
    }
}