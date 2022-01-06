package com.epam.dto;
import lombok.Data;
@Data
public class BookDto {
    private int bookId;
    private String name;
    private String publisher;
    private String author;
    public  BookDto(){}

    public BookDto(int bookId, String name, String publisher, String author) {
        this.bookId = bookId;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }
}
