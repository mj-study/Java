package com.example.effectivejava.section02.item07;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakTest {
	public static void main(String[] args) throws InterruptedException {

		HashMap<Integer, String> hashMap = new HashMap<>();

		Integer hmKey1 = 1;
		Integer hmKey2 = 2;
		hashMap.put(hmKey1, "abc");
		hashMap.put(hmKey2, "def");

		hmKey1 = null;

		WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();

		Integer whKey1 = 3;
		Integer whKey2 = 4;
		weakHashMap.put(whKey1, "abc");
		weakHashMap.put(whKey2, "def");

		whKey1 = null;

		// gc 호출을 할 뿐, 개발자가 직접 gc를 할 순 없음
		// 다음 GC때 weakHashMap에 key로 등록한 원소는 GC 대상이 됨
		System.gc();

		System.out.println("hashMap 사이즈 : " + hashMap.size());
		System.out.println("weakMap 사이즈 : " + weakHashMap.size());

		hashMap.entrySet()
			.forEach(k -> System.out.println("hashMap k = " + k));

		weakHashMap.entrySet()
			.forEach(k -> System.out.println("weakMap k = " + k));
	}

}
