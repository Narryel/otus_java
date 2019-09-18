package ru.otus.homework;

import ru.otus.homework.aop.LogAspect;
import ru.otus.homework.loggable.Loggable;
import ru.otus.homework.loggable.LoggableFullImpl;
import ru.otus.homework.loggable.LoggableImpl;
import ru.otus.homework.loggable.LoggableNewImp;
import ru.otus.homework.running.Running;
import ru.otus.homework.running.RunningImpl;

public class App {
    public static void main(String[] args) {
        Loggable loggable = (Loggable) LogAspect.createLoggedProxy(new LoggableImpl());
        loggable.doSomeLogic(6,7,8);
        loggable.doAnotherLogic(1,2,3);

        Loggable loggableNew = (Loggable) LogAspect.createLoggedProxy(new LoggableNewImp());
        loggableNew.doSomeLogic(1,1,1);
        loggableNew.doAnotherLogic("wow","this","works");
        loggableNew.doAnotherLogic("this shouldnt", "work");

        Loggable loggableFull = (Loggable) LogAspect.createLoggedProxy(new LoggableFullImpl());
        loggableFull.doSomeLogic(9,9,9);
        loggableFull.doAnotherLogic("log", "on Class","works aswell");
        loggableFull.doAnotherLogic("WOW", "THIS SHOULD BE PRINTED");

        Running r1 = (Running) LogAspect.createLoggedProxy(new RunningImpl());
        r1.start(2);
        //logged
        r1.stop(18);

    }
}
