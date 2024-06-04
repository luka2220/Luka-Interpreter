package org.example;

import org.example.lexer.Lexer;
import org.example.token.Token;
import org.example.token.TokenTypes;

import java.io.InputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Try out some fun programming 🤩");
        System.out.println("Type in source code to view the tokens 💻");
        System.out.println("Press ctrl+c to exit!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nProgram terminated.");
        }));

        InputStream input = System.in;
        System.out.print(">> ");
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Lexer lexer = new Lexer(line);

            Token tok = lexer.NextToken();
            while (!tok.getType().equals(TokenTypes.EOF)) {
                System.out.printf("{Type:%s, Literal:%s}\n", tok.getType(), tok.getLiteral());
                tok = lexer.NextToken();
            }
        }

        scanner.close();
    }
}
