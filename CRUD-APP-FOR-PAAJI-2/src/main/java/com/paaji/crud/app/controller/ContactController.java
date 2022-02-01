package com.paaji.crud.app.controller;

import com.paaji.crud.app.entity.Contact;
import com.paaji.crud.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactApi")
public class ContactController {
    @Autowired
    private ContactService theContactService;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return theContactService.getAllContacts();
    }

    @PostMapping("/contacts")
    public Contact addNewContact(@RequestBody Contact contactToBeAdded) {
        return theContactService.addNewContact(contactToBeAdded);
    }

    @GetMapping("/user/{userId}")
    public List<Contact> getContactsByUserId(@PathVariable Integer userId) {
        return theContactService.getContactsByUserId(userId);
    }
}
