package com.example.effectivejava.section02.item05;

public class Main {
	public static void main(String[] args) {
		Food apple = Food.create(Apple::new);

		Food banana = Food.create(() -> new Banana());

		apple.eat();
		banana.eat();
	}
}
