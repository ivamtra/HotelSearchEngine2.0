package hotelsearchengine.controllers;

import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Review;
import hotelsearchengine.models.Room;
import hotelsearchengine.storage.DatabaseInterface;

public class editController {
    private DatabaseInterface db;
    private loginController login;

    public editController(DatabaseInterface db, loginController login) {
        this.db = db;
        this.login = login;
    }

    public int addHotel(Hotel hotel){
        boolean isOwner = db.isOwner(login.getLogged());
        //Maður getur bara bætt við hóteli sem maður sjálfur á og ef maður er skráðu eigandi
        if (isOwner && hotel.getOwnerId()== login.getLogged()) {
            db.addHotel(hotel);
        }
        return 0; //TODO Veit ekki alveg hverju þetta átti að skila lol
    }
    public void addReview(Review review){
        boolean isOwner = db.isOwner(login.getLogged());
        if (!isOwner) {
            db.addReviews(review);
        }
    }
    public void addRoom(Room room){
        //Ef sá er loggaður inn er skráður sem eigandi hótelsins og er að finna í töflu má hann bæta herbergi við
        boolean isOwner = db.isOwner(login.getLogged());
        if (isOwner && login.getLogged()==db.getOwner(room.getHotelId())) {
            db.addRoom(room);
        }
    }

    //TODO remove föll
}
