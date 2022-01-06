package com.epam.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.epam.dto.BookDto;
import com.epam.entity.Book;
import com.epam.exceptions.BookAlreadyExistsException;
import com.epam.exceptions.BookNotFoundException;
import com.epam.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> allBooks = (List<Book>) bookRepository.findAll();
		List<BookDto> allBooksDto = new ArrayList<>();
		allBooks.forEach(book -> allBooksDto.add(modelMapper.map(book, BookDto.class)));
		return allBooksDto;
	}

	@Override
	public BookDto getBookById(int bookId) throws BookNotFoundException {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isEmpty()) {
			throw new BookNotFoundException("Book   Not Found");
		}
		return modelMapper.map(book.get(), BookDto.class);
	}

	@Override
	public void saveBook(BookDto bookDto) throws BookAlreadyExistsException {
		if (bookRepository.existsById(bookDto.getBookId())) {
			throw new BookAlreadyExistsException("Book Already Exist");
		}
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
	}

	@Override
	public void updateBook(int bookId, BookDto bookDto) throws BookNotFoundException {
		BookDto currentBookDto = getBookById(bookId);
		currentBookDto.setName(bookDto.getName());
		currentBookDto.setAuthor(bookDto.getAuthor());
		currentBookDto.setPublisher(bookDto.getPublisher());
		Book book = modelMapper.map(currentBookDto, Book.class);
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(int bookId) throws BookNotFoundException {
		BookDto currentBookDto = getBookById(bookId);
		Book book = modelMapper.map(currentBookDto, Book.class);
		bookRepository.delete(book);
	}

}
