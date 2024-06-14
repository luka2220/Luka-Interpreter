package org.example;

import org.example.ast.*;
import org.example.ast.contracts.Expression;
import org.example.ast.contracts.Statement;
import org.example.lexer.Lexer;
import org.example.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void testLetStatements() {
        // NOTE: let statements code test
        String input = "let x = 5;\n" +
                "let y = 10;\n" +
                "let foobar = 838383;\n";

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Program program = parser.parseProgram();
        this.checkParserErrors(parser);

        assertNotNull(program, "parseProgram() returned null. ❌");
        assertEquals(3, program.getStatements().size(), "program.Statements does not contain 3 statements. ❌");

        String[] expectedIdentifiers = {"x", "y", "foobar"};

        for (int i = 0; i < expectedIdentifiers.length; i++) {
            Statement stmt = program.getStatements().get(i);
            assertTrue(this.testLetStatement(stmt, expectedIdentifiers[i]), "Let statement test failed. ❌");
        }
    }

    @Test
    void testReturnStatements() {
        /**
         * Return statement test code
        */
        String input = "return 5;\n" +
                "return 10;\n" +
                "return 993322;\n";

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Program program = parser.parseProgram();
        this.checkParserErrors(parser);

        assertNotNull(program, "parseProgram() returned null. ❌");
        assertEquals(3, program.getStatements().size(), "program.Statements does not contain 3 statements. ❌");

        for (int i = 0; i < program.getStatements().size(); i++) {
            Statement stmt = program.getStatements().get(i);

            if (!(stmt instanceof ReturnStatement)) {
                fail("stmt is not ReturnStatement, got=" + stmt);
                continue;
            }

            if (!stmt.tokenLiteral().equals("return")) {
                fail("stmt literal is not 'return', got " + stmt.tokenLiteral());
            }
        }
    }

    @Test
    void testIdentifierExpression() {
        /**
         *  Initializing an identifier expression to test
         * */
        String input = "foobar";

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();

        this.checkParserErrors(parser);

        if (program.getStatements().size() != 1) {
            StringBuilder msg  = new StringBuilder();
            msg.append("program does not have enough statements, got=").append(program.getStatements().size());
            fail(msg.toString());
        }

        Statement stmt = program.getStatements().get(0);
        assertTrue(stmt instanceof ExpressionStatement,
                String.format("program.Statements[0] is not ExpressionStatement. got=%s", stmt.getClass().getSimpleName()));

        ExpressionStatement exprStmt = (ExpressionStatement) stmt;
        assertTrue(exprStmt.getExpression() instanceof Identifier,
                String.format("exp not Identifier. got=%s", exprStmt.getExpression().getClass().getSimpleName()));

        Identifier ident = (Identifier) exprStmt.getExpression();
        assertEquals("foobar", ident.getValue(),
                String.format("ident.Value not %s. got=%s", "foobar", ident.getValue()));
        assertEquals("foobar", ident.tokenLiteral(),
                String.format("ident.TokenLiteral not %s. got=%s", "foobar", ident.tokenLiteral()));

    }

    @Test
    void testIntegerLiteralExpressions() {
        String input = "5;";

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();

        this.checkParserErrors(parser);

        if (program.getStatements().size() != 1) {
            StringBuilder msg = new StringBuilder();
            msg.append("Program does not have enough statements, got=").append(program.getStatements().size());
            fail(msg.toString());
        }

        Statement stmt = program.getStatements().get(0);
        assertTrue(stmt instanceof ExpressionStatement,
                String.format("program.Statements[0] is not ExpressionStatement. got=%s", stmt.getClass().getSimpleName()));

        ExpressionStatement expStmt = (ExpressionStatement) stmt;
        assertTrue(expStmt.getExpression() instanceof IntegerLiteral,
                "exp not IntegerLiteral. got=" + expStmt.getExpression().getClass().getSimpleName());

        IntegerLiteral literal = (IntegerLiteral) expStmt.getExpression();

        // Check the value of the literal
        assertEquals(5, literal.getValue(),
                "literal.Value not 5. got=" + literal.getValue());

        // Check the token literal of the literal
        assertEquals("5", literal.tokenLiteral(),
                "literal.TokenLiteral not 5. got=" + literal.tokenLiteral());

    }

    private boolean testLetStatement(Statement stmt, String expectedName) {
        if (!stmt.tokenLiteral().equals("let")) {
            fail("TokenLiteral not 'let', got " + stmt.tokenLiteral());
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

    private void checkParserErrors(Parser p) {
        List<String> errors = p.Errors();

        if (errors.isEmpty()) {
            return;
        }

        StringBuilder errorMsg = new StringBuilder("The parser has " + errors.size() + " errors:\n");
        for (String error: errors) {
            errorMsg.append("parser error: " + error + "\n");
        }

        fail(errorMsg.toString());
    }
}
