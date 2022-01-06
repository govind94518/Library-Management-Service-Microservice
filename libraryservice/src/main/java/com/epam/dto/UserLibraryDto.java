package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class UserLibraryDto {
    private String userName;
    private String email;
    private String name;
    private List<BookDto> bookDtoList = new ArrayList<>();
    public UserLibraryDto() {
    }
}
