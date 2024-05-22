package com.example.effectivejava.section02.item04;

public class Instance {

	// 인스턴스 방지용
	private Instance(){
		throw new AssertionError("인스턴스 생성 불가!");
	}
}
