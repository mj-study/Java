package com.example.effectivejava.section02.item01;

public class EDIYAMenu {

	String menu;
	int price;
	String additionalInfo;

	public static EDIYAMenu from(EDIYAMenuDto menu) {
		EDIYAMenu ediyaMenu = new EDIYAMenu();
		ediyaMenu.setMenu(menu.menu());
		ediyaMenu.setPrice(menu.price());
		ediyaMenu.setAdditionalInfo(menu.additionalInfo());
		return ediyaMenu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return "EDIYAMenu{" +
			"menu='" + menu + '\'' +
			", price=" + price +
			", additionalInfo='" + additionalInfo + '\'' +
			'}';
	}
}
