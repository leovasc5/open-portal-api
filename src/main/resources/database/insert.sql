INSERT INTO portal.channel (name, is_active) VALUES
('Slack', 1),
('WhatsApp', 1),
('E-mail', 1);

INSERT INTO portal.user (name, email, password, permission, phone_number, is_receiver, date_time) VALUES
('Leonardo Vasconcelos', 'leovasc5@hotmail.com', '$2a$10$1HN8ARFUDccGbHoFSu5YpeaPWSKH9WBYkaAUn8sogZWUYQ5mapJtu', 'ADMIN', '11912345678', 1, NOW()),
('John Doe', 'john.doe@his.domain', '$2a$10$1HN8ARFUDccGbHoFSu5YpeaPWSKH9WBYkaAUn8sogZWUYQ5mapJtu', 'USER', '11987654321', 0, NOW());
-- ALL PASSWORDS CAN BE ACCESSED WITH: 123456


INSERT INTO portal.image_configuration (quantity_consumed, total_quota) VALUE
(0, 33554432);

INSERT INTO portal.city (name, is_active) VALUES
('New York', 1),
('Los Angeles', 1),
('Chicago', 1),
('São Paulo', 1),
('Philadelphia', 1),
('Ciudade del México', 1),
('Washington DC', 1),
('Boston', 1),
('Buenos Aires', 1),
('Dallas', 1),
('Atlanta', 1),
('San Francisco', 1),
('Houston', 1),
('Miami', 1),
('Toronto', 1),
('Detroit', 1),
('Seattle', 1),
('Rio de Janeiro', 1),
('Phoenix', 1),
('Minneapolis', 1),
('San Diego', 1),
('Denver', 1),
('Montreal', 1),
('Baltimore', 1),
('Osasco', 1);

INSERT INTO portal.category (name, is_active) VALUES
('Agriculture and Livestock', 1),
('Food and Gastronomy', 1),
('Independent Artist', 1),
('Beauty, Cosmetics, and Personal Care', 1),
('Personal Blog', 1),
('Shopping and Retail', 1),
('Community', 1),
('Construction and Engineering', 1),
('Human Resources Consulting', 1),
('Business Consulting', 1),
('Legal Consulting', 1),
('Medical Office', 1),
('Dental Office', 1),
('Digital Creator', 1),
('Graphic Design', 1),
('Editor', 1),
('Education', 1),
('Entrepreneur', 1),
('Writer', 1),
('Events and Entertainment', 1),
('Photographer', 1),
('Gamer', 1),
('Real Estate', 1),
('Market', 1),
('Fashion and Style', 1),
('Musician/Band', 1),
('Product/Service', 1),
('Restaurant', 1),
('Mental Health and Well-being', 1),
('Cleaning and Maintenance Services', 1),
('Financial Services', 1),
('Sustainability and Environment', 1),
('Information Technology', 1),
('Tourism and Accommodation', 1),
('Apparel', 1);