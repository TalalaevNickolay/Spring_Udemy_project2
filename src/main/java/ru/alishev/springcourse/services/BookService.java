package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByYear){
        if(page != null && booksPerPage != null && sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else if(page!= null && booksPerPage != null)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        return booksRepository.findAll();
    }

    public List<Book> findByName(String name){
        return booksRepository.findByNameStartingWith(name);
    }

    public Book findOne(int id){
        Optional<Book> book =  booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book person){
        booksRepository.save(person);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        Book book = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(book.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

    public Person getBookOwner(int id){
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPerson);
            book.setTakenAt(new Date());
        });
    }
}

