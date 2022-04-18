package hotelsearchengine.storage;

import hotelsearchengine.models.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class databaseHelperMock implements DatabaseInterface {

    @Override
    public void addReviews(Review review) {
        // TODO Auto-generated method stub
    }
    @Override
    public void addRoom(Room room){
        return;
    }
    @Override
    public int getOwner(int hotelId) {
        return 0;
    }

    @Override
    public List<Review> getHotelReviews(int hotelId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Room> getHotelRooms(Restrictions restrictions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Hotel getHotel(int hotelId) {
        return null;
    }

    @Override
    public List<Hotel> getHotels(Restrictions restrictions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Service> getAllServices() {
        return null;
    }

    @Override
    public double getAvgRating(int hotelId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addHotel(Hotel hotel) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Booking> getBookings(int roomId) {
        if(roomId==100){
            Booking[] bookings = {
                    new Booking(1,100,201,new Date(1000000), new Date(2000000)),
                    new Booking(2,100,202,new Date(3000000), new Date(4000000)),
                    new Booking(2,100,202,new Date(8000000), new Date(9000000)),
                    new Booking(2,100,202,new Date(10000000), new Date(11000000))
            };

            return null;
            //return bookings; //Breyta í einhver gögn
        }
        if(roomId==101){
            Booking[] bookings = {
                    new Booking(1,100,201,new Date(1000000), new Date(2000000)),
                    new Booking(2,100,202,new Date(3000000), new Date(4000000)),
                    new Booking(2,100,202,new Date(5500000), new Date(7000000)),
                    new Booking(2,100,202,new Date(10000000), new Date(11000000))
            };
            //return bookings; //Breyta í einhver gögn
            return null;
        }
        return null;
    }

    @Override
    public Booking book(int roomId, int personId, Date startDate, Date endDate) {
        Booking booking = new Booking(1, roomId, personId, startDate, endDate);
        return booking;
    }

    @Override
    public Booking unbook(int bookingId) {
        //Booking booking = new Booking();
        //return booking;
        return null;

    }

    @Override
    public Person login(String name, String password) {
        Person person = new Person(name, password, 1);
        return person;
    }
    @Override
    public List<Room> getRoomsInHotels(int hotelId) {
        return null;
    }

    @Override
    public int logout(String name) {
        // Þarf þess aðferð???
        return 0;
    }
    @Override
    public boolean isOwner(int personId){
        return false;
    }

    @Override
    public int deleteLineFromTable(String reviews, String reviewId, int reviewId1) {
        return 0;
    }

    @Override
    public int[] deleteReview(Review review) {
        return new int[0];
    }
}
