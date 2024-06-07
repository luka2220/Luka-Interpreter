package org.example.parser;

import org.example.ast.Identifier;
import org.example.ast.LetStatement;
import org.example.ast.Program;
import org.example.ast.ReturnStatement;
import org.example.ast.contracts.Statement;
import org.example.lexer.Lexer;
import org.example.token.Token;
import org.example.token.TokenTypes;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer l;
    private Token curToken;
    private Token peekToken;
    private List<String> errors;

    public Parser(Lexer l) {
        this.l = l;
        this.errors = new ArrayList<>();

        // Read two tokens, so curToken and peekToken are both set
        this.nextToken();
        this.nextToken();
    }

    public void nextToken() {
        this.curToken = peekToken;
        this.peekToken = l.NextToken();
    }

    public List<String> Errors() {
        return this.errors;
    }

    public Program parseProgram() {
        Program program = new Program();
        program.setStatements(new ArrayList<>());

        while (!this.curTokenIs(TokenTypes.EOF)) {
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
            case RETURN -> {
                return this.parseReturnStatement();
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

    private ReturnStatement parseReturnStatement() {
        ReturnStatement stmt = new ReturnStatement(curToken);

        this.nextToken();

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

    // NOTE: Helper function to add error messages to list errors
    private void peekError(TokenTypes token) {
        String msg = "expected next token to be " + token.getLiteral() +
                ", got " + this.peekToken.getLiteral();

        this.errors.add(msg);
    }

    // NOTE: enforce the correctness of the order of tokens by checking the type of the next token
    private boolean expectPeek(TokenTypes token) {
        if (this.peekTokenIs(token)) {
            this.nextToken();
            return true;
        } else {
            this.peekError(token);
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