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

    /**
     * Добавляет персону
     * @param person персона
     * @return 1 - успешная вставка, 0 - неудачная вставка
     */
    int createPerson(Person person);

    /**
     * Удаляет персону
     * @param id идентификатор удаляемой персоны
     * @return 1 - успешная вставка, 0 - неудачная вставка
     */
    int deletePerson(Long id);

    /**
     * Получает персону по указанному идентификатору
     * @param id идентификатор персоны
     * @return объект {@link Person}
     */
    Person getPersonById(Long id);

    /**
     * Получает список персон
     * @return список персон {@link Person}
     */
    List<Person> getPersons();

    /**
     * Обновляет контакты персоны по указанному идентификатору
     * @param id идентификатор персоны
     * @param contacts список контактов {@link Contact}
     * @return 1 - успешная вставка, 0 - неудачная вставка
     */
    int updatePersonContacts(Long id, List<Contact> contacts);
}
