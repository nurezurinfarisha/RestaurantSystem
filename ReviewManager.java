package pap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class ReviewManager {

    private final DefaultTableModel tableModel2;
    private final List<Review> reviews = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private RestaurantManager restaurantManager;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Ceah3007";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public ReviewManager(DefaultTableModel tableModel2) {
        this.tableModel2 = tableModel2;
    }

    public void addReviewToTableModel(String restaurantName, String reviewerName, String review, double rating) {
        Object[] rowData = { restaurantName, reviewerName, review, rating };
        tableModel2.addRow(rowData);
    }

    public void addReview(String restaurantName, String reviewerName, String review, double rating) {
        Review newReview = new Review(restaurantName, reviewerName, review, rating);
        reviews.add(newReview);
        System.out.println("Review added successfully!");
    }

    public List<Review> getAllReviews() throws SQLException {
        List<Review> reviewList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT * FROM review";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String restaurantName = rs.getString("restaurantName");
                String reviewerName = rs.getString("reviewerName");
                String review = rs.getString("review");
                double rating = rs.getDouble("rating"); // Get the rating as a double
                Review reviewObj = new Review(restaurantName, reviewerName, review, rating);
                reviewList.add(reviewObj);
            }

            // Update the reviews list with the latest data from the database
            reviews.clear();
            reviews.addAll(reviewList);

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return reviewList;
    }

    public void updateTableData(DefaultTableModel tableModel) throws SQLException {
        tableModel.setRowCount(0); // Clear the current data in the table

        List<Review> reviewList = getAllReviews();
        for (Review review : reviewList) {
            Object[] rowData = { review.getRestaurantName(), review.getReviewerName(), review.getReview(),
                    review.getRating() };
            tableModel.addRow(rowData);
        }
    }
public double getAverageRatingForRestaurant(String restaurantName) {
    // Fetch the reviews for the specific restaurant from the database
    try {
        List<Review> reviewsForRestaurant = getAllReviewsForRestaurant(restaurantName);
        if (reviewsForRestaurant.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Review review : reviewsForRestaurant) {
            totalRating += review.getRating();
        }

        return totalRating / reviewsForRestaurant.size();
    } catch (SQLException ex) {
        System.out.println("Error fetching reviews for the restaurant: " + ex.getMessage());
        return 0.0;
    }
}

public void displayReviews() {
        if (reviews.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No reviews to display!");
            return;
        }

        Object[] options = { "Restaurant Name", "Reviewer Name", "Rating" };
        int choice = JOptionPane.showOptionDialog(null, "Sort Reviews By:", "Sort Options", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == -1) {
            return; // User closed the dialog
        }

        List<Review> sortedReviews = new ArrayList<>(reviews);

        switch (choice) {
            case 0:
                sortedReviews.sort(Comparator.comparing(Review::getRestaurantName));
                break;
            case 1:
                sortedReviews.sort(Comparator.comparing(Review::getReviewerName));
                break;
            case 2:
                sortedReviews.sort(Comparator.comparing(Review::getRating).reversed());
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice.");
                return;
        }

        // Display the sorted reviews
        StringBuilder sortedReviewInfo = new StringBuilder();
        for (Review review : sortedReviews) {
            sortedReviewInfo.append("Restaurant Name: ").append(review.getRestaurantName()).append("\n")
                    .append("Reviewer Name: ").append(review.getReviewerName()).append("\n")
                    .append("Review: ").append(review.getReview()).append("\n")
                    .append("Rating: ").append(review.getRating()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sortedReviewInfo.toString(), "Sorted Reviews",
                JOptionPane.INFORMATION_MESSAGE);
    }

// Helper method to get all reviews for a specific restaurant from the database
private List<Review> getAllReviewsForRestaurant(String restaurantName) throws SQLException {
    List<Review> reviewList = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        conn = getConnection();
        String query = "SELECT * FROM review WHERE restaurantName=?";
        pst = conn.prepareStatement(query);
        pst.setString(1, restaurantName);
        rs = pst.executeQuery();

        while (rs.next()) {
            String reviewerName = rs.getString("reviewerName");
            String review = rs.getString("review");
            double rating = rs.getDouble("rating"); // Get the rating as a double
            Review reviewObj = new Review(restaurantName, reviewerName, review, rating);
            reviewList.add(reviewObj);
        }

        // Update the reviews list with the latest data for the specific restaurant
        reviews.removeIf(review -> review.getRestaurantName().equalsIgnoreCase(restaurantName));
        reviews.addAll(reviewList);

    } finally {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    return reviewList;
}
}
    