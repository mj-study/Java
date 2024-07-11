package com.example.effectivejava.section02.item12;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameItemTest {

	@Test
	void toStringPrint () throws Exception {
		GameItem glove = new GameItem("장갑", 1500, 1415);
		System.out.println(glove);
	}

}