package dev.hack14.colmena.dtos;

import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NotificationDtoTest {

    @Test
    public void testNotificationDtoCreation() {
        Long id = 1L;
        Long userId = 2L;
        String message = "Test notification message";
        boolean read = false;
        LocalDateTime createdAt = LocalDateTime.now();
        Long adId = 3L;
        String adTitle = "Test Ad Title";
        Long contactId = 4L;

        NotificationDto notification = new NotificationDto(
                id, userId, message, read, createdAt, adId, adTitle, contactId);

        assertEquals(id, notification.id());
        assertEquals(userId, notification.userId());
        assertEquals(message, notification.message());
        assertEquals(read, notification.read());
        assertEquals(createdAt, notification.createdAt());
        assertEquals(adId, notification.adId());
        assertEquals(adTitle, notification.adTitle());
        assertEquals(contactId, notification.contactId());
    }

    @Test
    public void testEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        NotificationDto notification1 = new NotificationDto(
                1L, 2L, "Message", false, now, 3L, "Ad Title", 4L);

        NotificationDto notification2 = new NotificationDto(
                1L, 2L, "Message", false, now, 3L, "Ad Title", 4L);

        NotificationDto notification3 = new NotificationDto(
                2L, 2L, "Message", false, now, 3L, "Ad Title", 4L);

        assertEquals(notification1, notification2);
        assertNotEquals(notification1, notification3);

        assertEquals(notification1.hashCode(), notification2.hashCode());
        assertNotEquals(notification1.hashCode(), notification3.hashCode());
    }

    @Test
    public void testToString() {

        LocalDateTime now = LocalDateTime.now();
        NotificationDto notification = new NotificationDto(
                1L, 2L, "Message", false, now, 3L, "Ad Title", 4L);

        String toString = notification.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("userId=2"));
        assertTrue(toString.contains("message=Message"));
        assertTrue(toString.contains("read=false"));
        assertTrue(toString.contains("createdAt=" + now));
        assertTrue(toString.contains("adId=3"));
        assertTrue(toString.contains("adTitle=Ad Title"));
        assertTrue(toString.contains("contactId=4"));
    }

    @Test
    public void testNullValues() {

        NotificationDto notification = new NotificationDto(
                null, null, null, false, null, null, null, null);

        assertNull(notification.id());
        assertNull(notification.userId());
        assertNull(notification.message());
        assertFalse(notification.read());
        assertNull(notification.createdAt());
        assertNull(notification.adId());
        assertNull(notification.adTitle());
        assertNull(notification.contactId());
    }

    @Test
    public void testCopyWithModifiedValues() {

        LocalDateTime now = LocalDateTime.now();
        NotificationDto original = new NotificationDto(
                1L, 2L, "Original message", false, now, 3L, "Original Ad Title", 4L);

        NotificationDto modified = new NotificationDto(
                original.id(),
                original.userId(),
                "Updated message",
                true,
                original.createdAt(),
                original.adId(),
                original.adTitle(),
                original.contactId());

        assertEquals(original.id(), modified.id());
        assertEquals(original.userId(), modified.userId());
        assertNotEquals(original.message(), modified.message());
        assertEquals("Updated message", modified.message());
        assertNotEquals(original.read(), modified.read());
        assertTrue(modified.read());
        assertEquals(original.createdAt(), modified.createdAt());
        assertEquals(original.adId(), modified.adId());
        assertEquals(original.adTitle(), modified.adTitle());
        assertEquals(original.contactId(), modified.contactId());
    }
}