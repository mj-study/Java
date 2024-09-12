package com.example.effectivejava.section04.item22;

/**
 * 인터페이스를 잘못 사용한 예시
 * 오로지 상수 정보 제공만을 목적으로 메서드 없이 정적 필드 제공
 */
public interface IncorrectInterface {
	static final String USER_NAME = "상수";
	static final int USER_CODE = 123_333_555;
}
