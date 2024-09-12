package com.example.effectivejava.section04.item22;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DaysTest {
	@Test
	void day_constant_test () {
	    //given
		String day = Days.MON.getDay();
		int dayCode = Days.MON.getDayCode();

	    //then
		assertEquals("월요일", day);
		assertEquals(1, dayCode);
	}

}