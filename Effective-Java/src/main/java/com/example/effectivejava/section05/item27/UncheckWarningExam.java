package com.example.effectivejava.section05.item27;

import java.util.ArrayList;
import java.util.List;

public class UncheckWarningExam {
	public static void main(String[] args) {
		// RAW 타입 사용 -> 비검사 경고 발생
		List list = new ArrayList();
		list.add("테스트");
		list.add(123);

		list.forEach(System.out::println);

		//#####################################

		// 제네릭 타입 사용 -> 타입 안전성 보장
		List<String> stringList = new ArrayList<>();
		stringList.add("테스트");

		stringList.forEach(System.out::println);

		//#####################################
		List<?> rawList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<String> stringList1 = (List<String>)rawList; // 비검사 형변환, 불가피한 경우에만 어노테이션 사용, 코드가 안전하다는 확신이 있을때!

		stringList1.add("하이");
		System.out.println(stringList1.get(0)); // 정상 출력
	}
}
