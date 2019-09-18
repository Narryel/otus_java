package ru.otus.homework;

public class App {
    public static void main(String[] args) {
        Loggable loggable = LogAspect.createLoggedProxy(new LoggableImpl());
        loggable.doSomeLogic(6,7,8);
        loggable.doAnotherLogic(1,2,3);

        Loggable loggableNew = LogAspect.createLoggedProxy(new LoggableNewImp());
        loggableNew.doSomeLogic(1,1,1);
        loggableNew.doAnotherLogic("wow","this","works");
        loggableNew.doAnotherLogic("this shouldnt", "work");

        Loggable loggableFull = LogAspect.createLoggedProxy(new LoggableFullImpl());
        loggableFull.doSomeLogic(9,9,9);
        loggableFull.doAnotherLogic("log", "on Class","works aswell");

    }
}
