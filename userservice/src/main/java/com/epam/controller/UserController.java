package com.epam.controller;
import java.util.List;
import com.epam.exceptions.UserAlreadyExistsException;
import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException {
    	userService.saveUser(userDto);
        return new ResponseEntity<>(true,HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Boolean> updateUser(@PathVariable String userName ,@RequestBody UserDto userDto) throws  UserNotFoundException {
        userService.updateUser(userName,userDto);
        return new ResponseEntity<>(true,HttpStatus.CREATED);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userName) throws  UserNotFoundException {
        userService.deleteUser(userName);
        return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
    }

}

