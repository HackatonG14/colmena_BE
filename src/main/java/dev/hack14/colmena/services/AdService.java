package dev.hack14.colmena.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.repositories.AdRepository;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;
    
    public List<Ad> getAllAds(){
        return adRepository.findAll();
    }

    public Optional<Ad> getAdById(Long id){
        return adRepository.findById(id);
    }

    public List<Ad> getAdsbyTitle(String title){
        return adRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Ad> getAdsByCategory(String category){
        return adRepository.findByCategory(category);
    }

    public List<Ad> getAdsByAdmin(User admin){
        return adRepository.findByAdmin(admin);
    }

    public Ad createAd(Ad ad){
        return adRepository.save(ad);
    }

    public Ad updateAd(Ad ad){
        return adRepository.save(ad);
    }

    public void deleteAd(Long id){
        adRepository.deleteById(id);
    }

}
