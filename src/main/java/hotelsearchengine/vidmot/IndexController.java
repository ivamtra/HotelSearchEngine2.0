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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
            hotelContainer.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("hotel-view.fxml"));
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }  catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            });


            mainContainer.getChildren().add(hotelContainer);
        }

    }
}