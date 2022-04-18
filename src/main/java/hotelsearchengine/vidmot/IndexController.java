package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.loginController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Service;
import hotelsearchengine.storage.databaseHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    public static searchController sc;

    @FXML
    private VBox mainContainer;

    @FXML
    private Pane searchContainer;

    @FXML
    private TextField searchMaxPrice;

    @FXML
    private TextField searchMinPrice;

    @FXML
    private TextField searchMaxStars;

    @FXML
    private TextField searchMinStars;

    @FXML
    private TextField searchHotelName;

    @FXML
    private TextField searchHotelLocation;

    @FXML
    private DatePicker searchAvailableFrom;

    @FXML
    private DatePicker searchAvailableTo;

    @FXML
    private VBox servicesContainer;

    @FXML
    private Pane loginContainer;

    @FXML
    private Pane confirmPasswordContainer;

    @FXML
    private Button switchLoginRegisterBtn;

    @FXML
    private Button loginRegisterBtn;

    public static int hotelId = 100;

    private HashMap<Integer, MouseEvent> map;

    public static boolean isLogginIn = true;

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
        insertHotels(sc, null);
        insertServices(sc);
        restrictSearchTextFields();
    }

    public void insertHotels(searchController sc, ArrayList<Hotel> currentHotels) {

        ArrayList<Hotel> hotels = currentHotels != null ? currentHotels : sc.getAllHotels();

        mainContainer.getChildren().clear();

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

    public void restrictSearchTextFields() {
        setNumberOnly(searchMaxPrice);
        setNumberOnly(searchMinPrice);
        setNumberOnly(searchMaxStars);
        setNumberOnly(searchMinStars);
    }

    public void setNumberOnly(TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void showSearchContainer(MouseEvent e) {
        searchContainer.setVisible(true);
    }

    public void hideSearchContainer(MouseEvent e) {
        searchContainer.setVisible(false);
    }

    public boolean isInt(String str) {
        try
        {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public void handleSearch(ActionEvent e) {

        searchAvailableFrom.setValue(searchAvailableFrom.getConverter().fromString(searchAvailableFrom.getEditor().getText()));
        searchAvailableTo.setValue(searchAvailableTo.getConverter().fromString(searchAvailableTo.getEditor().getText()));

        Integer maxPrice = isInt(searchMaxPrice.getText()) ? Integer.valueOf(searchMaxPrice.getText()) : null;
        Integer minPrice = isInt(searchMinPrice.getText()) ? Integer.valueOf(searchMinPrice.getText()) : null;
        Integer maxStars = isInt(searchMaxStars.getText()) ? Integer.valueOf(searchMaxStars.getText()) : null;
        Integer minStars = isInt(searchMinStars.getText()) ? Integer.valueOf(searchMinStars.getText()) : null;
        String name = searchHotelName.getText().length() > 0 ? searchHotelName.getText() : null;
        String location = searchHotelLocation.getText().length() > 0 ? searchHotelLocation.getText() : null;


        Date availableFrom = searchAvailableFrom.getValue() != null ? Date.valueOf(searchAvailableFrom.getValue()) : null;
        Date availableTo = searchAvailableTo.getValue() != null ? Date.valueOf(searchAvailableTo.getValue()) : null;

        ArrayList<Service> services = new ArrayList<Service>();
        ArrayList<Hotel> hotels = sc.searchHotels(maxPrice, minPrice, maxStars, minStars, name, location, services, availableFrom, availableTo, null,null, null);


        insertHotels(sc, hotels);

        searchContainer.setVisible(false);
    }

    public void showLoginContainer(MouseEvent e) {
        loginContainer.setVisible(true);
    }

    public void hideLoginContainer(MouseEvent e) {
        loginContainer.setVisible(false);
    }

    public void switchLoginRegister(ActionEvent e) {
        isLogginIn = !isLogginIn;
        confirmPasswordContainer.setVisible(!isLogginIn);
        switchLoginRegisterBtn.setText(isLogginIn ? "Register" : "Login");
        switchLoginRegisterBtn.setStyle(isLogginIn ? "-fx-translate-x: 0; -fx-background-color: blue" : "-fx-translate-x: 10; -fx-background-color: blue");
        loginRegisterBtn.setText(isLogginIn ? "Login" : "Register");
        loginRegisterBtn.setStyle(isLogginIn ? "-fx-translate-x: 0; -fx-background-color: blue" : "-fx-translate-x: -15; -fx-background-color: blue");
    }

    public void loginRegisterUser(ActionEvent e) {

    }
}
