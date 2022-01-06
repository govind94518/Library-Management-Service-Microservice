package com.epam.service;
import java.util.List;
import com.epam.exceptions.UserAlreadyExistsException;
import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;


public interface UserService {
	
	 List<UserDto> getAllUsers();

     UserDto getUserByUserName(String userName) throws UserNotFoundException;

     void saveUser(UserDto user) throws UserAlreadyExistsException;

     void updateUser(String userName, UserDto user) throws UserNotFoundException;

     void deleteUser(String userName) throws UserNotFoundException;

}
