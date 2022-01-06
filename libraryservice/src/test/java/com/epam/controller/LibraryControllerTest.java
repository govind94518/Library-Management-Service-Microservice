package com.epam.controller;
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookLimitExceededException;
import com.epam.exception.UserException;
import com.epam.service.LibraryService;
import org.junit.runner.RunWith;;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
 class LibraryControllerTest {
   @MockBean
   UserClient userClient;
    @Autowired
    LibraryController  libraryController;
    @Mock
    LibraryService libraryService;
    @Test
    void testIssueBook() throws UserException, BookAlreadyIssuedException, BookLimitExceededException {
      ///  UserLibraryDto userLibraryDto = new UserLibraryDto();
          ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.CREATED);
        when(  libraryService.issueBookToUser(anyString(),anyInt())).thenReturn(true);
         ResponseEntity<Boolean> actual = libraryController.issueBook("ram",1);
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }
        @Test
    void testDeleteUserByUserName() throws Exception {
        ResponseEntity<Boolean> expected1 = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
           when(userClient.deleteUser(anyString())).thenReturn(true);
        assertEquals(HttpStatus.ACCEPTED, libraryController.deleteUserByUserName("A12").getStatusCode());
    }

//    @Test
//    void testDeleteBookToUser() throws Exception {
//        ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.ACCEPTED);
//         when(libraryService.deleteBookToUser(anyString(),anyInt())).thenReturn(true);
//        ResponseEntity<Boolean> actual = libraryController.deleteBookToUser("A12",1);
//        assertEquals(expected.getStatusCode(), actual.getStatusCode());
//    }
}
