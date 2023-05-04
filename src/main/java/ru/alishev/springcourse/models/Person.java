package ru.alishev.springcourse.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Neil Alishev
 */
public class Person {
    private int id;

    @NotEmpty(message = "Full name should not be empty")
    @Size(min = 2, max = 30, message = "Full name should be between 2 and 30 characters")
    private String fullName;

    @NotEmpty(message = "Year Of Birth should not be empty")
    @Size(min = 4, max = 4, message = "Year Of Birth should be of 4 characters")
    private String yearOfBirth;

    private List<Book> books;

    public Person() {

    }

    public Person(int id, String fullName, String yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
