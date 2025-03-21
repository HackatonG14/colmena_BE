package dev.hack14.colmena.dtos;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class AdDtoTest {

        @Test
        public void testAdDtoCreation() {
                Long id = 1L;
                String title = "Apartment for Rent";
                String description = "Beautiful 2-bedroom apartment";
                String category = "Real Estate";
                Date datePosted = new Date();
                Long adminId = 2L;
                String adminUsername = "admin_user";
                String imageUrl = "https://example.com/image.jpg";

                AdDto ad = new AdDto(
                                id, title, description, category, datePosted,
                                adminId, adminUsername, imageUrl);

                assertEquals(id, ad.id());
                assertEquals(title, ad.title());
                assertEquals(description, ad.description());
                assertEquals(category, ad.category());
                assertEquals(datePosted, ad.datePosted());
                assertEquals(adminId, ad.adminId());
                assertEquals(adminUsername, ad.adminUsername());
                assertEquals(imageUrl, ad.imageUrl());
        }

        @Test
        public void testEqualsAndHashCode() {
                Date now = new Date();

                AdDto ad1 = new AdDto(
                                1L, "Title", "Description", "Category", now,
                                2L, "admin", "https://example.com/image.jpg");

                AdDto ad2 = new AdDto(
                                1L, "Title", "Description", "Category", now,
                                2L, "admin", "https://example.com/image.jpg");

                AdDto ad3 = new AdDto(
                                2L, "Title", "Description", "Category", now,
                                2L, "admin", "https://example.com/image.jpg");

                assertEquals(ad1, ad2);
                assertNotEquals(ad1, ad3);

                assertEquals(ad1.hashCode(), ad2.hashCode());
                assertNotEquals(ad1.hashCode(), ad3.hashCode());
        }

        @Test
        public void testToString() {
                Date now = new Date();
                AdDto ad = new AdDto(
                                1L, "Title", "Description", "Category", now,
                                2L, "admin", "https://example.com/image.jpg");

                String toString = ad.toString();

                assertTrue(toString.contains("id=1"));
                assertTrue(toString.contains("title=Title"));
                assertTrue(toString.contains("description=Description"));
                assertTrue(toString.contains("category=Category"));
                assertTrue(toString.contains("datePosted=" + now));
                assertTrue(toString.contains("adminId=2"));
                assertTrue(toString.contains("adminUsername=admin"));
                assertTrue(toString.contains("imageUrl=https://example.com/image.jpg"));
        }

        @Test
        public void testNullValues() {
                AdDto ad = new AdDto(
                                null, null, null, null, null, null, null, null);

                assertNull(ad.id());
                assertNull(ad.title());
                assertNull(ad.description());
                assertNull(ad.category());
                assertNull(ad.datePosted());
                assertNull(ad.adminId());
                assertNull(ad.adminUsername());
                assertNull(ad.imageUrl());
        }

        @Test
        public void testCopyWithModifiedValues() {

                Date now = new Date();
                AdDto original = new AdDto(
                                1L, "Original Title", "Original Description", "Original Category",
                                now, 2L, "admin", "https://example.com/original.jpg");

                AdDto modified = new AdDto(
                                original.id(),
                                "Updated Title",
                                "Updated Description",
                                original.category(),
                                original.datePosted(),
                                original.adminId(),
                                original.adminUsername(),
                                "https://example.com/updated.jpg");

                assertEquals(original.id(), modified.id());
                assertNotEquals(original.title(), modified.title());
                assertEquals("Updated Title", modified.title());
                assertNotEquals(original.description(), modified.description());
                assertEquals("Updated Description", modified.description());
                assertEquals(original.category(), modified.category());
                assertEquals(original.datePosted(), modified.datePosted());
                assertEquals(original.adminId(), modified.adminId());
                assertEquals(original.adminUsername(), modified.adminUsername());
                assertNotEquals(original.imageUrl(), modified.imageUrl());
                assertEquals("https://example.com/updated.jpg", modified.imageUrl());
        }

        @Test
        public void testWithDifferentDates() {
                Date date1 = new Date(1672531200000L);
                Date date2 = new Date(1672617600000L);

                AdDto ad1 = new AdDto(
                                1L, "Title", "Description", "Category", date1,
                                2L, "admin", "https://example.com/image.jpg");

                AdDto ad2 = new AdDto(
                                1L, "Title", "Description", "Category", date2,
                                2L, "admin", "https://example.com/image.jpg");

                assertNotEquals(ad1, ad2);
                assertNotEquals(ad1.hashCode(), ad2.hashCode());
                assertEquals(date1, ad1.datePosted());
                assertEquals(date2, ad2.datePosted());
        }

        @Test
        public void testWithEmptyStrings() {
                AdDto ad = new AdDto(
                                1L, "", "", "", new Date(), 2L, "", "");

                assertNotNull(ad.title());
                assertEquals("", ad.title());
                assertNotNull(ad.description());
                assertEquals("", ad.description());
                assertNotNull(ad.category());
                assertEquals("", ad.category());
                assertNotNull(ad.adminUsername());
                assertEquals("", ad.adminUsername());
                assertNotNull(ad.imageUrl());
                assertEquals("", ad.imageUrl());
        }
}