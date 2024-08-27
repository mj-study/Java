package com.example.effectivejava.section02.item14;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonComparableTest {

	@Test
	void comparableTest () throws Exception {
	    //given
		List<Person> personList = new ArrayList<>();
		Person p1 = new Person("김또깡", 21);
		Person p2 = new Person("아무개", 31);
		Person p3 = new Person("이지민", 24);
		personList.add(p2);
		personList.add(p1);
		personList.add(p3);

		//when
		Collections.sort(personList);

		List<Person> expectedList = Arrays.asList(p1, p3, p2);
		//then
		assertEquals(expectedList, personList);
		assertEquals(p1.getName(), personList.get(0).getName());
	}

}