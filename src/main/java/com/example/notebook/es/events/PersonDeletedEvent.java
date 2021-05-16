package com.example.notebook.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDeletedEvent extends Event {

    private Long personId;
}
