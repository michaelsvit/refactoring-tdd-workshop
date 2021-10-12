package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WalletTest {

    //Z - Zero
    //O - One
    //M - Many
    //B - Boundary
    //I - Interface
    //E - Exceptions
    //S - (Simple)
    @Test
    void testNewWalletIsEmpty() { //Zero Condition
        Wallet wallet = new Wallet();
        boolean result = wallet.isEmpty();
        assertThat(result).isTrue();
    }

    @Test
    void testAddMoneyWalletIsNotEmpty() { //Zero Condition
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        assertThat(wallet.isEmpty()).isFalse();
    }

    @Test
    void testANewWalletHasZeroBalance() { //Zero Condition
        Wallet wallet = new Wallet();
        int result = wallet.balance();
        assertThat(result).isZero();
    }

    @Test
    void testWalletWithAdd15ShouldReturn15Balance() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        int result = wallet.balance();
        assertThat(result).isEqualTo(15);
    }

    @Test
    void testWalletWithAdd15Add15ShouldReturn30Balance() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        wallet.addMoney(15);
        int result = wallet.balance();
        assertThat(result).isEqualTo(30);
    }


    @Test
    void testWalletNoNegativeAddMoney() {
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.addMoney(-15))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void bettingReducesBalance() {
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        wallet.bet(7);
        assertThat(wallet.balance()).isEqualTo(3);
    }

    @Test
    void bettingTwiceReducesBalanceCorrectly() {
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        wallet.bet(7);
        wallet.bet(2);
        assertThat(wallet.balance()).isEqualTo(1);
    }

    @Test
    void bettingFullBalanceCausesWalletToBeEmpty() {
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        wallet.bet(10);
        assertThat(wallet.isEmpty()).isTrue();
    }

    @Test
    void bettingMoreThanBalanceShouldThrow() {
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        assertThatThrownBy(() -> wallet.bet(13))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testWalletNoNegativeBet() {
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.bet(-15))
                .isInstanceOf(IllegalArgumentException.class);
    }

}