package dev.hack14.colmena.services;

import dev.hack14.colmena.models.Notification;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.models.Contact;
import dev.hack14.colmena.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Métodos originales que usan la entidad User
    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Notification> getUnreadNotificationsByUser(User user) {
        return notificationRepository.findByUserAndReadOrderByCreatedAtDesc(user, false);
    }

    public long countUnreadNotificationsByUser(User user) {
        return notificationRepository.countByUserAndRead(user, false);
    }

    // Nuevos métodos que usan userId directamente
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdAndReadOrderByCreatedAtDesc(userId, false);
    }

    public long countUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.countByUserIdAndRead(userId, false);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification createContactNotification(Contact contact) {
        User adOwner = contact.getAd().getAdmin();
        String message = "Alguien está interesado en tu anuncio: " + contact.getAd().getTitle();
        
        Notification notification = new Notification(
            adOwner,
            message,
            contact.getAd(),
            contact
        );
        
        return notificationRepository.save(notification);
    }

    public Notification markAsRead(Long id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }

    public void markAllAsReadByUserId(Long userId) {
        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndReadOrderByCreatedAtDesc(userId, false);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}