package org.example;

public class App {
    public String getGreeting() {
        return "Building an Interpreter in Java 🤩";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
