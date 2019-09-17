package ru.otus.homework;

public class App {
    public static void main(String[] args) {
        Loggable loggable = LogAspect.createLoggedProxy();
        loggable.doSomeLogic(6,7,8);
        loggable.doAnotherLogic(1,2,3);

    }
}
