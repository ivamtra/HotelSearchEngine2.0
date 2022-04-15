package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.storage.databaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    private searchController sc;

    @FXML
    private VBox mainContainer;

    @FXML
    public void initialize(URL location, ResourceBundle resourceBundle) {

        databaseHelper DB = new databaseHelper();
        loginController LOGIN = new loginController(DB);
        sc = new searchController(DB, LOGIN);
        insertHotels(sc);
    }

    public void insertHotels(searchController sc) {

        Hotel[] hotels = sc.getAllHotels();

        for (Hotel h : hotels) {

            Label hotelContainer = new Label();
            hotelContainer.setText(h.getName());

            Label hotelNameContainer = new Label();

            mainContainer.getChildren().add(hotelContainer);
        }

    }
}