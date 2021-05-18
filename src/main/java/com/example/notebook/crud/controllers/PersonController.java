package com.example.notebook.crud.controllers;

import com.example.notebook.crud.services.PersonService;
import com.example.notebook.crud.services.PersonServiceImpl;
import com.example.notebook.model.Person;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.util.List;

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

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public void createPerson(@RequestBody Person person) {
        //todo THROW
        if(personService.createPerson(person) != 1);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
