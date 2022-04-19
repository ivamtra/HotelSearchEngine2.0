package hotelsearchengine.models;

import java.util.ArrayList;

public class HotelOwner extends Person{
    private ArrayList<Hotel> hotels;
    private int ownerId;

    public HotelOwner(String name, String password, int Id) {
        super(name, password, Id);
        this.hotels = new ArrayList<>();
    }

    public void AddHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }
}
