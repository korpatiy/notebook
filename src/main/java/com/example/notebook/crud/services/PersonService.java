package com.example.notebook.crud.services;

import com.example.notebook.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, предоставляющий методы для работы с Person
 */
@Service
public interface PersonService {

    Person createPerson(Person person);

    boolean deletePerson(Long id);

    Person getPersonById(Long id);

    List<Person> getPersons();
}
