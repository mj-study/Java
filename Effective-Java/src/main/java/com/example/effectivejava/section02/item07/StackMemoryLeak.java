package com.example.effectivejava.section02.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackMemoryLeak {
	private Object[] elemetns;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public StackMemoryLeak() {
		elemetns = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object o) {
		ensureCapacity();
		elemetns[size++] = o;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		/*
		* 메모리 누수 지점
		* 해당 객체를 꺼낸 뒤 남아 있는 원소는 자동으로 G.C 되지 않음
		* 별도로 처리해주어야함
		* */
		// return elemetns[--size];

		/*
		* 메모리 누수 개선
		* 꺼낸 뒤 꺼낸 원소 index에 null로 객체 참조를 해줌
		* */
		Object result = elemetns[--size];
		elemetns[size] = null;
		return result;
	}

	private void ensureCapacity() {
		if (elemetns.length == size) {
			elemetns = Arrays.copyOf(elemetns, elemetns.length * 2 + 1);
		}
	}
}
