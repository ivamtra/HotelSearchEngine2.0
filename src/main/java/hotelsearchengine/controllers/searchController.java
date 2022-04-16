package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.storage.databaseHelper;

import java.util.ArrayList;

public class searchController {

    private databaseHelper db;
    private loginController login;

    public searchController(databaseHelper DB, loginController LOGIN){
        this.db = DB;
        this.login = LOGIN;
    }

    public ArrayList<Hotel> getAllHotels() {
        Restrictions r = new Restrictions();

        ArrayList<Hotel> hotels = (ArrayList<Hotel>) this.db.getHotels(r);

        //Hotel[] hotels = new Hotel[14];
        //hotels[0] = new Hotel(1, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Fínt hótel", hotelOwner);
        //hotels[1] = new Hotel(2, hotelName, hotelDescription, hotelLocation, 3, hotelAverageReview, "Allt í lagi hótel", hotelOwner);
        //hotels[2] = new Hotel(3, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Lélegt Hótel", hotelOwner);
        //hotels[3] = new Hotel(4, hotelName, hotelDescription, hotelLocation, 5, hotelAverageReview, "Ekki hótel", hotelOwner);
        //hotels[4] = new Hotel(5, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Fínt hótel", hotelOwner);
        //hotels[5] = new Hotel(6, hotelName, hotelDescription, hotelLocation, 3, hotelAverageReview, "Allt í lagi hótel", hotelOwner);
        //hotels[6] = new Hotel(7, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Lélegt Hótel", hotelOwner);
        //hotels[7] = new Hotel(8, hotelName, hotelDescription, hotelLocation, 5, hotelAverageReview, "Ekki hótel", hotelOwner);
        //hotels[8] = new Hotel(9, hotelName, hotelDescription, hotelLocation, 5, hotelAverageReview, "Ekki hótel", hotelOwner);
        //hotels[9] = new Hotel(10, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Fínt hótel", hotelOwner);
        //hotels[10] = new Hotel(11, hotelName, hotelDescription, hotelLocation, 3, hotelAverageReview, "Allt í lagi hótel", hotelOwner);
        //hotels[11] = new Hotel(12, hotelName, hotelDescription, hotelLocation, 4, hotelAverageReview, "Lélegt Hótel", hotelOwner);
        //hotels[12] = new Hotel(13, hotelName, hotelDescription, hotelLocation, 5, hotelAverageReview, "Ekki hótel", hotelOwner);
        //hotels[13] = new Hotel(14, hotelName, hotelDescription, hotelLocation, 5, hotelAverageReview, "Ekki hótel", hotelOwner);

        return hotels;
    }
}
