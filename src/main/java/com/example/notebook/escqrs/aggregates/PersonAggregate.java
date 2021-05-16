package com.example.notebook.escqrs.aggregates;

import com.example.notebook.configurations.SpringJdbcConfig;
import com.example.notebook.es.events.Event;
import com.example.notebook.es.events.PersonCreatedEvent;
import com.example.notebook.es.events.PersonDeletedEvent;
import com.example.notebook.es.repostitory.EventStore;
import com.example.notebook.csrs.commands.CreatePersonCommand;
import com.example.notebook.csrs.commands.DeletePersonCommand;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Агрегат приема и обработки команд
 */
public class PersonAggregate {

    private final EventStore writeRepository;
    private List<Event> events;
    private JdbcTemplate jdbcTemplate;
    private SpringJdbcConfig springJdbcConfig;

    public PersonAggregate(EventStore writeRepository) {
        this.writeRepository = writeRepository;
    }

    public List<Event> handleCreatePersonCommand(CreatePersonCommand command) {
        events = new ArrayList<>();
        PersonCreatedEvent event = new PersonCreatedEvent(
                command.getUserId(), command.getFirstName(),
                command.getLastName(), command.getBirthDate());
        events.add(event);
        writeRepository.save(events);
        return Collections.singletonList(event);
    }

    public List<Event> handleDeletePersonCommand(DeletePersonCommand command) throws ChangeSetPersister.NotFoundException {
        events = new ArrayList<>();
        PersonDeletedEvent event = new PersonDeletedEvent(command.getPersonId());
        events.add(event);
        writeRepository.save(events);
        return Collections.singletonList(event);
    }

    /*public Person handleUpdatePersonCommand(UpdatePersonCommand command) throws ChangeSetPersister.NotFoundException {
        Person person = getPerson(command.getUserId());
        person.setContacts(command.getContacts());
        person.setAddresses(command.getAddresses());
        personWriteRepository.save(person);
        return person;
    }*/

   /* private Person getPerson(Long userId) throws ChangeSetPersister.NotFoundException {
        return personWriteRepository.findById(userId).
                orElseThrow(ChangeSetPersister.NotFoundException::new);
    }*/

}
