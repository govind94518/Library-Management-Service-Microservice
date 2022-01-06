package com.epam.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import com.epam.dto.BookDto;
import com.epam.entity.Book;
import com.epam.exceptions.BookAlreadyExistsException;
import com.epam.exceptions.BookNotFoundException;
import com.epam.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl bookService;
	@Mock
	BookRepository bookRepository;
	@Mock
	ModelMapper modelMapper;
	BookDto bookDto;
	Book book;

	@BeforeEach
	public void setUp() {
		bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
		book = new Book(1, "ram", "Ayodhya", "RamTiwari");
	}

	@Test
	void testGetAllBooks() {
		when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
		when(bookRepository.findAll()).thenReturn(List.of(book, book));
		assertEquals(List.of(bookDto, bookDto), bookService.getAllBooks());
	}

	@Test
	void testGetBookById() throws BookNotFoundException {
		when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
		when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.of(book));
		assertEquals(bookDto, bookService.getBookById(1));
	}

	@Test
	  void getBookShouldThrowExceptionIfBookNotExist() {
		when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());
		assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
	}

	@Test
	void testSaveBook() {
		when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
		assertDoesNotThrow(() -> bookService.saveBook(bookDto));
	}

	@Test
	  void saveBookShouldThrowExceptionIfBookAlreadyExist() {
		when(bookRepository.existsById(anyInt())).thenReturn(true);
		assertThrows(BookAlreadyExistsException.class, () -> bookService.saveBook(bookDto));
	}

	@Test
	void testUpdateBook() {
		when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
		when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
		assertDoesNotThrow(() -> bookService.updateBook(bookDto.getBookId(), bookDto));
	}

	@Test
	void updateBookShouldThrowExceptionIfBookNotExist() {
		when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookDto.getBookId(), bookDto));
	}

	@Test
	void testDeleteBook() {
		when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
		assertDoesNotThrow(() -> bookService.deleteBook(book.getBookId()));
	}
	
	@Test
    void deleteBookShouldThrowExceptionIfBookNotExist(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class ,()->bookService.deleteBook(book.getBookId()));
    }
}
