package dev.hack14.colmena.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.hack14.colmena.enums.Role;
import java.time.LocalDateTime;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "test@example.com", "password123", Role.USER);
    }

    @Test
    void testUserConstructor() {
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void testSettersAndGetters() {
        user.setId(1L);
        assertEquals(1L, user.getId());

        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());

        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());

        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());

        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testPrePersist() {
        user.onCreate();
        assertNotNull(user.getCreatedAt());
        assertTrue(
                user.getCreatedAt().isBefore(LocalDateTime.now()) || user.getCreatedAt().isEqual(LocalDateTime.now()));
    }
}
