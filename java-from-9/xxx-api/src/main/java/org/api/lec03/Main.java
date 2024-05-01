package org.api.lec03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	@SafeVarargs
	public static <T> List<T> flatten(List<T>... lists) {
		List<T> result = new ArrayList<>();
		for (List<T> list : lists) {
			result.addAll(list);
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5);
		List<Integer> result = flatten(list1, list2);
		System.out.println(result);

		// 생성시 제네릭타입에 명시안해줘도 됨, diamond 데이터타입 명시 x (필수가아님)
		InnerClass<Integer> innerclass = new InnerClass<>(3);
	}

	public static class InnerClass<T> {
		private final T t;

		public InnerClass(T t) {
			this.t = t;
		}
	}

}
