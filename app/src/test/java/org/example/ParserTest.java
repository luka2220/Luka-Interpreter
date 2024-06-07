package org.example;

import org.example.ast.LetStatement;
import org.example.ast.Program;
import org.example.ast.contracts.Statement;
import org.example.lexer.Lexer;
import org.example.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void testLetStatements() {
        // NOTE: let statements code test piece
        String input = "let x = 5;\n" +
                "let y = 10;\n" +
                "let foobar = 838383;\n";

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Program program = parser.parseProgram();

        assertNotNull(program, "parseProgram() returned null. ❌");
        assertEquals(3, program.getStatements().size(), "program.Statements does not contain 3 statements. ❌");

        String[] expectedIdentifiers = {"x", "y", "foobar"};

        for (int i = 0; i < expectedIdentifiers.length; i++) {
            Statement stmt = program.getStatements().get(i);
            assertTrue(testLetStatement(stmt, expectedIdentifiers[i]), "Let statement test failed. ❌");
        }
    }

    private boolean testLetStatement(Statement stmt, String expectedName) {
        if (!stmt.tokenLiteral().equals("let")) {
            fail("TokenLiteral not 'Let', got " + stmt.tokenLiteral());
            return false;
        }

        if (!(stmt instanceof LetStatement)) {
            fail("stmt is not LetStatement, got=" + stmt);
            return false;
        }

        LetStatement letStmt = (LetStatement) stmt;

        if (!letStmt.getName().getValue().equals(expectedName)) {
            fail("letStmt.Name.Value wrong. expected=" + expectedName + ", got=" + letStmt.getName().getValue());
            return false;
        }

        if (!letStmt.getName().getToken().getLiteral().equals(expectedName)) {
            fail("letStmt.Name.TokenLiteral wrong. expected=" + expectedName + ", got=" +
                    letStmt.getName().getToken().getLiteral());
            return false;
        }

        return true;
    }
}
