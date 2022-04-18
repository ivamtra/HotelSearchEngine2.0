package hotelsearchengine.models;

public class Review {
    private int hotelId;
    private int customerId;
    private int rating;
    private String comment;

    public Review(int hotelId,int customerId, String comment, int rating) {
        this.hotelId = hotelId;
        this.customerId = customerId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
