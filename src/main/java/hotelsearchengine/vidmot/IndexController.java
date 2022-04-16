package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.storage.databaseHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    private searchController sc;

    @FXML
    private VBox mainContainer;

    public static int hotelId = 100;

    private HashMap<Integer, MouseEvent> map;

    @FXML
    public void initialize(URL location, ResourceBundle resourceBundle) {

        databaseHelper DB = null;
        try {
            DB = new databaseHelper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loginController LOGIN = new loginController(DB);
        sc = new searchController(DB, LOGIN);
        insertHotels(sc);
    }

    public void insertHotels(searchController sc) {

        Hotel[] hotels = sc.getAllHotels();

        int hotelNumber = 0;
        HBox currentHBox = new HBox();

        for (Hotel h : hotels) {

            if(hotelNumber % 4 == 0) {
                currentHBox = new HBox();
                currentHBox.getStyleClass().add("hotel-row");
                mainContainer.getChildren().add(currentHBox);
            }

            Pane hotelContainer = new Pane();
            hotelContainer.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    openHotelScene(e, h);
                }
            });
            hotelContainer.getStyleClass().add("hotel-container");
            currentHBox.getChildren().add(hotelContainer);

            Pane imageContainer = new Pane();
            imageContainer.getStyleClass().add("hotel-image-container");
            hotelContainer.getChildren().add(imageContainer);

            Pane hotelInfoContainer = new Pane();
            hotelInfoContainer.getStyleClass().add("hotel-info-container");
            hotelContainer.getChildren().add(hotelInfoContainer);

            Label hotelName = new Label();
            hotelName.setText(h.getName());
            hotelName.getStyleClass().add("hotel-name");
            hotelInfoContainer.getChildren().add(hotelName);

            Label hotelStars = new Label();
            hotelStars.setText("Stjörnur: " + h.getHotelStars());
            hotelStars.getStyleClass().add("hotel-stars");
            hotelInfoContainer.getChildren().add(hotelStars);

            hotelNumber++;
        }

    }

    public void openHotelScene(MouseEvent e, Hotel h) {
        try {
            hotelId = h.getHotelId();
            Parent root = FXMLLoader.load(getClass().getResource("hotel-view.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }  catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}