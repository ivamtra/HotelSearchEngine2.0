package hotelsearchengine.controllers;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.storage.DatabaseInterface;
import hotelsearchengine.models.Booking;
import hotelsearchengine.storage.DatabaseInterface;

import java.util.*;

public class bookController {

    private DatabaseInterface db;
    private loginController login;

    public bookController(DatabaseInterface DB, loginController LOGIN){
        this.db = DB;
        this.login = LOGIN;
    }

    public boolean book(int roomID, Date start, Date end) {
        boolean available = getAvailability(roomID, start, end);
        if(available){
            int personID = login.getLogged();
            db.book(roomID,personID, start, end);
        }
        return available;

    }
    public void unbook(int bookingId){
        db.unbook(bookingId);
    }

    public void unbook(int roomID, Date start, Date end){
        Booking[] bookings = db.getBookings(roomID);
        int personID = login.getLogged();
        for(int i=0;i<bookings.length;i++){
            Booking book = bookings[i];
            if(book.eq(roomID,personID,start,end)){
                int bookingId = book.getBookingId();
                db.unbook(bookingId);
                break;
            }
        }

    }

    public boolean getAvailability(int roomID, Date start, Date end){
        Booking[] bookings = db.getBookings(roomID);
        for(int i=0;i<bookings.length;i++){
            Booking book = bookings[i];
            boolean byrjarArgFyrirByrjun = book.getStartDate().compareTo(start)>=0;
            boolean endarArgFyrirEndi = book.getEndDate().compareTo(end)>=0;
            boolean byrjarArgFyrirEndi = book.getStartDate().compareTo(end)>=0;
            boolean endarArgFyrirByrjun = book.getEndDate().compareTo(start)>=0;

            if(!byrjarArgFyrirByrjun && byrjarArgFyrirEndi){
                return false;
            }
            if(!endarArgFyrirByrjun && endarArgFyrirEndi){
                return false;
            }
        }
        return true;
    }
}
