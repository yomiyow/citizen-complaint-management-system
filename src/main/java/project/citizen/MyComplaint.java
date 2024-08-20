
package project.citizen;

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
public class MyComplaint extends javax.swing.JPanel {
    
    private Connection connection;
    private DefaultTableModel tableModel;
    
    private String email;
    private byte[] proofImageData;
    
    private int complaintNo;
    private String category;
    private String description;
    private String date;
    private String location;
    private String landmark;
    private String urgencyLevel;
    private String status;
    
    /**
     * Constructs a new MyComplaint panel.
     * Initializes GUI components, sets up table header, customizes components,
     * establishes database connection, and populates the complaints table.
     */
    public MyComplaint() {
        initComponents();
        initTableHeader();
        initTableColumn();
        customizeComponents();
        customizeCellRender();
        
        try {
            this.connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MyComplaint.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        this.tableModel = (DefaultTableModel) complaintTable.getModel();
        this.email = LoginFrame.email;
        
        populateTable();
        
    }

    /**
     * Populates the complaints table with user-specific complaints retrieved from the database.
     */
    private void populateTable() {
  
        String selectQuery = 
                
                "SELECT " +
                    "[ComplaintNo], [Category], " +
                    "[Description], [CreatedDate], " + 
                    "[Location], [Landmark], " + 
                    "[UrgencyLevel], [Status] " +
                "FROM [UserComplaint] " +
                "WHERE [UserID] = (" +
                    "SELECT [UserID] " +
                    "FROM [User] " +
                    "WHERE [Email] = ?) " +
                    "AND [Status] != 'Withdrawn'";
        
        try (PreparedStatement complaint = connection.prepareStatement(selectQuery)) {
            
            complaint.setString(1, this.email);
            
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
            Logger.getLogger(MyComplaint.class.getName()).log(Level.SEVERE, "populateTable method", ex);
        }
        
    }
    
    
    /**
     * Initializes the table header with a specific font and alignment.
     */
    private void initTableHeader() {
        
        JTableHeader header = complaintTable.getTableHeader();
        header.setFont(new Font("Roboto", Font.PLAIN, 15));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        
    }
    
    
    private void initTableColumn() {
        
        TableColumn complaintNoColumn = complaintTable.getColumnModel().getColumn(ComplaintColumn.COMPLAINT_NO.ordinal());
        complaintNoColumn.setPreferredWidth(5);
        
    }
    
    
    /**
     * Customizes components, such as setting padding for the status combo box.
     */
    private void customizeComponents() {
        
        statusComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 3, 10, 3, 10;");
        
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
        filterBy = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        refreshButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        complaintTableScrollPane = new javax.swing.JScrollPane();
        complaintTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1056, 750));

        java.awt.GridBagLayout topPanelLayout = new java.awt.GridBagLayout();
        topPanelLayout.columnWidths = new int[] {0, 10, 0, 10, 0};
        topPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        topPanel.setLayout(topPanelLayout);

        filterBy.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        filterBy.setForeground(new java.awt.Color(51, 51, 51));
        filterBy.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        topPanel.add(filterBy, gridBagConstraints);

        statusComboBox.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        statusComboBox.setForeground(new java.awt.Color(51, 51, 51));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='gray'>All...</font></html>", "New", "Under Review", "Assigned", "Resolved" }));
        statusComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statusComboBox.setPreferredSize(new java.awt.Dimension(140, 40));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
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
                true, false, false, false, false, false, false, false
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
        
