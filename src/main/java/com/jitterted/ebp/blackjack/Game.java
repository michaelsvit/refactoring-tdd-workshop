package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    public Game() {
        deck = new Deck();
    }

    // too long - creates game, prints welcome screen, starts game, resets game
    public static void main(String[] args) {
        printWelcomeScreen();
        initGame();
        resetGame();
    }

    private static void printWelcomeScreen() {
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" Jitterted's")
                                   .fgBlack().a(" BlackJack"));
    }

    private static void initGame() {
        Game game = new Game();
        game.dealRound();
        game.dealRound();
        game.play();
    }

    private static void resetGame() {
        System.out.println(ansi().reset());
    }

    private void dealRound() {
        playerHand.deal(deck.draw());
        dealerHand.deal(deck.draw());
    }

    public void play() {
        boolean playerBusted = playerTurn();
        if (!playerBusted) {
            dealerTurn();
        }
        displayFinalGameState();
        printResult(playerBusted);
    }

    private boolean playerTurn() {
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer();
            if (playerChoseStand(playerChoice)) {
                break;
            }
            if (playerChoseHand(playerChoice)) {
                playerBusted = dealCardToPlayer();
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
        return playerBusted;
    }

    private boolean dealCardToPlayer() {
        playerHand.deal(deck.draw());
        return playerHand.isBusted();
    }

    private boolean playerChoseStand(String playerChoice) {
        return playerChoice.startsWith("s");
    }

    private boolean playerChoseHand(String playerChoice) {
        return playerChoice.startsWith("h");
    }

    private void dealerTurn() {
        while (dealerHand.value() <= 16) {
            dealerHand.deal(deck.draw());
        }
    }

    private void printResult(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.isBusted()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (playerHand.isGreaterThan(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
        } else if (playerHand.isEqualTo(dealerHand)) {
            System.out.println("Push: You tie with the Dealer. 💸");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    private void displayGameState() {
        displayDealerFaceUpCard();
        displayBackOfCard();
        displayPlayerHand();
    }

    private void displayPlayerHand() {
        System.out.println();
        System.out.println("Player has: ");
        playerHand.display();
        System.out.println(" (" + playerHand.value() + ")");
    }

    public void displayDealerFaceUpCard() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.getFirstCard().display()); // first card is Face Up
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    // too long - prints multiple unrelated sections
    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.display();
        System.out.println(" (" + dealerHand.value() + ")");

        System.out.println();
        System.out.println("Player has: ");
        playerHand.display();
        System.out.println(" (" + playerHand.value() + ")");
    }
}
