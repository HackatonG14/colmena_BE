package dev.hack14.colmena.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.hack14.colmena.models.Contact;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.repositories.ContactRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ContactService contactService;

    private Contact contact;
    private User sender;
    private Ad ad;

    @BeforeEach
    void setUp() {
        sender = new User();
        ad = new Ad();
        contact = new Contact();
        contact.setId(1L);
        contact.setSender(sender);
        contact.setAd(ad);
    }

    @Test
    void testGetAllContacts() {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));

        List<Contact> contacts = contactService.getAllContacts();

        assertEquals(1, contacts.size());
        assertEquals(contact, contacts.get(0));
    }

    @Test
    void testGetContactById() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Optional<Contact> foundContact = contactService.getContactById(1L);

        assertTrue(foundContact.isPresent());
        assertEquals(contact, foundContact.get());
    }

    @Test
    void testGetContactsBySender() {
        when(contactRepository.findBySender(sender)).thenReturn(Arrays.asList(contact));

        List<Contact> contacts = contactService.getContactsBySender(sender);

        assertEquals(1, contacts.size());
        assertEquals(contact, contacts.get(0));
    }

    @Test
    void testGetContactsByAd() {
        when(contactRepository.findByAd(ad)).thenReturn(Arrays.asList(contact));

        List<Contact> contacts = contactService.getContactsByAd(ad);

        assertEquals(1, contacts.size());
        assertEquals(contact, contacts.get(0));
    }

    @Test
    void testCreateContact() {
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact savedContact = contactService.createContact(contact);

        assertNotNull(savedContact);
        verify(contactRepository, times(1)).save(contact);
        verify(notificationService, times(1)).createContactNotification(contact);
    }

    @Test
    void testDeleteContact() {
        doNothing().when(contactRepository).deleteById(1L);

        contactService.deleteContact(1L);

        verify(contactRepository, times(1)).deleteById(1L);
    }
}
