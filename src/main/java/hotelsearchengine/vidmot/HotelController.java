package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.storage.databaseHelper;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelController {

    int id;

    public void setHotelId(int hotelId) {
        id = hotelId;

        System.out.println(id);
    }



}
