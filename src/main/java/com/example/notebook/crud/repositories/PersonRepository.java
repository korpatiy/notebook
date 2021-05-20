package com.example.notebook.crud.repositories;

import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;

import java.util.List;

/**
 * Предоставляет методы для работы с хранилищем
 * <p>
 * Подождем возможность использовать JPA и CrudRepo....
 */
public interface PersonRepository {

    /*Сохраняет персону в бд*/
    int save(Person person);

    /*Удаляет персону из бд*/
    int delete(Long id);

    /*Удаляет всех персон из бд*/
    void deleteAll();

    /*Поиск персоны по бд*/
    Person findById(Long id);

    /*Получает всех персон*/
    List<Person> getAll();

    /*Обновляет контактную информацию*/
    int updatePersonContacts(Long id, List<Contact> contacts);

    /*Полнотекстовой поиск по бд*/
    List<Person> search(String param, String value);
}
