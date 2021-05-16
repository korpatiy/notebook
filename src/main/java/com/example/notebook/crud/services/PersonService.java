package com.example.notebook.crud.services;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.crud.repositories.PersonRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для работы с {@link com.example.notebook.model.Person}
 */
@Service
public class PersonService {

    private final PersonRepository repository;
    private final SimpleJdbcInsert simpleJdbcInsert =
            new SimpleJdbcInsert(SpringJdbcConfig.writeDataSource()).withTableName("Person");

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public int createPerson(Long personId, String firstName, String lastName, String birthDate) {
        /*Person person = new Person(personId, firstName, lastName, birthDate);
        repository.save(person);*/
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", personId);
        parameters.put("first_name", firstName);
        parameters.put("last_name", lastName);
        parameters.put("birth_date", birthDate);
        return simpleJdbcInsert.execute(parameters);
    }

    public void deletePerson(Long personId) throws ChangeSetPersister.NotFoundException {
    }

}
