package com.example.effectivejava.section02.item02;

public class Lecture {

	private final String lectureName;
	private final int price;
	private final String teacher;
	private final String info;
	private final int time;

	public static class Builder {
		// 필수 매개변수
		private final String lectureName;
		private final int price;
		private final String teacher;

		// 선택 매개변수
		private String info;
		private int time;

		public Builder(String lectureName, int price, String teacher) {
			if (lectureName == null || lectureName.isEmpty()) {
				throw new IllegalArgumentException("강의명은 필수입니다");
			}
			if (price <= 0) {
				throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
			}
			if (teacher == null || teacher.isEmpty()) {
				throw new IllegalArgumentException("강사명은 필수입니다.");
			}

			this.lectureName = lectureName;
			this.price = price;
			this.teacher = teacher;
		}

		public Builder info(String info) {
			if (info == null) {
				throw new IllegalArgumentException("정보를 null로 줄 수는 없습니다.");
			}
    		this.info = info;
    		return this;
    	}

		public Builder time(int time) {
			if (time <= 0) {
				throw new IllegalArgumentException("시간이 0보다 작을 수 없습니다.");
			}
			this.time = time;
			return this;
		}

		public Lecture build() {
			return new Lecture(this);
		}

	}

	private Lecture(Builder builder) {
		lectureName = builder.lectureName;
		price = builder.price;
		teacher = builder.teacher;
		info = builder.info;
		time = builder.time;
	}

	@Override
	public String toString() {
		return "Lecture{" +
			"lectureName='" + lectureName + '\'' +
			", price=" + price +
			", teacher='" + teacher + '\'' +
			", info='" + info + '\'' +
			", time=" + time +
			'}';
	}
}
