package dev.hack14.colmena.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.services.AdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/ads")
public class AdController {
    @Autowired
    private AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/all") //no se si la ruta es la correcta
    public List<Ad> getAllAds() {
        return adService.getAllAds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable Long id) {
        Optional<Ad> ad = adService.getAdById(id);
        return ad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/title")
    public List<Ad> getAdsbyTitle(@PathVariable String title) {
        return adService.getAdsbyTitle(title);
    }

    @GetMapping("/category")
    public List<Ad> getAdsByCategory(@PathVariable String category) {
        return adService.getAdsByCategory(category);
    }

    @GetMapping("/admin")
    public List<Ad> getAdsByAdmin(@PathVariable User admin) {
        return adService.getAdsByAdmin(admin);
    }

    @PostMapping("/admin/{admin}")
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad) {
        Ad newAd = adService.createAd(ad);
        return ResponseEntity.ok(newAd);
    }

    @PutMapping("/admin/{admin}")
    public ResponseEntity<Ad> updateAd(@RequestBody Ad ad) {
        Ad updatedAd = adService.updateAd(ad);
        return ResponseEntity.ok(updatedAd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
