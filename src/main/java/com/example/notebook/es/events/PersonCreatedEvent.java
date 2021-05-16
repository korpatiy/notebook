package com.example.notebook.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCreatedEvent extends Event {

    private Long personId;
    private String firstName;
    private String lastName;
    private String birthDate;
}
