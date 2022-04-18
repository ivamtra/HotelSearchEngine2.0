package hotelsearchengine.models;

public class Room {
    private int roomId;
    private int hotelId;
    private int price;
    private int capacity;

    public Room(int roomId, int hotelId, int price, int capacity) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.price = price;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }
}
