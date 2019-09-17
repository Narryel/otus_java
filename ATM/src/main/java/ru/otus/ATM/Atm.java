package ru.otus.ATM;

import java.util.*;

public class Atm implements AtmFucntions {

    private TreeMap<Nominal, CashCassete> cassetes;

    public Atm(TreeMap<Nominal, CashCassete> cassetes) {
        this.cassetes = cassetes;
    }

    @Override
    public void depositBanknotes(List<Banknote> cash) {
        for (Banknote note : cash) {
            cassetes.get(note.getNominal()).storeBanknote();
        }

    }

    @Override
    public List<Banknote> withdrawMoney(int summ) {
        Map<Nominal, Integer> withdrawMap;
        try {
            withdrawMap = checkSumm(summ);
        } catch (Exception e) {
            System.out.println("Не получилось выдать сумму : " + e.getMessage());
            return new ArrayList<>();
        }
        List<Banknote> banknotesToWithdrawal = new ArrayList<>();
        for (Map.Entry<Nominal, Integer> entry : withdrawMap.entrySet()) {
            banknotesToWithdrawal.addAll(cassetes.get(entry.getKey()).getBanknotes(entry.getValue()));

        }

        return banknotesToWithdrawal;
    }

    private Map<Nominal, Integer> checkSumm(int summ) throws Exception {
        if (summ > this.getBalance()) throw new Exception("Слишком мало денег в банкомате");
        Map<Nominal, Integer> wdrwlMap = new HashMap<>();
        for (CashCassete cassete : cassetes.values() ) {
            if(!(summ>0)) break;

            int count = summ / cassete.getNominal().getValue();
            if (count > cassete.getBanknotesCount()) {
                wdrwlMap.put(cassete.getNominal(), cassete.getBanknotesCount());
                summ -= cassete.getNominal().getValue() * cassete.getBanknotesCount();
            } else {
                wdrwlMap.put(cassete.getNominal(), count);
                summ -= cassete.getNominal().getValue() * count;
            }
        }
        if (summ != 0) throw new Exception("Не хватило банкнот или сумма нацело не делится на текущие номиналы");
        return wdrwlMap;
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (CashCassete cassete : cassetes.values()) {
            balance += cassete.getBanknotesCount() * cassete.getNominal().getValue();
        }
        return balance;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "cassetes=" + cassetes +
                '}';
    }
}
