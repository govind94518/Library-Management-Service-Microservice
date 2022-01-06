package com.epam.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.epam.exceptions.UserAlreadyExistsException;
import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.exceptions.UserNotFoundException;
import com.epam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = (List<User>) userRepository.findAll();
        List<UserDto> allUserDto = new ArrayList<>();
        allUsers.forEach(user -> allUserDto.add(modelMapper.map(user , UserDto.class)));
        return allUserDto;
    }

    @Override
    public UserDto getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userName);
        if(user.isEmpty()){
            throw new UserNotFoundException(" userName does not exist");
        }
        return modelMapper.map(user.get() , UserDto.class);
    }

    @Override
    public void saveUser(UserDto userDto) throws UserAlreadyExistsException {
        User user = modelMapper.map(userDto ,User.class );
        if(userRepository.existsById(user.getUserName())){
            throw new UserAlreadyExistsException(" Already Exist ");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(String userName, UserDto userDto) throws UserNotFoundException {
        UserDto user1 = getUserByUserName(userName);
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        User user = modelMapper.map(user1 , User.class);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) throws UserNotFoundException {
        UserDto userDto = getUserByUserName(userName);
        User user = modelMapper.map(userDto , User.class);
        userRepository.delete(user);
    }
}
