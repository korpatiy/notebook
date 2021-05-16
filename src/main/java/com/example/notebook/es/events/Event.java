package com.example.notebook.es.events;

import java.util.Date;
import java.util.UUID;

/**
 * Базовое событие
 */
public abstract class Event {
    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();
}
