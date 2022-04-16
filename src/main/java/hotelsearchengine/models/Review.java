package hotelsearchengine.models;

public class Review {
    private int reviewId;
    private int customerId;
    private int rating;
    private String comment;

    public Review(int reviewId, int customerId, String comment, int rating) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getRating() {
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
