package com.example.effectivejava.section02.item11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PersonTest {

	static Person p1;
	static Person p2;

	@BeforeAll
	static void init() {
		p1 = new Person("김철수", 21, 65_000_000);
		p2 = new Person("김철수", 21, 65_000_000);
	}

	@Test
	public void hashCodeTest () throws Exception {
		Person p3 = new Person("김철수", 21, 65_000_001);

		Assertions.assertAll(
			() -> assertEquals(p1.hashCode(), p2.hashCode()),
			() -> assertNotEquals(p1.hashCode(), p3.hashCode())
		);
	}

}