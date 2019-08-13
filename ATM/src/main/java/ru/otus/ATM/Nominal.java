package ru.otus.ATM;

public enum Nominal{
    TEN(10),
    HUNDRED(100),
    THOUSAND(1000);
    private int value;

    Nominal(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}