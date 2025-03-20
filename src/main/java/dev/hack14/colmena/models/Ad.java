package dev.hack14.colmena.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePosted;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User advertiser;

    private String imageUrl;

    public Ad(Long id, String title, String description, String category, Date datePosted, User advertiser, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.datePosted = datePosted;
        this.advertiser = advertiser;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public User getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(User advertiser) {
        this.advertiser = advertiser;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
