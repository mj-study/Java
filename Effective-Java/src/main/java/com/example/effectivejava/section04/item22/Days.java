package com.example.effectivejava.section04.item22;

/**
 * 연관되어 있는 상수 제공시 enum 타입을 이용
 */
public enum Days {
	SUN("일요일", 0),
	MON("월요일",1),
	TUE("화요일", 2),
	WED("수요일", 3),
	THU("목요일", 4),
	FRI("금요일", 5),
	SAT("토요일", 6),
	;

	private final String day;
	private final int dayCode;

	Days(String day, int dayCode) {
		this.day = day;
		this.dayCode = dayCode;
	}

	public String getDay() {
		return day;
	}

	public int getDayCode() {
		return dayCode;
	}
}
