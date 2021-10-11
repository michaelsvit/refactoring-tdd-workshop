package com.jitterted.ebp.blackjack;

public enum Suit {
    HEARTS("♥"), SPADES("♠"), DIAMONDS( "♦"), CLUBS("♣");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
