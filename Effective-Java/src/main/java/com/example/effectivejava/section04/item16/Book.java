package com.example.effectivejava.section04.item16;

/**
 * 필드는 private, 외부 노출x
 * 접근자 메서드를 통해 필드 제공
 */
public class Book {
	private String author;
	private int price;

	public Book(String author, int price) {
		this.author = author;
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public int getPrice() {
		return price;
	}

}
