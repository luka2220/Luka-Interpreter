package org.example.ast.types;

/**
 * Operator precedence for expression statements
 * */
public enum Precedence {
    LOWEST(1),
    EQUALS(2),        // ==
    LESSGREATER(3),   // > or <
    SUM(4),           // +
    PRODUCT(5),       // *
    PREFIX(6),        // -X or !X
    CALL(7);

    private final int order;

    Precedence(int order) {
        this.order = order;
    }

    public int getPrecedence() {
        return this.order;
    }
}
