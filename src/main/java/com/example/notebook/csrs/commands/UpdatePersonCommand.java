package com.example.notebook.csrs.commands;

import com.example.notebook.model.Address;
import com.example.notebook.model.Contact;

import java.util.Set;

public class UpdatePersonCommand {
    private Long userId;
    private Address addresses;
    private Set<Contact> contacts;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
