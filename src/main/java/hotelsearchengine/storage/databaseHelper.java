package hotelsearchengine.storage;

import hotelsearchengine.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class databaseHelper implements DatabaseInterface {

    ResultSet resultSet;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;


    public databaseHelper() throws SQLException {
        connect();
    }

//     public static ResultSet query(String query) throws SQLException {
//         Connection conn;
//         try {
//             Class.forName("org.postgresql.Driver");
//             java.util.Properties props = new java.util.Properties();
//             conn = DriverManager.getConnection("jdbc:postgresql:COMPANY", props);
//         } catch (Exception e) {
//             try {
//                 String s = System.getProperty("user.dir") + "/src/main/resources/hotelsearchengine/storage/gagnagrunnur.db";
//                 System.out.println(s);
//                 Class.forName("org.sqlite.JDBC");
//                 conn = DriverManager.getConnection("jdbc:sqlite:" + s);
//             } catch (Exception e2) {
//                 conn = DriverManager.getConnection("jdbc:odbc:COMPANY");
//             }
//         }
//         PreparedStatement p = conn.prepareStatement(query);
//         p.clearParameters();
//         ResultSet r = p.executeQuery();
//         return r;
//     }

    private void connect() throws SQLException {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            java.util.Properties props = new java.util.Properties();
            connection = DriverManager.getConnection("jdbc:postgresql:COMPANY", props);
        } catch (Exception e) {
            try {
                String s = System.getProperty("user.dir") + "/src/main/resources/hotelsearchengine/storage/gagnagrunnur.db";
                System.out.println(s);
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + s);
            } catch (Exception e2) {
                connection = DriverManager.getConnection("jdbc:odbc:COMPANY");
            }
        }
        statement = connection.createStatement();
    }

    public static void main(String[] args) throws SQLException {
        databaseHelper db = new databaseHelper();

        db.getHotelReviews(1);

        ArrayList<Review> reviews = (ArrayList<Review>) db.getHotelReviews(1);

        for (Review r : reviews) {
            System.out.println(r.getComment());
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
    public List<Review> getHotelReviews(int hotelId) {
        ArrayList<Review> reviewList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("Select * from reviews where hotelId = ?");
            preparedStatement.setInt(1,hotelId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int reviewId = resultSet.getInt(1);
                int personId = resultSet.getInt(2);
                String reviewDescription = resultSet.getString(3);
                int rating = resultSet.getInt(4);

                Review review = new Review(reviewId, personId, reviewDescription, rating);

                reviewList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
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

