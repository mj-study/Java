package com.example.effectivejava.section02.item10.transitivity;

import java.util.Objects;

public class ColorPointV2{
	private final Point point; // 상속대신 컴포지션으로 추가
	private final Color color;

	public ColorPointV2(int x, int y, Color color) {
		point = new Point(x, y);
		this.color = Objects.requireNonNull(color);
	}

	/*
	* ColorPoint Point 뷰 반환
	* */
	public Point asPoint() {
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ColorPointV2)) {
			return false;
		}
		ColorPointV2 cp = (ColorPointV2)o;
		return cp.point.equals(point) && cp.point.equals(color);
	}
}
