package com.epam.controller;
import com.epam.dto.UserDto;
import com.epam.dto.UserLibraryDto;
import com.epam.exception.UserException;
import com.epam.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/library")
public class LibraryUserController {
    @Autowired
    UserClient userClient;
    @Autowired
    LibraryService libraryService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userClient.getAllUsersFromUserService(), HttpStatus.OK);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserLibraryDto> getUserName(@PathVariable String userName) throws UserException {
        return new ResponseEntity<>(libraryService.getUserName(userName), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDto userDto) {
        userClient.saveUserInUserService(userDto);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userName}")
    public ResponseEntity<Boolean> updateUserByUserName(@PathVariable String userName, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userClient.updateUserByUserName(userName, userDto), HttpStatus.CREATED);
    }

}
