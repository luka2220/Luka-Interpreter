package org.example;

import org.example.ast.Identifier;
import org.example.ast.LetStatement;
import org.example.ast.Program;
import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.token.Token;
import org.example.token.TokenTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AstTest {
    @Test
    void testString() {
        List<Statement> statements = new ArrayList<>();
        statements.add(new LetStatement(
                new Token(TokenTypes.LET, "let"),
                new Identifier(new Token(TokenTypes.IDENT, "myVar"), "myVar"),
                new Identifier(new Token(TokenTypes.IDENT, "anotherVar"), "anotherVar"))
        );

        Program p = new Program(statements);

        if (!p.string().equals("let myVar = anotherVar;")) {
            StringBuilder msg = new StringBuilder();
            msg.append("program.string() wrong, got = ").append(p.string()).append(" expected = let myVar = anotherVar;");
            fail(msg.toString());
        }
    }
}