        tableModel.setRowCount(0);
        statusComboBox.setSelectedIndex(0);
        tableModel.setRowCount(0);
        populateTable();
        
    }//GEN-LAST:event_refreshButtonActionPerformed

    
    /**
     * Handles the action event when the status combo box selection changes.
     * Filters complaints based on the selected status.
     */
    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        
        String selectedStatus = statusComboBox.getSelectedItem().toString();
        tableModel.setRowCount(0);
        
        String selectQuery = 
                
                "SELECT " +
                    "[ComplaintNo], [Category], " + 
                    "[Description], [CreatedDate], " + 
                    "[Location], [Landmark], " + 
                    "[UrgencyLevel], [Status] " +
                "FROM [UserComplaint] " +
                "WHERE [Status] = ? AND [UserID] = (" +
                                            "SELECT [UserID] " +
                                            "FROM [User] " +
                                            "WHERE [Email] = ?)";
        
        try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {
        
            pst.setString(1, selectedStatus);
            pst.setString(2, this.email);
            
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
            Logger.getLogger(MyComplaint.class.getName())
                  .log(Level.SEVERE, "statusComboBoxActionPerformed method", ex);
        }
        
        
        if (selectedStatus.equals("<html><font color='gray'>All...</font></html>") ) {
           populateTable();
        }
        
    }//GEN-LAST:event_statusComboBoxActionPerformed

    
    /**
     * Handles the mouse click event on the complaints table.
     * Displays detailed information about the selected complaint and allows withdrawal.
     */
    private void complaintTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complaintTableMouseClicked
        
        int selectedRow = 0;
        
        try {
            selectedRow = complaintTable.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(MyComplaint.class.getName())
                  .log(Level.SEVERE, "complaintTableMouseClicked method", ex);
        }
        
        // Getting the value of each column at the selected row.
        try {
            
            complaintNo = Integer.parseInt(String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.COMPLAINT_NO.ordinal())));
            category = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.CATEGORY.ordinal()));
            description = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.DESCRIPTION.ordinal()));
            date = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.DATE.ordinal()));
            location = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.LOCATION.ordinal()));
            landmark = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.LANDMARK.ordinal()));
            urgencyLevel = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.URGENCY_LEVEL.ordinal()));
            status = String.valueOf(complaintTable.getValueAt(selectedRow, ComplaintColumn.STATUS.ordinal()));
            
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(MyComplaint.class.getName())
                  .log(Level.SEVERE, "getValueAt method", ex);
        }
        
        
        // Retrieving the associated image of the selected row in the table.
        String selectImage =
                "SELECT [Proof] FROM [UserComplaint] " +
                "WHERE [ComplaintNo] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectImage)) {
            
            pst.setInt(1, complaintNo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                this.proofImageData = rs.getBytes("Proof");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MyComplaint.class.getName())
                  .log(Level.SEVERE, "complaintTableMouseClicked method", ex);
        }
        
        // Resizing the image
        ImageIcon imageIcon = new ImageIcon(proofImageData);
        Image scaledImage = imageIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
          
        final int CLICKED_2_TIMES = 2;
        
        if (evt.getClickCount() == CLICKED_2_TIMES) {
            
            String popUpDetails = 
                    "<html>" +
                    "Category:<b> " + category + "</b><br>" +
                    "Description:<b> " + description + "</b><br>" +
                    "Created Date:<b> " + date + "</b><br>" +
                    "Location:<b> " + location + "</b><br>" +
                    "Landmark:<b> " + landmark + "</b><br>" +
                    "Urgency Level:<b> " + urgencyLevel + "</b><br>" +
                    "Status:<b> " + status + "</b><br>" + "<br>" +
                    "</html>";
            
            Object[] content = {
                new JLabel(popUpDetails),
                new JLabel(scaledImageIcon)
            };
            
            
            int option = JOptionPane.showOptionDialog(
                                this, 
                                content, 
                                "Complaint Details", 
                                JOptionPane.YES_NO_OPTION, 
                                JOptionPane.PLAIN_MESSAGE, 
                                null, 
                                new String[]{"Withdraw", "Cancel"}, 
                                "Withdraw");
            
            
            
            
            if (option == JOptionPane.YES_OPTION) {
                
                if (!status.equals("New") && !status.equals("Under Review")) {
                
                JOptionPane.showMessageDialog(
                    this, 
                    "Cannot withdraw. The complaint is being addressed or resolved.", 
                    "Withdrawal Not Allowed", 
                    JOptionPane.WARNING_MESSAGE);
                
                return;
                }
                
                int confirmOption = JOptionPane.showConfirmDialog(
                                            this, 
                                            "Are you sure you want to withdraw this complaint?", 
                                            "Confirm Withdrawal", 
                                            JOptionPane.YES_NO_OPTION);
                
                if (confirmOption == JOptionPane.YES_OPTION) {
                    
                    String updateQuery = 
                            "UPDATE [UserComplaint] " +
                            "SET [Status] = 'Withdrawn' " +
                            "WHERE [ComplaintNo] = ?";

                    try (PreparedStatement pst = connection.prepareStatement(updateQuery)) {

                        pst.setInt(1, complaintNo);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(
                                            this, 
                                            "Complaint has been Withdrawn.", 
                                            "Withdrawal Successful", 
                                            JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(MyComplaint.class.getName())
                              .log(Level.SEVERE, "complaintTableMouseClicked method", ex);
                    }
                }
            }
        }
        
        
    }//GEN-LAST:event_complaintTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable complaintTable;
    private javax.swing.JScrollPane complaintTableScrollPane;
    private javax.swing.JLabel filterBy;
    private javax.swing.JButton refreshButton;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
