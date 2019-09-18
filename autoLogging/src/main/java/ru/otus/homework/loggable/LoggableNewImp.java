package ru.otus.homework.loggable;

import ru.otus.homework.aop.Log;

public class LoggableNewImp implements Loggable {
    @Override
    public void doSomeLogic(int b1, int i, int b) {

    }

    @Log
    @Override
    public void doAnotherLogic(Object a, Object b, Object c) {
    }

    public void doAnotherLogic(Object a, Object b){

    }


}
