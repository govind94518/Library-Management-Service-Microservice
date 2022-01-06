package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.dto.BookDto;
import com.epam.exceptions.BookAlreadyExistsException;
import com.epam.exceptions.BookNotFoundException;
import com.epam.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@MockBean
	BookService bookService;
	@Autowired
	MockMvc mockMvc;

	@Test
	void testAllBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/books/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testBookById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testBookByIdIfBookNotExist() throws Exception {
		doThrow(BookNotFoundException.class).when(bookService).getBookById(anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/books/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testSaveBook() throws Exception {
		BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
		mockMvc.perform(MockMvcRequestBuilders.post("/books/").content(asJsonString(bookDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testSaveBookIfBookAlreadyExist() throws Exception {
		doThrow(BookAlreadyExistsException.class).when(bookService).saveBook(any());
		BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
		mockMvc.perform(MockMvcRequestBuilders.post("/books/").content(asJsonString(bookDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAlreadyReported());
	}

	@Test
	void testUpdateBook() throws Exception {
		BookDto bookDto = new BookDto(1, "ram", "Ayodhya", "RamTiwari");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
    public void testUpdateBookIfBookNotExist() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).updateBook(anyInt() ,any());
        BookDto bookDto = new BookDto(1,"","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/1")
                        .content(asJsonString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@Test
	void testDeleteBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
                .delete("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
	@Test
    public void testDeleteBookIfBookNotExist() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).deleteBook(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
