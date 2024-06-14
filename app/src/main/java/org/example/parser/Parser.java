package org.example.parser;

import org.example.ast.*;
import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.ast.types.Precedence;
import org.example.lexer.Lexer;
import org.example.token.Token;
import org.example.token.TokenTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
interface PrefixParseFn {
    Expression parse();
}

@FunctionalInterface
interface InfixParseFn {
    Expression parse(Expression expression);
}

public class Parser {
    private Lexer l;
    private Token curToken;
    private Token peekToken;
    private List<String> errors;

    private Map<TokenTypes, PrefixParseFn> prefixParseFns = new HashMap<>();
    private Map<TokenTypes, InfixParseFn> infixParseFns = new HashMap<>();

    public void registerPrefix(TokenTypes tokenType, PrefixParseFn fn) {
        prefixParseFns.put(tokenType, fn);
    }

    public void registerInfix(TokenTypes tokenType, InfixParseFn fn) {
        infixParseFns.put(tokenType, fn);
    }

    public Parser(Lexer l) {
        this.l = l;
        this.errors = new ArrayList<>();

        // Register prefix parse functions
        registerPrefix(TokenTypes.IDENT, this::parseIdentifier);

        // Read two tokens, so curToken and peekToken are both set
        this.nextToken();
        this.nextToken();
    }

    private Expression parseIdentifier() {
        return new Identifier(this.curToken, this.curToken.getLiteral());
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
                return this.parseExpressionStatement();
            }
        }
    }

    /**
     * Builds an AST Node and fills it by calling the parsing function
     *
     * */
    private Statement parseExpressionStatement() {
        ExpressionStatement stmt = new ExpressionStatement(this.curToken);
        stmt.setExpression(this.parseExpression(Precedence.LOWEST.getPrecedence()));

        if (this.peekTokenIs(TokenTypes.SEMICOLON)) {
            this.nextToken();
        }

        return stmt;
    }

    /**
     * Check whether there's a parsing function associated with the current token
     * */
    private Expression parseExpression(int precedence) {
        PrefixParseFn prefix = prefixParseFns.get(curToken.getType());
        if (prefix == null) {
            return null;
        }

        Expression leftExp = prefix.parse();
        return leftExp;
    }

    private LetStatement parseLetStatement() {
        LetStatement stmt = new LetStatement(this.curToken);

        if (!this.expectPeek(TokenTypes.IDENT)) {
            return null;
        }

        stmt.setName(new Identifier(this.curToken, this.curToken.getLiteral()));

        if (!this.expectPeek(TokenTypes.ASSIGN)) {
            return null;
        }

        while (!this.curTokenIs(TokenTypes.SEMICOLON)) {
            this.nextToken();
        }

        return stmt;
    }

    private ReturnStatement parseReturnStatement() {
        ReturnStatement stmt = new ReturnStatement(this.curToken);

        this.nextToken();

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

    /**
     * Helper function to add error messages to list errors
     * */
    private void peekError(TokenTypes token) {
        String msg = "expected next token to be " + token.getLiteral() +
                ", got " + this.peekToken.getLiteral();

        this.errors.add(msg);
    }

    /**
     * Enforces the correctness of the order of tokens by checking the type of the next token
     * */
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