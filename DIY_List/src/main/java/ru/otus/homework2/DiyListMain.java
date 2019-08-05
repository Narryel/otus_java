package ru.otus.homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiyListMain {
    public static void main(String[] args) {
        DyiLIST list = new DyiLIST();
        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());

        List<String> exampleList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            exampleList.add("i = " + i);
        }
        Collections.addAll(list, exampleList);
        System.out.println("list = " + list);
    }
}