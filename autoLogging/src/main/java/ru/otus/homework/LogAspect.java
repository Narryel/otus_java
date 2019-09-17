package ru.otus.homework;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Slf4j
public class LogAspect {

    static Loggable createLoggedProxy() {
        InvocationHandler ih = new LoggerHandler(new LoggableImpl());
        return (Loggable) Proxy.newProxyInstance(LoggableImpl.class.getClassLoader(), new Class<?>[]{Loggable.class}, ih);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class LoggerHandler implements InvocationHandler {
        Loggable className;
        HashSet<String> annotatedMethods = new HashSet<>();


        public LoggerHandler(Loggable className) {
            this.className = className;

            checkForAnnotation(className);
        }

        private void checkForAnnotation(Loggable className) {
            if (className.getClass().getAnnotation(Log.class) != null) {
                for (Method method : className.getClass().getMethods()) {
                    annotatedMethods.add(method.getName());
                }
                return;
            }


            for (Method method : className.getClass().getMethods()) {
                if (method.getAnnotation(Log.class) != null) {
                    annotatedMethods.add(method.getName());
                }
            }
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(method.getName())) {
                String argString = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                System.out.println("Invoking method: " + method.getName() + " , with params: " + argString + " ");
            }

            return method.invoke(className, args);
        }
    }
}
