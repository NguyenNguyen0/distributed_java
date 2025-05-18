//create unique constraints
CREATE CONSTRAINT unique_customer_id FOR (n: Customer) REQUIRE n.customer_id IS UNIQUE;
CREATE CONSTRAINT unique_Room_id FOR (n: Room) REQUIRE n.room_id IS UNIQUE;
CREATE CONSTRAINT unique_Booking_id FOR (d: Booking) REQUIRE d.Booking_id IS UNIQUE;

//Loading data from CSV files
LOAD CSV WITH HEADERS FROM 'file:///hotel/customers.csv' AS row
WITH row WHERE row.customer_id IS NOT NULL
MERGE (c:Customer {customer_id: row.customer_id})
SET c.name = row.name,
    c.year_of_birth = toInteger(row.year_of_birth),
    c.phone = row.phone,
    c.address = row.address;

LOAD CSV WITH HEADERS FROM 'file:///hotel/rooms.csv' AS row
WITH row WHERE row.room_id IS NOT NULL
MERGE (r:Room {room_id: row.room_id})
SET r.type = row.type,
    r.capacity = toInteger(row.capacity),
    r.price = toFloat(row.price);

//and (b:Booking)-[:MADE_BY]->(c:Customer)
LOAD CSV WITH HEADERS FROM 'file:///hotel/bookings.csv' AS row
WITH row WHERE row.booking_id IS NOT NULL AND row.customer_id IS NOT NULL
MERGE (b:Booking {booking_id: row.booking_id})
SET b.booking_date = date(row.booking_date)
WITH row, b
MATCH (c:Customer {customer_id: row.customer_id})
MERGE (b)-[:MADE_BY]->(c);

//and (b:Booking)-[rl:BOOKED]->(r:Room)
LOAD CSV WITH HEADERS FROM 'file:///hotel/bookings_details.csv' AS row
WITH row WHERE row.booking_id IS NOT NULL AND row.room_id IS NOT NULL
MATCH (b:Booking {booking_id: row.booking_id})
MATCH (r:Room {room_id: row.room_id})
MERGE (b)-[rl:BOOKED]->(r)
SET rl.checkin = date(row.checkin),
    rl.checkout = date(row.checkout),
    rl.status = row.status;


MATCH (r:Room)<-[:BOOKED]-(b:Booking)
RETURN r, COUNT(b) AS bookingCount
ORDER BY bookingCount DESC
LIMIT 1;

MATCH (c:Customer {customer_id: "C004"})-[:MADE_BY]-()-[:BOOKED]->(r:Room) RETURN r;

MATCH (b:Booking)-[:BOOKED]->(r:Room)
RETURN r.type AS roomType, COUNT(b) AS bookingCount;
