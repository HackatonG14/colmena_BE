package dev.hack14.colmena.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.hack14.colmena.enums.Role;
import java.time.LocalDateTime;

class NotificationTest {

    private Notification notification;
    private User user;
    private Ad ad;
    private Contact contact;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "test@example.com", "password123", Role.USER);
        ad = new Ad();
        contact = new Contact();
        notification = new Notification(user, "New message received", ad, contact);
    }

    @Test
    void testNotificationConstructor() {
        assertEquals(user, notification.getUser());
        assertEquals("New message received", notification.getMessage());
        assertEquals(ad, notification.getAd());
        assertEquals(contact, notification.getContact());
        assertFalse(notification.isRead());
    }

    @Test
    void testSettersAndGetters() {
        notification.setId(1L);
        assertEquals(1L, notification.getId());

        notification.setMessage("Updated message");
        assertEquals("Updated message", notification.getMessage());

        notification.setRead(true);
        assertTrue(notification.isRead());

        LocalDateTime newDate = LocalDateTime.now();
        notification.setCreatedAt(newDate);
        assertEquals(newDate, notification.getCreatedAt());

        User newUser = new User("newUser", "new@example.com", "newPass", Role.USER);
        notification.setUser(newUser);
        assertEquals(newUser, notification.getUser());

        Ad newAd = new Ad();
        notification.setAd(newAd);
        assertEquals(newAd, notification.getAd());

        Contact newContact = new Contact();
        notification.setContact(newContact);
        assertEquals(newContact, notification.getContact());
    }

    @Test
    void testPrePersist() {
        notification.onCreate();
        assertNotNull(notification.getCreatedAt());
        assertFalse(notification.isRead());
    }
}
