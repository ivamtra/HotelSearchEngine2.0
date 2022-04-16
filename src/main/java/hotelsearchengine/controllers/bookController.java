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


    // Þetta breyttist eftir að db.getBookings var breytt til þess að skila lista
    // Í staðinn fyrir fylki
    // Athuga virkni
    public void unbook(int roomID, Date start, Date end){
        ArrayList<Booking> bookings = (ArrayList<Booking>) db.getBookings(roomID);
        int personID = login.getLogged();
        for(Booking book : bookings){
            if(book.eq(roomID,personID,start,end)){
                int bookingId = book.getBookingId();
                db.unbook(bookingId);
                break;
            }
        }

    }

    // Þetta líka
    public boolean getAvailability(int roomID, Date start, Date end){
        ArrayList<Booking> bookings = (ArrayList<Booking>) db.getBookings(roomID);
        for(Booking book : bookings){
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
