package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.models.Service;
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
        return hotels;
    }

    public ArrayList<Service> getAllServices() {
        return (ArrayList<Service>) this.db.getAllServices();
    }
}
