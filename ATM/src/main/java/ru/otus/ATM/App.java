package ru.otus.ATM;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Slf4j
public class App {
    public static void main(String[] args) {

        TreeMap<Nominal, CashCassete> cassetes = new TreeMap<>();
        cassetes.put(Nominal.HUNDRED, new CashCassete(Nominal.HUNDRED));
        cassetes.put(Nominal.TEN, new CashCassete(Nominal.TEN));
        cassetes.put(Nominal.THOUSAND, new CashCassete(Nominal.THOUSAND));

        Atm atm1 = new Atm(cassetes);
        List<Banknote> money = Arrays.asList(new Banknote(Nominal.TEN), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.THOUSAND));

        //1210
        atm1.depositBanknotes(money);
        System.out.println(atm1.getBalance());

        //-110
        List<Banknote> banknotes = atm1.withdrawMoney(110);
        System.out.println("banknotes = " + banknotes);
        System.out.println(atm1.getBalance());

        //+ 600
        atm1.depositBanknotes(Arrays.asList(new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED), new Banknote(Nominal.HUNDRED)));

        System.out.println(atm1.getBalance());

        //-110
        List<Banknote> banknotes2 = atm1.withdrawMoney(110);

    }
}
