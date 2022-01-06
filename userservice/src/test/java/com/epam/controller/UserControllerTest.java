package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.dto.UserDto;
import com.epam.exceptions.UserAlreadyExistsException;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.UserService;
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
public class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
      void testGetAllUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/ram")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
  void getUserByUserNameShouldReturnNotFoundStatusIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).getUserByUserName(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/ram")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
     void testSaveUser() throws Exception {
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testSaveUserIfUserAlreadyExist() throws Exception {
        doThrow(UserAlreadyExistsException.class).when(userService).saveUser(any());
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
     void testUpdateUserIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).updateUser(anyString() ,any());
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/siddharth")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
     void testUpdateUser() throws Exception {
        UserDto user = new UserDto("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/siddharth")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testDeleteUserIfUserNotExist() throws Exception {
        doThrow(UserNotFoundException.class).when(userService).deleteUser(anyString());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/siddharth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/siddharth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
