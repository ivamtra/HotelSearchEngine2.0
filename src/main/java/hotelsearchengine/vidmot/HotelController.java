package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.bookController;
import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Restrictions;
import hotelsearchengine.models.Review;
import hotelsearchengine.models.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    private int hotelId;
    private Hotel hotel;

    private bookController bc;

    @FXML
    private Pane mainContainer;

    @FXML
    private Pane starPane;

    @FXML
    private VBox imageContainer;

    @FXML
    private Label hotelName;

    @FXML
    private Text hotelInfo;

    @FXML
    private VBox reviewContainer;

    @FXML
    private VBox roomContainer;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button searchRooms;

    private searchController sc;

    public void setHotelId(int id) {
        hotelId = id;
    }

    public void returnToIndexScene(ActionEvent e) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("index.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setTitle("Book a hotel room");
            stage.setScene(scene);
            stage.show();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private void displayHotel() {
        System.out.println(hotel.getImageURLs());
        for (int i = 0; i < hotel.getImageURLs().size(); i++) {
            Image img = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/images/" + hotel.getImageURLs().get(i)));
            ImageView imgView = new ImageView(img);
            imgView.getStyleClass().add("image");
            imgView.setFitHeight(140.0);
            imgView.setFitWidth(200);
            imageContainer.getChildren().add(imgView);
        }
        hotelName.setText(hotel.getName());
        hotelName.getStyleClass().add("hotel-name");
        hotelInfo.setText(hotel.getDescription());

        HBox starContainer = new HBox();
        Image starIcon = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/icons/star.png"));
        Image notAStarIcon = new Image(getClass().getResourceAsStream("/hotelsearchengine/storage/icons/notAStar.png"));

        for (int i = 0; i < hotel.getHotelStars(); i++) {
            ImageView starView = new ImageView(starIcon);
            starView.setFitHeight(15);
            starView.setFitWidth(15);
            starContainer.getChildren().add(starView);
        }

        for (int i = hotel.getHotelStars(); i < 10; i++) {
            ImageView starView = new ImageView(notAStarIcon);
            starView.setFitHeight(15);
            starView.setFitWidth(15);
            starContainer.getChildren().add(starView);
        }

        starContainer.getStyleClass().add("hotel-stars");
        mainContainer.getChildren().add(starContainer);

        for (Review review : hotel.getReviews()) {
            TextField reviewItem = new TextField();
            reviewItem.setText(review.getComment());
            reviewItem.getStyleClass().add("review");
            reviewContainer.getChildren().add(reviewItem);
        }
    }

    @FXML
    public void searchRooms(ActionEvent e) {
        startDate.setValue(startDate.getConverter().fromString(startDate.getEditor().getText()));
        endDate.setValue(endDate.getConverter().fromString(endDate.getEditor().getText()));

        Date from = startDate.getValue() != null ? Date.valueOf(startDate.getValue()) : null;
        Date to = endDate.getValue() != null ? Date.valueOf(endDate.getValue()) : null;

        insertRooms(from, to);
    }


    private void insertRooms(Date startDate, Date endDate) {
        Restrictions restrictions = new Restrictions(hotel.getHotelId(), null, null, null, null, null, null, null, startDate, endDate, null, null, null);
        ArrayList<Room> rooms = sc.searchHotelRooms(restrictions);

        for (Room room : rooms) {
            System.out.println(room.getRoomId());
            Pane roomPane = new Pane();

            Label roomLabel = new Label();
            roomLabel.setText("Verð: " + room.getPrice() + "kr.  Herbergi fyrir: " + room.getCapacity()+ " manns");
            roomLabel.getStyleClass().add("room_label");

            Button roomButton = new Button();
            roomButton.getStyleClass().add("room_button");

            roomButton.setOnAction(e -> registerRoom(room));
            roomButton.setText("Bóka");
            roomPane.getChildren().add(roomLabel);
            roomPane.getChildren().add(roomButton);
            roomContainer.getChildren().add(roomPane);
        }
    }

    private void registerRoom(Room room){
        startDate.setValue(startDate.getConverter().fromString(startDate.getEditor().getText()));
        endDate.setValue(endDate.getConverter().fromString(endDate.getEditor().getText()));

        Date from = startDate.getValue() != null ? Date.valueOf(startDate.getValue()) : null;
        Date to = endDate.getValue() != null ? Date.valueOf(endDate.getValue()) : null;
        if(from == null || to == null) {

        }
        bc.book(room.getRoomId(),from, to);
        System.out.println("Bókaði herbergi " + room.getRoomId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelId = IndexController.hotelId;
        sc = IndexController.sc;
        bc = IndexController.bc;
        hotel = sc.getHotelById(hotelId);
        displayHotel();
    }
}