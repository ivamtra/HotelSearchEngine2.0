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

        Hotel[] hotels = new Hotel[2];
        hotels[0] = new Hotel(0, 4, "Fínt hótel");
        hotels[1] = new Hotel(1, 3, "Allt í lagi hótel");

        return hotels;
    }
}
