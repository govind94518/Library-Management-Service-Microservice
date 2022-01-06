package com.epam.controller;
import com.epam.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryBookController {
    @Autowired
    BookClient bookClient;

    @GetMapping("/books")
    public  ResponseEntity<List<BookDto>> getAllBooks() {
        return   new ResponseEntity<>(bookClient.getAllBookFromBookService(), HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String bookId) {
        return   new ResponseEntity<>(bookClient.getBookByIdFromBookService(bookId), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Boolean> saveBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookClient.saveBookFromBookService(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Boolean> updateBookById(@PathVariable int bookId, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookClient.updateBookByIdFormBookService(bookId,bookDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Boolean> deleteBookById(@PathVariable int bookId) {
        return new ResponseEntity<>(bookClient.deleteBookByIdFormBookService(bookId), HttpStatus.NO_CONTENT);
    }

}
