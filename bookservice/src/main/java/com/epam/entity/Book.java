package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "BOOK_TABLE")
public class Book {
	@Id
	private int bookId;
	private String name;
	private String publisher;
	private String author;
	public Book(){}
}
