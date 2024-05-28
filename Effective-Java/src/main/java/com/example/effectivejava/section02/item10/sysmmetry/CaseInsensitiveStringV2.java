package com.example.effectivejava.section02.item10.sysmmetry;

import java.util.Objects;

/**
 * 대칭에 대한 동치를 확인하기 위한 클래스 개선
 */
public class CaseInsensitiveStringV2 {
	private final String s;

	public CaseInsensitiveStringV2(String s) {
		this.s = Objects.requireNonNull(s);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof CaseInsensitiveStringV2 &&
			((CaseInsensitiveStringV2)o).s.equalsIgnoreCase(s);
	}
}
