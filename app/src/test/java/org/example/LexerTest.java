package org.example;

import org.example.lexer.Lexer;
import org.example.token.Token;
import org.example.token.TokenTypes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LexerTest {
    private Lexer l;

    @Test
    void testNextToken1() {
        Token []tests = new Token[] {
                new Token(TokenTypes.ASSIGN, "="),
                new Token(TokenTypes.PLUS, "+"),
                new Token(TokenTypes.LPAREN, "("),
                new Token(TokenTypes.RPAREN, ")"),
                new Token(TokenTypes.LBRACE, "{"),
                new Token(TokenTypes.RBRACE, "}"),
                new Token(TokenTypes.COMMA, ","),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.EOF, ""),
        };

        l = new Lexer("=+(){},;");

        for (int i = 0; i < tests.length; i++) {
            Token tok = l.NextToken();

            // NOTE: Fail the test if the TokenType from the test lexer(l) does
            // not equal the expected TokenType value from the tests array
            if (tok.getType() != tests[i].getType()) {
                fail(String.format("test[%d] - tokentype wrong. expected=%q, got=%q", i, tests[i].getType(), tok.getType()));
            }

            // NOTE: Fail the test if the string literal from the test lexer(l) does not
            // equal the expected string literal value from the test array
            if (!tok.getLiteral().equals(tests[i].getLiteral())) {
                fail(String.format("test[%d] - token literal wrong. expected=%s, got=%s", i, tests[i].getLiteral(), tok.getLiteral()));
            }
        }
    }
}
