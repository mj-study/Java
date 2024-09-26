package com.example.effectivejava.section05.item28;

import java.util.ArrayList;
import java.util.List;

public class ListExam {
	public static void main(String[] args) {
		// Long은 Object의 하위 타입이므로 공변
		Object[] objArr = new Long[1];
		// Long 배열로 초기화 한 뒤 문자열을 넣었지만, 컴파일 단계에서는 에러를 뱉지 않음
		// objArr[0] = "문자열 넣으면 런타임 단계에서 에러 발생!";

		// 런타임 단계에서 에러 발생
		// System.out.println("objArr = " + objArr[0]);

		// #################################################

		// 제네릭은 불공변, 따라서 제네릭타입 Object List에서 Long 제네릭 타입으로 초기화 하려하면 컴파일 에러 발생
		// List<Object> listObj = new ArrayList<Long>();

	}
}
