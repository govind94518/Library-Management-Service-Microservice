//package com.epam.service;
//
//import com.epam.dto.BookDto;
//import com.epam.dto.LibraryDto;
//import com.epam.dto.UserDto;
//import com.epam.entity.Library;
//import com.epam.exception.UserException;
//import com.epam.repository.LibraryRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//@WebMvcTest(LibraryServiceImpl.class)
//@RunWith(SpringRunner.class)
//public class LibraryServiceTest {
//    @InjectMocks
//    LibraryServiceImpl libraryService;
//    @Mock
//    RestTemplate restTemplate;
//    @MockBean
//    LibraryRepository libraryRepository;
//    @Mock
//    ModelMapper modelMapper;
//    LibraryDto libraryDto;
//    Library library;
//    BookDto bookDto;
//    UserDto userDto;
//
//    @BeforeEach
//    public void setUp() {
//        libraryDto = new LibraryDto(1, 101, "RamTiwari");
//        library = new Library(1, 101, "RamTiwari");
//        bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
//        userDto = new UserDto("", "", "");
//    }
////    @Test
////      void testDeleteUserByUserName() throws UserException {
////        List list = new LinkedList();
////        List<Library> myLibrary= (List<Library>) mock(Library.class);
////        doNothing().when(libraryService).deleteUserByUserName(isA(String.class));
////
//////        when(libraryRepository.findByUserName(anyString())).thenReturn(List.of(library,library));
//////
//////        doNothing().when(libraryRepository.deleteAll(anyList()));
//////        assertDoesNotThrow(()-> libraryService.deleteUserByUserName("ram"));
////    }
//    @Test
//    void testIssueBookToUser() throws UserException {
//        when(libraryRepository.findByUserNameAndBookId(anyString(),anyInt())).thenReturn(Optional.of(library));
//        assertThrows(UserException.class,()->libraryService.issueBookToUser("ram",1));
//
//    }
////        @Test
////    void testDeleteBookToUser() throws UserException {
////        when(libraryRepository.findByUserNameAndBookId(anyString(),anyInt()).isEmpty()).thenReturn(true);
////        assertThrows(UserException.class,()->libraryService.deleteBookToUser("ram",1));
////
////    }
//
////    @Test
////    void testDeleteBookToUser() throws UserException {
////        when(libraryRepository.findByUserNameAndBookId(anyString(), anyInt()).isEmpty()).thenReturn(true);
////        assertThrows(UserException.class, () -> libraryService.deleteBookToUser("ram", 1));
////    }
//
////    @Test
////    void testDeleteBookToUserThrowsException() throws UserException {
////        when(libraryRepository.findByUserNameAndBookId(anyString(), anyInt()).isEmpty()).thenReturn(java.util.Optional.of(library));
////   //     when(libraryRepository.findByUserNameAndBookId(anyString(), anyInt()).isEmpty()).thenReturn(false);
////        assertDoesNotThrow( () -> libraryService.deleteBookToUser("ram", 1));
////    }
//
////    @Test
////    void testGetUserName() throws UserException {
////        ResponseEntity<UserDto> expected = new ResponseEntity<>(userDto, HttpStatus.OK);
////        when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<BookDto>>any())).thenReturn(bookDto);
////        when(modelMapper.map(library, LibraryDto.class)).thenReturn(libraryDto);
////        when(libraryRepository.findByUserName(anyString())).thenReturn(List.of(library, library));
////        when(restTemplate.exchange(
////                ArgumentMatchers.anyString(),
////                ArgumentMatchers.eq(HttpMethod.POST),
////                ArgumentMatchers.any(HttpEntity.class),
////                ArgumentMatchers.<Class<UserDto>>any()
////        )).thenReturn(expected);
////        assertDoesNotThrow(() -> libraryService.deleteBookToUser("ram", 1));
////    }
//
//
//}
