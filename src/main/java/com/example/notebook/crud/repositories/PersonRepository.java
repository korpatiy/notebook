package com.example.notebook.crud.repositories;

import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;

import java.util.List;

/**
 * Предоставляет методы для работы с хранилищем
 *
 * Подождем возможность использовать JPA и CrudRepo....
 */
public interface PersonRepository {

    int save(Person person);

    int delete(Long id);

    void deleteAll();

    Person findById(Long id);

    List<Person> getAll();

    int updatePersonContacts(Long id, List<Contact> contacts);
}
