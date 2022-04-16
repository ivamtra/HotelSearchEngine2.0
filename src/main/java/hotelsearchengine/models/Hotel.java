package hotelsearchengine.models;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private Review[] reviews;
    private Room[] rooms;
    private String location;
    private int stars;
    private Image picture;
    private Services services;
    private int ownerId;
    private String description; // Þetta er ný breyta
    private double averageRating; // ný breyta
    private String contactInfo; // ný breyta
    private boolean hasGym; // Refactorast seinna með services
    private boolean hasCasino; // Refactorast seinna



    public Hotel(int hotelId, int stars, String hotelName) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.reviews = new Review[0]; // TODO
        this.rooms = new Room[0]; // TODO
        this.location = ""; // TODO
        this.stars = stars;
        this.picture = new Image(); // TODO
        this.services = new Services(); // TODO
        this.ownerId = 0; // TODO
    }

    public String getName() {
        return this.hotelName;
    }
    public int getHotelId() { return this.hotelId; }
    public int getHotelStars() { return this.stars; }

    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
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

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
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
}
