package com.epam.service;
import com.epam.controller.BookClient;
import com.epam.controller.UserClient;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.dto.UserLibraryDto;
import com.epam.entity.Library;
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookLimitExceededException;
import com.epam.exception.UserException;
import com.epam.repository.LibraryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookClient bookClient;
    @Autowired
    UserClient userClient;

    public void deleteUserByUserName(String userName) throws UserException {
        libraryRepository.deleteAll(libraryRepository.findByUserName(userName));
    }

    public Boolean issueBookToUser(String userName, int bookId) throws BookAlreadyIssuedException ,BookLimitExceededException {
        if (libraryRepository.findByUserNameAndBookId(userName, bookId).isPresent()) {
            throw new BookAlreadyIssuedException("Book already issued to " + userName);
        }
        if (libraryRepository.findByUserName(userName).size() >= 3) {
            throw new BookLimitExceededException("Maximum number of Books you can issue only 3 for this user " + userName);
        }
        Library library = new Library();
        library.setUserName(userName);
        library.setBookId(bookId);
        libraryRepository.save(library);
        return true;
    }

    public Boolean deleteBookToUser(String userName, int bookId) throws UserException {
        if (libraryRepository.findByUserNameAndBookId(userName, bookId).isEmpty()) {
            throw new UserException("not found  bookId and userName and " + userName);
        }
        Optional<Library> library = libraryRepository.findByUserNameAndBookId(userName, bookId);
        library.ifPresent(value -> libraryRepository.delete(value));
        return true;
    }

    public UserLibraryDto getUserName(String userName) throws UserException {
        List<BookDto> bookDtoList = new ArrayList<>();
        List<Library> libraries = libraryRepository.findByUserName(userName);
        List<LibraryDto> libraryDto = new ArrayList<>();
        libraries.forEach(library -> libraryDto.add(modelMapper.map(library, LibraryDto.class)));
        libraryDto.forEach(libraryDto1 -> {
            BookDto bookDto = bookClient.getBookByIdFromBookService(Integer.toString(libraryDto1.getBookId()));
            bookDtoList.add(bookDto);
        });
        Optional<UserDto> optionalUserDto = Optional.ofNullable(userClient.getUserNameFromUserService(userName));
        if (optionalUserDto.isEmpty())
            throw new UserException(userName + "  with   user not found");
        UserDto userDto = optionalUserDto.get();
        UserLibraryDto userLibraryDto = new UserLibraryDto();
        userLibraryDto.setUserName(userDto.getUserName());
        userLibraryDto.setName(userDto.getName());
        userLibraryDto.setEmail(userDto.getEmail());
        userLibraryDto.setBookDtoList(bookDtoList);
        return userLibraryDto;
    }
}
