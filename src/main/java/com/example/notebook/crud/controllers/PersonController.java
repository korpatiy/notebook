package com.example.notebook.crud.controllers;

import com.example.notebook.crud.services.PersonService;
import com.example.notebook.crud.services.PersonServiceImpl;
import com.example.notebook.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService = new PersonServiceImpl();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", produces = "application/json")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
