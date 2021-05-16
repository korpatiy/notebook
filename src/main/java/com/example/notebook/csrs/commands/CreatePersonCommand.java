package com.example.notebook.csrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePersonCommand {

    private Long userId;
    private String firstName;
    private String lastName;
    private String birthDate;

}
