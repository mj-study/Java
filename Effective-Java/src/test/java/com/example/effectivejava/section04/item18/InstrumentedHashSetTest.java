package com.example.effectivejava.section04.item18;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstrumentedHashSetTest {

	@Test
	void addAll_test () throws Exception {
	    //given
		InstrumentedHashSet_V1<String> set = new InstrumentedHashSet_V1<>();

	    //when
		set.addAll(List.of("running", "swimming", "cycling"));

		//then
		// 기대하는 값은 3이지만 메서드 중복 호출로 인해 6
		Assertions.assertEquals(6, set.getAddCount());
	}

}