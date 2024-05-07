package org.api.lec06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		/*
		* var
		* 지역 변수에만 사용
		* 키워드가 아님
		* 타입을 추론하는 예약어기때문에 값이 없거나 null 이면 안됨
		*/
		var num = 3;
		num = 10;
		// num = "ABC"; // 한 번 추론된 데이터타입 외 다른 타입들어오면 에러
		// final var num2 = 4; // 불변 가능
		var list = List.of(1, 2, 3);

		// copyOfList();

		// unModifiableList();
		optionalNoSuch();
	}

	private static void copyOfList() {
		// copyOf : 깊은 복사
		List<Integer> oldNums = new ArrayList<>();
		oldNums.add(1);
		oldNums.add(2);

		List<Integer> newNums = List.copyOf(oldNums);
		oldNums.add(3);

		oldNums.forEach(x-> System.out.println("old = " + x));
		newNums.forEach(x -> System.out.println("new = " + x));
	}

	private static void unModifiableList() {
		// view 처럼 보임, 원본에 데이터가 추가되면 같이 추가됨
		List<Integer> oldNums = new ArrayList<>();
		oldNums.add(1);
		oldNums.add(2);

		List<Integer> newNums = Collections.unmodifiableList(oldNums);
		oldNums.add(3);

		oldNums.forEach(x-> System.out.println("old = " + x));
		newNums.forEach(x -> System.out.println("new = " + x));
	}

	private static void optionalNoSuch() {
		// Optional.ofNullable(3)
		String str = null;
		Optional.ofNullable(str)
			.orElseThrow();
	}
}
