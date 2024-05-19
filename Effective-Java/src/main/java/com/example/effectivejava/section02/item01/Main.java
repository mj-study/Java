package com.example.effectivejava.section02.item01;

public class Main {

	public static void main(String[] args) {
		EDIYAMenuDto dto = new EDIYAMenuDto("아이스 아메리카노", 3200, "카페인 함유");

		EDIYAMenu ediyaMenu = EDIYAMenu.from(dto);

		System.out.println("ediyaMenu = " + ediyaMenu);
	}
}
