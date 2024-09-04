package com.example.effectivejava.section04.item19;

public abstract	class LolCharacter {

	/**
	 * 기본적인 롤 캐릭터 패시브, 특성, 스킬 생성
	 */
	public final void make() {
		System.out.println("== 캐릭터 생성중 ==");
		passive();
		motion();
	}

	/**
	 * 롤 캐릭터 별 패시브가 다르기 때문에 하위 클래스는 반드시 재정의
	 */
	protected abstract void passive();

	/**
	 * 필요에 따라 재정의
	 * ex) 이동방법이 다른 캐릭터가 있는 경우 재정의할 것
	 */
	protected void motion() {
		System.out.println("캐릭터가 이동중입니다");
	}
}
