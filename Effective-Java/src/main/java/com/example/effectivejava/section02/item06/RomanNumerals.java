package com.example.effectivejava.section02.item06;

import java.util.regex.Pattern;

public class RomanNumerals {

	/*
	아래 메서드는 유효한 로마 숫자인지 확인하는 정규 표현식을 간단하게 작성한 경우인데
	반복해서 호출할 경우 성능이 저하될 수 있음
	*/
	static boolean isRaomanNumeralV1(String s) {
		return s.matches("^[IVXLVL]$");
	}

	// static 에 캐싱해두고 나중에 isRomanNumeralV2 메서드가 호출될때마다 인스턴스를 재사용함
	// => 성능 상당 개선효과
	private static final Pattern ROMAN = Pattern.compile("^[IVXLVL]$");

	static boolean isRomanNumeralV2(String s) {
		return ROMAN.matcher(s).matches();
	}

}
