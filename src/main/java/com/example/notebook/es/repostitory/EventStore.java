package com.example.notebook.es.repostitory;

import com.example.notebook.es.events.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStore extends CrudRepository<List<Event>, Long> {

}
