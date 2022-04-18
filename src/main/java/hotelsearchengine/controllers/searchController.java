package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.models.Room;
import hotelsearchengine.models.Review;
import hotelsearchengine.models.Service;
import hotelsearchengine.storage.DatabaseInterface;
import java.util.Date;
import java.util.ArrayList;


public class searchController {

    private DatabaseInterface db;
    private loginController login;

    public searchController(DatabaseInterface DB, loginController LOGIN){
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
    public ArrayList<Hotel> searchHotels(Restrictions r){
        return (ArrayList<Hotel>) this.db.getHotels(r);
    }

    public ArrayList<Room> searchHotelRooms(Integer minPrice, Integer maxPrice, Integer minStars, Integer maxStars, String name, String location, ArrayList<Service> services, Date startDate, Date endDate, Double avgRating, Integer minSize, Integer maxSize) {
        Restrictions r = new Restrictions(maxPrice, minPrice, maxStars, minStars, name, location, services, startDate, endDate,avgRating,minSize,maxSize);
        ArrayList<Room> rooms = (ArrayList<Room>) this.db.getHotelRooms(r);
        return rooms;
    }

    public ArrayList<Room> searchHotelRooms(Restrictions r) {
        return (ArrayList<Room>) this.db.getHotelRooms(r);
    }

    public Hotel getHotelById(int hotelId) {
        Hotel hotel = db.getHotel(hotelId);
        ArrayList<Review> reviews = (ArrayList<Review>) db.getHotelReviews(hotelId);
        hotel.setReviews(reviews);
        return hotel;
    }

    public ArrayList<Service> getAllServices() {
        return (ArrayList<Service>) this.db.getAllServices();
    }
}
