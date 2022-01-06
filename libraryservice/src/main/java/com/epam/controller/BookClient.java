package com.epam.controller;
import com.epam.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name="book-service" )
public interface BookClient {
    @GetMapping("/books/")
    List<BookDto> getAllBookFromBookService();
    @GetMapping("/books/{bookId}")
    BookDto getBookByIdFromBookService(@PathVariable String bookId);
    @PostMapping("/books/")
    Boolean saveBookFromBookService(@RequestBody BookDto bookDto);
    @PutMapping("/books/{bookId}")
    Boolean updateBookByIdFormBookService(@PathVariable  int bookId,@RequestBody  BookDto bookDto);
    @DeleteMapping("/books/{bookId}")
    Boolean deleteBookByIdFormBookService(@PathVariable int bookId);
}
