package com.epam.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int  bookId;
    String userName;
    public Library(){}

    public Library(int id, int bookId, String userName) {
        this.id = id;
        this.bookId = bookId;
        this.userName = userName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
