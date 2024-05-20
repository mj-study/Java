package com.example.effectivejava.section02.item02;

public class BuilderTest {
	public static void main(String[] args) {
		success();
		fail();
	}

	public static void success() {
		Lecture lecture = new Lecture.Builder("Java", 90000, "민준")
			.info("명강사")
			.time(90)
			.build();

		System.out.println("lecture = " + lecture);
	}

	public static void fail() {
		Lecture lecture = new Lecture.Builder("Java", 90000, null)
			.info(null)
			.time(-1)
			.build();

		System.out.println("lecture = " + lecture);
	}
}
