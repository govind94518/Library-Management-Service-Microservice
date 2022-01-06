package com.epam.service;

import com.epam.dto.UserLibraryDto;
import com.epam.exception.BookAlreadyIssuedException;
import com.epam.exception.BookLimitExceededException;
import com.epam.exception.UserException;

public interface LibraryService {
    void deleteUserByUserName(String userName) throws UserException;

      Boolean issueBookToUser(String userName, int bookId) throws UserException, BookAlreadyIssuedException, BookLimitExceededException;
    Boolean deleteBookToUser(String userName,int bookId) throws UserException;
    UserLibraryDto getUserName(String userName) throws UserException;
}
