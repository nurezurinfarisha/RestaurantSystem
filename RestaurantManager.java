package pap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

import java.util.stream.Collectors;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class RestaurantManager {

    private final DefaultTableModel tableModel;
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Ceah3007";

    public RestaurantManager(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void addRestaurantToTableModel(String name, String location, String cuisine, double rating, String comments) {
        Object[] rowData = {name, location, cuisine, rating, comments};
        tableModel.addRow(rowData);
    }

    public void addRestaurant(String name, String location, String cuisine, double rating, String comments) {
        try {
            if (rating < 0 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 0 and 5.");
            }

            Restaurant newRestaurant = new Restaurant(name, location, cuisine, rating, comments);
            newRestaurant.addRating(rating);

             restaurants.add(newRestaurant);
            //System.out.println("Restaurant added successfully!");
        } catch (IllegalArgumentException e) {
            
            System.out.println("Restaurant added successfully!");
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Rating", JOptionPane.ERROR_MESSAGE);
            //System.out.println(e.getMessage());
        }

    }

    public void deleteRestaurant(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = getConnection();

            // Create a PreparedStatement to delete the restaurant entry from the database
            String deleteQuery = "DELETE FROM restaurant WHERE name = ?";
            pst = conn.prepareStatement(deleteQuery);
            pst.setString(1, name);

            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Restaurant deleted from the database successfully!");
            } else {
                System.out.println("Failed to delete restaurant from the database.");
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteAndUpdateTable(String name) throws SQLException {
        deleteRestaurant(name);
        updateTableData(tableModel);
    }

    public void calculateAverageRating() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println("---- Calculate Average Rating ----");
        System.out.println("Select a restaurant to display its average rating:");

        int index = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(index + ". " + restaurant.getName());
            index++;
        }

        System.out.print("Enter your choice (1-" + restaurants.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > restaurants.size()) {
            throw new IllegalArgumentException("Invalid choice.");
        }

        Restaurant selectedRestaurant = restaurants.get(choice - 1);
        System.out.println("Average rating for " + selectedRestaurant.getName() + " is: " + selectedRestaurant.getAverageRating());
    }

    public void updateRestaurant(String name, String location, String cuisine, double rating, String comments) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = getConnection();

            // Create a PreparedStatement to update the restaurant entry in the database
            String updateQuery = "UPDATE restaurant SET location = ?, cuisine = ?, rating = ?, comments = ? WHERE name = ?";
            pst = conn.prepareStatement(updateQuery);
            pst.setString(1, location);
            pst.setString(2, cuisine);
            pst.setDouble(3, rating);
            pst.setString(4, comments);
            pst.setString(5, name);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Restaurant updated in the database successfully!");
            } else {
                System.out.println("Failed to update restaurant in the database.");
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void displayRestaurants() {
        if (restaurants.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No restaurants to display!");
            return;
        }

        Object[] options = { "Name", "Location", "Cuisine", "Rating" };
        int choice = JOptionPane.showOptionDialog(null, "Sort Restaurants By:", "Sort Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == -1) {
            return; // User closed the dialog
        }

        List<Restaurant> sortedRestaurants = new ArrayList<>(restaurants);

        switch (choice) {
            case 0:
                sortedRestaurants.sort(Comparator.comparing(Restaurant::getName));
                break;
            case 1:
                sortedRestaurants.sort(Comparator.comparing(Restaurant::getLocation));
                break;
            case 2:
                sortedRestaurants.sort(Comparator.comparing(Restaurant::getCuisine));
                break;
            case 3:
                sortedRestaurants.sort(Comparator.comparing(Restaurant::getAverageRating).reversed());
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice.");
                return;
        }

        // Display the sorted restaurants
        StringBuilder sortedRestaurantInfo = new StringBuilder();
        for (Restaurant restaurant : sortedRestaurants) {
            sortedRestaurantInfo.append("Name: ").append(restaurant.getName()).append("\n")
                    .append("Location: ").append(restaurant.getLocation()).append("\n")
                    .append("Cuisine: ").append(restaurant.getCuisine()).append("\n")
                    .append("Average Rating: ").append(restaurant.getAverageRating()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sortedRestaurantInfo.toString(), "Sorted Restaurants",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Restaurant> getAllRestaurants() throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT * FROM restaurant";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                String cuisine = rs.getString("cuisine");
                double rating = rs.getDouble("rating");
                String comments = rs.getString("comments");

                Restaurant restaurant = new Restaurant(name, location, cuisine, rating, comments);
                restaurantList.add(restaurant);
            }
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

        return restaurantList;
    }

    public void updateTableData(DefaultTableModel tableModel) throws SQLException {
        tableModel.setRowCount(0); // Clear the current data in the table

        List<Restaurant> restaurantList = getAllRestaurants();
        for (Restaurant restaurant : restaurantList) {
            Object[] rowData = {restaurant.getName(), restaurant.getLocation(), restaurant.getCuisine(), restaurant.getRating(), restaurant.getComments()};
            tableModel.addRow(rowData);
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0); // Clear the existing rows in the table model

        for (Restaurant restaurant : restaurants) {
            Object[] rowData = {restaurant.getName(), restaurant.getLocation(), restaurant.getCuisine(),
                restaurant.getAverageRating(), restaurant.getComments()};
            tableModel.addRow(rowData);
        }
    }

    public List<Restaurant> searchRestaurants(String keyword) {
        keyword = keyword.toLowerCase(); // Convert the keyword to lowercase for case-insensitive search

        final String searchKeyword = keyword; // Declare a final variable to use within the lambda expression

        List<Restaurant> matchingRestaurants = restaurants.stream()
                .filter(restaurant
                        -> restaurant.getName().toLowerCase().contains(searchKeyword)
                || restaurant.getLocation().toLowerCase().contains(searchKeyword)
                || restaurant.getCuisine().toLowerCase().contains(searchKeyword))
                .collect(Collectors.toList());

        return matchingRestaurants;
    }

    public void searchAndUpdateTable(String keyword) {
        // Clear the table first
        tableModel.setRowCount(0);

        // Perform the search in the database using the keyword
        try ( Connection conn = getConnection()) {
            String sql = "SELECT * FROM restaurant WHERE name LIKE ? OR location LIKE ? OR cuisine LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String cuisine = resultSet.getString("cuisine");
                double rating = resultSet.getDouble("rating");
                String comments = resultSet.getString("comments");

                // Add the retrieved data to the tableModel
                Object[] rowData = {name, location, cuisine, rating, comments};
                tableModel.addRow(rowData);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error searching restaurant: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Restaurant findRestaurantByName(String name) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        return null;
    }

    
    
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}
