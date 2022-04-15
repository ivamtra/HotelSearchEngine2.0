package hotelsearchengine.models;

public class Hotel {
    private int hotelId;
    private Review[] reviews;
    private Room[] rooms;
    private String location;
    private int stars;
    private Image picture;
    private Services services;
    private int ownerId;

    public Hotel() {
        this.hotelId = 0;
        this.reviews = new Review[0];
        this.rooms = new Room[0];
        this.location = "";
        this.stars = 0;
        this.picture = new Image();
        this.services = new Services();
        this.ownerId = 0;
    }
}
