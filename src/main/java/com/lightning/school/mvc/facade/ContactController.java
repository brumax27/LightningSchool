package com.lightning.school.mvc.facade;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts/")
public class ContactController {
    private static final List<Contact> contacts = Arrays.asList(
            new Contact("Bruno Krebs", "+5551987654321"),
            new Contact("John Doe", "+5551888884444")
    );

    @GetMapping
    public List<Contact> getContacts() {
        return contacts;
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contacts.add(contact);
    }

    @Data
    @AllArgsConstructor
    private static class Contact {

        private String name;

        private String phone;


    }
}
