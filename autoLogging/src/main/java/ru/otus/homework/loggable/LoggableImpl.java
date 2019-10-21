package ru.otus.homework.loggable;

import ru.otus.homework.aop.Log;

public class LoggableImpl implements Loggable {
    @Log
    @Override
    public void doSomeLogic(int b1, int i, int a) {

    }

    @Override
    public void doAnotherLogic(Object a, Object b, Object c) {

    }

    @Override
    public void doAnotherLogic(Object a, Object b) {

    }

}


