package hotelsearchengine.storage;

import hotelsearchengine.models.*;
import java.util.*;

public  interface DatabaseInterface {

    public void addReviews(Review review);

    public List<Review> getHotelReviews(int hotelId);

    public List<Room> getHotelRooms(Restrictions restrictions);

    public List<Hotel> getHotels(Restrictions restrictions);

    public double getAvgRating(int hotelId);

    public void addHotel(Hotel hotel);

    // Í staðinn fyrir getAvailability
    // Skilar öllum bookings með gefnum restrictions.
    // Vinnslan mun höndla getAvailability
    // Skilar Booking fylki
    public Booking[] getBookings(int roomId);

    public Booking book(int roomId, int personId, Date startDate, Date endDate);

    public Booking unbook(int bookingId);

    public Person login(String name, String password);

    public int logout(String name);


}