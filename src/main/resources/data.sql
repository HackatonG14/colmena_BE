INSERT INTO users (id, username, email, password, created_at, role) 
VALUES (default, 'user1', 'admin@example.com', 'hashed_password', NOW(), 'ADMIN');

INSERT INTO ad (id, title, description, category, date_posted, user_id, image_url)
VALUES (default, 'Anuncio de prueba', 'Este es un anuncio de prueba', 'Tecnología', NOW(), 1, 'https://pixabay.com/es/photos/mostrar-vidrio-din%C3%A1mica-reflex-7439375/');