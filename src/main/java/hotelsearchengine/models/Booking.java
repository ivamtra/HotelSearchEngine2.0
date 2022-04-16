package hotelsearchengine.models;





import java.util.Date;


public class Booking {
    private int bookingId;
    private int roomId;
    private int customerId;
    public Date startDate;
    private Date endDate;

    public Booking(int bookingId, int roomId, int customerId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getBookingId() {
        return bookingId;
    }
    public int getRoomId() {
        return roomId;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public int getCustomerId() {return customerId;}

    public boolean eq(int roomId, int customerId, Date startDate, Date endDate) {
        boolean eq = this.customerId==customerId && this.startDate==startDate && this.endDate==endDate && this.roomId==roomId;
        return eq;
    }
}

