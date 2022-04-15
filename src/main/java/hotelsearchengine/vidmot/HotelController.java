package hotelsearchengine.vidmot;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    int hotelId;

    public void setHotelId(int id) {
        hotelId = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelId = IndexController.hotelId;
        System.out.println(hotelId);

    }
}