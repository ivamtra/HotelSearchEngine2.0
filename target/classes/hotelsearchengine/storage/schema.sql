--
-- Þessi skrá sýnir yfirlit yfir gagnagrunninn.
--
-- Til að búa til gagnagrunninn og lesa queries.sql þarf að keyra
-- sqlite3 test.db < schema.sql < queries.sql
--
-----------------------------------------------------------------

CREATE TABLE Rooms (
	roomId serial PRIMARY KEY,
	size int,
	hotelId int,
	price int,
	FOREIGN KEY (hotelId) references Hotels(hotelId)
);
CREATE TABLE Persons (
	personId INTEGER PRIMARY KEY,
	name varchar(100),
	password varchar(100),
	isOwner boolean
);

CREATE TABLE Reviews (
    hotelId integer NOT NULL,
    personId int NOT NULL,
    review varchar(300),
    rating integer,
    FOREIGN KEY (hotelId) references Hotels(hotelId),
    FOREIGN KEY (personId) references Persons(personId),
    PRIMARY KEY(hotelId, personId)
);
CREATE TABLE Hotels (
    hotelId INTEGER Primary Key ,
    hotelName varchar(100),
    hotelDescription varchar(1000),
    location varchar(100),
    hotelStars integer,
    averageReview float,
    hotelContactInfo varchar(100),
    hotelOwner varchar(100),
    FOREIGN KEY (hotelOwner) references Persons(personId)
);
CREATE TABLE HotelImages (
    id serial PRIMARY KEY,
    hotelId int NOT NULL,
    imageURL varchar(100),
    FOREIGN KEY (hotelId) references Hotels(hotelId)
);
CREATE TABLE Services (
    id serial PRIMARY KEY,
    service varchar(100)
);
CREATE TABLE HotelHasService (
    id serial PRIMARY KEY,
    hotelId int NOT NULL,
    serviceId int NOT NULL,
    FOREIGN KEY (hotelId) references Hotels(hotelId),
    FOREIGN KEY (serviceId) references Services(id)
);
CREATE TABLE Bookings (
    bookingId serial PRIMARY KEY,
    roomId int,
    personId int,
    startDate Date,
    endDate DATE,
    FOREIGN KEY (personId) references Persons(personId),
    FOREIGN KEY (roomId) references Rooms(roomId)
);




-- CREATE TABLE Images (
-- 	FOREIGN KEY hotelId integer,
-- 	imgName text,
-- 	img bytea
-- 	hotelId references Hotels(hotelId)
-- );

