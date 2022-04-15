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

-- (hotelId, hotelName, hotelDescription, location, hotelStars integer, averageReview, hotelContactInfo, hotelOwner, hasGym, hasCasino)

INSERT INTO Hotels VALUES(1, "Hotel saga", "Þetta er hotel saga", "Reykjavik", 8, 9.0, "581234", "Kalli", TRUE, TRUE); 


------------ Bæta inn í Reviews töfluna -------------------------

--  (hotelId,personId ,review ,rating)

INSERT INTO Reviews VALUES(1, 1, "Þetta er mjög gott hótel", 8);

INSERT INTO Reviews VALUES(1, 2, "Ég fýlaði þetta", 1);

------------ Bæta inn í Rooms töfluna -------------------------

-- (roomId, size, hotelId, price)

INSERT INTO Rooms VALUES(1, 75, 1, 20000);

------------ Bæta inn í Bookings töfluna -------------------------

-- (bookingId, roomId, personId, startDate, endDate)

INSERT INTO Bookings VALUES(1, 1, 1, date(20-04-2022), date(24-04-2022));
------------ Bæta inn í Persons töfluna -------------------------

-- 	(personId, name, password, isOwner)

insert into Persons VALUES(1, "Sös", "apakisi123", FALSE);

INSERT INTO Persons VALUES(2, "Siggi", "123456789", FALSE);
