package com.example.notebook.crud.repositories;

import com.example.notebook.model.Address;
import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий персон
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findPersonByBirthDate(String birthDate);

    Optional<Person> findPersonByAddresses(Address addresses);

    Optional<Person> findPersonByContacts(Set<Contact> contacts);

    Optional<Person> findPersonByText(String text);
}
