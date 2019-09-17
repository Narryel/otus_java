package ru.otus.ATM;

public class Banknote {
    private Nominal nominal;

    public Banknote(Nominal nominal) {
        this.nominal = nominal;
    }

    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "nominal=" + nominal +
                '}';
    }
}
