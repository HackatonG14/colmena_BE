package dev.hack14.colmena.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.hack14.colmena.models.Notification;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.models.Contact;
import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.repositories.NotificationRepository;
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
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notification;
    private User user;
    private Contact contact;
    private Ad ad;

    @BeforeEach
    void setUp() {
        user = new User();
        ad = new Ad();
        contact = new Contact();
        notification = new Notification(user, "Test message", ad, contact);
        notification.setId(1L);
    }

    @Test
    void testGetAllNotifications() {
        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification));

        List<Notification> notifications = notificationService.getAllNotifications();

        assertEquals(1, notifications.size());
        assertEquals(notification, notifications.get(0));
    }

    @Test
    void testGetNotificationById() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        Optional<Notification> foundNotification = notificationService.getNotificationById(1L);

        assertTrue(foundNotification.isPresent());
        assertEquals(notification, foundNotification.get());
    }

    @Test
    void testGetNotificationsByUser() {
        when(notificationRepository.findByUserOrderByCreatedAtDesc(user)).thenReturn(Arrays.asList(notification));

        List<Notification> notifications = notificationService.getNotificationsByUser(user);

        assertEquals(1, notifications.size());
        assertEquals(notification, notifications.get(0));
    }

    @Test
    void testCreateNotification() {
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationService.createNotification(notification);

        assertNotNull(savedNotification);
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    void testMarkAsRead() {
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification updatedNotification = notificationService.markAsRead(1L);

        assertNotNull(updatedNotification);
        assertTrue(updatedNotification.isRead());
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    void testDeleteNotification() {
        doNothing().when(notificationRepository).deleteById(1L);

        notificationService.deleteNotification(1L);

        verify(notificationRepository, times(1)).deleteById(1L);
    }
}