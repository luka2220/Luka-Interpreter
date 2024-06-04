package org.example.lexer;

import org.example.token.Token;
import org.example.token.TokenTypes;

public class Lexer {
    private final String input;
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

        skipWhiteSpace();

        switch (this.ch) {
            case '=':
                if (peekChar() == '=') {
                    byte lch = this.ch;
                    readChar();
                    String literal = "" + (char) lch + (char) this.ch;
                    tok = new Token(TokenTypes.EQ, literal);
                } else {
                    tok = new Token(TokenTypes.ASSIGN, "=");
                }
                break;
            case '!':
                if (peekChar() == '=') {
                    byte lch = this.ch;
                    readChar();
                    String literal = "" + (char) lch + (char) this.ch;
                    tok = new Token(TokenTypes.NOT_EQ, literal);
                } else {
                    tok = new Token(TokenTypes.BANG, "!");
                }
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
            case '-':
                tok = new Token(TokenTypes.MINUS, "-");
                break;
            case '*':
                tok = new Token(TokenTypes.ASTERISK, "*");
                break;
            case '/':
                tok = new Token(TokenTypes.SLASH, "/");
                break;
            case '<':
                tok = new Token(TokenTypes.LT, "<");
                break;
            case '>':
                tok = new Token(TokenTypes.GT, ">");
                break;
            case 0:
                tok = new Token(TokenTypes.EOF, "");
                break;
            default:
                if (isLetter(this.ch)) {
                    tok = new Token();
                    tok.setLiteral(readIdentifier());
                    tok.setType(tok.LookupIdent(tok.getLiteral()));
                    return tok;
                } else if (isDigit(this.ch)) {
                    tok = new Token();
                    tok.setLiteral(readNumber());
                    tok.setType(TokenTypes.INT);
                    return tok;
                } else {
                    tok = new Token(TokenTypes.ILLEGAL, Character.toString(this.ch));
                }
        };

        this.readChar();
        return tok;
    }

    // NOTE: Removes all the whitespace from the code
    private void skipWhiteSpace() {
        while (this.ch == ' ' || this.ch == '\t' || this.ch == '\n' || this.ch == '\r') {
            readChar();
        }
    }

    // NOTE: Looks at the char in the next position but does not increase the current position
    private byte peekChar() {
        if (this.readPosition >= this.input.length()) {
            return 0;
        } else {
            return (byte) this.input.charAt(this.readPosition);
        }
    }

    // NOTE: Reads an identifier token until it encounters a non-letter token
    private String readIdentifier() {
        int pos = this.position;

        while (isLetter(this.ch)) {
            this.readChar();
        }

        return this.input.substring(pos, this.position);
    }

    // NOTE: Checks if the current token is a letter
    private boolean isLetter(byte ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
    }

    // NOTE: Reads a number token until it encounters a non-number token
    private String readNumber() {
        int pos = this.position;

        while (isDigit(this.ch)) {
            this.readChar();
        }

        return this.input.substring(pos, this.position);
    }

    // NOTE: Checks if the current token is a number
    private boolean isDigit(byte ch) {
        return '0' <= ch && ch <= '9';
    }
}
