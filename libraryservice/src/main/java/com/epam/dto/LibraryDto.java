package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryDto {
    private int  id;
    private int  bookId;
    private  String userName;
    public LibraryDto(){}
}
