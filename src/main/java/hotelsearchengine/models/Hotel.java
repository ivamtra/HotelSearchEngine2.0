package hotelsearchengine.models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private int hotelId;
    private String hotelName;
    private ArrayList<Review> reviews;
    private Room[] rooms;
    private String location;
    private int stars;
    private ArrayList<Service> services;
    private int ownerId;
    private String description; // Þetta er ný breyta
    private double averageRating; // ný breyta
    private String contactInfo; // ný breyta
    private ArrayList<String> imageURLs;

    public Hotel(int hotelId, String hotelName, String hotelDescription, String hotelLocation, int hotelStars, Double hotelAverageReview, String hotelContactInfo, String hotelOwner, ArrayList<String> hotelImageURLs) {

        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.reviews = new ArrayList<Review>();
        this.rooms = new Room[0]; // TODO
        this.location = hotelLocation;
        this.stars = hotelStars;
        this.imageURLs = hotelImageURLs;
        this.services = new ArrayList<Service>(); // TODO
        this.ownerId = 0; // TODO
    }

    public String getName() {
        return this.hotelName;
    }
    public int getHotelId() { return this.hotelId; }
    public int getHotelStars() { return this.stars; }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", reviews=" + reviews +
                ", rooms=" + rooms +
                ", location='" + location + '\'' +
                ", stars=" + stars +
                ", services=" + services +
                ", ownerId=" + ownerId +
                ", description='" + description + '\'' +
                ", averageRating=" + averageRating +
                ", contactInfo='" + contactInfo + '\'' +
                ", imageURLs=" + imageURLs +
                '}';
    }
}
