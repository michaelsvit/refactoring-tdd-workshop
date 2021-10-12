package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private final List<Card> cardList;

    public Hand(List<Card> cards) {
        this.cardList = cards;
    }

    public Hand() {
        this.cardList = new ArrayList<Card>();
    }

    public void deal(Card card) {
        cardList.add(card);
    }

    public int value() {
        int handValue = cardList
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = cardList
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    Card firstCard() {
        return cardList.get(0);
    }

    void display() {
        System.out.println(cardList.stream()
                                   .map(Card::display)
                                   .collect(Collectors.joining(
                                       ansi().cursorUp(6).cursorRight(1).toString())));
    }

    boolean isBust() {
        return value() > 21;
    }

    boolean pushes(Hand otherHand) {
        return otherHand.value() == value();
    }

    boolean beats(Hand otherHand) {
        return otherHand.value() < value();
    }
}