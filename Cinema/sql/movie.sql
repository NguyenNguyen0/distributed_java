
-- Insert data into movies table
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M101','The Shawshank Redemption','Drama',1994,'Frank Darabont',142);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M102','The Godfather','Crime',1972,'Francis Ford Coppola',175);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M103','The Dark Knight','Action',2008,'Christopher Nolan',152);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M104','The Avengers','Action',2012,'Joss Whedon',143);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M105','Inception','Action',2010,'Christopher Nolan',148);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M106','The Matrix','Action',1999,'Lana Wachowski',136);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M107','Interstellar','Adventure',2014,'Christopher Nolan',169);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M108','The Lord of the Rings: The Fellowship of the Ring','Adventure',2001,'Peter Jackson',178);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M109','Forrest Gump','Drama',1994,'Robert Zemeckis',142);
INSERT INTO movies (movie_id, title, genre, release_year, director, duration) VALUES('M110','The Lion King','Animation',1994,'Roger Allers',88);

-- Insert data into movie_actors table
INSERT INTO movie_actors (movie_id, actor) VALUES('M101','Tim Robbins');
INSERT INTO movie_actors (movie_id, actor) VALUES('M101','Morgan Freeman');
INSERT INTO movie_actors (movie_id, actor) VALUES('M102','Marlon Brando');
INSERT INTO movie_actors (movie_id, actor) VALUES('M102','Al Pacino');
INSERT INTO movie_actors (movie_id, actor) VALUES('M103','Christian Bale');
INSERT INTO movie_actors (movie_id, actor) VALUES('M103','Heath Ledger');
INSERT INTO movie_actors (movie_id, actor) VALUES('M104','Robert Downey Jr.');
INSERT INTO movie_actors (movie_id, actor) VALUES('M104','Chris Evans');
INSERT INTO movie_actors (movie_id, actor) VALUES('M105','Leonardo DiCaprio');
INSERT INTO movie_actors (movie_id, actor) VALUES('M105','Joseph Gordon-Levitt');
INSERT INTO movie_actors (movie_id, actor) VALUES('M106','Keanu Reeves');
INSERT INTO movie_actors (movie_id, actor) VALUES('M106','Laurence Fishburne');
INSERT INTO movie_actors (movie_id, actor) VALUES('M107','Matthew McConaughey');
INSERT INTO movie_actors (movie_id, actor) VALUES('M107','Anne Hathaway');
INSERT INTO movie_actors (movie_id, actor) VALUES('M108','Elijah Wood');
INSERT INTO movie_actors (movie_id, actor) VALUES('M108','Viggo Mortensen');
INSERT INTO movie_actors (movie_id, actor) VALUES('M109','Tom Hanks');
INSERT INTO movie_actors (movie_id, actor) VALUES('M109','Robin Wright');
INSERT INTO movie_actors (movie_id, actor) VALUES('M110','Matthew Broderick');
INSERT INTO movie_actors (movie_id, actor) VALUES('M110','Jeremy Irons');

-- Insert data into customers table
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C101','John Doe',1980,'123-456-7890','123 Main Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C102','Jane Smith',1985,'234-567-8901','456 Elm Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C103','Jack Wilson',1990,'345-678-9012','789 Oak Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C104','Jill Johnson',1995,'456-789-0123','101 Pine Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C105','Jerry Brown',1988,'567-890-1234','222 Maple Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C106','Jackie White',1992,'678-901-2345','333 Birch Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C107','Jim Black',1983,'789-012-3456','444 Cedar Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C108','Jennifer Green',1978,'890-123-4567','555 Walnut Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C109','Jane Doe',1975,'901-234-5678','666 Pine Street');
INSERT INTO customers (customer_id, name, year_of_birth, phone, address) VALUES('C110','Jill Wilson',1970,'012-345-6789','777 Elm Street');

-- Insert data into shows table
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S101','2024-01-11 18:00:00','Hall 1','M101');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S102','2024-01-11 18:00:00','Hall 2','M101');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S103','2024-01-13 18:00:00','Hall 3','M102');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S104','2024-01-11 18:00:00','Hall 4','M102');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S105','2024-01-13 18:00:00','Hall 5','M103');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S106','2024-01-12 18:00:00','Hall 6','M103');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S107','2024-01-11 09:30:00','Hall 7','M101');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S108','2024-01-12 09:30:00','Hall 8','M102');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S109','2024-01-11 09:30:00','Hall 9','M103');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S110','2024-05-11 14:30:00','Hall 10','M101');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S111','2024-05-12 14:30:00','Hall 11','M104');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S112','2024-05-11 14:30:00','Hall 12','M105');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S113','2024-01-15 18:00:00','Hall 1','M106');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S114','2024-01-15 18:00:00','Hall 2','M106');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S115','2024-04-15 18:00:00','Hall 3','M107');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S116','2024-05-11 18:00:00','Hall 4','M107');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S117','2024-01-15 18:00:00','Hall 5','M108');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S118','2024-05-11 14:00:00','Hall 6','M108');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S119','2024-01-15 14:00:00','Hall 7','M109');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S120','2024-01-15 14:00:00','Hall 8','M109');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S121','2024-01-15 14:00:00','Hall 9','M110');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S122','2024-05-23 14:00:00','Hall 10','M110');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S123','2024-05-23 18:00:00','Hall 11','M102');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S124','2024-05-23 14:00:00','Hall 12','M110');
INSERT INTO shows (show_id, show_date_time, hall_name, movie_id) VALUES('S125','2024-05-23 14:00:00','Hall 11','M101');



-- Insert data into tickets table
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-101','A1','Standard',10,'2024-01-01','S101','C101');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-102','A2','Standard',12,'2024-01-01','S101','C102');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-103','A3','Standard',14,'2024-01-01','S101','C103');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-104','A4','VIP',18,'2024-01-01','S101','C104');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-105','A5','VIP',20,'2024-01-01','S101','C105');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-106','B1','Standard',10,'2024-01-02','S102','C106');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-107','B2','Standard',12,'2024-01-02','S102','C107');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-108','B3','Standard',14,'2024-01-02','S102','C108');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-109','B4','VIP',18,'2024-01-02','S102','C109');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-110','B5','VIP',20,'2024-01-02','S102','C110');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-111','C1','Standard',10,'2024-01-03','S103','C101');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-112','C2','Standard',12,'2024-01-03','S103','C102');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-113','C3','Standard',14,'2024-01-03','S103','C103');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-114','C4','VIP',18,'2024-01-03','S103','C104');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-115','C5','VIP',20,'2024-01-03','S103','C105');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-116','D1','Standard',10,'2024-01-04','S104','C106');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-117','D2','Standard',12,'2024-01-04','S104','C107');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-118','D3','Standard',14,'2024-01-04','S105','C108');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-119','D4','VIP',18,'2024-01-04','S106','C109');
INSERT INTO tickets (ticket_number, seat, type, price, booking_date, show_id, customer_id) VALUES('0121-120','D5','VIP',20,'2024-01-04','S106','C110');



SELECT * FROM movies;
SELECT * FROM movie_actors;
SELECT * FROM customers;
SELECT * FROM shows;
SELECT * FROM tickets;