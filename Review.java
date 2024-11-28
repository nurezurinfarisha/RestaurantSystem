package pap;

public class Review {
    private String restaurantName;
    private String reviewerName;
    private String review;
    private double rating;

    public Review(String restaurantName, String reviewerName, String review, double rating) {
        this.restaurantName = restaurantName;
        this.reviewerName = reviewerName;
        this.review = review;
        this.rating = rating;
    }

    // Getters and setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getReviewerName() {
    return reviewerName;
}


    public void setreviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
        // Getters and setters for rating
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
