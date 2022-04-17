package hotelsearchengine.models;

public class Room {
    private int roomId;
    private int hotelId;
    private int roomNumber;
    private int price;
    private int capacity;

    public Room(int roomId, int hotelId, int roomNumber, int price, int capacity) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }
}
