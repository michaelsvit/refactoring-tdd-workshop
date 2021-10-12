package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(Card... cards) {
        this.cards = Arrays.asList(cards);
    }

    public boolean isBusted() {
        return value() > 21;
    }

    public int value() {
        int handValue = cards
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = cards
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    public void deal(Card card) {
        cards.add(card);
    }

    public void display() {
        System.out.println(cards.stream()
                                .map(Card::display)
                                .collect(Collectors.joining(
                                        Ansi.ansi().cursorUp(6).cursorRight(1).toString())));
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    boolean isGreaterThan(Hand hand) {
        return hand.value() < value();
    }

    boolean isEqualTo(Hand hand) {
        return hand.value() == value();
    }
}