package com.epam.repository;

import com.epam.entity.Library;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Library,Integer> {
    Optional<Library> findByUserNameAndBookId(String userName,int bookId);
    List<Library> findByUserName(String userName);
}
