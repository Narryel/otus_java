package ru.otus.ATM;


import java.util.ArrayList;
import java.util.List;

public class CashCassete implements Comparable<CashCassete>{
    private Nominal nominal;
    private int banknotesCount;


    public Nominal getNominal() {
        return nominal;
    }

    public CashCassete(Nominal nominal) {
        this.nominal = nominal;
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    public void storeBanknote(){
        banknotesCount++;
    }

    public List<Banknote> getBanknotes(int count){
        banknotesCount -= count;
        List<Banknote> notes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            notes.add(new Banknote(this.getNominal()));
        }
        return notes;
    }

    @Override
    public int compareTo(CashCassete o) {
        if(this.nominal.getValue() == o.getNominal().getValue()) return 0;
        return this.nominal.getValue() > o.getNominal().getValue() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "CashCassete{" +
                "nominal=" + nominal +
                ", banknotesCount=" + banknotesCount +
                '}';
    }
}
