package project.admin;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import project.authentication.LoginFrame;
import project.concrete_class.ComplaintColumn;
import project.concrete_class.MultilineTableCellRenderer;
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
public class ManageComplaints extends javax.swing.JPanel {
    
    private Connection connection;
    private DefaultTableModel tableModel;
    
    private String email;
    private byte[] proofImageData;
    String fullName;
    
    /**
     * Constructs a new MyComplaint panel.
     * Initializes GUI components, sets up table header, customizes components,
     * establishes database connection, and populates the complaints table.
     */
    public ManageComplaints() {
        initComponents();
        initTableHeader();
        initTableColumn();
        customizeCumponent();
        customizeCellRender();
        
        try {
            this.connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        this.tableModel = (DefaultTableModel) complaintTable.getModel();
        
        this.email = LoginFrame.email;
        
        populateTable();
        
    }
    
    
    private void customizeCumponent() {
        statusComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 3, 10, 3, 10;");
        
        searchTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;");
    }
    

    /**
     * Populates the complaints table with user-specific complaints retrieved from the database.
     */
    private void populateTable() {
  
        tableModel.setRowCount(0);
        
        String selectQuery = 
                "SELECT " +
                    "[ComplaintNo], [Category], " +
                    "[Description], [CreatedDate], " +
                    "[Location], [Landmark], " +
                    "[UrgencyLevel], [Status] " +
                "FROM [UserComplaint]";
        
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
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, " populateTable method ", ex);
        }
    }
    
    
    /**
     * Initializes the table header with a specific font and alignment.
     */
    private void initTableHeader() {
        
        JTableHeader header = complaintTable.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 15));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        
    }
    
    
    private void initTableColumn() {
        
        TableColumn complaintNoColumn = complaintTable.getColumnModel().getColumn(ComplaintColumn.COMPLAINT_NO.ordinal());
        complaintNoColumn.setPreferredWidth(5);
        
    }
       
    
    /**
     * Customizes cell rendering for the complaints table to support multiline text.
     */
    private void customizeCellRender() {
     
        for (int i = 0; i < complaintTable.getColumnCount(); i++) {
            
            complaintTable.getColumnModel()
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
        tablePanel = new javax.swing.JPanel();
        complaintTableScrollPane = new javax.swing.JScrollPane();
        complaintTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1056, 750));

        java.awt.GridBagLayout topPanelLayout = new java.awt.GridBagLayout();
        topPanelLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
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
        updateStatusButton.setToolTipText("Update status of selected complaint.");
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
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='b6b6b6'>Status</font></html>", "New", "Under Review", "Assigned", "Resolved" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
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
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 315);
        topPanel.add(refreshButton, gridBagConstraints);

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));

        complaintTableScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        complaintTable.setAutoCreateRowSorter(true);
        complaintTable.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        complaintTable.setForeground(new java.awt.Color(51, 51, 51));
        complaintTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Category", "Description", "Date", "Location", "Landmark", "Urgency Level", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        complaintTable.setFillsViewportHeight(true);
        complaintTable.setRowHeight(40);
        complaintTable.setShowHorizontalLines(true);
        complaintTable.getTableHeader().setReorderingAllowed(false);
        complaintTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                complaintTableMouseClicked(evt);
            }
        });
        complaintTableScrollPane.setViewportView(complaintTable);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(complaintTableScrollPane)
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(complaintTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private void complaintTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complaintTableMouseClicked
        
        int selectedRow = 0;
        
        try {
            selectedRow = complaintTable.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, " complaintTableMouseClicked method ", ex);
        }
        
        // Getting the value of each column at the selected row.
        int complaintNo = Integer.parseInt(String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.COMPLAINT_NO.ordinal())));
        String category = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.CATEGORY.ordinal()));
        String description = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.DESCRIPTION.ordinal()));
        String date = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.DATE.ordinal()));
        String location = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.LOCATION.ordinal()));
        String landmark = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.LANDMARK.ordinal()));
        String urgencyLevel = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.URGENCY_LEVEL.ordinal()));
        String status = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.STATUS.ordinal()));
        
        // Retrieving the associated image of the selected row in the table.
        String selectImage =
                "SELECT " +
                    "c.[Proof], " +
                    "u.[Firstname] + ' ' + u.[Lastname] AS [Fullname]" +
                "FROM [UserComplaint] AS c " +
                "INNER JOIN [User] AS u " +
                    "ON c.[UserID] = u.[UserID] " +
                "WHERE c.[ComplaintNo] = ?";  
        
        try (PreparedStatement pst = connection.prepareStatement(selectImage)) {
            
            pst.setInt(1, complaintNo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                this.proofImageData = rs.getBytes("Proof");
                this.fullName = rs.getString("Fullname");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, " complaintTableMouseClicked method ", ex);
        }
        
        // clicked a row
        final int CLICKED_1_TIMES = 1;
        if (evt.getClickCount() == CLICKED_1_TIMES) {
            statusComboBox.setSelectedItem(status);
        }
        
        // Resizing the image
        ImageIcon imageIcon = new ImageIcon(proofImageData);
        Image scaledImage = imageIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        
        // Clicked a row 2 times
        final int CLICKED_2_TIMES = 2;
        if (evt.getClickCount() == CLICKED_2_TIMES) {
            String complaintDetails = 
                    "<html>" +
                    "Category:<b> " + category + "</b><br>" +
                    "Description:<b> " + description + "</b><br>" +
                    "Created Date:<b> " + date + "</b><br>" +
                    "Location:<b> " + location + "</b><br>" +
                    "Landmark:<b> " + landmark + "</b><br>" +
                    "Urgency Level:<b> " + urgencyLevel + "</b><br>" +
                    "Status:<b> " + status + "</b><br>" + "<br>" +
                    "Complainant:<b> " + this.fullName + "</b><br>" + "<br>" +
                    "</html>";
            
            Object[] content = {
                new JLabel(complaintDetails),
                new JLabel(scaledImageIcon)
            };
            
            // here na ko
            int option = JOptionPane.showOptionDialog(
                                this, 
                                content, 
                                "Complaint Details", 
                                JOptionPane.YES_NO_OPTION, 
                                JOptionPane.PLAIN_MESSAGE, 
                                null, 
                                new String[]{"Delete", "Cancel"}, 
                                "Delete");
            
            
            if (option == JOptionPane.YES_OPTION) {
                
                int confirmOption = JOptionPane.showConfirmDialog(
                                            this, 
                                            "Are you sure you want to delete this complaint?", 
                                            "Confirm Deletion", 
                                            JOptionPane.YES_NO_OPTION);
                
                if (confirmOption == JOptionPane.YES_OPTION) {
                    
                    String updateQuery = 
                            "DELETE FROM [UserComplaint] " +
                            "WHERE [ComplaintNo] = ?";

                    try (PreparedStatement pst = connection.prepareStatement(updateQuery)) {

                        pst.setInt(1, complaintNo);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(
                                            this, 
                                            "The selected complaint has been deleted successfully.", 
                                            "Deletion Completed", 
                                            JOptionPane.INFORMATION_MESSAGE);
            
                    } catch (SQLException ex) {
                        Logger.getLogger(ManageComplaints.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_complaintTableMouseClicked

    
    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        
        tableModel.setRowCount(0);
        
        String searchInput = searchTextField.getText();
        
        String searchQuery =  
                "SELECT " +
                    "[ComplaintNo], [Category], " +
                    "[Description], [CreatedDate], " +
                    "[Location], [Landmark], " +
                    "[UrgencyLevel], [Status] " +
                "FROM [UserComplaint] " +
                "WHERE " +
                    "[Category] LIKE ? OR " +
                    "[Location] LIKE ? OR " +
                    "[UrgencyLevel] LIKE ? OR " +
                    "[Status] LIKE ? OR " +
                    "[CreatedDate] LIKE ?";
        
        
        try (PreparedStatement pst = connection.prepareStatement(searchQuery)) {
            
            String searchPattern = "%" + searchInput + "%";
            
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);
            pst.setString(5, searchPattern);
            
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
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, " searchTextFieldKeyReleased method ", ex);
        }
        
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void updateStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStatusButtonActionPerformed
        
        String selectedStatus = statusComboBox.getSelectedItem().toString();
        int selectedRow = complaintTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    tablePanel, 
                    "Please select a row.",
                    "No Row Selected",
                    JOptionPane.WARNING_MESSAGE);
        }
            
        int complaintNo = Integer.parseInt(String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.COMPLAINT_NO.ordinal())));
        
        String updateQuery = 
                "UPDATE [UserComplaint] " + 
                "SET [Status] = ? " +
                "WHERE [ComplaintNo] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(updateQuery)) {
        
            pst.setString(1, selectedStatus);
            pst.setInt(2, complaintNo);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(
                    tablePanel,
                    "Complaint status has been successfully updated.",
                    "Complaint Status Updated",
                    JOptionPane.INFORMATION_MESSAGE);
            
            populateTable();
            
            statusComboBox.setSelectedIndex(0);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageComplaints.class.getName())
                  .log(Level.SEVERE, " updateStatusButtonActionPerformed method ", ex);
        }
    }//GEN-LAST:event_updateStatusButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable complaintTable;
    private javax.swing.JScrollPane complaintTableScrollPane;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton updateStatusButton;
    // End of variables declaration//GEN-END:variables
}
