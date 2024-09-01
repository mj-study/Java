package com.example.effectivejava.section04.item17;

import java.util.Date;

public final class Study {
	private final Date time;
	private final String name;

	public Study(Date time, String name) {
		// 가변 객체를 참조하는 Date는 방어적 복사를 이용하여 반환하자
		this.time = new Date(time.getTime());
		this.name = name;
	}

	public Date getTime() { return time; }
	public String getName() { return name; }
}
