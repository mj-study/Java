package com.example.effectivejava.section02.item05;

import java.util.function.Supplier;

public class Food {

	// 팩토리 메서드 패턴을 이용한 의존객체 주입
	static Food create(Supplier<? extends Food> foodFactory) {
		return foodFactory.get();
	}

	public void eat() {
		System.out.println("음식 먹기");
	}
}
