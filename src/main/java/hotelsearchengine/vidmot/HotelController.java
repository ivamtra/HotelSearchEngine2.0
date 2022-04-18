package hotelsearchengine.vidmot;

import hotelsearchengine.controllers.searchController;
import hotelsearchengine.models.Hotel;
import hotelsearchengine.models.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    private int hotelId;
    private Hotel hotel;

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
        for(int i = 0; i < hotel.getImageURLs().size(); i++) {
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

        for(int i = 0; i < hotel.getHotelStars(); i++) {
            ImageView starView = new ImageView(starIcon);
            starView.setFitHeight(15);
            starView.setFitWidth(15);
            starContainer.getChildren().add(starView);
        }

        for(int i = hotel.getHotelStars(); i < 10; i++) {
            ImageView starView = new ImageView(notAStarIcon);
            starView.setFitHeight(15);
            starView.setFitWidth(15);
            starContainer.getChildren().add(starView);
        }

        starContainer.getStyleClass().add("hotel-stars");
        mainContainer.getChildren().add(starContainer);

        for(Review review : hotel.getReviews()) {
            TextField reviewItem = new TextField();
            reviewItem.setText(review.getComment());
            reviewItem.getStyleClass().add("review");
            reviewContainer.getChildren().add(reviewItem);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelId = IndexController.hotelId;
        sc = IndexController.sc;
        hotel = sc.getHotelById(hotelId);
        displayHotel();
    }
}