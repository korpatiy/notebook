package com.example.notebook.crud.services;

import com.example.notebook.crud.repositories.PersonRepositoryImpl;
import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer
 * Реализация {@link PersonService}
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();

    @Override
    public int createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public int deletePerson(Long id) {
        return personRepository.delete(id);
    }

    //todo Optional обертка
    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.getAll();
    }

    @Override
    public int updatePersonContacts(Long id, List<Contact> contacts) {
        return personRepository.updatePersonContacts(id, contacts);
    }
}
