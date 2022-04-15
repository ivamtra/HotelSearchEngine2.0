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
}
