package com.example.notebook.crud.controllers;

import com.example.notebook.crud.services.PersonService;
import com.example.notebook.crud.services.PersonServiceImpl;
import com.example.notebook.model.Contact;
import com.example.notebook.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Controller layer
 */
@RestController
@RequestMapping(path = "/person")
public class PersonController {

    private final PersonService personService = new PersonServiceImpl();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<Person> getSearch(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lasName = request.getParameter("lastName");
        List<String> attributeNames = Arrays.asList("firstName", "lastName", "birthdate", "contactType", "contactDetail");
        //return personService.fullTextSearch(param, value);
        return null;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        return getStringResponseEntity(personService.createPerson(person), "Вставка не выполнена", HttpStatus.CREATED, "Успешно создан");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return getStringResponseEntity(personService.deletePerson(id), "Удаление не выполнено", HttpStatus.NO_CONTENT, "Успешно удален");
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> updatePersonContacts(@PathVariable Long id, @RequestBody List<Contact> contacts) {
        return getStringResponseEntity(personService.updatePersonContacts(id, contacts), "Обновление не выполнено", HttpStatus.OK, "Информация обновлена");
    }

    private ResponseEntity<String> getStringResponseEntity(int i, String s, HttpStatus status, String s2) {
        if (i != 1) {
            ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(s);
        }
        return ResponseEntity
                .status(status)
                .body(s2);
    }

}
