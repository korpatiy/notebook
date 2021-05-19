package com.example.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель контактов
 */
public class Contact {

    private String type;
    private String detail;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
