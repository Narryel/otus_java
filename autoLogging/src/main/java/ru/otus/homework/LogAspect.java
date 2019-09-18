package ru.otus.homework;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
@Slf4j
public class LogAspect {

    static Loggable createLoggedProxy(Loggable loggable) {
        InvocationHandler ih = new LoggerHandler(loggable);
        return (Loggable) Proxy.newProxyInstance(loggable.getClass().getClassLoader(), new Class<?>[]{Loggable.class}, ih);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class LoggerHandler implements InvocationHandler {
        Loggable className;
        Set<Method> annotatedMethods = new HashSet<>();


        public LoggerHandler(Loggable className) {
            this.className = className;

            checkForAnnotation(className);
        }

        private void checkForAnnotation(Loggable className) {
            if (className.getClass().getAnnotation(Log.class) != null) {
                annotatedMethods.addAll(Arrays.asList(className.getClass().getMethods()));
            } else {
                for (Method method : className.getClass().getMethods()) {
                    if (method.getAnnotation(Log.class) != null) {
                        annotatedMethods.add(method);
                    }
                }
            }
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method annotatedMethod : annotatedMethods) {
                if(hasSameSignature(annotatedMethod, method)){
                    {
                        String argString = Arrays.stream(args)
                                .map(Object::toString)
                                .collect(Collectors.joining(", "));

                        System.out.println("Invoking method: " + method.getName() + " , with params: " + argString + " ");
                    }
                }
            }
//            if (annotatedMethods.contains(method.getName()) &&
//                    annotatedMethods.get(method.getName()).contains(method.getParameterTypes()))

            return method.invoke(className, args);
        }

        private boolean hasSameSignature(Method method1, Method method2){
            if (method1 == null)
                return false;
            if (method2 == null)
                return false;

            if(!method1.getName().equals(method2.getName())) return false;

            List parameterTypesA = Arrays.asList(method1.getParameterTypes());
            List parameterTypesB = Arrays.asList(method2.getParameterTypes());

            if (parameterTypesA.equals(parameterTypesB))
                return true;

            return false;

        }
    }
}
