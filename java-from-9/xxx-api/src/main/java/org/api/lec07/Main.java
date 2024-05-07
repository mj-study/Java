package org.api.lec07;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		// stringMethod();

		// collectionMethod();

		predicateMethod();
	}

	static void predicateMethod() {
		List<String> list = List.of("A", " ", "  ");

		List<String> result = list.stream()
			.filter(Predicate.not(String::isBlank))
			.toList();
		result.forEach(System.out::println);
	}

	static void collectionMethod() {
		List<Integer> list = List.of(1, 2, 3);
		Integer[] array = list.toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
	}

	static void stringMethod() {
		String str = "   ab c   ";
		// 앞 뒤 공백 제거
		System.out.println(str.strip());

		String str2 = "  abc  ";
		// 앞 공백 제거
		System.out.println(str2.stripLeading());

		// 뒤 공백 제거
		String str3 = "   abc   ";
		System.out.println(str3.stripTrailing());

		String blank = "     ";
		System.out.println("blank = " + blank.isBlank());
		System.out.println("str3 = " + str3.isBlank());

		String line = "Abc \n bcbda";
		line.lines()
			.forEach(System.out::println);

		String repeat = "* ";
		System.out.println(repeat.repeat(5));
	}
}
