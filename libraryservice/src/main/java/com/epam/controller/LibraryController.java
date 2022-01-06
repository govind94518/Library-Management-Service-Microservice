package com.epam.controller;
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookLimitExceededException;
import com.epam.exception.UserException;
import com.epam.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    LibraryService libraryService;
    @Autowired
    UserClient userClient;

    @PostMapping("/users/{userName}/books/{bookId}")
    public   ResponseEntity<Boolean>  issueBook(@PathVariable  String userName,@PathVariable Integer bookId) throws UserException, BookAlreadyIssuedException, BookLimitExceededException {
        return new ResponseEntity<>(libraryService.issueBookToUser(userName,bookId), HttpStatus.CREATED);
    }
    @DeleteMapping("/users/{userName}")
    public ResponseEntity<Boolean> deleteUserByUserName(@PathVariable String userName) throws UserException {
       libraryService.deleteUserByUserName(userName);
        return  new ResponseEntity<>(userClient.deleteUser(userName),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userName}/books/{bookId}")
    public   ResponseEntity<Boolean>  deleteBookToUser(@PathVariable  String userName,@PathVariable int bookId)throws UserException {
        return new ResponseEntity<>(libraryService.deleteBookToUser(userName,bookId), HttpStatus.NO_CONTENT);
    }
}
