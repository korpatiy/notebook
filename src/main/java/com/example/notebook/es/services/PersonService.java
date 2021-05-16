package com.example.notebook.es.services;

import com.example.notebook.es.events.Event;
import com.example.notebook.es.events.PersonCreatedEvent;
import com.example.notebook.es.events.PersonDeletedEvent;
import com.example.notebook.es.repostitory.EventStore;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Event Sourcing Сервис для работы с {@link com.example.notebook.model.Person}
 */
@Service
public class PersonService {

    private final EventStore repository;

    public PersonService(EventStore repository) {
        this.repository = repository;
    }

    public void createPerson(Long personId, String firstName, String lastName, String birthDate) {
        List<Event> events = new ArrayList<>();
        events.add(new PersonCreatedEvent(personId, firstName, lastName, birthDate));
        repository.save(events);
    }

    public void deletePerson(Long personId) throws ChangeSetPersister.NotFoundException {
        List<Event> events = new ArrayList<>();
        events.add(new PersonDeletedEvent(personId));
        repository.save(events);
    }

}
