package com.example.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель контактов
 */
@AllArgsConstructor
@Data
public class Contact {

    private String type;
    private String detail;
}
