package dev.hack14.colmena.dtos;

import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ContactDtoTest {

    @Test
    public void testContactDtoCreation() {
        Long id = 1L;
        Long senderId = 2L;
        String senderUsername = "john_doe";
        Long adId = 3L;
        String adTitle = "Apartment for Rent";
        String message = "I'm interested in your apartment";
        String contactEmail = "john@example.com";
        String contactPhone = "123-456-7890";
        LocalDateTime createdAt = LocalDateTime.now();

        ContactDto contact = new ContactDto(
            id, senderId, senderUsername, adId, adTitle, message, 
            contactEmail, contactPhone, createdAt
        );

        assertEquals(id, contact.id());
        assertEquals(senderId, contact.senderId());
        assertEquals(senderUsername, contact.senderUsername());
        assertEquals(adId, contact.adId());
        assertEquals(adTitle, contact.adTitle());
        assertEquals(message, contact.message());
        assertEquals(contactEmail, contact.contactEmail());
        assertEquals(contactPhone, contact.contactPhone());
        assertEquals(createdAt, contact.createdAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        
        ContactDto contact1 = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", now
        );
        
        ContactDto contact2 = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", now
        );
        
        ContactDto contact3 = new ContactDto(
            2L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", now
        );

        assertEquals(contact1, contact2);
        assertNotEquals(contact1, contact3);

        assertEquals(contact1.hashCode(), contact2.hashCode());
        assertNotEquals(contact1.hashCode(), contact3.hashCode());
    }

    @Test
    public void testToString() {

        LocalDateTime now = LocalDateTime.now();
        ContactDto contact = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", now
        );

        String toString = contact.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("senderId=2"));
        assertTrue(toString.contains("senderUsername=john_doe"));
        assertTrue(toString.contains("adId=3"));
        assertTrue(toString.contains("adTitle=Apartment for Rent"));
        assertTrue(toString.contains("message=I'm interested"));
        assertTrue(toString.contains("contactEmail=john@example.com"));
        assertTrue(toString.contains("contactPhone=123-456-7890"));
        assertTrue(toString.contains("createdAt=" + now));
    }

    @Test
    public void testNullValues() {
        ContactDto contact = new ContactDto(
            null, null, null, null, null, null, null, null, null
        );

        assertNull(contact.id());
        assertNull(contact.senderId());
        assertNull(contact.senderUsername());
        assertNull(contact.adId());
        assertNull(contact.adTitle());
        assertNull(contact.message());
        assertNull(contact.contactEmail());
        assertNull(contact.contactPhone());
        assertNull(contact.createdAt());
    }

    @Test
    public void testCopyWithModifiedValues() {
        LocalDateTime now = LocalDateTime.now();
        ContactDto original = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "Original message", "john@example.com", "123-456-7890", now
        );

        ContactDto modified = new ContactDto(
            original.id(),
            original.senderId(),
            original.senderUsername(),
            original.adId(),
            original.adTitle(),
            "Updated message",
            "updated@example.com",
            original.contactPhone(),
            original.createdAt()
        );

        assertEquals(original.id(), modified.id());
        assertEquals(original.senderId(), modified.senderId());
        assertEquals(original.senderUsername(), modified.senderUsername());
        assertEquals(original.adId(), modified.adId());
        assertEquals(original.adTitle(), modified.adTitle());
        assertNotEquals(original.message(), modified.message());
        assertEquals("Updated message", modified.message());
        assertNotEquals(original.contactEmail(), modified.contactEmail());
        assertEquals("updated@example.com", modified.contactEmail());
        assertEquals(original.contactPhone(), modified.contactPhone());
        assertEquals(original.createdAt(), modified.createdAt());
    }
    
    @Test
    public void testWithDifferentCreationTimes() {
        LocalDateTime time1 = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 1, 2, 12, 0);
        
        ContactDto contact1 = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", time1
        );
        
        ContactDto contact2 = new ContactDto(
            1L, 2L, "john_doe", 3L, "Apartment for Rent", 
            "I'm interested", "john@example.com", "123-456-7890", time2
        );

        assertNotEquals(contact1, contact2);
        assertNotEquals(contact1.hashCode(), contact2.hashCode());
        assertEquals(time1, contact1.createdAt());
        assertEquals(time2, contact2.createdAt());
    }
    
    @Test
    public void testWithEmptyStrings() {
        ContactDto contact = new ContactDto(
            1L, 2L, "", 3L, "", "", "", "", LocalDateTime.now()
        );

        assertNotNull(contact.senderUsername());
        assertEquals("", contact.senderUsername());
        assertNotNull(contact.adTitle());
        assertEquals("", contact.adTitle());
        assertNotNull(contact.message());
        assertEquals("", contact.message());
        assertNotNull(contact.contactEmail());
        assertEquals("", contact.contactEmail());
        assertNotNull(contact.contactPhone());
        assertEquals("", contact.contactPhone());
    }
}