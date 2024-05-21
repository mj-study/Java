package com.example.effectivejava.section02.item03;

import java.io.Serial;
import java.io.Serializable;

public class HomeSingleTon implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private static final HomeSingleTon INSTANCE = new HomeSingleTon();

	private HomeSingleTon() {}

	public static HomeSingleTon getInstance() {
		return INSTANCE;
	}

	/*
	* 직렬화 역직렬화시 싱글턴 보장을 위한 readResolve 메서드 사용
	* Java 14 부터 직렬화 역직렬화 메커니즘 변경에 따른 이슈 예방을 위해 @Serial 어노테이션 사용
	* => 해당 코드가 직렬화 관련된 것을 알 수 있음
	* */

	@Serial
	private Object readResolve() {
		return INSTANCE;
	}
}

