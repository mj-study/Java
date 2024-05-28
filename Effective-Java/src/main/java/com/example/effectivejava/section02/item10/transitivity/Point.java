package com.example.effectivejava.section02.item10.transitivity;

/**
 * 추이성 확인을 위한 클래스
 * null이 아닌 모든 참조 값 x,y,z에 대해 x.equals(y) true이고 y.equals(z) true이면 x.equals(z)도 true
 */
public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		// instance pattern 사용
		if (!(o instanceof Point p)) {
			return false;
		}
		return p.x == x && p.y == y;
	}
}
