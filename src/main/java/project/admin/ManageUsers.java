package project.admin;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Font;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import project.authentication.LoginFrame;
import project.concrete_class.MultilineTableCellRenderer;
import project.concrete_class.UserColumn;
import project.database.Database;


/**
 * The MyComplaint class represents a panel for displaying and managing user complaints.
 * It provides functionalities to populate, filter, and interact with the complaints table.
 * This panel includes options to view complaint details, withdraw complaints, and refresh the table.
 * 
 * The complaints are fetched from the database based on the currently logged-in user's email.
 * Users can filter complaints by their status (e.g., New, Assigned, Resolved).
 * Double-clicking on a complaint row displays detailed information about the complaint,
 * including its category, description, date, location, landmark, urgency level, and status.
 * Additionally, users can withdraw complaints by selecting the respective option from the pop-up window.
 */
public class ManageUsers extends javax.swing.JPanel {
    
    private Connection connection;
    private DefaultTableModel tableModel;
    
    private String email;
    private byte[] profilePictureData;
    
    /**
     * Constructs a new MyComplaint panel.
     * Initializes GUI components, sets up table header, customizes components,
     * establishes database connection, and populates the complaints table.
     */
    public ManageUsers() {
        initComponents();
        initTableHeader();
        initTableColumn();
        customizeComponents();
        customizeCellRender();
        
        try {
            this.connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        this.tableModel = (DefaultTableModel) userTable.getModel();
        
        this.email = LoginFrame.email;
        
        populateTable();
        
    }

    /**
     * Populates the complaints table with user-specific complaints retrieved from the database.
     */
    private void populateTable() {
  
        tableModel.setRowCount(0);
        
        String selectQuery = 
                "SELECT " +
                       "u.[Firstname] + ' ' + u.[Lastname], " +
                       "u.[Email], u.[ContactNo], " +
                       "u.[Street] + ' ' + u.[Barangay] + ' ' + " +
                       "u.[City] + ' ' + u.[Province], " + 
                       "a.[Status] " +
                "FROM [User] AS u " +
                "INNER JOIN [Account] AS a " +
                    "ON u.[Email] = a.[Email] " + 
                "WHERE a.[Role] != 'Admin'";                                                                                                                                                                                                                                                         
        
        try (PreparedStatement complaint = connection.prepareStatement(selectQuery)) {
            
            ResultSet rs = complaint.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            
            while (rs.next()) {
                
                Object[] rowData = new Object[columnCount];
                
                for (int i = 1; i <= columnCount; i++) {
                    
                    rowData[i-1] = rs.getObject(i); 
                    
                }
                
                tableModel.addRow(rowData);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " populateTable method ", ex);
        }
    }
    
    
    /**
     * Initializes the table header with a specific font and alignment.
     */
    private void initTableHeader() {
        
        JTableHeader header = userTable.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 15));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        
    }
    
    
    private void initTableColumn() {
        final int STATUS_COLUMN = 4;
        
        TableColumn complaintNoColumn = userTable.getColumnModel().getColumn(STATUS_COLUMN);
        complaintNoColumn.setPreferredWidth(20);
        
    }
    
    
    /**
     * Customizes components, such as setting padding for the status combo box.
     */
    private void customizeComponents() {
        statusComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 3, 10, 3, 10;");
        
        searchTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;");
    }
    
    
    /**
     * Customizes cell rendering for the complaints table to support multiline text.
     */
    private void customizeCellRender() {
     
        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.getColumnModel()
                          .getColumn(i)
                          .setCellRenderer(new MultilineTableCellRenderer());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        topPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        updateStatusButton = new javax.swing.JButton();
        statusComboBox = new javax.swing.JComboBox<>();
        refreshButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        userTableScrollPane = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1056, 750));

        java.awt.GridBagLayout topPanelLayout = new java.awt.GridBagLayout();
        topPanelLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        topPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        topPanel.setLayout(topPanelLayout);

        searchLabel.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(51, 51, 51));
        searchLabel.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        topPanel.add(searchLabel, gridBagConstraints);

        searchTextField.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        searchTextField.setForeground(new java.awt.Color(51, 51, 51));
        searchTextField.setPreferredSize(new java.awt.Dimension(300, 40));
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        topPanel.add(searchTextField, gridBagConstraints);

        updateStatusButton.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        updateStatusButton.setForeground(new java.awt.Color(51, 51, 51));
        updateStatusButton.setText("Update");
        updateStatusButton.setToolTipText("Update status of selected user.");
        updateStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStatusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        topPanel.add(updateStatusButton, gridBagConstraints);

        statusComboBox.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        statusComboBox.setForeground(new java.awt.Color(51, 51, 51));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='b6b6b6'>Status</font></html>", "Active", "Suspended" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        topPanel.add(statusComboBox, gridBagConstraints);

        refreshButton.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(51, 51, 51));
        refreshButton.setText("Refresh");
        refreshButton.setToolTipText("Refresh table");
        refreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 225);
        topPanel.add(refreshButton, gridBagConstraints);

        printButton.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        printButton.setForeground(new java.awt.Color(51, 51, 51));
        printButton.setText("Print");
        printButton.setToolTipText("Print user information");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        topPanel.add(printButton, gridBagConstraints);

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));

        userTableScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        userTable.setAutoCreateRowSorter(true);
        userTable.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        userTable.setForeground(new java.awt.Color(51, 51, 51));
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "ContactNo", "Address", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setFillsViewportHeight(true);
        userTable.setRowHeight(40);
        userTable.setShowHorizontalLines(true);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        userTableScrollPane.setViewportView(userTable);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTableScrollPane)
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Refreshes the complaints table by clearing existing data and repopulating it.
     */
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        
        searchTextField.setText("");
        statusComboBox.setSelectedIndex(0);
        tableModel.setRowCount(0);
        populateTable();
        
    }//GEN-LAST:event_refreshButtonActionPerformed

    
    
    /**
     * Handles the mouse click event on the complaints table.
     * Displays detailed information about the selected complaint and allows withdrawal.
     */
    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        
        int selectedRow = 0;
        
        try {
            selectedRow = userTable.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " complaintTableMouseClicked method ", ex);
        }
        
        // Getting the value of each column at the selected row.
        String fullName = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.NAME.ordinal()));
        String email = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.EMAIL.ordinal()));
        String contactNo = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.CONTACT_NO.ordinal()));
        String address = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.ADDRESS.ordinal()));
        String status = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.STATUS.ordinal()));    
        
        // Retrieving the associated image of the selected row in the table.
        String selectImage =
                "SELECT [ProfilePicture] " +
                "FROM [User] " +
                "WHERE [Email] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectImage)) {
            
            pst.setString(1, email);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                this.profilePictureData = rs.getBytes("ProfilePicture");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " complaintTableMouseClicked method ", ex);
        }
        
        final int CLICKED_1_TIMES = 1;
        if (evt.getClickCount() == CLICKED_1_TIMES) {
            statusComboBox.setSelectedItem(status);
        }
        
        // Resizing the image
        ImageIcon imageIcon;
        Image scaledImage;
        ImageIcon scaledImageIcon = null;
        try {
            imageIcon = new ImageIcon(this.profilePictureData);
            scaledImage = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            scaledImageIcon = new ImageIcon(scaledImage);
        } catch (NullPointerException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " complaintTableMouseClicked method ", ex);
        }
        
          
        final int CLICKED_2_TIMES = 2;
        if (evt.getClickCount() == CLICKED_2_TIMES) {
            String userInformation = 
                    "<html>" +
                    "Name:<b> " + fullName + "</b><br>" +
                    "Email:<b> " + email + "</b><br>" +
                    "Contact No:<b> " + contactNo + "</b><br>" +
                    "Address:<b> " + address + "</b><br>" +
                    "Status:<b> " + status + "</b><br><br>" +
                    "</html>";
            
            Object[] content = {
                new JLabel(userInformation),
                new JLabel(scaledImageIcon)
            };
            
            int option = JOptionPane.showOptionDialog(
                                this, 
                                content, 
                                "User Information", 
                                JOptionPane.YES_NO_OPTION, 
                                JOptionPane.PLAIN_MESSAGE, 
                                null, 
                                new String[]{"Delete", "Cancel"}, 
                                "Delete");
            
            
            if (option == JOptionPane.YES_OPTION) {
                
                int confirmOption = JOptionPane.showConfirmDialog(
                                            this, 
                                            "Are you sure you want to delete this user?", 
                                            "Confirm Deletion", 
                                            JOptionPane.YES_NO_OPTION);
                
                if (confirmOption == JOptionPane.YES_OPTION) {
                    
                    String userTableQuery = 
                            "DELETE FROM [User] " +
                            "WHERE [Email] = ?";
                    
                    String accountTableQuery = 
                            "DELETE FROM [Account] " +
                            "WHERE [Email] = ?";

                    try (PreparedStatement deleteUser = connection.prepareStatement(userTableQuery);
                         PreparedStatement deleteAccount = connection.prepareStatement(accountTableQuery)) {

                        connection.setAutoCommit(false);
                        
                        deleteUser.setString(1, email);
                        deleteUser.executeUpdate();
                        
                        deleteAccount.setString(1, email);
                        deleteAccount.executeUpdate();
                        
                        connection.commit();

                        JOptionPane.showMessageDialog(
                                            this, 
                                            "The user has been successfully deleted.", 
                                            "Deletion Completed", 
                                            JOptionPane.INFORMATION_MESSAGE);
            
                    } catch (SQLException ex) {
                        Logger.getLogger(ManageComplaints.class.getName())
                              .log(Level.SEVERE, " userTableMouseClicked method ", ex);
                    }
                }
            }
            
        }
        
        
    }//GEN-LAST:event_userTableMouseClicked

    
    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        
        tableModel.setRowCount(0);
        
        String searchInput = searchTextField.getText();
        
        String searchQuery =  
                "SELECT " +
                       "u.[Firstname] + ' ' + u.[Lastname], " +
                       "u.[Email], u.[ContactNo], " +
                       "u.[Street] + ' ' + u.[Barangay] + ' ' + " +
                       "u.[City] + ' ' + u.[Province], " + 
                       "a.[Status] " +
                "FROM [User] AS u " +
                "INNER JOIN [Account] AS a " +
                    "ON u.[Email] = a.[Email] " +
                "WHERE " +
                    "(u.[Firstname] + ' ' + u.[Lastname] LIKE ? " +
                    "OR u.[Email] LIKE ? " +
                    "OR u.[ContactNo] LIKE ? " +
                    "OR u.[Street] LIKE ? " +
                    "OR u.[Barangay] LIKE ? " +
                    "OR u.[City] LIKE ? " +
                    "OR u.[Province] LIKE ? " +
                    "OR a.[Status] LIKE ?) " +
                    "AND a.[Role] != 'Admin'";
        
        try (PreparedStatement pst = connection.prepareStatement(searchQuery)) {
            
            String searchPattern = "%" + searchInput + "%";
            
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);
            pst.setString(5, searchPattern);
            pst.setString(6, searchPattern);
            pst.setString(7, searchPattern);
            pst.setString(8, searchPattern);
            
            ResultSet rs = pst.executeQuery();
            
            int columnCount = rs.getMetaData().getColumnCount();
            
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                
                for (int i = 1; i <= columnCount; i++) {
                    row[i-1] = rs.getObject(i);
                }
                
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " searchTextFieldKeyReleased method ", ex);
        }
        
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void updateStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStatusButtonActionPerformed
        
        String selectedStatus = statusComboBox.getSelectedItem().toString();
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    userTable, 
                    "Please select a row.",
                    "No Row Selected",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        String email = String.valueOf(userTable.getValueAt(selectedRow, UserColumn.EMAIL.ordinal()));
        
        String updateQuery = 
                "UPDATE [Account] " + 
                "SET [Status] = ? " +
                "WHERE [Email] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(updateQuery)) {
        
            pst.setString(1, selectedStatus);
            pst.setString(2, email);
            pst.executeUpdate();
            
            populateTable();
            
            statusComboBox.setSelectedIndex(0);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " updateStatusButtonActionPerformed method ", ex);
        }
    }//GEN-LAST:event_updateStatusButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
       
        MessageFormat header = new MessageFormat("Users Information Report");
        
        try {
            
            userTable.print(JTable.PrintMode.FIT_WIDTH, header, null);
            
        } catch (PrinterException ex) {
            Logger.getLogger(ManageUsers.class.getName())
                  .log(Level.SEVERE, " printReportButtonActionPerformed method ", ex);
        }
        
    }//GEN-LAST:event_printButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton printButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton updateStatusButton;
    private javax.swing.JTable userTable;
    private javax.swing.JScrollPane userTableScrollPane;
    // End of variables declaration//GEN-END:variables
}
