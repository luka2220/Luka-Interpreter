package org.example.lexer;

import org.example.token.Token;
import org.example.token.TokenTypes;

public class Lexer {
    private String input;
    private int position;        // current position in the input (points to the current char)
    private int readPosition;    // current reading position in the input (points to next char)
    private byte ch;             // current char under examination

    public Lexer(String input) {
        this.input = input;
        this.readChar();
    }

    // NOTE: Helper function to read the next char in the string and advance the positions
    private void readChar() {
        if (this.readPosition >= input.length()) {
            this.ch = 0;
        } else {
            this.ch = (byte) this.input.charAt(this.readPosition);
        }

        this.position = this.readPosition;
        this.readPosition += 1;
    }

    // NOTE: Method that returns a token based on the current character under examination,
    // advances the position in the lexer by calling readChar()
    public Token NextToken() {
        Token tok;

        switch (this.ch) {
            case '=':
                tok = new Token(TokenTypes.ASSIGN, "=");
                break;
            case ';':
                tok = new Token(TokenTypes.SEMICOLON, ";");
                break;
            case '(':
                tok = new Token(TokenTypes.LPAREN, "(");
                break;
            case ')':
                tok = new Token(TokenTypes.RPAREN, ")");
                break;
            case '{':
                tok = new Token(TokenTypes.LBRACE, "{");
                break;
            case '}':
                tok = new Token(TokenTypes.RBRACE, "}");
                break;
            case ',':
                tok = new Token(TokenTypes.COMMA, ",");
                break;
            case '+':
                tok = new Token(TokenTypes.PLUS, "+");
                break;
            case 0:
                tok = new Token(TokenTypes.EOF, "");
                break;
            default:
                tok = new Token(TokenTypes.ILLEGAL, Character.toString(this.ch));
                break;
        }

        this.readChar();
        return tok;
    }
}
