package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    // too long, does too many things - initializes values, suits, creates cards, shuffles
    public Deck() {
        List<String> cardValues = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        Suit[] suits = Suit.values();
        for (Suit suit : suits) {
            for (String cardValue : cardValues) {
                cards.add(new Card(suit, cardValue));
            }
        }
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.remove(0);
    }
}
