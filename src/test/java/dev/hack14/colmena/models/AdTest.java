package dev.hack14.colmena.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.hack14.colmena.enums.Role;
import java.util.Date;

class AdTest {

    private Ad ad;
    private User admin;

    @BeforeEach
    void setUp() {
        admin = new User("adminUser", "admin@example.com", "adminPass", Role.ADMIN);
        ad = new Ad(1L, "Sample Ad", "This is a sample description", "Category1", new Date(), admin, "image.jpg");
    }

    @Test
    void testAdConstructor() {
        assertEquals(1L, ad.getId());
        assertEquals("Sample Ad", ad.getTitle());
        assertEquals("This is a sample description", ad.getDescription());
        assertEquals("Category1", ad.getCategory());
        assertNotNull(ad.getDatePosted());
        assertEquals(admin, ad.getAdmin());
        assertEquals("image.jpg", ad.getImageUrl());
    }

    @Test
    void testSettersAndGetters() {
        ad.setId(2L);
        assertEquals(2L, ad.getId());

        ad.setTitle("Updated Ad");
        assertEquals("Updated Ad", ad.getTitle());

        ad.setDescription("Updated description");
        assertEquals("Updated description", ad.getDescription());

        ad.setCategory("UpdatedCategory");
        assertEquals("UpdatedCategory", ad.getCategory());

        Date newDate = new Date();
        ad.setDatePosted(newDate);
        assertEquals(newDate, ad.getDatePosted());

        User newAdmin = new User("newAdmin", "newAdmin@example.com", "newAdminPass", Role.ADMIN);
        ad.setAdmin(newAdmin);
        assertEquals(newAdmin, ad.getAdmin());

        ad.setImageUrl("newImage.jpg");
        assertEquals("newImage.jpg", ad.getImageUrl());
    }
}
