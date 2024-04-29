package org.api.lec02;

import java.lang.reflect.Field;

// import org.domain.Lec02Person;

public class Main {
	public static void main(String[] args) throws Exception{
		// Lec02Person p = new Lec02Person();
		//
		// Class<Lec02Person> personClass = Lec02Person.class;
		// Field weight = personClass.getDeclaredField("weight");
		// weight.setAccessible(true);
		//
		// weight.set(p, 100);
		// System.out.println("weight = " + p.getWeight());


		Class<?> personClass = Class.forName("org.domain.Lec02Person");
		Field weight = personClass.getDeclaredField("weight");
		weight.setAccessible(true);

		Object p = personClass.getConstructor().newInstance();

		weight.set(p, 100);
		System.out.println(p);
	}
}
