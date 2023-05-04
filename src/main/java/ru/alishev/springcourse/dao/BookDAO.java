package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM \"Book\"", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM \"Book\" WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO \"Book\"(name, author, year, person_id) VALUES(?, ?, ?, ?)", book.getName(),
                book.getAuthor(), book.getYear(), book.getPersonId());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE \"Book\" SET name=?, author=?, year=?, person_id=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(),
                updatedBook.getYear(), updatedBook.getPersonId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"Book\" WHERE id=?", id);
    }

    public Person getPerson(Book book){
            return jdbcTemplate.query("SELECT * FROM \"Person\" WHERE id = ?", new Object[]{book.getPersonId()}, new BeanPropertyRowMapper<>(Person.class))
                    .stream().findAny().orElse(null);
    }

    public void updatePerson(int id, Integer personId){
        jdbcTemplate.update("UPDATE \"Book\" SET person_id=? WHERE id=?", personId, id);
    }
}
