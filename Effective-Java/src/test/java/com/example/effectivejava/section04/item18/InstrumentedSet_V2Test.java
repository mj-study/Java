package com.example.effectivejava.section04.item18;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstrumentedSet_V2Test {

	@Test
	void useComposition_addAll () throws Exception {
	    //given
		InstrumentedSet_V2<String> set = new InstrumentedSet_V2<>(new HashSet<>());

	    //when
		set.addAll(List.of("running", "swimming", "cycling"));

	    //then
		Assertions.assertEquals(3,set.getAddCount());
	}

}