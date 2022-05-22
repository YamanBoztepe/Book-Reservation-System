package com.yeditepe.bookrez.model;

import java.io.Serializable;

public class Book implements Serializable {
	private Integer id;
	private String book_name;
	private String author_name;
	private String publisher;
	private String category;
	private Integer quantity;
	
	public Book() {
		super();
	}
	
	

	public Book(Integer id, String book_name, String author_name, String publisher, String category, Integer quantity) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.author_name = author_name;
		this.publisher = publisher;
		this.category = category;
		this.quantity = quantity;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
