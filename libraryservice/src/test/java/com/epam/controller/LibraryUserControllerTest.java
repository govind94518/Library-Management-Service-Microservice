package com.epam.controller;
import com.epam.dto.UserDto;
import com.epam.service.LibraryService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
 class LibraryUserControllerTest {
    @MockBean
    UserClient userClient;
    @Autowired
    LibraryUserController libraryUserController;
    @Mock
    LibraryService libraryService;

    @Test
    void testGetAllUsers() {
        List<UserDto> userDto = new ArrayList<>();
        UserDto userDto1=new UserDto("R12","ram@gmail.com","Ram");
        ResponseEntity<List<UserDto>> expected = new ResponseEntity<>(userDto, HttpStatus.OK);
        when(userClient.getAllUsersFromUserService()).thenReturn(List.of(userDto1,userDto1));
        ResponseEntity<List<UserDto>> actual = libraryUserController.getAllUsers();
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

//    @Test
//    void testUserName() throws UserException {
//        UserLibraryDto userLibraryDto = new UserLibraryDto();
//        ResponseEntity<UserLibraryDto> expected = new ResponseEntity<>(userLibraryDto, HttpStatus.OK);
//      when(libraryService.getUserName(anyString())).thenReturn(userLibraryDto );
//        ResponseEntity<UserLibraryDto> actual = libraryUserController.getUserName("G12");
//       assertEquals(expected.getStatusCode(), actual.getStatusCode());
//      //  doThrow(UserException.class).when(libraryService.getUserName(anyString()));
//      //  assertThrows(UserException.class, (Executable) libraryUserController.getUserName("ram"));
//    }

    @Test
    void testSaveUser() {
        UserDto userDto=new UserDto("R12","ram@gmail.com","Ram");
        ResponseEntity<Boolean> actual = libraryUserController.saveUser(userDto);
        assertEquals( HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void testUpdateUser() {
        UserDto userDto=new UserDto("R12","ram@gmail.com","Ram");
        ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.OK);
        when(userClient.updateUserByUserName("ram",userDto)).thenReturn(true);
        ResponseEntity<Boolean> actual = libraryUserController.updateUserByUserName("ram", userDto);
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

}
