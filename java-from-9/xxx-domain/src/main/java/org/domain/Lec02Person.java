package org.domain;

public class Lec02Person {
	private int weight;

	public String name;

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "weight : " + weight;
	}
}
