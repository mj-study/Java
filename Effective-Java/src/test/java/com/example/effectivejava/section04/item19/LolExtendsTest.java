package com.example.effectivejava.section04.item19;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LolExtendsTest {

	@Test
	void lol_character_test () throws Exception {
	    //given
		LolExtends lol = new LolExtends();

		LolExtends.Mundo mundo = lol.new Mundo();
		LolExtends.Timo timo = lol.new Timo();
	    //when
		timo.make();
		System.out.println("----------");
		mundo.make();

	    //then
	}
}