package com.example.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Set;

/**
 * Модель персоны
 */
@Data
@NoArgsConstructor
public class Person {

    private Long personId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Set<Contact> contacts;
    private Address addresses;

    public Person(Long personId, String firstName, String lastName, String birthDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
