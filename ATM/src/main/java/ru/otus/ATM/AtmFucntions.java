package ru.otus.ATM;

import java.util.List;

public interface AtmFucntions {
    void depositBanknotes(List<Banknote> cash);
    List<Banknote> withdrawMoney(int summ) throws Exception;
    int getBalance();

}
