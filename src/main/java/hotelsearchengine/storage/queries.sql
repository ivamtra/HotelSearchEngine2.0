-----------------------------------------------------------------
--
-- Þessi skrá inniheldur allar skipanir sem fara inn í gagna-
-- grunninn.
--
--
--
--
-----------------------------------------------------------------






------------ Bæta inn í Hotels töfluna -------------------------

-- (hotelId, hotelName, hotelDescription, location, hotelStars integer, averageReview, hotelContactInfo, hotelOwner)

INSERT INTO Hotels VALUES(1, "Hotel saga", "Þetta er hotel saga", "Reykjavik", 8, 9.0, "581234", "Kalli");

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

------------ Bæta inn í Reviews töfluna -------------------------

--  (hotelId,personId ,review ,rating)

INSERT INTO Reviews VALUES(1, 1, "Þetta er mjög gott hótel", 8);

INSERT INTO Reviews VALUES(1, 2, "Ég fýlaði þetta", 1);

------------ Bæta inn í Rooms töfluna -------------------------

-- (roomId, size, hotelId, price)

INSERT INTO Rooms VALUES(1, 75, 1, 20000);

------------ Bæta inn í Bookings töfluna -------------------------

-- (bookingId, roomId, personId, startDate, endDate)

INSERT INTO Bookings VALUES(1, 1, 1, date('2022-04-20'), date('2022-04-24'));

------------ Bæta inn í Persons töfluna -------------------------

-- 	(personId, name, password, isOwner)

insert into Persons VALUES(1, "Sös", "apakisi123", FALSE);

INSERT INTO Persons VALUES(2, "Siggi", "123456789", FALSE);
