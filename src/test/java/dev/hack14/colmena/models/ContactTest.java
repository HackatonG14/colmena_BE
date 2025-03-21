package dev.hack14.colmena.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.hack14.colmena.enums.Role;
import java.time.LocalDateTime;

class ContactTest {

    private Contact contact;
    private User sender;
    private Ad ad;

    @BeforeEach
    void setUp() {
        sender = new User("testUser", "test@example.com", "password123", Role.USER);
        ad = new Ad();
        contact = new Contact(sender, ad, "Test message", "test@example.com", "1234567890");
    }

    @Test
    void testContactConstructor() {
        assertEquals(sender, contact.getSender());
        assertEquals(ad, contact.getAd());
        assertEquals("Test message", contact.getMessage());
        assertEquals("test@example.com", contact.getContactEmail());
        assertEquals("1234567890", contact.getContactPhone());
    }

    @Test
    void testSettersAndGetters() {
        contact.setId(1L);
        assertEquals(1L, contact.getId());

        contact.setMessage("Updated message");
        assertEquals("Updated message", contact.getMessage());

        contact.setContactEmail("updated@example.com");
        assertEquals("updated@example.com", contact.getContactEmail());

        contact.setContactPhone("0987654321");
        assertEquals("0987654321", contact.getContactPhone());

        LocalDateTime newDate = LocalDateTime.now();
        contact.setCreatedAt(newDate);
        assertEquals(newDate, contact.getCreatedAt());
    }

    @Test
    void testPrePersist() {
        contact.onCreate();
        assertNotNull(contact.getCreatedAt());
    }
}
