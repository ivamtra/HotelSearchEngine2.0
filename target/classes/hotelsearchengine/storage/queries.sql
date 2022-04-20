-----------------------------------------------------------------
--
-- Þessi skrá inniheldur allar skipanir sem fara inn í gagna-
-- grunninn.
--
--
--
--
-----------------------------------------------------------------


------------ Restarta gagnagrunninn ---------------------------

DROP TABLE Hotels;
DROP TABLE Services;
DROP TABLE HotelHasService;
DROP TABLE HotelImages;
DROP TABLE Reviews;
DROP TABLE Rooms;
DROP TABLE Bookings;
DROP TABLE Persons;



------------ Bæta inn í Hotels töfluna -------------------------

-- (hotelId, hotelName, hotelDescription, location, hotelStars integer, averageReview, hotelContactInfo, hotelOwner)

INSERT INTO Hotels VALUES(1, "Hotel saga", "Þetta er hotel saga", "Reykjavik", 8, 9.0, "581234", "Kalli");
INSERT INTO Hotels VALUES(2, "Hilton Reykjavík Nordica", "Þetta er hotel hilton", "Reykjavik", 4, 3.0, "581234", "Siggi");
INSERT INTO Hotels VALUES(3, "Hótel Bjarkalundur", "Þetta er hotel bjarkalundur", "Bjarkalundur", 10, 10.0, "581234", "Siggi");
INSERT INTO Hotels VALUES(4, "Kvosin Hotel", "Þetta er hotel kvosin", "Reykjavik", 6, 6.0, "581234", "Kalli");
INSERT INTO Hotels VALUES(5, "Center Hotels Arnarhvoll", "Þetta er hotel Center Hotels Arnarhvoll", "Reykjavík", 4, 4.0, "581234", "Siggi");
INSERT INTO Hotels VALUES(6, "Refurinn gistihús", "Þetta er refurinn gistihús", "Reykjavik", 3, 3.0, "581234", "Kalli");


------------ Bæta inn í Services töfluna -------------------------

-- (id, service)

INSERT INTO Services VALUES(1, "Casino");
INSERT INTO Services VALUES(2, "Gym");
INSERT INTO Services VALUES(3, "Pool");
INSERT INTO Services VALUES(4, "Restaurant");
INSERT INTO Services VALUES(5, "Spa");
INSERT INTO Services VALUES(6, "Wifi");
INSERT INTO Services VALUES(7, "Parking");
INSERT INTO Services VALUES(8, "TV");
INSERT INTO Services VALUES(9, "Air Conditioning");
INSERT INTO Services VALUES(10, "Bar");
INSERT INTO Services VALUES(11, "Laundry");
INSERT INTO Services VALUES(12, "Pets");
INSERT INTO Services VALUES(13, "Wheelchair Access");
INSERT INTO Services VALUES(14, "Smoking");
INSERT INTO Services VALUES(15, "Wheelchair");
INSERT INTO Services VALUES(16, "Airport Shuttle");

------------ Bæta inn í HotelHasService töfluna -------------------------

-- (id, hotelId, serviceId)

INSERT INTO HotelHasService VALUES(1, 1, 1);
INSERT INTO HotelHasService VALUES(2, 1, 2);

------------ Bæta inn í HotelImages töfluna -------------------------

-- (id, hotelId, imageURL)

INSERT INTO HotelImages VALUES(1, 1, "1_1.jpeg");
INSERT INTO HotelImages VALUES(2, 1, "1_2.jpeg");
INSERT INTO HotelImages VALUES(3, 2, "2_1.jpeg");
INSERT INTO HotelImages VALUES(4, 2, "2_2.jpeg");
INSERT INTO HotelImages VALUES(5, 2, "2_3.jpeg");
INSERT INTO HotelImages VALUES(6, 3, "3_1.jpeg");
INSERT INTO HotelImages VALUES(7, 3, "3_2.jpeg");
INSERT INTO HotelImages VALUES(8, 3, "3_3.jpeg");
INSERT INTO HotelImages VALUES(9, 4, "4_1.jpeg");
INSERT INTO HotelImages VALUES(10, 5, "5_1.jpeg");
INSERT INTO HotelImages VALUES(11, 6, "6_1.jpeg");

