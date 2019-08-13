package ru.otus.homework2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiyListMain {
    public static void main(String[] args) {
        DiyList list = new DiyList();
        DiyList list2 = new DiyList();
        DiyList<BasicDto> list3 = new DiyList<BasicDto>();

        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());
        System.out.println("list.getLength() = " + list.getLength());

        List<String> exampleList = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            exampleList.add("i = " + i);
            list2.add(i);
            list3.add(new BasicDto("DTO NUMBER " + i, i));
        }
        Collections.addAll(list, exampleList);
        System.out.println("list = " + list);

        System.out.println("list2 = " + list2);
        Collections.copy(list2, exampleList);
        System.out.println("list2 = " + list2);

        System.out.println("list3 = " + list3);
        Collections.sort(list3, (o1, o2) -> {
            System.out.println("o1 = " + o1);
            System.out.println("o2 = " + o2);
            if(o1.getNumber() == o2.getNumber()) return 0;
            return o1.getNumber() < o2.getNumber() ? 1 : -1;
        });
        System.out.println("list3 = " + list3);


    }
}