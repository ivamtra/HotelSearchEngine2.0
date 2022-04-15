package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.storage.DatabaseInterface;
import hotelsearchengine.storage.databaseHelper;

import java.util.Date;

public class searchController {

    private databaseHelper db;
    private loginController login;

    public searchController(databaseHelper DB, loginController LOGIN){
        this.db = DB;
        this.login = LOGIN;
    }

    public Hotel[] getAllHotels() {
        Restrictions r = new Restrictions();

        // TODO: Uncomment
        // Hotel[] hotels = this.db.getHotels(r);

        Hotel[] hotels = new Hotel[4];
        hotels[0] = new Hotel(1, 4, "Fínt hótel");
        hotels[1] = new Hotel(2, 3, "Allt í lagi hótel");
        hotels[2] = new Hotel(3, 4, "Lélegt Hótel");
        hotels[3] = new Hotel(4, 5, "Ekki hótel");

        return hotels;
    }
}
