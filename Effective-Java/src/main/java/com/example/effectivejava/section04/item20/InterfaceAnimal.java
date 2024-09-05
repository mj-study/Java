package com.example.effectivejava.section04.item20;

public class InterfaceAnimal {
	interface Animal {
		String getName();
		void makeSound();
		default void move() {
			System.out.println(getName() + "이동 중입니다");
		};
	}

	interface Flyable{
		void fly();
	}

	class Dog implements Animal{
		private String name;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void makeSound() {
			System.out.println("왈왈");
		}
	}

	/**
	 * 인터페이스는 다중 구현이 가능하기에 원하는 인터페이스를 추가로 구현하면 됨
	 */
	class Bird implements Animal, Flyable{
		private String name;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void makeSound() {
			System.out.println("조잘조잘");
		}

		@Override
		public void fly() {
			System.out.println("날아 다닙니다.");
		}
	}
}
