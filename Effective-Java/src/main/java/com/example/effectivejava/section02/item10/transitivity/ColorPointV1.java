package com.example.effectivejava.section02.item10.transitivity;

public class ColorPointV1 extends Point{
	private final Color color;

	public ColorPointV1(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	// 대칭성 위배
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ColorPointV1)) {
			return false;
		}
		return super.equals(o) && ((ColorPointV1) o).color == color;
	}
}
