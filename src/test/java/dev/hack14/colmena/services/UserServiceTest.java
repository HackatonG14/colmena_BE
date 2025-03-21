package dev.hack14.colmena.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.hack14.colmena.dtos.UserDto;
import dev.hack14.colmena.enums.Role;
import dev.hack14.colmena.exceptions.ResourceNotFoundException;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {
        user1 = new User("testuser", "test@example.com", "password", Role.USER);
        user1.setId(1L);
        user1.setCreatedAt(LocalDateTime.now());

        user2 = new User("admin", "admin@example.com", "adminpass", Role.ADMIN);
        user2.setId(2L);
        user2.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("testuser", result.get(0).username());
        assertEquals("admin", result.get(1).username());

        verify(userRepository).findAll();
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        UserDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("testuser", result.username());
        assertEquals("test@example.com", result.email());

        verify(userRepository).findById(1L);
    }

    @Test
    public void testUserNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(99L);
        });

        verify(userRepository).findById(99L);
    }

    @Test
    public void testCreateUser() {
        User newUser = new User("newuser", "new@example.com", "newpass", Role.USER);
        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        UserDto result = userService.createUser(newUser);

        assertNotNull(result);
        assertEquals("newuser", result.username());
        assertEquals("new@example.com", result.email());

        verify(userRepository).existsByUsername("newuser");
        verify(userRepository).existsByEmail("new@example.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testCreateUserConflict() {
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        User newUser = new User("testuser", "new@example.com", "newpass", Role.USER);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(newUser);
        });

        verify(userRepository).existsByUsername("testuser");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        User updatedUser = new User("updateduser", "updated@example.com", "updatedpass", Role.USER);
        updatedUser.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        UserDto result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("updateduser", result.username());
        assertEquals("updated@example.com", result.email());

        verify(userRepository).findById(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        doNothing().when(userRepository).delete(user1);

        userService.deleteUser(1L);

        verify(userRepository).findById(1L);
        verify(userRepository).delete(user1);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(99L);
        });

        verify(userRepository).findById(99L);
        verify(userRepository, never()).delete(any(User.class));
    }
}
