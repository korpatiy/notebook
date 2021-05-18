package com.example.notebook.crud.services;

import com.example.notebook.crud.DAO.PersonRepository;
import com.example.notebook.crud.DAO.PersonRepositoryImpl;
import com.example.notebook.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация {@link PersonService}
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean deletePerson(Long id) {
        return false;
    }

    //todo Optional обертка
    @Override
    public Person getPersonById(Long id) {
        return null;
    }

    @Override
    public List<Person> getPersons() {
        return null;
    }
}
