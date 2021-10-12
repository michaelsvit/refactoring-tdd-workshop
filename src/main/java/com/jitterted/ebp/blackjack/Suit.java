package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

public enum Suit {
    SPADES("♠", Ansi.Color.BLACK), DIAMONDS("♦", Ansi.Color.RED),
    HEARTS("♥", Ansi.Color.RED), CLUBS("♣", Ansi.Color.BLACK);

    private final String symbol;
    private final Ansi.Color color;

    Suit(String symbol, Ansi.Color color) {
        this.symbol = symbol;
        this.color = color;
    }

    String symbol() {
        return symbol;
    }

    public Ansi.Color getColor() {
        return color;
    }
}
