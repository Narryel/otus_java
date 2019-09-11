package ru.otus.testFramework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface Test {
	//дефолтным выбран Exception.class потому что его, вроде, стандартная библиотека не кидает
	Class expectedException() default Exception.class;
}
