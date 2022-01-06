package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

	private int bookId;
	private String name;
	private String publisher;
	private String author;
	public  BookDto(){}
}
