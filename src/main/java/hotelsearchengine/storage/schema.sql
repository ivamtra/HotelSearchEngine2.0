-----------------------------------------------------------------
--
-- Þessi skrá sýnir yfirlit yfir gagnagrunninn.
--
--
--
--
-----------------------------------------------------------------

CREATE TABLE Rooms (
	roomId int NOT NULL,
	size int,
	hotelId int,
	price int,
	FOREIGN KEY (hotelId) references Hotels(hotelId),
	PRIMARY KEY(roomId)
);
CREATE TABLE Persons (
	personId int NOT NULL,
	name varchar(100),
	password varchar(100),
	isOwner boolean,
	PRIMARY KEY(personId)
);
CREATE TABLE Reviews (
    hotelId integer,
    personId int,
    review varchar(300),
    rating integer,
    FOREIGN KEY (hotelId) references Hotels(hotelId),
    FOREIGN KEY (personId) references Persons(personId),
    PRIMARY KEY(hotelId, personId)
);
CREATE TABLE Hotels (
    hotelId int NOT NULL,
    hotelName varchar(100),
    hotelDescription varchar(1000),
    location varchar(100),
    hotelStars integer,
    averageReview float,
    hotelContactInfo varchar(100),
    hotelOwner varchar(100),
    FOREIGN KEY (hotelOwner) references Persons(personId),
    PRIMARY KEY(hotelId)
);
CREATE TABLE HotelImages (
    id int NOT NULL,
    hotelId int NOT NULL,
    imageURL varchar(100),
    FOREIGN KEY (hotelId) references Hotels(hotelId),
    PRIMARY KEY(id)
);
CREATE TABLE Services (
    id int NOT NULL,
    service varchar(100),
    PRIMARY KEY(id)
);
CREATE TABLE HotelHasService (
    id int NOT NULL,
    hotelId int NOT NULL,
    serviceId int NOT NULL,
    FOREIGN KEY (hotelId) references Hotels(hotelId),
    FOREIGN KEY (serviceId) references Services(id),
    PRIMARY KEY(id)
);
CREATE TABLE Bookings (
    bookingId INTEGER PRIMARY KEY AUTOINCREMENT,
    roomId int,
    personId int,
    startDate Date,
    endDate DATE,
    FOREIGN KEY (personId) references Persons(personId),
    FOREIGN KEY (roomId) references Rooms(roomId)
);
CREATE TABLE sqlite_sequence(name,seq);




-- CREATE TABLE Images (
-- 	FOREIGN KEY hotelId integer,
-- 	imgName text,
-- 	img bytea
-- 	hotelId references Hotels(hotelId)
-- );

