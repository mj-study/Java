package com.example.effectivejava.section04.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CafeConstant {

	private static class CafeInfo {
		public CafeInfo(String menu, int price) {
			this.menu = menu;
			this.price = price;
		}

		private String menu;
		private int price;
	}

	public static final String AVAILABLE_ROLE = "super";
	// 배열에 접근하여 값을 바꿀 수 있는 상수는 사용x
	public static final CafeInfo[] INCORRECT_CAFE_INFOS = {new CafeInfo("iceAmericano", 5000)};

	private static final CafeInfo[] CORRECT_CAFE_INFOS = {new CafeInfo("iceAmericano", 5000)};
	/*
	방법1. 배열을 private 으로 만들고 배열을 public 불변 리스트에 추가
	 */
	public static final List<CafeInfo> VALUE_LIST = Collections.unmodifiableList(Arrays.asList(CORRECT_CAFE_INFOS));

	/*
	방법2. 배열을 private 으로 만들고 복사본 반환하는 public 메서드 추가 (방어적 복사)
	 */
	public static final CafeInfo[] getCafes() {
		return CORRECT_CAFE_INFOS.clone();
	}

}
