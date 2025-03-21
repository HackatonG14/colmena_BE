INSERT INTO users (username, email, password, created_at, role) 
VALUES ('admin', 'admin@colmena.com', 'password', NOW(), 'ADMIN');

INSERT INTO users (username, email, password, created_at, role) 
VALUES ('admin2', 'admin2@colmena.com', '1234', NOW(), 'ADMIN');

INSERT INTO users (username, email, password, created_at, role) 
VALUES ('user3', 'user3@colmena.com', 'doremi', NOW(), 'USER');

INSERT INTO users (username, email, password, created_at, role) 
VALUES ('user4', 'user4@colmena.com', 'sollasi', NOW(), 'USER');

INSERT INTO ad (title, description, category, date_posted, user_id, image_url) 
VALUES ('Clases de Guitarra', 'Ofrezco clases de guitarra para principiantes a cambio de clases de inglés', 'Música', NOW(), 1, 'https://example.com/guitar.jpg');

INSERT INTO ad (title, description, category, date_posted, user_id, image_url) 
VALUES ('Intercambio Cocina por Programación', 'Puedo enseñar a cocinar platos tradicionales a cambio de ayuda con programación en Python', 'Cocina', NOW(), 2, 'https://example.com/cooking.jpg');

INSERT INTO contacts (sender_id, ad_id, message, contact_email, contact_phone, created_at) 
VALUES (2, 1, 'Hola, estoy interesado en tus clases de guitarra. Yo puedo ofrecerte clases de inglés a cambio.', 'user3@colmena.com', '123456789', NOW());

INSERT INTO contacts (sender_id, ad_id, message, contact_email, contact_phone, created_at) 
VALUES (1, 2, 'Me interesa tu oferta de intercambio de cocina. Soy desarrollador Python y puedo ayudarte.', 'user4@colmena.com', '987654321', NOW());