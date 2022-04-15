package hotelsearchengine.models;

public class Review {
    private int reviewId;
    private int customerId;
    private double rating;
    private String comment;

    public Review(int reviewId, int customerId, double rating, String comment) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.rating = rating;
        this.comment = comment;
    }
}
