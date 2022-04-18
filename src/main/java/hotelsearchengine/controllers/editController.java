package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Review;
import hotelsearchengine.models.Room;
import hotelsearchengine.storage.DatabaseInterface;
import hotelsearchengine.storage.databaseHelper;

import java.sql.SQLException;

public class editController {
    private DatabaseInterface db;
    private loginController login;

    public editController(DatabaseInterface db, loginController login) {
        this.db = db;
        this.login = login;
    }

    /**
     * Bætir hóteli við gagnagrunninn.
     * @param hotel Hotel hlutur
     */
    public void addHotel(Hotel hotel){
        boolean isOwner = db.isOwner(login.getLogged());
        //Maður getur bara bætt við hóteli sem maður sjálfur á og ef maður er skráðu eigandi
        if (isOwner && hotel.getOwnerId()== login.getLogged()) {
            db.addHotel(hotel);
        }
    }
    public void addReview(Review review,int hotelId){
        boolean isOwner = db.isOwner(login.getLogged());
        if (!isOwner) {
            db.addReviews(review,hotelId);
        }
    }
    public void addRoom(Room room){
        //Ef sá er loggaður inn er skráður sem eigandi hótelsins og er að finna í töflu má hann bæta herbergi við
        boolean isOwner = db.isOwner(login.getLogged());
        if (isOwner && login.getLogged()==db.getOwner(room.getHotelId())) {
            db.addRoom(room);
        }
    }


    // TODO á eftir að prufa
    public Review removeReview(Review review) {
        db.deleteLineFromTable("Reviews", "reviewId", review.getReviewId());
        return review;
    }

    // TODO á eftir að prufa
    public Room removeRoom(Room room) {
        db.deleteLineFromTable("Rooms", "roomId", room.getRoomId());
        return room;
    }

    // Fallið virkar en er hættulegt
    public Hotel removeHotel(Hotel hotel) {
        db.deleteLineFromTable("Hotels", "hotelId", hotel.getHotelId());
        return hotel;
    }


    public static void main(String[] args) throws SQLException {
        databaseHelper db = new databaseHelper();
        loginController loginController = new loginController(db);
        editController ec = new editController(db,loginController);

        Hotel hotelSaga = db.getHotel(1);
        System.out.println(hotelSaga.getHotelName());
        searchController sc = new searchController(db, loginController);
        Hotel hotel2 = sc.getHotelById(1);

        System.out.println(hotel2.getHotelId());

        ec.removeHotel(hotelSaga);
    }
}
