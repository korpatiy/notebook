package com.example.notebook.crud.repositories;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data layer
 * Репозиторий персон, реализующий {@link PersonRepository}
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final String BASE_QUERY = "select person.id as p_id, c.id as c_id, * from person join contact c on person.id = c.person_id";

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.writeDataSource());


    @Override
    public int save(Person person) {

        try {
            return jdbcTemplate.update("insert into person (first_name, last_name, birth_date) values (?,?,?)",
                    person.getFirstName(), person.getLastName(), Date.valueOf(person.getBirthDate()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        //todo посмотреть как вернуть id вставленной строки, используя RETURN_GENERATED_KEYS - можно обычным PreparedStatement
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
        //todo посмотреть каскад удаление
        try {
            jdbcTemplate.update("delete from contact where person_id = ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            jdbcTemplate.update("delete from person where id =?", id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public void deleteAll() {
        //todo
    }

    @Override
    public Person findById(Long id) {
        // return jdbcTemplate.queryForObject("select person.id as p_id, c.id as c_id, * from person join contact c on person.id = c.person_id where person.id = ?", (rs, rowNum) -> getPerson(rs), id);
        Person person = null;
        try (Connection connection = SpringJdbcConfig.writeDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(BASE_QUERY + " where person.id = ?")) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        if (person == null)
                            person = mapPerson(rs);
                        else if (person.getPersonId() != rs.getLong("id")) {
                            person = mapPerson(rs);
                        }
                        if (rs.getInt("c_id") != -1) {
                            Contact contact = new Contact();
                            contact.setType(rs.getString("type"));
                            contact.setDetail(rs.getString("detail"));
                            person.addContact(contact);
                        }
                    }
                }
            }
        } catch (Exception throwable) {
            throwable.printStackTrace();
            return null;
        }
        return person;
    }


    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = SpringJdbcConfig.writeDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(BASE_QUERY)) {
                mapRsList(persons, statement);
            }
        } catch (Exception throwable) {
            throwable.printStackTrace();
            return null;
        }
        return persons;

        //как переписать так, чтобы не вытягивало все строки, а для каждого - свои контакты? у MapROw не вытащить внутренний лист
        // return jdbcTemplate.query("select person.id as p_id, c.id as c_id, * from person full join contact c on person.id = c.person_id", (rs, rowNum) -> getPerson(rs));
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

    @Override
    public List<Person> search(String param, String value) {
        String sql = BASE_QUERY;
        boolean isDate = false;
        switch (param)
        {
            case "firstName":
                sql += " where person.first_name LIKE '%'||?||'%'";
                break;
            case "lastName":
                sql += " where person.last_name LIKE '%'||?||'%'";
                break;
            case "birthDate":
                sql += " where person.birth_date = ?";
                isDate = true;
                break;
            case "contactDetail":
                sql += " where c.detail LIKE '%'||?||'%'";
                break;
        }

        //Для хорошего поиска только ХП писать? наткнулся на to_tsvector, но тут надо таблицу кидать в аргумент
        //String sql = "select person.id as p_id, c.id as c_id, * from person join contact c on person.id =
        // c.person_id WHERE to_tsvector(?) @@ to_tsquery(?);";
        List<Person> persons = new ArrayList<>();

        try (Connection connection = SpringJdbcConfig.writeDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if(isDate)
                    statement.setDate(1, Date.valueOf(value));
                else
                    statement.setString(1, value);
                mapRsList(persons, statement);
            }
        } catch (Exception throwable) {
            throwable.printStackTrace();
            return null;
        }
        return persons;
    }

    /* маппит персону из бд*/
    private Person mapPerson(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setBirthDate(rs.getDate("birth_date").toLocalDate());
        return person;
    }

    /* маппит лист персон из бд*/
    private void mapRsList(List<Person> persons, PreparedStatement statement) throws SQLException {
        Person person = null;
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                if (person == null) {
                    person = mapPerson(rs);
                    persons.add(person);
                } else if (person.getPersonId() != rs.getLong("id")) {
                    person = mapPerson(rs);
                    persons.add(person);
                }
                if (rs.getInt("c_id") != -1) {
                    Contact contact = new Contact();
                    contact.setType(rs.getString("type"));
                    contact.setDetail(rs.getString("detail"));
                    person.addContact(contact);
                }
            }
        }
    }

}