------------ Bæta inn í Reviews töfluna -------------------------

--  (hotelId,personId ,review ,rating)

INSERT INTO Reviews VALUES(1, 1, "Þetta er mjög gott hótel", 8);

INSERT INTO Reviews VALUES(1, 2, "Ég fýlaði þetta", 1);

------------ Bæta inn í Rooms töfluna -------------------------

-- (roomId, size, hotelId, price)

INSERT INTO Rooms VALUES(1, 75, 1, 20000);
INSERT INTO Rooms VALUES(2, 5, 1, 15000);
INSERT INTO Rooms VALUES(3, 2, 1, 10000);
INSERT INTO Rooms VALUES(4, 1, 1, 8000);
INSERT INTO Rooms VALUES(5, 1, 1, 5000);
INSERT INTO Rooms VALUES(6, 6, 2, 25000);
INSERT INTO Rooms VALUES(7, 3, 2, 12000);
INSERT INTO Rooms VALUES(8, 3, 2, 12500);
INSERT INTO Rooms VALUES(9, 1, 2, 6000);
INSERT INTO Rooms VALUES(10, 1, 2, 10000);
INSERT INTO Rooms VALUES(11, 7, 3, 20000);
INSERT INTO Rooms VALUES(12, 5, 3, 15000);
INSERT INTO Rooms VALUES(13, 4, 3, 17000);
INSERT INTO Rooms VALUES(14, 4, 3, 15000);
INSERT INTO Rooms VALUES(15, 2, 3, 10000);
INSERT INTO Rooms VALUES(16, 1, 4, 10000);
INSERT INTO Rooms VALUES(17, 2, 4, 10000);
INSERT INTO Rooms VALUES(18, 3, 4, 10500);
INSERT INTO Rooms VALUES(19, 4, 4, 11000);
INSERT INTO Rooms VALUES(20, 5, 4, 11000);
INSERT INTO Rooms VALUES(21, 5, 5, 20000);
INSERT INTO Rooms VALUES(22, 1, 5, 15000);
INSERT INTO Rooms VALUES(23, 2, 5, 90000);
INSERT INTO Rooms VALUES(24, 3, 5, 9000);
INSERT INTO Rooms VALUES(25, 4, 5, 10000);
INSERT INTO Rooms VALUES(26, 1, 6, 5000);
INSERT INTO Rooms VALUES(27, 1, 6, 5000);
INSERT INTO Rooms VALUES(28, 2, 6, 10000);
INSERT INTO Rooms VALUES(29, 2, 6, 10000);
INSERT INTO Rooms VALUES(30, 3, 6, 15000);


------------ Bæta inn í Bookings töfluna -------------------------

-- (bookingId, roomId, personId, startDate, endDate)

INSERT INTO Bookings VALUES(12,1,1,1650412800000,1650758400000);
INSERT INTO Bookings VALUES(13,2,1,1650412800000,1650758400000);
INSERT INTO Bookings VALUES(14,3,1,1650412800000,1650758400000);
INSERT INTO Bookings VALUES(15,4,1,1650412800000,1650758400000);
INSERT INTO Bookings VALUES(16,5,1,1650412800000,1650758400000);
INSERT INTO Bookings VALUES(17,1,1,1651449600000,1652486400000);
INSERT INTO Bookings VALUES(18,2,1,1651536000000,1652745600000);
INSERT INTO Bookings VALUES(19,3,1,1652140800000,1653350400000);
INSERT INTO Bookings VALUES(20,4,1,1652572800000,1653350400000);
INSERT INTO Bookings VALUES(21,5,1,1652745600000,1654128000000);

------------ Bæta inn í Persons töfluna -------------------------

-- 	(personId, name, password, isOwner)

insert into Persons VALUES(1, "Sös", "apakisi123", FALSE);

INSERT INTO Persons VALUES(2, "Siggi", "123456789", FALSE);
