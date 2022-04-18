package hotelsearchengine.storage;

import hotelsearchengine.models.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public static void main(String[] args) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        databaseHelper db = new databaseHelper();
        //(Integer minPrice, Integer maxPrice, Integer minStars,
        // Integer maxStars, String name, String location,
        // ArrayList<Service> services, Date startDate, Date endDate,
        // Double avgRating, Integer minSize, Integer maxSize)
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = sdf.parse("2020-02-11");
            eDate = sdf.parse("2020-02-17");
            //db.book(1,2,sdf.parse("2020-02-05"),sdf.parse("2020-02-10"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(new Service(1, "leimshit"));
        services.add(new Service(3, "leimshit"));
        Restrictions res = new Restrictions(null, null, null, null, null, null, services, null, null, null, null, null);
        List<Room> rooms = db.getHotelRooms(res);
        for (Room r : rooms) {
            System.out.print(r.getRoomId() + " ");
            System.out.print(r.getHotelId() + " ");
            System.out.print(r.getCapacity() + " ");
            System.out.println(r.getPrice() + " ");
        }

        /*

        db.makeBookings();
    }

        db.getHotelReviews(1);

        ArrayList<Review> reviews = (ArrayList<Review>) db.getHotelReviews(1);

        for (Review r : reviews) {
            System.out.println(r.getComment());
        }

        // db.getAvgRating(1);

        //getBookings prófanir
        ArrayList<Booking> bookings = (ArrayList<Booking>) db.getBookings(2);

        for (Booking b : bookings) {
            System.out.println(b.getBookingId());
            System.out.println(b.getRoomId());
            System.out.println(b.getCustomerId());
            System.out.println(b.getStartDate());
            System.out.println(b.getEndDate());
        }
        bookings = (ArrayList<Booking>) db.getBookings(3);

        for (Booking b : bookings) {
            System.out.println(b.getBookingId());
            System.out.println(b.getRoomId());
            System.out.println(b.getCustomerId());
            System.out.println(b.getStartDate());
            System.out.println(b.getEndDate());
        }

        //Login prófanir
        Person p1 = db.login("Sös","apakisi123");
        System.out.println(p1.getId());
        System.out.println(p1.getName());
        System.out.println(p1.getPassword());
        Person p2 = db.login("Sus","apakisi123");
        System.out.println(p2==null);

         */
    }

    public void makeBookings() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        book(1, 1, sdf.parse("2022-04-20"), sdf.parse("2022-04-24"));
        book(2, 1, sdf.parse("2022-04-20"), sdf.parse("2022-04-24"));
        book(3, 1, sdf.parse("2022-04-20"), sdf.parse("2022-04-24"));
        book(4, 1, sdf.parse("2022-04-20"), sdf.parse("2022-04-24"));
        book(5, 1, sdf.parse("2022-04-20"), sdf.parse("2022-04-24"));
        book(1, 1, sdf.parse("2022-05-02"), sdf.parse("2022-05-14"));
        book(2, 1, sdf.parse("2022-05-03"), sdf.parse("2022-05-17"));
        book(3, 1, sdf.parse("2022-05-10"), sdf.parse("2022-05-24"));
        book(4, 1, sdf.parse("2022-05-15"), sdf.parse("2022-05-24"));
        book(5, 1, sdf.parse("2022-05-17"), sdf.parse("2022-06-02"));
    }

    @Override
    public Booking unbook(int bookingId) {
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
    public ArrayList<Review> getHotelReviews(int hotelId) {
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
    public List<Room> getRoomsInHotels(int hotelId) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("Select * from rooms where hotelId = ?");
            preparedStatement.setInt(1,hotelId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int rId = resultSet.getInt(1);
                int size = resultSet.getInt(2);
                int hId = resultSet.getInt(3);
                int price = resultSet.getInt(4);

                //int roomId, int hotelId, int price, int capacity
                Room room = new Room(rId,hId,price,size);

                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }


    @Override
    public List<Room> getHotelRooms(Restrictions restrictions) {

        ArrayList<Room> rooms = new ArrayList<>();

        Integer minPrice = restrictions.getMinPrice();
        Integer maxPrice = restrictions.getMaxPrice();
        Integer minStars = restrictions.getMinStars();
        Integer maxStars = restrictions.getMaxStars();
        String name = restrictions.getName();
        String location = restrictions.getLocation();
        Date startDate = restrictions.getStartDate();
        Date endDate = restrictions.getEndDate();
        Double avgRating = restrictions.getAvgRating();
        Integer minSize = restrictions.getMinSize();
        Integer maxSize = restrictions.getMaxSize();
        ArrayList<Service> services = restrictions.getServices();

        String query = "SELECT * FROM Rooms R Inner join hotels H on R.hotelId = H.hotelId where 1=1 ";
        boolean[] usedValues = new boolean[9]; // 9 restrictions atm

        // Þurfum að nota ? hér til að þetta sé öruggara (viljum ekki stinga gildunum beint inn)
        if(minPrice != null) {query += "AND ?<=R.price ";
            usedValues[0] = true;}
        if(maxPrice != null) {query += "AND ?>=R.price ";
            usedValues[1] = true;}
        if(minSize != null) {query += "AND ?<=R.size ";
            usedValues[2] = true;}
        if(maxSize != null) {query += "AND ?>=R.size ";
            usedValues[3] = true;}
        if(minStars != null) {query += "AND ?<=H.hotelStars ";
            usedValues[4] = true;}
        if(maxStars != null) {query += "AND ?>=H.hotelStars ";
            usedValues[5] = true;}
        if(name != null) {query += "AND H.hotelName LIKE ? ";
            usedValues[6] = true;}
        if(location != null) {query += "AND H.location LIKE ? ";
            usedValues[7] = true;}

        query += "COLLATE NOCASE";

        System.out.println(query);

        try {
            preparedStatement = connection.prepareStatement(query);

            int currentQueryParameter = 0;
            if(usedValues[0]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, minPrice);
            }
            if(usedValues[1]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, maxPrice);
            }
            if(usedValues[2]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, minSize);
            }
            if(usedValues[3]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, maxSize);
            }
            if(usedValues[4]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, minStars);
            }
            if(usedValues[5]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, maxStars);
            }
            if(usedValues[6]) {
                currentQueryParameter++;
                preparedStatement.setString(currentQueryParameter, name);
            }
            if(usedValues[7]) {
                currentQueryParameter++;
                preparedStatement.setString(currentQueryParameter, location);
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int roomId = resultSet.getInt(1);
                int hotelId = resultSet.getInt(3);
                int price = resultSet.getInt(4);
                int size = resultSet.getInt(2);

                boolean serv = true;
                if (services!=null) {
                    for (Service s : services){
                        if (!hasService(s.getServiceId(),hotelId)) {serv = false;}
                    }
                }

                Room room = new Room(roomId,hotelId,price,size);
                if (getAvailability(roomId,startDate,endDate)&&serv) {
                    rooms.add(room);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Hotel getHotel(int hotelId) {
        Hotel hotel = null;
        PreparedStatement pstmt;
        PreparedStatement imgPstmt;
        ResultSet resultSet;
        ResultSet imgResultSet;
        String query = "SELECT * FROM Hotels where hotelId = ?";
        String imgQuery = "Select * from hotelImages WHERE hotelId = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, hotelId);
            resultSet = pstmt.executeQuery();

            imgPstmt = connection.prepareStatement(imgQuery);
            imgPstmt.setInt(1, hotelId);
            imgResultSet = imgPstmt.executeQuery();

            String hotelName = resultSet.getString(2);
            String hotelDescription = resultSet.getString(3);
            String hotelLocation = resultSet.getString(4);
            int hotelStars = resultSet.getInt(5);
            Double hotelAverageReview = resultSet.getDouble(6);
            String hotelContactInfo = resultSet.getString(7);
            String hotelOwner = resultSet.getString(8);

            ArrayList<String> hotelImageUrls = new ArrayList();

            while(imgResultSet.next()) {
                hotelImageUrls.add(imgResultSet.getString(3));
            }

            hotel = new Hotel(
                    hotelId,
                    hotelName,
                    hotelDescription,
                    hotelLocation,
                    hotelStars,
                    hotelAverageReview,
                    hotelContactInfo,
                    hotelOwner,
                    hotelImageUrls
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public List<Hotel> getHotels(Restrictions restrictions) {

        ArrayList<Hotel> hotelList = new ArrayList<>();

        Integer minPrice = restrictions.getMinPrice();
        Integer maxPrice = restrictions.getMaxPrice();
        Integer minStars = restrictions.getMinStars();
        Integer maxStars = restrictions.getMaxStars();
        String name = restrictions.getName();
        String location = restrictions.getLocation();
        Date startDate = restrictions.getStartDate();
        Date endDate = restrictions.getEndDate();
        ArrayList<Service> services = restrictions.getServices();

        String query = "SELECT * FROM Hotels WHERE 1=1 ";
        boolean[] usedValues = new boolean[9]; // 9 restrictions atm

        // Þurfum að nota ? hér til að þetta sé öruggara (viljum ekki stinga gildunum beint inn)
        if(minStars != null) {
            query += "AND hotelStars >= ? ";
            usedValues[0] = true;
        }
        if(maxStars != null) {
            query += "AND hotelStars <= ? ";
            usedValues[1] = true;
        }
        if(name != null) {
            name = '%' + name + '%';
            query += "AND hotelName LIKE ? "; // TODO: Case sensitive etc.
            usedValues[2] = true;
        }
        if(location != null) {
            location = '%' + location + '%';
            query += "AND location LIKE ? "; // TODO: Case sensitive etc.
            usedValues[3] = true;
        }

        if(services != null) {} // TODO

        if(minPrice != null || maxPrice != null) {
            query += "AND Hotels.hotelId IN (SELECT hotelId FROM Rooms WHERE 1=1 ";
        }
        if(minPrice != null) {
            query += "AND price >= ? ";
            usedValues[5] = true;
        }
        if(maxPrice != null) {
            query += "AND price <= ? ";
            usedValues[6] = true;
        }

        if(startDate != null || endDate != null) {
            if(minPrice == null && maxPrice == null) {
                query += "AND Hotels.hotelId IN (SELECT hotelId FROM Rooms WHERE 1=1 ";
            }
            query += "AND NOT EXISTS (SELECT * FROM Bookings WHERE Bookings.roomId = Rooms.roomId ";
        }

        if(startDate != null) {
            query += "AND ((startDate < ? AND endDate > ?) "; // Start: 8 and 9
            usedValues[7] = true;
        }
        if(endDate != null) {
            if(startDate != null) {
                query += "OR (startDate < ? AND endDate > ?) "; // End: 10 and 11
                query += "OR (startDate > ? AND endDate < ?))"; // Start: 12, End: 13
            } else {
                query += "AND (startDate < ? AND endDate > ?) "; // End: 10 and 11
            }
            usedValues[8] = true;
        }

        if(startDate != null && endDate == null) {
            query += ")";
        }

        if(minPrice != null || maxPrice != null || ((startDate != null || endDate != null) && minPrice == null && maxPrice == null)) {
            query += ") ";
        }

        if(startDate != null || endDate != null) {
            query += ") ";
        }

        query += "COLLATE NOCASE";

        System.out.println(query);

        try {
            preparedStatement = connection.prepareStatement(query);

            int currentQueryParameter = 0;
            if(usedValues[0]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, minStars);
            }
            if(usedValues[1]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, maxStars);
            }
            if(usedValues[2]) {
                currentQueryParameter++;
                preparedStatement.setString(currentQueryParameter, name);
            }
            if(usedValues[3]) {
                currentQueryParameter++;
                preparedStatement.setString(currentQueryParameter, location);
            }

            if(usedValues[5]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, minPrice);
            }
            if(usedValues[6]) {
                currentQueryParameter++;
                preparedStatement.setInt(currentQueryParameter, maxPrice);
            }

            if(usedValues[7]) {
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) startDate);
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) startDate);
            }

            if(usedValues[8]) {
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) endDate);
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) endDate);
            }

            if(usedValues[7] && usedValues[8]) {
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) startDate);
                currentQueryParameter++;
                preparedStatement.setDate(currentQueryParameter, (java.sql.Date) endDate);
            }

            // TODO : rest of restrictions

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

                // Sækja myndir fyrir hotelið
                ArrayList<String> hotelImageURLs = new ArrayList<>();
                imagesPreparedStatement = connection.prepareStatement("Select * from hotelImages WHERE hotelId = ?");
                imagesPreparedStatement.setInt(1,hotelId);
                imageResultSet = imagesPreparedStatement.executeQuery();

                while (imageResultSet.next()) {
                    String imageURL = imageResultSet.getString(3);
                    hotelImageURLs.add(imageURL);
                }

                // Sækja myndir fyrir hotelið
                ArrayList<Service> hotelServices = new ArrayList<>();
                imagesPreparedStatement = connection.prepareStatement("Select * from Services WHERE id IN (SELECT serviceId FROM HotelHasService WHERE hotelId = ?)");
                imagesPreparedStatement.setInt(1,hotelId);
                imageResultSet = imagesPreparedStatement.executeQuery();

                while (imageResultSet.next()) {
                    int serviceId = imageResultSet.getInt(1);
                    String serviceName = imageResultSet.getString(2);
                    Service service = new Service(serviceId, serviceName);
                    hotelServices.add(service);
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
    public List<Service> getAllServices() {
        ArrayList<Service> serviceList = new ArrayList<Service>();
        try {
            preparedStatement = connection.prepareStatement("Select * from services");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int serviceId = resultSet.getInt(1);
                String serviceName = resultSet.getString(2);

                Service service = new Service(
                        serviceId,
                        serviceName
                );

                serviceList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
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
            preparedStatement = connection.prepareStatement("insert into Hotels values(?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, hotel.getHotelId());
            preparedStatement.setString(2,hotel.getHotelName());
            preparedStatement.setString(3, hotel.getDescription());
            preparedStatement.setString(4, hotel.getLocation());
            preparedStatement.setInt(5,hotel.getStars());
            preparedStatement.setDouble(6,hotel.getAverageRating());
            preparedStatement.setString(7, hotel.getContactInfo());
            preparedStatement.setInt(8, hotel.getOwnerId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /*
     * Athugasemd: þetta fall er mjög tregt með dagsetningar
     * Ætti að vera komið núna
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
                java.sql.Date sDate = resultSet.getDate(4);
                java.sql.Date eDate = resultSet.getDate(5);

                Booking booking = new Booking(bookingId, rId, personId, sDate, eDate);

                bookings.add(booking);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    @Override
    public Booking book(int roomId, int personId, Date startDate, Date endDate) {
        Booking booking = null;
        try {
            preparedStatement = connection.prepareStatement("Insert Into Bookings (roomId,personId,startDate,endDate) Values (?,?,?,?)");
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, personId);
            preparedStatement.setObject(3, startDate);
            preparedStatement.setObject(4, endDate);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("Select bookingId From Bookings where roomId=? AND personId=? AND startDate=? AND endDate=?");
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, personId);
            preparedStatement.setObject(3, startDate);
            preparedStatement.setObject(4, endDate);
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
        // Þurfum þetta fall líklega ekki
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

    public boolean isOwner(int personId){
        boolean isOwner = false;
        try {
            preparedStatement = connection.prepareStatement("select isOwner from Persons where personId = ?");
            preparedStatement.setInt(1,personId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            isOwner = resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isOwner;
    }

    public int getOwner(int hotelId) {
        int owner = -1;
        try {
            preparedStatement = connection.prepareStatement("select hotelOwner from Hotels where hotelId = ?");
            preparedStatement.setInt(1,hotelId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            owner = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    public void addRoom(Room room){
        try {
            preparedStatement = connection.prepareStatement("Insert Into Rooms (roomId,size,hotelId,price) Values (?,?,?,?)");
            preparedStatement.setInt(1, room.getRoomId());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setObject(3, room.getHotelId());
            preparedStatement.setObject(4, room.getPrice());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean getAvailability(int roomID, Date start, Date end){
        ArrayList<Booking> bookings = (ArrayList<Booking>) getBookings(roomID);
        for(Booking book : bookings){
            boolean byrjarArgFyrirByrjun = start != null && book.getStartDate().compareTo(start)>=0;
            boolean endarArgFyrirEndi = end != null && book.getEndDate().compareTo(end)>=0;
            boolean byrjarArgFyrirEndi = start != null && book.getEndDate().compareTo(start)>=0;
            boolean endarArgFyrirByrjun = end != null && book.getStartDate().compareTo(end)>=0;

            if(!byrjarArgFyrirByrjun && byrjarArgFyrirEndi){
                return false;
            }
            if(!endarArgFyrirByrjun && endarArgFyrirEndi){
                return false;
            }
            if (byrjarArgFyrirByrjun && !endarArgFyrirEndi) {
                return false;
            }
        }
        return true;
    }
    public boolean hasService(int serviceId, int hotelId){
        boolean skil = false;
        try {
            preparedStatement = connection.prepareStatement("Select * from hotels H Inner Join hotelHasService HHS on H.hotelId=HHS.hotelId where H.hotelId=? AND serviceId=?");
            preparedStatement.setInt(1,hotelId);
            preparedStatement.setInt(2,serviceId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skil;
    }
}

