package dev.hack14.colmena.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hack14.colmena.models.Ad;
import dev.hack14.colmena.models.User;
import dev.hack14.colmena.services.AdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AdService adService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Ad ad1;
    private Ad ad2;
    private User admin;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");

        ad1 = new Ad();
        ad1.setId(1L);
        ad1.setTitle("Clases de Guitarra");
        ad1.setDescription("Ofrezco clases de guitarra para principiantes a cambio de clases de inglés");
        ad1.setCategory("Música");
        ad1.setDatePosted(new Date());
        ad1.setAdmin(admin);
        ad1.setImageUrl("https://example.com/guitar.jpg");

        ad2 = new Ad();
        ad2.setId(2L);
        ad2.setTitle("Intercambio Cocina por Programación");
        ad2.setDescription("Puedo enseñar a cocinar platos tradicionales a cambio de ayuda con programación en Python");
        ad2.setCategory("Cocina");
        ad2.setDatePosted(new Date());
        ad2.setAdmin(admin);
        ad2.setImageUrl("https://example.com/cooking.jpg");

        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllAds_ShouldReturnAllAds() throws Exception {
        mockMvc.perform(get("/ads/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Clases de Guitarra")))
                .andExpect(jsonPath("$[1].title", is("Intercambio Cocina por Programación")));
    }

    @Test
    public void getAdById_WithExistingId_ShouldReturnAd() throws Exception {
        mockMvc.perform(get("/ads/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Clases de Guitarra")))
                .andExpect(jsonPath("$.description",
                        is("Ofrezco clases de guitarra para principiantes a cambio de clases de inglés")));
    }

    @Test
    public void getAdById_WithNonExistingId_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/ads/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAd_ShouldReturnCreatedAd() throws Exception {
        mockMvc.perform(post("/ads/admin/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ad1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Clases de Guitarra")));
    }

    @Test
    public void updateAd_ShouldReturnUpdatedAd() throws Exception {
        mockMvc.perform(put("/ads/admin/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ad1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Clases de Guitarra")));
    }

}
