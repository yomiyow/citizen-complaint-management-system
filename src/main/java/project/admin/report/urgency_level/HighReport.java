package project.admin.report.urgency_level;

import project.admin.report.category.*;
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
import project.concrete_class.ComplaintColumn;
import project.concrete_class.MultilineTableCellRenderer;
import project.database.Database;


public class HighReport extends javax.swing.JPanel {
    
    private Connection connection;
    private DefaultTableModel tableModel;
    
    private String email;
    private byte[] proofImageData;
    
    public HighReport() {
        initComponents();
        initTableHeader();
        initTableColumn();
        customizeCellRender();
        customizeCumponent();
        
        try {
            this.connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(HighReport.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        this.tableModel = (DefaultTableModel) complaintTable.getModel();
        
        this.email = LoginFrame.email;
        
        populateTable();
        
    }

    
    private void customizeCumponent() {
        
        searchTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;");
        
    }
    
    private void populateTable() {
  
        String selectQuery = 
                "SELECT " +
                    "[ComplaintNo], [Category], " +
                    "[Description], [CreatedDate], " +
                    "[Location], [Landmark], " +
                    "[UrgencyLevel], [Status] " +
                "FROM [UserComplaint] " +
                "WHERE [UrgencyLevel] = 'High'";
        
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
            Logger.getLogger(HighReport.class.getName())
                  .log(Level.SEVERE, "populateTable method", ex);
        }
    }
    
    
    private void initTableHeader() {
        
        JTableHeader header = complaintTable.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 15));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        
    }
    
    
    private void initTableColumn() {
        
        TableColumn complaintNoColumn = complaintTable.getColumnModel().getColumn(ComplaintColumn.COMPLAINT_NO.ordinal());
        complaintNoColumn.setPreferredWidth(5);
        
        TableColumn dateColumn = complaintTable.getColumnModel().getColumn(ComplaintColumn.DATE.ordinal());
        dateColumn.setPreferredWidth(30);

    }
     
    
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
        reportTitle = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        printReportButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        complaintTableScrollPane = new javax.swing.JScrollPane();
        complaintTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1056, 750));

        java.awt.GridBagLayout topPanelLayout = new java.awt.GridBagLayout();
        topPanelLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0};
        topPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        topPanel.setLayout(topPanelLayout);

        reportTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        reportTitle.setText("High Level Complaint Report");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        topPanel.add(reportTitle, gridBagConstraints);

        searchLabel.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        searchLabel.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        topPanel.add(searchLabel, gridBagConstraints);

        searchTextField.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        searchTextField.setPreferredSize(new java.awt.Dimension(300, 40));
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        topPanel.add(searchTextField, gridBagConstraints);

        printReportButton.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        printReportButton.setText("Print");
        printReportButton.setToolTipText("Print table content");
        printReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        topPanel.add(printReportButton, gridBagConstraints);

        refreshButton.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
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
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        
        searchTextField.setText("");
        tableModel.setRowCount(0);
        populateTable();
        
    }//GEN-LAST:event_refreshButtonActionPerformed

    
    private void complaintTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_complaintTableMouseClicked
        
        int selectedRow = 0;
        
        try {
            selectedRow = complaintTable.getSelectedRow();
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(HighReport.class.getName())
                  .log(Level.SEVERE, "complaintTableMouseClicked", ex);
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
                "SELECT [Proof] " +
                "FROM [UserComplaint] " +
                "WHERE [ComplaintNo] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectImage)) {
            
            pst.setInt(1, complaintNo);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                this.proofImageData = rs.getBytes("Proof");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HighReport.class.getName()).log(Level.SEVERE, "complaintTableMouseClicked", ex);
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
            
            // get the selected option
            JOptionPane.showMessageDialog(
                    complaintTable, 
                    content, 
                    "Complaint Details",
                    JOptionPane.PLAIN_MESSAGE);
            
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
                    "([Category] LIKE ? OR " +
                    "[Location] LIKE ? OR " +
                    "[UrgencyLevel] LIKE ? OR " +
                    "[Status] LIKE ?) AND " +
                    "[UrgencyLevel] = 'High'";
        
        try (PreparedStatement pst = connection.prepareStatement(searchQuery)) {
            
            String searchPattern = "%" + searchInput + "%";
            
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);
            
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
            Logger.getLogger(HighReport.class.getName())
                  .log(Level.SEVERE, " searchTextFieldKeyReleased method ", ex);
        }
        
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void printReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportButtonActionPerformed
        
        MessageFormat header = new MessageFormat(reportTitle.getText());
        
        try {
            
            complaintTable.print(JTable.PrintMode.FIT_WIDTH, header, null);
            
        } catch (PrinterException ex) {
            Logger.getLogger(HighReport.class.getName())
                  .log(Level.SEVERE, " printReportButtonActionPerformed method ", ex);
        }
        
    }//GEN-LAST:event_printReportButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable complaintTable;
    private javax.swing.JScrollPane complaintTableScrollPane;
    private javax.swing.JButton printReportButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel reportTitle;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
