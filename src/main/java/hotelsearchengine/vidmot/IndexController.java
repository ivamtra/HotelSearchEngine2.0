package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Service;
import hotelsearchengine.storage.databaseHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    private searchController sc;

    @FXML
    private VBox mainContainer;

    @FXML
    private Pane searchContainer;

    @FXML
    private VBox servicesContainer;

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
        insertServices(sc);
    }

    public void insertHotels(searchController sc) {

        ArrayList<Hotel> hotels = sc.getAllHotels();

        int hotelNumber = 0;
        HBox currentHBox = new HBox();

        for (Hotel h : hotels) {

            System.out.println("Displaying Hotel: " + h.getName());

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

            ImageView imgView;
            if(h.getImageURLs().size() > 0) {
                Image img = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/images/" + h.getImageURLs().get(0)));
                imgView = new ImageView(img);
            } else {
                imgView = new ImageView();
            }

            imgView.setFitHeight(140.0);
            imgView.setFitWidth(200);
            imageContainer.getChildren().add(imgView);

            Pane hotelInfoContainer = new Pane();
            hotelInfoContainer.getStyleClass().add("hotel-info-container");
            hotelContainer.getChildren().add(hotelInfoContainer);

            Label hotelName = new Label();
            hotelName.setText(h.getName());
            hotelName.getStyleClass().add("hotel-name");
            hotelInfoContainer.getChildren().add(hotelName);

            HBox starContainer = new HBox();
            Image starIcon = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/icons/star.png"));
            Image notAStarIcon = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/icons/notAStar.png"));

            for(int i = 0; i < h.getHotelStars(); i++) {
                ImageView starView = new ImageView(starIcon);
                starView.setFitHeight(15);
                starView.setFitWidth(15);
                starContainer.getChildren().add(starView);
            }

            for(int i = h.getHotelStars(); i < 10; i++) {
                ImageView starView = new ImageView(notAStarIcon);
                starView.setFitHeight(15);
                starView.setFitWidth(15);
                starContainer.getChildren().add(starView);
            }

            starContainer.getStyleClass().add("hotel-stars");
            hotelInfoContainer.getChildren().add(starContainer);

            hotelNumber++;
        }
    }

    public void insertServices(searchController sc) {
        ArrayList<Service> services = sc.getAllServices();
        for(Service s : services) {
            CheckBox serviceCheckbox = new CheckBox();
            serviceCheckbox.setText(s.getServiceName());

            // TODO: addListener or id

            servicesContainer.getChildren().add(serviceCheckbox);
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

    public void showSearchContainer(MouseEvent e) {
        searchContainer.setVisible(true);
    }

    public void hideSearchContainer(MouseEvent e) {
        searchContainer.setVisible(false);
    }
}