package hotelsearchengine.storage;

import hotelsearchengine.models.*;

import java.sql.*;
import java.util.Date;

public class databaseHelper implements DatabaseInterface{

    public static ResultSet query(String query) throws SQLException {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            java.util.Properties props = new java.util.Properties();
            conn = DriverManager.getConnection("jdbc:postgresql:COMPANY", props);
        } catch (Exception e) {
            try {
                String s = System.getProperty("user.dir") + "/src/main/resources/hotelsearchengine/storage/gagnagrunnur.db";
                System.out.println(s);
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + s);
            } catch (Exception e2) {
                conn = DriverManager.getConnection("jdbc:odbc:COMPANY");
            }
        }
        PreparedStatement p = conn.prepareStatement(query);
        p.clearParameters();
        ResultSet r = p.executeQuery();
        return r;
    }
    public static void main(String[] args) {
        String query = "select * from Hotels";
        ResultSet r;
        try {
            r = query(query);
            while (r.next()) {
                System.out.println(r.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

    }
    @Override
    public Booking unbook(int bookingId) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Person login(String name, String password) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void addReviews(Review review) {
        // TODO Auto-generated method stub

    }
    @Override
    public Review[] getHotelReviews(int hotelId) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Room[] getHotelRooms(Restrictions restrictions) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Hotel[] getHotels(Restrictions restrictions) {
        // TODO Auto-generated method stub
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
    public Booking[] getBookings(int roomId) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Booking book(int roomId, int personId, Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public int logout(String name) {
        // TODO Auto-generated method stub
        return 0;
    }

}

