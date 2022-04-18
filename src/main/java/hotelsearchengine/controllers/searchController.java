package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.models.Service;
import hotelsearchengine.storage.databaseHelper;

import java.util.ArrayList;
import java.util.Date;

public class searchController {

    private databaseHelper db;
    private loginController login;

    public searchController(databaseHelper DB, loginController LOGIN){
        this.db = DB;
        this.login = LOGIN;
    }

    public ArrayList<Hotel> getAllHotels() {
        Restrictions r = new Restrictions(null, null, null, null, null, null, null, null, null,null,null,null);
        ArrayList<Hotel> hotels = (ArrayList<Hotel>) this.db.getHotels(r);
        return hotels;
    }


    public ArrayList<Hotel> searchHotels(Integer minPrice, Integer maxPrice, Integer minStars, Integer maxStars, String name, String location, ArrayList<Service> services, Date startDate, Date endDate, Double avgRating, Integer minSize, Integer maxSize) {
        Restrictions r = new Restrictions(maxPrice, minPrice, maxStars, minStars, name, location, services, startDate, endDate,avgRating,minSize,maxSize);
        ArrayList<Hotel> hotels = (ArrayList<Hotel>) this.db.getHotels(r);
        return hotels;
    }

    public ArrayList<Service> getAllServices() {
        return (ArrayList<Service>) this.db.getAllServices();
    }
}
