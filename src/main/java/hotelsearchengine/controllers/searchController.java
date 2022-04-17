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
        Restrictions r = new Restrictions(null, null, null, null, null, null, null, null, null);
        ArrayList<Hotel> hotels = (ArrayList<Hotel>) this.db.getHotels(r);
        return hotels;
    }

    /**
     * Leita að hótelum eftirfarandi gildi á að setja sem 'null' ef ekki á að leita eftir þeim
     * @param maxPrice
     * @param minPrice
     * @param maxStars
     * @param minStars
     * @param hotelName
     * @param hotelLocation
     * @param availableFrom
     * @param availableTo
     * @param services
     * @return hotels (ArrayList<Hotel>)
     */
    public ArrayList<Hotel> searchHotels(Integer maxPrice, Integer minPrice, Integer maxStars, Integer minStars, String hotelName, String hotelLocation, String availableFrom, String availableTo, ArrayList<Service> services) {
        Restrictions r = new Restrictions(maxPrice, minPrice, maxStars, minStars, hotelName, hotelLocation, availableFrom, availableTo, services);
        ArrayList<Hotel> hotels = (ArrayList<Hotel>) this.db.getHotels(r);
        return hotels;
    }

    public ArrayList<Service> getAllServices() {
        return (ArrayList<Service>) this.db.getAllServices();
    }
}
