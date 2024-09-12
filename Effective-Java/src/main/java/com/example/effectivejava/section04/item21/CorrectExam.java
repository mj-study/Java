package com.example.effectivejava.section04.item21;

import java.io.PrintStream;

public class CorrectExam {
	private interface Resizable {
		void resize(int size);

		/**
		 * 각 구현체는 본인에게 적합한 최소 크기 값으로 구현해야한다.
		 */
		void resizeToMinimun();
	}

	private class Square implements Resizable {
		private int sideLength;

		@Override
		public void resize(int size) {
			sideLength = size;
			System.out.println("sideLength = " + sideLength);
		}

		@Override
		public void resizeToMinimun() {
			resize(2);
			System.out.println("사각형 최소 크기 지정");
		}
	}

	private class Circle implements Resizable {
		private int radius;

		@Override
		public void resize(int size) {
			radius = size;
			System.out.println("radius = " + radius);
		}

		@Override
		public void resizeToMinimun() {
			resize(5);
			System.out.println("원 최소 크기 지정");
		}
	}
}
