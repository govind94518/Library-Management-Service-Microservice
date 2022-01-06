package com.epam.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.exceptions.UserAlreadyExistsException;
import com.epam.exceptions.UserNotFoundException;
import com.epam.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@SpringBootTest
  class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userService;
	@Mock
	UserRepository userRepository;
	@Mock
	ModelMapper modelMapper;
	UserDto userDto;
	User user;

	@BeforeEach
	public void setUp() {
		userDto = new UserDto("", "", "");
		user = new User("", "", "");
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
	}

	@Test
	  void testGetAllUser() {
		when(userRepository.findAll()).thenReturn(List.of(user, user));
		assertEquals(List.of(userDto, userDto), userService.getAllUsers());
	}

	@Test
	  void testGetUser() throws UserNotFoundException {
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		assertEquals(userDto, userService.getUserByUserName(""));
	}

	@Test
	   void testGetUserIfUserNotExist() {
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUserByUserName(""));
	}

	@Test
	 void testSaveUser() {
		assertDoesNotThrow(() -> userService.saveUser(userDto));
	}

	@Test
	  void saveUserShouldThrowExceptionIfUserAlreadyExist() {
		when(userRepository.existsById(anyString())).thenReturn(true);
		assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(userDto));
	}

	@Test
	  void updateUserShouldThrowExceptionIfUserNotExist() {
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class,
				() -> userService.updateUser(userDto.getUserName(), userDto));
	}

	@Test
  void updateUserShouldUpdateUserIfExist() {
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		assertDoesNotThrow(() -> userService.updateUser(userDto.getUserName(), userDto));
	}

	@Test
  void deleteUserShouldThrowExceptionIfUserNotExist() {
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.deleteUser(user.getUserName()));
	}

	@Test
    void deleteUserShouldDeleteUserIfExist() {
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		assertDoesNotThrow(() -> userService.deleteUser(user.getUserName()));
	}

}
