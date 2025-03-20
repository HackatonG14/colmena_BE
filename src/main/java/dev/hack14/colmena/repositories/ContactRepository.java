package dev.hack14.colmena.repositories;

import dev.hack14.colmena.models.Contact;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.models.Ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findBySender(User sender);
    List<Contact> findByAd(Ad ad);
    List<Contact> findByAdAdmin(User admin);
}