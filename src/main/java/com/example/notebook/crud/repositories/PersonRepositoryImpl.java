package com.example.notebook.crud.repositories;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

/**
 * Data layer
 * Репозиторий персон, реализующий {@link PersonRepository}
 */
@Repository
@Transactional
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
    public int delete(Long id) {
        return jdbcTemplate.update("delete from person where id =?", id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Person findById(Long id) {
        return jdbcTemplate.queryForObject("select person.id as p_id, c.id as c_id, * from person join contact c on person.id = c.person_id where person.id = ?", (rs, rowNum) -> getPerson(rs), id);
    }

    private Person getPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setBirthDate(rs.getDate("birth_date").toLocalDate());
        if (rs.getString("c_id") != null) {
            Contact contact = new Contact();
            contact.setType(rs.getString("type"));
            contact.setDetail(rs.getString("detail"));
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        /*DataSource dataSource = SpringJdbcConfig.writeDataSource();
        Connection connection = dataSource.getConnection();*/
        return jdbcTemplate.query("select person.id as p_id, c.id as c_id, * from person full join contact c on person.id = c.person_id", new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setPersonId(rs.getLong("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setBirthDate(rs.getDate("birth_date").toLocalDate());
               // if (rs.getString("c_id") != null) {
                    Contact contact = new Contact();
                    contact.setType(rs.getString("type"));
                    contact.setDetail(rs.getString("detail"));
              //  }
                //person.setContacts();
                return person;
            }
        });
    }

    @Override
    public int updatePersonContacts(Long id, List<Contact> contacts) {
        try {
            jdbcTemplate.batchUpdate("insert into contact (type, detail, person_id) values (?,?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Contact contact = contacts.get(i);
                    ps.setString(1, contact.getType());
                    ps.setString(2, contact.getDetail());
                    ps.setLong(3, id);
                }

                @Override
                public int getBatchSize() {
                    return contacts.size();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
