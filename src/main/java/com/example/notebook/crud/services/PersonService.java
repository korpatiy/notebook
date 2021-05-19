package com.example.notebook.crud.services;

import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, предоставляющий методы для работы с Person
 */
@Service
public interface PersonService {

    int createPerson(Person person);

    int deletePerson(Long id);

    Person getPersonById(Long id);

    List<Person> getPersons();

    int updatePersonContacts(Long id, List<Contact> contacts);
}
