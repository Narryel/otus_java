package ru.otus.testFramework;

import ru.otus.testFramework.annotations.AfterTests;
import ru.otus.testFramework.annotations.BeforeTests;
import ru.otus.testFramework.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestStarter {

	@SuppressWarnings("WeakerAccess")
	public static void startTestClass(String classpath) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Class<?> testClass = Class.forName(classpath);

		Method[] methods = testClass.getMethods();
		//по умолчанию
		Constructor<?> constructor = testClass.getConstructors()[0];

		List<Method> before = new ArrayList<>();
		List<Method> test = new LinkedList<>();
		List<Method> after = new ArrayList<>();
		for (Method method : methods) {

			BeforeTests beforeAnno = method.getAnnotation(BeforeTests.class);
			if (beforeAnno != null) {
				before.add(method);
			}

			AfterTests afterAnno = method.getAnnotation(AfterTests.class);
			if (afterAnno != null) {
				after.add(method);
			}

			Test testAnno = method.getAnnotation(Test.class);
			if (testAnno != null) {
				test.add(method);
			}
		}

		int testMethodsCount = test.size();
		int failedTestsCount = 0;
		int passedTestsCount = 0;

		for (Method testMethod : test) {
			Object o = constructor.newInstance();
			for (Method beforeMethod : before) {
				//exceptions from @before and @after are not catched and thrown upwards
				beforeMethod.invoke(o);
			}

			try {
				testMethod.invoke(o);
				passedTestsCount++;
			} catch (Exception e) {
				Class expectedException = testMethod.getAnnotation(Test.class).expectedException();

				//fixme если несколько уровней cause?
				if(e.getCause().getClass().equals(expectedException)){
					passedTestsCount++;
				} else {
					failedTestsCount++;
				}
			}

			for (Method afterMethod : after) {
				//exceptions from @before and @after are not catched and thrown upwards
				afterMethod.invoke(o);
			}


		}

		System.out.println("testMethodsCount = " + testMethodsCount);
		System.out.println("passedTestsCount = " + passedTestsCount);
		System.out.println("failedTestsCount = " + failedTestsCount);
	}

	public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
		//todo get class from main args?

		TestStarter.startTestClass("ru.otus.testFramework.TestClassOne");




	}


}
