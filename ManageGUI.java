package pap;

import java.sql.ResultSet;

import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class ManageGUI extends javax.swing.JFrame {

    private final DefaultTableModel tableModel;
    private final RestaurantManager restaurantManager;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Ceah3007";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public ManageGUI() {
    initComponents();

    tableModel = (DefaultTableModel) tblOutput.getModel();
    restaurantManager = new RestaurantManager(tableModel);

    // Call the refreshTable() method to populate the JTable with data from the database
    try {
        restaurantManager.updateTableData(tableModel);
    } catch (SQLException ex) {
        System.out.println("Error updating table data: " + ex.getMessage());
    }

    // Set TableRowSorter for the JTable
    tblOutput.setRowSorter(new TableRowSorter<>(tableModel));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        sortNamebtn = new javax.swing.JButton();
        sortLocationbn = new javax.swing.JButton();
        sortCuisinebtn = new javax.swing.JButton();
        sortRatingbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOutput = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblRestaurantName = new javax.swing.JLabel();
        lblRestaurantLocation = new javax.swing.JLabel();
        lblRestaurantCuisine = new javax.swing.JLabel();
        lblRestaurantRating = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        txtCuisine = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtComments = new javax.swing.JTextField();
        btnHome = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        searchButton1 = new javax.swing.JButton();
        searchTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnAdd)
                .addGap(111, 111, 111)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(90, 90, 90))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(40, 300, 710, 47);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        jLabel5.setText("Sort By:");

        sortNamebtn.setText("NAME");
        sortNamebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortNamebtnActionPerformed(evt);
            }
        });

        sortLocationbn.setText("LOCATION");
        sortLocationbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortLocationbnActionPerformed(evt);
            }
        });

        sortCuisinebtn.setText("CUISINE");
        sortCuisinebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortCuisinebtnActionPerformed(evt);
            }
        });

        sortRatingbtn.setText("RATING");
        sortRatingbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortRatingbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addGap(37, 37, 37)
                .addComponent(sortNamebtn)
                .addGap(40, 40, 40)
                .addComponent(sortLocationbn)
                .addGap(36, 36, 36)
                .addComponent(sortCuisinebtn)
                .addGap(41, 41, 41)
                .addComponent(sortRatingbtn)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortNamebtn)
                    .addComponent(sortLocationbn)
                    .addComponent(sortCuisinebtn)
                    .addComponent(sortRatingbtn)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(30, 410, 720, 50);

        tblOutput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Location", "Cuisine", "Rating", "Comments"
            }
        ));
        jScrollPane2.setViewportView(tblOutput);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(40, 480, 700, 181);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel2.setText("Restaurant Information");

        lblRestaurantName.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        lblRestaurantName.setText("Restaurant Name:");

        lblRestaurantLocation.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        lblRestaurantLocation.setText("Restaurant Location: ");

        lblRestaurantCuisine.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        lblRestaurantCuisine.setText("Restaurant Cuisine :");

        lblRestaurantRating.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        lblRestaurantRating.setText("Restaurant Rating:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 15)); // NOI18N
        jLabel3.setText("Restaurant Comments:");

        txtComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCommentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRestaurantName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblRestaurantLocation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRestaurantRating, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblRestaurantCuisine)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRating)
                            .addComponent(txtComments)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLocation)
                            .addComponent(txtCuisine))))
                .addGap(123, 123, 123))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRestaurantName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRestaurantLocation)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRestaurantCuisine)
                    .addComponent(txtCuisine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRestaurantRating)
                    .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(40, 70, 700, 225);

        btnHome.setFont(new java.awt.Font("Sitka Text", 1, 15)); // NOI18N
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);
        btnHome.setBounds(670, 670, 90, 28);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        searchButton1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        searchButton1.setText("SEARCH");
        searchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(searchButton1)
                .addGap(51, 51, 51)
                .addComponent(searchTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton1)
                    .addComponent(searchTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 360, 718, 47);

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\USER\\Downloads\\Untitled (781 × 712px) (1).png")); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 780, 710);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCommentsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCommentsActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        // TODO add your handling code here:
        String name = txtName.getText();
        String location = txtLocation.getText();
        String cuisine = txtCuisine.getText();
        double rating = Double.parseDouble(txtRating.getText());
        String comments = txtComments.getText();

        try {
            Connection conn = getConnection();
            String query = "INSERT INTO restaurant (name, location, cuisine, rating, comments) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, location);
            pst.setString(3, cuisine);
            pst.setDouble(4, rating);
            pst.setString(5, comments);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Restaurant added successfully!");
            }

            pst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        restaurantManager.addRestaurant(name, location, cuisine, rating, comments);
        restaurantManager.addRestaurantToTableModel(name, location, cuisine, rating, comments);

        txtName.setText("");
        txtLocation.setText("");
        txtCuisine.setText("");
        txtRating.setText("");
        txtComments.setText("");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        int selectedRow = tblOutput.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a restaurant to delete.");
            return;
        }

        String name = (String) tblOutput.getValueAt(selectedRow, 0); // Assuming the first column contains the restaurant name

        try {
            restaurantManager.deleteAndUpdateTable(name);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting restaurant: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblOutput.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a restaurant to update.");
            return;
        }

        String name = (String) tblOutput.getValueAt(selectedRow, 0); // Assuming the first column contains the restaurant name
        String location = txtLocation.getText();
        String cuisine = txtCuisine.getText();
        double rating = Double.parseDouble(txtRating.getText());
        String comments = txtComments.getText();

        try {
            restaurantManager.updateRestaurant(name, location, cuisine, rating, comments);
            restaurantManager.refreshTable(); // After updating, refresh the JTable to show the updated data
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating restaurant in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        HomePageGUI d = new HomePageGUI();
        d.show();
        dispose();

    }//GEN-LAST:event_btnHomeActionPerformed

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
        // TODO add your handling code here:
        String keyword = searchTextField1.getText();
    searchAndUpdateTable(keyword);

    }//GEN-LAST:event_searchButton1ActionPerformed

    private void sortNamebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortNamebtnActionPerformed
        // TODO add your handling code here:
        sortAndUpdateTable("name");
    }//GEN-LAST:event_sortNamebtnActionPerformed

    private void sortCuisinebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortCuisinebtnActionPerformed
        // TODO add your handling code here:
        sortAndUpdateTable("cuisine");
    }//GEN-LAST:event_sortCuisinebtnActionPerformed

    private void sortLocationbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortLocationbnActionPerformed
        // TODO add your handling code here:
        sortAndUpdateTable("location");
    }//GEN-LAST:event_sortLocationbnActionPerformed

    private void sortRatingbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortRatingbtnActionPerformed
        // TODO add your handling code here:
        sortAndUpdateTable("rating");
    }//GEN-LAST:event_sortRatingbtnActionPerformed

   private void sortAndUpdateTable(String columnToSort) {
    List<Restaurant> sortedRestaurants = null;
    
    // Sort the restaurants based on the selected column
    switch (columnToSort) {
        case "name":
        {
            try {
                sortedRestaurants = restaurantManager.getAllRestaurants().stream()
                        .sorted(Comparator.comparing(Restaurant::getName))
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                Logger.getLogger(ManageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

        case "cuisine":
        {
            try {
                sortedRestaurants = restaurantManager.getAllRestaurants().stream()
                        .sorted(Comparator.comparing(Restaurant::getCuisine))
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                Logger.getLogger(ManageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

        case "location":
        {
            try {
                sortedRestaurants = restaurantManager.getAllRestaurants().stream()
                        .sorted(Comparator.comparing(Restaurant::getLocation))
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                Logger.getLogger(ManageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

        case "rating":
        {
            try {
                sortedRestaurants = restaurantManager.getAllRestaurants().stream()
                        .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                Logger.getLogger(ManageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;

        default:
            JOptionPane.showMessageDialog(this, "Invalid column for sorting.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
    }
    
    // Update the table with the sorted data
    tableModel.setRowCount(0);
    for (Restaurant restaurant : sortedRestaurants) {
        Object[] rowData = {restaurant.getName(), restaurant.getLocation(),
            restaurant.getCuisine(), restaurant.getRating(), restaurant.getComments()};
        tableModel.addRow(rowData);
    }
}

    public void searchAndUpdateTable(String keyword) {
       tableModel.setRowCount(0);

    // Perform the search in the database using the keyword
    try (Connection conn = getConnection()) {
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
        JOptionPane.showMessageDialog(this, "Error searching restaurant: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblRestaurantCuisine;
    private javax.swing.JLabel lblRestaurantLocation;
    private javax.swing.JLabel lblRestaurantName;
    private javax.swing.JLabel lblRestaurantRating;
    private javax.swing.JButton searchButton1;
    private javax.swing.JTextField searchTextField1;
    private javax.swing.JButton sortCuisinebtn;
    private javax.swing.JButton sortLocationbn;
    private javax.swing.JButton sortNamebtn;
    private javax.swing.JButton sortRatingbtn;
    private javax.swing.JTable tblOutput;
    private javax.swing.JTextField txtComments;
    private javax.swing.JTextField txtCuisine;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRating;
    // End of variables declaration//GEN-END:variables

    private void tblOutputsetRowSorter(TableRowSorter<DefaultTableModel> tr) {
        tblOutput.setRowSorter(new TableRowSorter<>(tableModel));

    }
}