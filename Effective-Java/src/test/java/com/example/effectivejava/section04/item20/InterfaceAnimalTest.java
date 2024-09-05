package com.example.effectivejava.section04.item20;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InterfaceAnimalTest {

	@Test
	void animal_test () throws Exception {
	    //given
		InterfaceAnimal animal = new InterfaceAnimal();
		InterfaceAnimal.Dog dog = animal.new Dog();
		InterfaceAnimal.Bird bird = animal.new Bird();

	    //when
		dog.makeSound();
		bird.makeSound();
		bird.fly();

	    //then
	}
}