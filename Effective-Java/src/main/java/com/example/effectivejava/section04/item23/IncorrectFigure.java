package com.example.effectivejava.section04.item23;

/**
 * 태그 달린 클래스로 계층구조보다 복잡하고, 파악하기 힘들다.
 */
public class IncorrectFigure {
	enum Shape {RECTANGLE, CIRCLE}

	private final Shape shape;

	// 사각형일때 사용
	private double width;
	private double length;

	// 원일때 사용
	private double radius;

	// 사각형 전용 생성자
	public IncorrectFigure(Shape shape, double width, double length) {
		this.shape = shape;
		this.width = width;
		this.length = length;
	}

	// 원 전용 생성자
	public IncorrectFigure(Shape shape, double radius) {
		this.shape = shape;
		this.radius = radius;
	}

	// switch로 사각형, 원일때 면적 구함
	public double area() {
		switch (shape) {
			case CIRCLE -> {
				return Math.PI * (radius * radius);
			}
			case RECTANGLE -> {
				return length * width;
			}
		}
		throw new IllegalArgumentException("해당 모양이 없습니다.");
	}
}
