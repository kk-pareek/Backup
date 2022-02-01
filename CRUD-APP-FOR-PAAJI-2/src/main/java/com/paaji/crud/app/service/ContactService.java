package com.paaji.crud.app.service;

import com.paaji.crud.app.entity.Contact;
import com.paaji.crud.app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
    private ContactRepository theContactRepository;


    public List<Contact> getAllContacts() {
        return theContactRepository.findAll();
    }

    public Contact addNewContact(Contact contactToBeAdded) {
        return theContactRepository.save(contactToBeAdded);
    }

    public List<Contact> getContactsByUserId(Integer userId) {
        List<Contact> contacts = getAllContacts();
        return contacts.stream()
                .filter(contact -> contact.getUserId() != null)
                .filter(contact -> contact.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
