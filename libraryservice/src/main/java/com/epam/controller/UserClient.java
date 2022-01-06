package com.epam.controller;
import com.epam.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name="user-service" )
public interface UserClient {
    @GetMapping("/users/")
    List<UserDto> getAllUsersFromUserService();
    @GetMapping("/users/{userName}")
    UserDto getUserNameFromUserService(@PathVariable String userName);
    @PostMapping("/users/")
    void saveUserInUserService(@RequestBody UserDto userDto);
    @PutMapping("/users/{userName}")
    Boolean updateUserByUserName(@PathVariable String userName, @RequestBody UserDto userDto);
    @DeleteMapping("/users/{userName}")
    Boolean deleteUser(@PathVariable String userName);

}
