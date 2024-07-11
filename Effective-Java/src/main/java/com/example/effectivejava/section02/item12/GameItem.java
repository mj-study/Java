package com.example.effectivejava.section02.item12;

public class GameItem {
	String name;
	int itemLevel;
	int equipLevel; // 착용레벨

	public GameItem(String name, int itemLevel, int equipLevel) {
		this.name = name;
		this.itemLevel = itemLevel;
		this.equipLevel = equipLevel;
	}

	/**
	 * name은 아이템명
	 * itemLevel은 아이템 레벨
	 * equipLevel은 아이템 착용 가능 레벨
	 * @return
	 */
	@Override
	public String toString() {
		return "GameItem [name=" + name + ", itemLevel=" + itemLevel + ", equipLevel=" + equipLevel + "]";
	}
}
