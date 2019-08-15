package ru.otus.testFramework;

import ru.otus.testFramework.annotations.AfterTests;
import ru.otus.testFramework.annotations.BeforeTests;
import ru.otus.testFramework.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestClassOne {
	private Map<String, Integer> map = new HashMap<>();

	@BeforeTests
	public void before(){
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THREE", 3);
		map.put("FOUR", 4);
		System.out.println("map = " + map);
	}

	@Test
	public void test1(){
		Integer one = map.get("ONE");
		System.out.println("one.equals(1) = " + one.equals(1));

	}

	@Test(expectedException = RuntimeException.class)
	public void test2(){
		throw new RuntimeException();
	}

	@Test
	public void test3(){
		throw new OutOfMemoryError();
	}


	@AfterTests
	public void after(){

		map.clear();
		System.out.println("map = " + map);
	}
}
