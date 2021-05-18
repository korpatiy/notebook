package com.example.notebook.crud.DAO;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий персон, реализующий {@link PersonRepository}
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.writeDataSource());

    @Override
    public int save(Person person) {

        return jdbcTemplate.update("insert into person (first_name, last_name, birth_date) values (?,?,?)",
                person.getFirstName(), person.getLastName(), Date.valueOf(person.getBirthDate()));


        //todo посмотреть как вернуть id вставленной строки
        /*GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement statement = conn.prepareStatement("insert into person (first_name, last_name, birth_date) values (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setDate(3 ,Date.valueOf(person.getBirthDate()));
                return statement;
            }
        }, holder);
        return person;*/
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

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setPersonId(rs.getLong("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                return person;
            }
        });
    }
}
