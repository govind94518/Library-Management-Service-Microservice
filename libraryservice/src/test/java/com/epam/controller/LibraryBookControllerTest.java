package com.epam.controller;
import com.epam.dto.BookDto;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
class LibraryBookControllerTest {
    @Autowired
    LibraryBookController libraryBookController;
    @MockBean
    BookClient bookClient;

    @Test
    void testGetAllBooks() throws Exception {
        BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
        ResponseEntity<List<BookDto>> expected = new ResponseEntity<>(List.of(bookDto, bookDto), HttpStatus.OK);
        when(bookClient.getAllBookFromBookService()).thenReturn(List.of(bookDto, bookDto));
        ResponseEntity<List<BookDto>> actual = libraryBookController.getAllBooks();
        assertEquals(expected.getStatusCode(), actual.getStatusCode());

    }
@Test
void testGetBookById() throws Exception {
    BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
   ResponseEntity<BookDto> expected = new ResponseEntity<>(bookDto, HttpStatus.OK);
    when(bookClient.getBookByIdFromBookService(anyString())).thenReturn(bookDto);
   ResponseEntity<BookDto> actual = libraryBookController.getBookById("1");
   assertEquals(expected.getStatusCode(), actual.getStatusCode());
}

   @Test
   void testSaveBook() throws Exception {
      ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.CREATED);
       BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
       when(bookClient.saveBookFromBookService(bookDto)).thenReturn(true);
      ResponseEntity<Boolean> actual = libraryBookController.saveBook(bookDto);
      assertEquals(expected.getStatusCode(), actual.getStatusCode());
   }
    @Test
    void testUpdateBookById() throws Exception {
        ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.CREATED);
        BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
        when(bookClient.updateBookByIdFormBookService(0,bookDto)).thenReturn(true);
        ResponseEntity<Boolean> actual = libraryBookController.updateBookById(0, bookDto);
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }
    @Test
    void testDeleteBookById() throws Exception {
        ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        when(bookClient.deleteBookByIdFormBookService(1)).thenReturn(true);
        ResponseEntity<Boolean> actual = libraryBookController.deleteBookById(1);
        assertEquals(expected.getStatusCode(), actual.getStatusCode());

    }


}
