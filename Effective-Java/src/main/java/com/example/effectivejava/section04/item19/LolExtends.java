package com.example.effectivejava.section04.item19;

public class LolExtends {

	class Timo extends LolCharacter {

		@Override
		protected void motion() {
			System.out.println("귀엽게 걸어요");
		}

		@Override
		protected void passive() {
			System.out.println("은신 효과 획득");
		}
	}

	class Mundo extends LolCharacter {

		@Override
		protected void passive() {
			System.out.println("체력 짱짱 많이 획득");
		}
	}
}
