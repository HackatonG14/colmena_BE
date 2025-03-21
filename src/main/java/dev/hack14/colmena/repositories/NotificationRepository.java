package dev.hack14.colmena.repositories;

import dev.hack14.colmena.models.Notification;
import dev.hack14.colmena.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Métodos originales que usan la entidad User
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
    List<Notification> findByUserAndReadOrderByCreatedAtDesc(User user, boolean read);
    long countByUserAndRead(User user, boolean read);
    
    // Nuevos métodos que usan userId directamente
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    List<Notification> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);
    
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.read = :read ORDER BY n.createdAt DESC")
    List<Notification> findByUserIdAndReadOrderByCreatedAtDesc(@Param("userId") Long userId, @Param("read") boolean read);
    
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.read = :read")
    long countByUserIdAndRead(@Param("userId") Long userId, @Param("read") boolean read);
}
