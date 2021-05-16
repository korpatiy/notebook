package com.example.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель адреса
 */
@AllArgsConstructor
@Data
public class Address {

    private String city;
    private String state;
    private String postcode;
}
