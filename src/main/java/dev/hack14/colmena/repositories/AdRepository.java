package dev.hack14.colmena.repositories;

import java.util.List;
import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByAdmin(User admin);
    List<Ad> findByTitleContainingIgnoreCase(String title);
    List<Ad> findByCategory(String category);
}
