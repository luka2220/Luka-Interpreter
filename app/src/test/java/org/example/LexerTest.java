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
                fail(String.format("test[%d] - tokentype wrong. expected=%s, got=%s", i, tests[i].getType(), tok.getType()));
            }

            // NOTE: Fail the test if the string literal from the test lexer(l) does not
            // equal the expected string literal value from the test array
            if (!tok.getLiteral().equals(tests[i].getLiteral())) {
                fail(String.format("test[%d] - token literal wrong. expected=%s, got=%s", i, tests[i].getLiteral(), tok.getLiteral()));
            }
        }
    }

    @Test
    void testNextToken2() {
        Token [] tests = new Token[]{
                new Token(TokenTypes.LET, "let"),
                new Token(TokenTypes.IDENT, "five"),
                new Token(TokenTypes.ASSIGN, "="),
                new Token(TokenTypes.INT, "5"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.LET, "let"),
                new Token(TokenTypes.IDENT, "ten"),
                new Token(TokenTypes.ASSIGN, "="),
                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.LET, "let"),
                new Token(TokenTypes.IDENT, "add"),
                new Token(TokenTypes.ASSIGN, "="),
                new Token(TokenTypes.FUNCTION, "fn"),
                new Token(TokenTypes.LPAREN, "("),
                new Token(TokenTypes.IDENT, "x"),
                new Token(TokenTypes.COMMA, ","),
                new Token(TokenTypes.IDENT, "y"),
                new Token(TokenTypes.RPAREN, ")"),
                new Token(TokenTypes.LBRACE, "{"),
                new Token(TokenTypes.IDENT, "x"),
                new Token(TokenTypes.PLUS, "+"),
                new Token(TokenTypes.IDENT, "y"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.RBRACE, "}"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.LET, "let"),
                new Token(TokenTypes.IDENT, "result"),
                new Token(TokenTypes.ASSIGN, "="),
                new Token(TokenTypes.IDENT, "add"),
                new Token(TokenTypes.LPAREN, "("),
                new Token(TokenTypes.IDENT, "five"),
                new Token(TokenTypes.COMMA, ","),
                new Token(TokenTypes.IDENT, "ten"),
                new Token(TokenTypes.RPAREN, ")"),
                new Token(TokenTypes.SEMICOLON, ";"),

                new Token(TokenTypes.BANG, "!"),
                new Token(TokenTypes.MINUS, "-"),
                new Token(TokenTypes.SLASH, "/"),
                new Token(TokenTypes.ASTERISK, "*"),
                new Token(TokenTypes.INT, "5"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.INT, "5"),
                new Token(TokenTypes.LT, "<"),
                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.GT, ">"),
                new Token(TokenTypes.INT, "5"),
                new Token(TokenTypes.SEMICOLON, ";"),

                new Token(TokenTypes.EOF, ""),
        };

        l = new Lexer("let five = 5;\n" +
                "let ten = 10;\n" +
                "     \n" +
                "let add = fn(x, y) {\n" +
                "   x + y;\n" +
                "};\n" +
                "let result = add(five, ten);" +
                "!-/*5;\n" +
                "5 < 10 > 5;");

        for (int i = 0; i < tests.length; i++) {
            Token tok = l.NextToken();

            // NOTE: Fail the test if the TokenType from the test lexer(l) does
            // not equal the expected TokenType value from the tests array
            if (tok.getType() != tests[i].getType()) {
                fail(String.format("test[%d] - tokentype wrong. expected=%s, got=%s", i, tests[i].getType(), tok.getType()));
            }

            // NOTE: Fail the test if the string literal from the test lexer(l) does not
            // equal the expected string literal value from the test array
            if (!tok.getLiteral().equals(tests[i].getLiteral())) {
                fail(String.format("test[%d] - token literal wrong. expected=%s, got=%s", i, tests[i].getLiteral(), tok.getLiteral()));
            }
        }
    }

    @Test
    void testNextToken3() {
        Token [] tests = new Token[]{
                new Token(TokenTypes.IF, "if"),
                new Token(TokenTypes.LPAREN, "("),
                new Token(TokenTypes.INT, "5"),
                new Token(TokenTypes.LT, "<"),
                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.RPAREN, ")"),
                new Token(TokenTypes.LBRACE, "{"),
                new Token(TokenTypes.RETURN, "return"),
                new Token(TokenTypes.TRUE, "true"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.RBRACE, "}"),
                new Token(TokenTypes.ELSE, "else"),
                new Token(TokenTypes.LBRACE, "{"),
                new Token(TokenTypes.RETURN, "return"),
                new Token(TokenTypes.FALSE, "false"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.RBRACE, "}"),

                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.EQ, "=="),
                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.SEMICOLON, ";"),
                new Token(TokenTypes.INT, "10"),
                new Token(TokenTypes.NOT_EQ, "!="),
                new Token(TokenTypes.INT, "9"),
                new Token(TokenTypes.SEMICOLON, ";"),

                new Token(TokenTypes.EOF, ""),
        };


        l = new Lexer("if (5 < 10) {\n" +
                "       return true;\n" +
                "   } else {\n" +
                "       return false;\n" +
                "   }\n" +
                "   10 == 10;\n" +
                "   10 != 9;");

        for (int i = 0; i < tests.length; i++) {
            Token tok = l.NextToken();

            // NOTE: Fail the test if the TokenType from the test lexer(l) does
            // not equal the expected TokenType value from the tests array
            if (tok.getType() != tests[i].getType()) {
                fail(String.format("test[%d] - tokentype wrong. expected=%s, got=%s", i, tests[i].getType(), tok.getType()));
            }

            // NOTE: Fail the test if the string literal from the test lexer(l) does not
            // equal the expected string literal value from the test array
            if (!tok.getLiteral().equals(tests[i].getLiteral())) {
                fail(String.format("test[%d] - token literal wrong. expected=%s, got=%s", i, tests[i].getLiteral(), tok.getLiteral()));
            }
        }
    }
}
