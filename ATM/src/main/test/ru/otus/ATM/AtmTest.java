package ru.otus.ATM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtmTest {

    Atm atm1;

    @BeforeEach
    void setUp() {
        TreeMap<Nominal, CashCassete> cassetes = new TreeMap<>();
        cassetes.put(Nominal.HUNDRED, new CashCassete(Nominal.HUNDRED));
        cassetes.put(Nominal.TEN, new CashCassete(Nominal.TEN));
        cassetes.put(Nominal.THOUSAND, new CashCassete(Nominal.THOUSAND));

        atm1 = new Atm(cassetes);
    }

    @Test
    @DisplayName("проверка баланса у нового АТМ ")
    void newAtm() {
        assertEquals(0, atm1.getBalance(), "Баланс не сходится :(");
    }

    private void deposit1210() {
        List<Banknote> money = Arrays.asList(new Banknote(Nominal.TEN), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.THOUSAND));
        atm1.depositBanknotes(money);
    }

    @Test
    @DisplayName("Внос 1210 рублев ")
    void checkDeposit() {
        deposit1210();
        assertEquals(1210, atm1.getBalance(), "Баланс не сходится :(");
    }

    @Test
    @DisplayName("Взнос 1210, попытка снятия 1500")
    void depositThenWithdrawal(){
        deposit1210();
        List<Banknote> banknotes = atm1.withdrawMoney(1500);

        assertTrue(banknotes.isEmpty());
    }

    @Test
    @DisplayName("Взнос 1210, снятие 200")
    void depositThenWithdrawal2(){
        deposit1210();
        atm1.withdrawMoney(210);

        assertThat(atm1.getBalance(), equalTo(1000));
    }

}
