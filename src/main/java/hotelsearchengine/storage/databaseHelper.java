package hotelsearchengine.storage;

import hotelsearchengine.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class databaseHelper implements DatabaseInterface {

    ResultSet resultSet;
    ResultSet imageResultSet;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    PreparedStatement imagesPreparedStatement;

    public databaseHelper() throws SQLException {
        connect();
    }

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

        // db.getAvgRating(1);

        ArrayList<Booking> bookings = (ArrayList<Booking>) db.getBookings(1);

        for (Booking b : bookings) {
            System.out.println(b.getBookingId());
            System.out.println(b.getRoomId());
            System.out.println(b.getCustomerId());
            System.out.println(b.getStartDate());
            System.out.println(b.getEndDate());
        }


    }


    @Override
    public Booking unbook(int bookingId) {
        // TODO Prófa þetta
        Booking booking = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from Bookings where bookingId = ?");
            preparedStatement.setInt(1, bookingId);
            resultSet = preparedStatement.executeQuery();
            //bookingId er primary key svo bara 0 eða 1 bókun sem kemur
            if(!resultSet.next()){
                return null;
            }
            int rId = resultSet.getInt(2);
            int pId = resultSet.getInt(3);
            java.sql.Date sDate = resultSet.getDate(4);
            java.sql.Date eDate = resultSet.getDate(5);
            booking = new Booking(bookingId,rId,pId,sDate,eDate);
            preparedStatement = connection.prepareStatement("Delete from Bookings where bookingId = ?");
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Person login(String name, String password) {
        // TODO Prófa þetta
        Person person = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from Persons where name = ? AND password = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            int pId = resultSet.getInt(1);
            person = new Person(name,password,pId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void addReviews(Review review, int hotelId) {
        try {
            preparedStatement = connection.prepareStatement("Insert Into Reviews Values(?,?,?,?)");
            preparedStatement.setInt(1,hotelId);
            preparedStatement.setInt(2,review.getCustomerId());
            preparedStatement.setString(3,review.getComment());
            preparedStatement.setDouble(4,review.getRating());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getHotelReviews(int hotelId) {
        ArrayList<Review> reviewList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("Select * from reviews where hotelId = ?");
            preparedStatement.setInt(1,hotelId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int hId = resultSet.getInt(1);
                int personId = resultSet.getInt(2);
                String reviewDescription = resultSet.getString(3);
                int rating = resultSet.getInt(4);

                Review review = new Review(hId, personId, reviewDescription, rating);

                reviewList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }


    @Override
    public List<Room> getHotelRooms(Restrictions restrictions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Hotel> getHotels(Restrictions restrictions) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("Select * from hotels");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int hotelId = resultSet.getInt(1);
                String hotelName = resultSet.getString(2);

                String hotelDescription = resultSet.getString(3);
                String hotelLocation = resultSet.getString(4);
                int hotelStars = resultSet.getInt(5);
                Double hotelAverageReview = resultSet.getDouble(6);
                String hotelContactInfo = resultSet.getString(7);
                String hotelOwner = resultSet.getString(8);

                ArrayList<String> hotelImageURLs = new ArrayList<>();
                imagesPreparedStatement = connection.prepareStatement("Select * from hotelImages WHERE hotelId = ?");
                imagesPreparedStatement.setInt(1,hotelId);
                imageResultSet = imagesPreparedStatement.executeQuery();

                while (imageResultSet.next()) {
                    String imageURL = imageResultSet.getString(3);
                    hotelImageURLs.add(imageURL);
                }

                Hotel hotel = new Hotel(
                        hotelId,
                        hotelName,
                        hotelDescription,
                        hotelLocation,
                        hotelStars,
                        hotelAverageReview,
                        hotelContactInfo,
                        hotelOwner,
                        hotelImageURLs
                );

                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    @Override
    public double getAvgRating(int hotelId) {
        double averageRating = -1.0;
        try {
            preparedStatement = connection.prepareStatement("select averageReview from Hotels where hotelId = ?");
            preparedStatement.setInt(1,hotelId);
            resultSet = preparedStatement.executeQuery();
            averageRating = resultSet.getDouble(1);
            System.out.println(averageRating);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        return averageRating;
    }


    /**
     *
     * Mun líklegast þurfa að refactora það sem lítið
     * samræmi er á milli Hotel klasans og hotel töfluna
     * í gagnagrunninum.
     */
    @Override
    public void addHotel(Hotel hotel) {
        try {
            // (hotelId, hotelName, hotelDescription, location, hotelStars integer, averageReview, hotelContactInfo, hotelOwner, hasGym, hasCasino)
            preparedStatement = connection.prepareStatement("insert into Hotels values(?,?,?,?,?,?,?,?,?,?");
            preparedStatement.setInt(1, hotel.getHotelId());
            preparedStatement.setString(2,hotel.getHotelName());
            preparedStatement.setString(3, hotel.getDescription());
            preparedStatement.setString(4, hotel.getLocation());
            preparedStatement.setInt(5,hotel.getStars());
            preparedStatement.setDouble(6,hotel.getAverageRating());
            preparedStatement.setString(7, hotel.getContactInfo());
            preparedStatement.setInt(8, hotel.getOwnerId());
            preparedStatement.setBoolean(9,hotel.getServices().isHasGym());
            preparedStatement.setBoolean(10,hotel.getServices().isHasCasino());
            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /*
     * Athugasemd: þetta fall er mjög tregt með dagsetningar
     */
    @Override
    public List<Booking> getBookings(int roomId) {
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select * from Bookings where roomId = ?");
            preparedStatement.setInt(1,roomId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int bookingId = resultSet.getInt(1);
                int rId = resultSet.getInt(2);
                int personId = resultSet.getInt(3);

                // Þetta ehv tregt
                Date startDate = resultSet.getDate(4);
                Date endDate = resultSet.getDate(5);

                System.out.println(startDate);
                System.out.println(endDate);

                //Date date = new Date(2022, 4, 20);

                //System.out.println(date);



                //Booking booking = new Booking(bookingId, rId, personId, startDate, endDate);

                //bookings.add(booking);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    @Override
    public Booking book(int roomId, int personId, Date startDate, Date endDate) {
        //TODO láta AUTOINCREMENT VIRKA

        Booking booking = null;
        try {
            preparedStatement = connection.prepareStatement("Insert Into Bookings (roomId,personId,startDate,endDdate) Values (?,?,?,?)");
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, personId);
            preparedStatement.setObject(1, startDate);
            preparedStatement.setObject(1, endDate);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("Select bookingId From Bookings where roomId=? AND personId=? AND startDate=? AND endDate=?");
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, personId);
            preparedStatement.setObject(1, startDate);
            preparedStatement.setObject(1, endDate);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            int bId = resultSet.getInt(1);
            booking = new Booking(bId,roomId,personId,startDate,endDate);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public int logout(String name) {
        // TODO Auto-generated method stub
        return 0;
    }

    private static Date convertStringToDate(String s) {
        int year = Integer.parseInt(s.substring(0,5));
        System.out.println(year);
        int month = Integer.parseInt(s.substring(5,7));
        System.out.println(month);
        int day = Integer.parseInt(s.substring(8));
        return new Date(year,month,day);
    }

}

