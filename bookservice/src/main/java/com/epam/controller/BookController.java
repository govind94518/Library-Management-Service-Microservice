package com.epam.controller;
import java.util.List;
import com.epam.dto.BookDto;
import com.epam.exceptions.BookAlreadyExistsException;
import com.epam.exceptions.BookNotFoundException;
import com.epam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto> getBookById(@PathVariable int bookId) throws BookNotFoundException {
		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Boolean> saveBook(@RequestBody BookDto bookDto) throws BookAlreadyExistsException {
		bookService.saveBook(bookDto);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<Boolean> updateBook(@PathVariable int bookId, @RequestBody BookDto bookDto)
			throws BookNotFoundException {
		bookService.updateBook(bookId, bookDto);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable int bookId) throws BookNotFoundException {
		bookService.deleteBook(bookId);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}
}
