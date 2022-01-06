package com.epam.service;

import java.util.List;

import com.epam.dto.BookDto;
import com.epam.exceptions.BookAlreadyExistsException;
import com.epam.exceptions.BookNotFoundException;

public interface BookService {

	List<BookDto> getAllBooks();

	BookDto getBookById(int bookId) throws BookNotFoundException;

	void saveBook(BookDto bookDto) throws BookAlreadyExistsException;

	void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException;

	void deleteBook(int bookId) throws BookNotFoundException;

}
