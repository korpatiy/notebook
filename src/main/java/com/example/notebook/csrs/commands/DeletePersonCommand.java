package com.example.notebook.csrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePersonCommand {

    private Long personId;
}
