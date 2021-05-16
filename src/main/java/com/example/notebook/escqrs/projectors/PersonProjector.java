package com.example.notebook.escqrs.projectors;

import com.example.notebook.es.events.Event;

import java.util.List;

/**
 * Проецирования модели области записи в модель области чтения.
 */
public class PersonProjector {

    public void project(Long personId, List<Event> events) {
        /*for (Event event : events) {
            if(event instanceof )
        }*/
    }
}
