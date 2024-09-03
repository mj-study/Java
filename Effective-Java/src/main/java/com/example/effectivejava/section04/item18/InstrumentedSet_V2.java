package com.example.effectivejava.section04.item18;

import java.util.Collection;
import java.util.Set;

public class InstrumentedSet_V2<E> {
	private final Set<E> set;
	private int addCount = 0;

	public InstrumentedSet_V2(Set<E> set) {
		this.set = set;
	}

	public boolean add(E e) {
		addCount++;
		return set.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return set.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}
