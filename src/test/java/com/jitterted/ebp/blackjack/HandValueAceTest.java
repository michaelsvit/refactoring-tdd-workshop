package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() {
        Hand hand = new Hand(new Card(Suit.SPADES, "A"),
                             new Card(Suit.SPADES, "5"));

        assertThat(hand.value())
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() {
        Hand hand = new Hand(new Card(Suit.SPADES, "A"),
                             new Card(Suit.SPADES, "8"),
                             new Card(Suit.SPADES, "3"));

        assertThat(hand.value())
                .isEqualTo(1 + 8 + 3);
    }

    @Test
    void aceCountsAs11() {
        Hand hand = new Hand(new Card(Suit.SPADES, "A"),
                             new Card(Suit.SPADES, "10"));

        assertThat(hand.value()).isEqualTo(21);
    }

    @Test
    void aceCountsAs11WithFace() {
        Hand hand = new Hand(new Card(Suit.SPADES, "A"),
                             new Card(Suit.SPADES, "K"));

        assertThat(hand.value()).isEqualTo(21);
    }

    @Test
    void aceCountsAs1() {
        Hand hand = new Hand(new Card(Suit.SPADES, "A"),
                             new Card(Suit.SPADES, "9"),
                             new Card(Suit.SPADES, "3"));

        assertThat(hand.value()).isEqualTo(13);
    }
}
