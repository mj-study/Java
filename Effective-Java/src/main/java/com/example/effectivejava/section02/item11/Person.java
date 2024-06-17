package com.example.effectivejava.section02.item11;

import java.util.Objects;

public class Person {
	private String name;
	private int age;
	private double salary;

	public Person(String name, int age, double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		int result = 17;  // 초기값으로 소수를 사용
		result = 31 * result + (name == null ? 0 : name.hashCode());  // String 필드
		result = 31 * result + Integer.hashCode(age);  // int 필드
		long temp = Double.doubleToLongBits(salary);  // double 필드
		result = 31 * result + (int)(temp ^ (temp >>> 32));  // double 필드를 int로 변환 후 해시 코드 계산
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Person person = (Person)obj;
		return age == person.age && Double.compare(person.salary, salary) == 0 &&
			Objects.equals(name, person.name);
	}
}
