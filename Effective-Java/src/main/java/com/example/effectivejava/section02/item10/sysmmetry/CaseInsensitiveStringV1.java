package com.example.effectivejava.section02.item10.sysmmetry;

import java.util.Objects;

/**
 * 대칭에 대한 동치를 확인하기 위한 클래스
 */
public class CaseInsensitiveStringV1 {
	private final String s;

	public CaseInsensitiveStringV1(String s) {
		this.s = Objects.requireNonNull(s);
	}

	// 대칭성 위배되는 경우
	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveStringV1) {
			return s.equalsIgnoreCase(
				((CaseInsensitiveStringV1)o).s
			);
		}

		// 이 부분에서 다른 String.equals(o) 할 경우 다른 경우가 나올 수 있음
		if (o instanceof String) {
			return s.equalsIgnoreCase((String) o);
		}
		return false;
	}
}
