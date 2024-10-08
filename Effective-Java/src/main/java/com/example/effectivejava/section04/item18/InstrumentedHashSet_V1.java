package com.example.effectivejava.section04.item18;

import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet_V1<E> extends HashSet<E> {
	private int addCount = 0;

	public InstrumentedHashSet_V1() {
	}

	public InstrumentedHashSet_V1(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}