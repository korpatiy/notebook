package com.example.notebook.crud.DAO;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Optional;

/**
 * Репозиторий персон, реализующий {@link PersonRepository}
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.writeDataSource());

    @Override
    public Person save(Person person) {
        int id = jdbcTemplate.update("insert into person (first_name, last_name, birth_date) values (?,?,?)",
                person.getFirstName(), person.getLastName(), Date.valueOf(person.getBirthDate()));
        person.setPersonId((long) id);
        return person;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.empty();
    }
}
