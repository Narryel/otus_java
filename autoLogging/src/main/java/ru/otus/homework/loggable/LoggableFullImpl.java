package ru.otus.homework.loggable;


import ru.otus.homework.aop.Log;

@Log
public class LoggableFullImpl implements Loggable {
    @Override
    public void doSomeLogic(int b1, int i, int b) {

    }

    @Override
    public void doAnotherLogic(Object a, Object b, Object c) {

    }

    @Override
    public void doAnotherLogic(Object a, Object b) {

    }
}
