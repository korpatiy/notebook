package com.example.notebook.crud.DAO;

import com.example.notebook.model.Person;

import java.util.Optional;

/**
 * Предоставляет методы для работы с хранилищем
 *
 * Подождем возможность использовать JPA и CrudRepo....
 */
public interface PersonRepository {

    Person save(Person person);

    void delete(Person person);

    void deleteAll();

    Optional<Person> findById(Long id);
}
