package ru.otus.homework.aop;

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

    public static Object createLoggedProxy(Object object) {
        InvocationHandler ih = new LoggerHandler(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), ih);
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class LoggerHandler implements InvocationHandler {
        Object obj;
        Set<String> annotatedMethods = new HashSet<>();


        public LoggerHandler(Object obj) {
            this.obj = obj;

            checkForAnnotation(obj.getClass());
        }

        private void checkForAnnotation(Class<?> className) {
            if (className.getAnnotation(Log.class) != null) {
                annotatedMethods.addAll(Arrays.stream(className.getDeclaredMethods())
                        .map(LoggerHandler::getMethodSignatureCode)
                        .collect(Collectors.toSet()));
            } else {
                for (Method method : className.getMethods()) {
                    if (method.getAnnotation(Log.class) != null) {
                        String signatureCode = getMethodSignatureCode(method);
                        annotatedMethods.add(signatureCode);
                    }
                }
            }
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(getMethodSignatureCode(method))) {
                {
                    String argString = Arrays.stream(args)
                            .map(Object::toString)
                            .collect(Collectors.joining(", "));

                    System.out.println("Invoking method: " + method.getName() + " , with params: " + argString + " ");
                }
            }

            return method.invoke(obj, args);
        }


        private static String getMethodSignatureCode(Method method) {
            StringBuilder sb = new StringBuilder(method.getName()).append('@');
            for (Parameter parameter : method.getParameters()) {
                sb.append(parameter.getType().getName())
                        .append('+');
            }
            return sb.toString();


        }

    }
}
