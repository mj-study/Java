package com.example.effectivejava.section04.item21;

public class FaultExam {
	private interface Resizable {
		void resize(int size);

		// 크기를 최소화하도록 정의
		/*
		Resizable 인터페이스에서 선언한 디폴트 메서드는 모든 도형에 대해 크기를 1로 설정하는 동작이
		각 도형에 적합한지 보장하지 않음
		 */
		default void resizeToMinimun() {
			resize(1);
		}
	}

	private class Square implements Resizable {
		private int sideLength;

		@Override
		public void resize(int size) {
			sideLength = size;
			System.out.println("sideLength = " + sideLength);
		}
	}

	private class Circle implements Resizable {
		private int radius;

		@Override
		public void resize(int size) {
			radius = size;
			System.out.println("radius = " + radius);
		}
	}
}

