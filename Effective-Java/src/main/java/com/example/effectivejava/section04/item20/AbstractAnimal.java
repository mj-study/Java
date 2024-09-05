package com.example.effectivejava.section04.item20;

/**
 * Animal 클래스를 상속받는 클래스들에 새로운 기능을 추가하려면 직접 하위 클래스에 기능을 넣어야함
 * ex) 날아다니는 메서드를 추가하려고할때 하위클래스에서 직접 정의해야함 -> 확장 불편
 */
public class AbstractAnimal {
	abstract class Animal {
		String name;

		public Animal(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public abstract void makeSound();

		public void move() {
			System.out.println(name +  " 움직이는 중");
		}
	}

	class Dog extends Animal {
		public Dog(String name) {
			super(name);
		}

		@Override
		public void makeSound() {
			System.out.println(getName() + " 짖는중");
		}
	}

	class Cat extends Animal {

		public Cat(String name) {
			super(name);
		}

		@Override
		public void makeSound() {
			System.out.println(getName() + " 우는 중");
		}
	}
}

