package com.example.effectivejava.section05.item26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenericExam {
	public static void main(String[] args) {
		// raw 타입은 안전하지 않음
		List nonGeneric = new ArrayList();

		nonGeneric.add("abc");
		nonGeneric.add(123);

		nonGeneric.forEach(System.out::println);

		List<String> stringList = new ArrayList<>();
		stringList.add("abcdef");
		stringList.forEach(System.out::println);

		Set<Object> set = new HashSet<>();
		set.add(1);
		set.add("문자");
		set.add(true);
		set.add(2.3f);

		readOnly(set);
	}

	/**
	 * set 읽기 전용 메서드z
	 * 비한정적 와일드카드를 사용했기에 해당 값을 다시 재연산에 사용x
	 * @param s
	 */
	static void readOnly(Set<?> s) {
		s.forEach(System.out::println);
	}
}
