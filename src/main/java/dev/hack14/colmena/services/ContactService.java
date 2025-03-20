package dev.hack14.colmena.services;

import dev.hack14.colmena.models.Contact;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.repositories.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public List<Contact> getContactsBySender(User sender) {
        return contactRepository.findBySender(sender);
    }

    public List<Contact> getContactsByAd(Ad ad) {
        return contactRepository.findByAd(ad);
    }

    public List<Contact> getContactsByAdAdmin(User admin) {
        return contactRepository.findByAdAdmin(admin);
    }

    public Contact createContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        
        // Create notification for the ad owner
        notificationService.createContactNotification(savedContact);
        
        return savedContact;
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}