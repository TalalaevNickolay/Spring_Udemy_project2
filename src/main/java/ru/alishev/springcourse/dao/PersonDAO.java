package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.*;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM \"Person\"", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM \"Person\" WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO \"Person\"(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE \"Person\" SET full_name=?, year_of_birth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"Person\" WHERE id=?", id);
    }

    public List<Book> getBooks(Person person){
        Object[] params = new Object[1];
        params[0] = person.getId();
        return jdbcTemplate.query("SELECT b.* from \"Person\" p, \"Book\" b where b.person_id = p.id and p.id = ?", params,new BeanPropertyRowMapper<>(Book.class));
    }

}
