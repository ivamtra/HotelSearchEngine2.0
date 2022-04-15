-----------------------------------------------------------------
--
-- Þessi skrá sýnir yfirlit yfir gagnagrunninn.
--
--
--
--
-----------------------------------------------------------------

CREATE TABLE Hotels (
    hotelId int NOT NULL,
    hotelName varchar(100),
    hotelDescription varchar(1000),
    location varchar(100),
    hotelStars integer,
    averageReview float,
    --Myndir?
    hotelContactInfo varchar(100),
    hotelOwner varchar(100),
    hasGym boolean,
    hasCasino boolean,
    FOREIGN KEY (hotelOwner) references Persons(personId),
    PRIMARY KEY(hotelId)
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

CREATE TABLE Rooms (
	roomId int NOT NULL,
	size int,
	hotelId int,
	price int,
	FOREIGN KEY (hotelId) references Hotels(hotelId),
	PRIMARY KEY(roomId)
);
	

CREATE TABLE Bookings (
	bookingId int NOT NULL,
	roomId int,
	personId int,
	startDate Date,
	endDate DATE,
	FOREIGN KEY (personId) references Persons(personId),
	FOREIGN KEY (roomId) references Rooms(rommId),
	PRIMARY KEY(bookingId)
);

CREATE TABLE Persons (
	personId int NOT NULL,
	name varchar(100),
	password varchar(100),
	isOwner boolean,
	PRIMARY KEY(personId)
);




-- CREATE TABLE Images (
-- 	FOREIGN KEY hotelId integer,
-- 	imgName text,
-- 	img bytea
-- 	hotelId references Hotels(hotelId)
-- );

