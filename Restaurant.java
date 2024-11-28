package pap;

public class Restaurant {
    public String name;
    private String location;
    private String cuisine;
    private String comments;
    private double totalRating;
    private int numReviews;
    private double rating;

    public Restaurant(String name, String location, String cuisine, double rating, String comments) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        setRating(rating); // Validate and set the rating
        this.comments = comments;
        this.totalRating = rating;
        this.numReviews = 1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws IllegalArgumentException {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Invalid rating. Rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

    
    public void addRating(double rating) {
        this.totalRating += rating;
        this.numReviews++;
    }

    public double getAverageRating() {
        return numReviews == 0 ? 0.0 : totalRating / numReviews;
    }
    
    
}
