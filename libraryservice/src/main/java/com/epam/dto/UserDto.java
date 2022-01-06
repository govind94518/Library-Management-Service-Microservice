package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String email;
    private String name;
    public  UserDto(){}
}
