package com.example.effectivejava.section02.item10.transitivity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColorPointTest {

	/*
	* 현재 Point equals는 색상무시, ColorPoint equals는 입력매개변수가 ColorPoint가 아니니 달라서 false 반환
	* */
	@Test
	void pointEqualsColorPoint () {
		Point p = new Point(1, 2);
		ColorPointV1 cp = new ColorPointV1(1, 2, Color.BLUE);

		// Point equals는 x, y 비교 color는 무시함
		boolean pToCp = p.equals(cp); // 예상결과 true
		// ColorPointV1는 color까지 비교
		boolean cpToP = cp.equals(p); // 예상결과 false
		assertNotEquals(pToCp, cpToP);
	}

	@Test
	void pointEqualsComposition () {
		Point p = new Point(1, 2);
		ColorPointV2 cp = new ColorPointV2(1, 2, Color.BLUE);

		// Point equals는 x, y 비교 color는 무시함
		boolean pToCp = p.equals(cp); // 예상결과 true
		// ColorPointV2
		boolean cpToP = cp.equals(p); // 예상결과 false
		assertEquals(pToCp, cpToP);
	}

}