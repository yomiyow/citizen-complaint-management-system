package project.citizen;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import project.authentication.LoginFrame;
import project.concrete_class.ImageFilter;
import project.concrete_class.ComplaintStatus;
import project.database.Database;

/**
 * This class represents the Create Complaint panel of the application.
 * Users can file complaints using this panel.
 *
 * @since 4/27/2024
 */
public class CreateComplaint extends javax.swing.JPanel {
    
    private Connection connection;
    
    private String category;
    private String description;
    private String createdDate;
    private String location;
    private String landmark;
    private String urgencyLevel;
    private byte[] proofImageData;
    private String email;
    private int currentUser;
    
    private SimpleDateFormat dateFormat;
    
    public CreateComplaint() {
        initComponents();
        customizeComponent();
        
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CreateComplaint.class.getName()).log(Level.ALL.SEVERE, ex.getMessage(), ex);
        }
        
        this.email = LoginFrame.email;
        
        this.currentUser = getCurrentUser();
        
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        descriptionTextAreaFocusLost(new FocusEvent(descriptionTextArea, FocusEvent.FOCUS_LOST));
    }

    
     /**
     * Customizes the appearance of Swing components.
     * Sets styles and properties for various components.
     */
    private void customizeComponent() {
        
        categoryComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 3, 10, 3, 10;");
        
        categoryHelpButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,
                FlatClientProperties.BUTTON_TYPE_HELP);
        
        descriptionTextArea.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 10, 10, 3, 10;");
        
        locationTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, 
                "e.g. \"Mc Arthur Highway Malolos Bulacan\"");
        locationTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;");
        
        landmarkTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, 
                "e.g. \"Near BSU Gate 1\"");
        landmarkTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;");
        
        urgencyLevelComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 3, 10, 3, 10;");
        
        urgencyLevelHelpButton.putClientProperty(FlatClientProperties.BUTTON_TYPE, 
                FlatClientProperties.BUTTON_TYPE_HELP);
        
    }
    
    // unused method
    private void loadProofLabelIcon() {
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/upload.png"));
        Image uploadImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(uploadImage);
    
        proofImageLabel.setIcon(scaledImageIcon);
        proofImageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        proofImageLabel.setVerticalAlignment(SwingConstants.CENTER); 
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        header = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Heading = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        Category = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        Description = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        Date = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        Location = new javax.swing.JLabel();
        locationTextField = new javax.swing.JTextField();
        Landmark = new javax.swing.JLabel();
        landmarkTextField = new javax.swing.JTextField();
        urgencyLevelComboBox = new javax.swing.JComboBox<>();
        categoryHelpButton = new javax.swing.JButton();
        urgencyLevelHelpButton = new javax.swing.JButton();
        urgencyLevelComboBox1 = new javax.swing.JComboBox<>();
        Urgency1 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        proofLabel = new javax.swing.JLabel();
        uploadFileButton = new javax.swing.JButton();
        proofImageLabel = new javax.swing.JLabel();
        footerSeparator = new javax.swing.JSeparator();
        footer = new javax.swing.JPanel();
        clearButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();

        header.setLayout(new java.awt.GridBagLayout());

        Title.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Title.setForeground(new java.awt.Color(51, 51, 51));
        Title.setText("Welcome to Citizen Complaint Center!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        header.add(Title, gridBagConstraints);

        Heading.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Heading.setForeground(new java.awt.Color(51, 51, 51));
        Heading.setText("Please use this form to report any concerns or issues you encounter in your community.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        header.add(Heading, gridBagConstraints);

        java.awt.GridBagLayout leftPanelLayout = new java.awt.GridBagLayout();
        leftPanelLayout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0};
        leftPanelLayout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        leftPanel.setLayout(leftPanelLayout);

        Category.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Category.setForeground(new java.awt.Color(51, 51, 51));
        Category.setText("Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 98, 0, 0);
        leftPanel.add(Category, gridBagConstraints);

        categoryComboBox.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        categoryComboBox.setForeground(new java.awt.Color(51, 51, 51));
        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='b6b6b6'>Select...</font></html>", "Environment", "Infrastructure", "Utilities", "Public Services", "Government Agencies" }));
        categoryComboBox.setPreferredSize(new java.awt.Dimension(81, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(categoryComboBox, gridBagConstraints);

        Description.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Description.setForeground(new java.awt.Color(51, 51, 51));
        Description.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 81, 0, 0);
        leftPanel.add(Description, gridBagConstraints);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        descriptionTextArea.setForeground(new java.awt.Color(51, 51, 51));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setToolTipText("");
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionTextAreaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTextAreaFocusLost(evt);
            }
        });
        descriptionScrollPane.setViewportView(descriptionTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        leftPanel.add(descriptionScrollPane, gridBagConstraints);

        Date.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        leftPanel.add(Date, gridBagConstraints);

        dateChooser.setToolTipText("YYYY-MM--DD");
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setPreferredSize(new java.awt.Dimension(99, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        leftPanel.add(dateChooser, gridBagConstraints);

        Location.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Location.setForeground(new java.awt.Color(51, 51, 51));
        Location.setText("Location");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 99, 0, 0);
        leftPanel.add(Location, gridBagConstraints);

        locationTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        locationTextField.setForeground(new java.awt.Color(51, 51, 51));
        locationTextField.setPreferredSize(new java.awt.Dimension(64, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(locationTextField, gridBagConstraints);

        Landmark.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Landmark.setForeground(new java.awt.Color(51, 51, 51));
        Landmark.setText("Landmark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 89, 0, 0);
        leftPanel.add(Landmark, gridBagConstraints);

        landmarkTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        landmarkTextField.setForeground(new java.awt.Color(51, 51, 51));
        landmarkTextField.setPreferredSize(new java.awt.Dimension(64, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(landmarkTextField, gridBagConstraints);

        urgencyLevelComboBox.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        urgencyLevelComboBox.setForeground(new java.awt.Color(51, 51, 51));
        urgencyLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='b6b6b6'>Select...</font></html>", "Low", "Medium", "High", "Emergency" }));
        urgencyLevelComboBox.setPreferredSize(new java.awt.Dimension(81, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(urgencyLevelComboBox, gridBagConstraints);

        categoryHelpButton.setToolTipText("See category description");
        categoryHelpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        categoryHelpButton.setPreferredSize(new java.awt.Dimension(25, 25));
        categoryHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        leftPanel.add(categoryHelpButton, gridBagConstraints);

        urgencyLevelHelpButton.setToolTipText("See Urgency Level description");
        urgencyLevelHelpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        urgencyLevelHelpButton.setPreferredSize(new java.awt.Dimension(25, 25));
        urgencyLevelHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urgencyLevelHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        leftPanel.add(urgencyLevelHelpButton, gridBagConstraints);

        urgencyLevelComboBox1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        urgencyLevelComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        urgencyLevelComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><font color='b6b6b6'>Select...</font></html>", "Low", "Medium", "High", "Emergency" }));
        urgencyLevelComboBox1.setPreferredSize(new java.awt.Dimension(81, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(urgencyLevelComboBox1, gridBagConstraints);

        Urgency1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        Urgency1.setForeground(new java.awt.Color(51, 51, 51));
        Urgency1.setText("Urgency level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 68, 0, 0);
        leftPanel.add(Urgency1, gridBagConstraints);

        java.awt.GridBagLayout rightPanelLayout = new java.awt.GridBagLayout();
        rightPanelLayout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        rightPanelLayout.rowHeights = new int[] {0, 16, 0, 16, 0};
        rightPanel.setLayout(rightPanelLayout);

        proofLabel.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        proofLabel.setForeground(new java.awt.Color(51, 51, 51));
        proofLabel.setText("Attach proof for your complaint");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        rightPanel.add(proofLabel, gridBagConstraints);

        uploadFileButton.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        uploadFileButton.setForeground(new java.awt.Color(51, 51, 51));
        uploadFileButton.setText("Upload");
        uploadFileButton.setToolTipText("Browse on file");
        uploadFileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uploadFileButton.setPreferredSize(new java.awt.Dimension(100, 40));
        uploadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        rightPanel.add(uploadFileButton, gridBagConstraints);

        proofImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        proofImageLabel.setToolTipText("Proof of complaint");
        proofImageLabel.setAlignmentX(0.5F);
        proofImageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 209, 209)));
        proofImageLabel.setOpaque(true);
        proofImageLabel.setPreferredSize(new java.awt.Dimension(300, 250));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        rightPanel.add(proofImageLabel, gridBagConstraints);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        clearButton.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        clearButton.setForeground(new java.awt.Color(51, 51, 51));
        clearButton.setText("Clear");
        clearButton.setToolTipText("Clear");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(0, 122, 255));
        submitButton.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.setToolTipText("Submit");
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitButton.setPreferredSize(new java.awt.Dimension(80, 40));
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(footerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(footerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    String descriptionPlaceholder = "e.g. \"A plothole in main street\"";

    /**
     * Handles the event when the description text area loses focus.
     * If the text area is empty, sets its text to a placeholder and changes its text color.
     *
     */
    private void descriptionTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusLost
        
        if (descriptionTextArea.getText().isEmpty()) {
            descriptionTextArea.setText(descriptionPlaceholder);
            descriptionTextArea.setForeground(Color.decode("#b6b6b6"));
        }
        
    }//GEN-LAST:event_descriptionTextAreaFocusLost

    /**
     * Handles the event when the description text area gains focus.
     * If the text area contains the placeholder, clears the text and resets its text color.
     * 
     */
    private void descriptionTextAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusGained
        
        if (descriptionTextArea.getText().equals(descriptionPlaceholder)) {
            descriptionTextArea.setText("");
            descriptionTextArea.setForeground(Color.decode("#333333"));
        }
        
    }//GEN-LAST:event_descriptionTextAreaFocusGained

    
    /**
     * Handles the event when the "Upload File" button is clicked.
     * Opens a file chooser dialog to select an image file and displays it in the proof image label.
     *
     */
    private void uploadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileButtonActionPerformed
        
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Image image  = new ImageIcon(file.getAbsolutePath()).getImage();
            Image scaledImage = image.getScaledInstance(proofImageLabel.getWidth(),
                                                        proofImageLabel.getHeight(),
                                                        Image.SCALE_SMOOTH);
            
            
            proofImageLabel.setIcon(new ImageIcon(scaledImage));
            
            try {
                this.proofImageData = readImageToByteArray(file);
            } catch (IOException ex) {
                Logger.getLogger(CreateComplaint.class.getName()).log(Level.SEVERE, "uploadFileButtonActionPerformed method", ex);
            }
        }
        
    }//GEN-LAST:event_uploadFileButtonActionPerformed

    /**
     * Reads the contents of an image file and converts it to a byte array.
     *
     * @param file The image file to be read.
     * @return The byte array representing the image.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private byte[] readImageToByteArray(File file) throws IOException {
        
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            return buffer;
        }
        
    }
    
    
    /**
     * Handles the event when the "Clear" button is clicked.
     * Clears all input fields and resets the proof image label.
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        
        clearInputFields();
        
    }//GEN-LAST:event_clearButtonActionPerformed

     /**
     * Clears all input fields on the form.
     */
    private void clearInputFields() {
        
        categoryComboBox.setSelectedIndex(0);
        descriptionTextArea.setText("");
        descriptionTextAreaFocusLost(new FocusEvent(descriptionTextArea, FocusEvent.FOCUS_LOST));
        dateChooser.setDate(null);
        locationTextField.setText("");
        landmarkTextField.setText("");
        urgencyLevelComboBox.setSelectedIndex(0);
        proofImageLabel.setIcon(null);
        
    }
    
    /**
     * Retrieves the current user's ID from the database based on the logged-in email.
     *
     * @return The ID of the current user.
     */
    private int getCurrentUser() {
        
        String selectQuery = 
                "SELECT [UserID] FROM [User] " +
                "WHERE [Email] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectQuery)) { 
            
            pst.setString(1, this.email);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                return this.currentUser = rs.getInt("UserID");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateComplaint.class.getName())
                  .log(Level.SEVERE, "getCurrentUser method", ex);
        }
        
        return -1;
    }
    
    
    /**
     * Handles the event when the "Submit" button is clicked.
     * Collects input data, validates it, and submits a complaint to the database.
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
    
        this.category = categoryComboBox.getSelectedItem().toString();
        this.description = descriptionTextArea.getText(); 
        try {
            this.createdDate = dateChooser.getDate().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate().toString();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(
                        content, 
                        "Fill out needed information!",
                        "Reminder",
                        JOptionPane.ERROR_MESSAGE);  
            
            Logger.getLogger(CreateComplaint.class.getName())
                  .log(Level.INFO, ex.getMessage(), ex);
                 
            return;
        }
        this.location = locationTextField.getText();
        this.landmark = landmarkTextField.getText();
        this.urgencyLevel = urgencyLevelComboBox.getSelectedItem().toString(); 
        
        try {
            
            if (isInputFieldEmpty() || isProofImageIconEmpty()) {
                
                JOptionPane.showMessageDialog(
                        content, 
                        "Fill out needed information!",
                        "Reminder",
                        JOptionPane.ERROR_MESSAGE);   
                 
                return;
            }
            
        } catch (NullPointerException ex) {  
            JOptionPane.showMessageDialog(
                content, 
                "Fill out needed information!",
                "Reminder",
                JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(CreateComplaint.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
            
            return;
        }   
        
        String userComplaintQuery = 
                "INSERT INTO [UserComplaint] (" +
                    "[Category], [Description], [CreatedDate], " +
                    "[Location], [Landmark], [UrgencyLevel], " +
                    "[Status], [Proof], [UserID]) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  
        try (PreparedStatement complaint = connection.prepareStatement(userComplaintQuery)) {
            
            // Complaint Table
            complaint.setString(1, category);
            complaint.setString(2, description);
            try {
               complaint.setDate(3, new java.sql.Date(dateFormat.parse(this.createdDate).getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(CreateComplaint.class.getName())
                      .log(Level.INFO, ex.getMessage(), ex);
            }
            complaint.setString(4, location);
            complaint.setString(5, landmark);
            complaint.setString(6, urgencyLevel);
            complaint.setString(7, ComplaintStatus.NEW.getDescription());
            complaint.setBytes(8, proofImageData);
            complaint.setInt(9, this.currentUser);
            complaint.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateComplaint.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }
        
        JOptionPane.showMessageDialog(
            this,
            "Your complaint has been successfully submitted.",
            "Submission Successful",
            JOptionPane.INFORMATION_MESSAGE);
        
        clearInputFields();
    }//GEN-LAST:event_submitButtonActionPerformed

    /**
     * Checks if any of the input fields related to complaint details are empty.
     * 
     * @return true if any input field is empty, false otherwise.
     * @throws NullPointerException If any required field is null.
     */    
    private boolean isInputFieldEmpty() throws NullPointerException {

        String placeholder = "<html><font color='b6b6b6'>Select...</font></html>";
        
        return description.isBlank() ||
                createdDate == null ||
               location.isBlank() ||
               landmark.isBlank() ||
               category.equals(placeholder) ||
               urgencyLevel.equals(placeholder);
    
    }
    
    /**
     * Checks if the proofImageLabel has an image icon.
     * 
     * @return true if the proofImageLabel has no image icon, false otherwise.
     * @throws NullPointerException If the proof image label is null.
     */
    private boolean isProofImageIconEmpty() throws NullPointerException {

        return proofImageLabel.getIcon() == null;
    
   }
    
    /**
     * Displays a popup with information about complaint categories.
     */
    private void categoryHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryHelpButtonActionPerformed
        
        String popUpDetails =
                "<html>" +
                "<b>Please categorize your complaint according to the following categories:</b><br><br>" +
                "1. <b>Environment:</b> Issues related to pollution, waste management, natural resource conservation, etc.<br><br>" +
                "2. <b>Infrastructure:</b> Concerns about roads, bridges, buildings, public transport, etc.<br><br>" +
                "3. <b>Utilities:</b> Problems with water supply, electricity, gas, telecommunications, etc.<br><br>" +
                "4. <b>Public Services:</b> Issues with healthcare, education, law enforcement, firefighting services, etc.<br><br>" +
                "5. <b>Government Agencies:</b> Complaints related to the services provided by government departments and agencies." +
                "</html>";
        
        JOptionPane.showMessageDialog(categoryHelpButton, popUpDetails, "Category Guide", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_categoryHelpButtonActionPerformed

    
    /**
     * Displays a popup with information about complaint urgency levels.
     */
    private void urgencyLevelHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urgencyLevelHelpButtonActionPerformed
        
        String popUpDetails =
            "<html>" +
            "<b>Priority Levels for Complaints:</b><br><br>" +
            "<b>Low:</b><br>" +
            "Description: Non-urgent issues that do not require immediate attention.<br>" +
            "Examples: Minor road damage, graffiti in public areas.<br><br>" +
            "<b>Medium:</b><br>" +
            "Description: Issues that need attention soon but are not emergencies.<br>" +
            "Examples: Moderate traffic congestion, streetlights out in a residential area.<br><br>" +
            "<b>High:</b><br>" +
            "Description: Urgent issues requiring prompt action or intervention.<br>" +
            "Examples: Major road accidents, burst water mains, large-scale environmental hazards.<br><br>" +
            "<b>Critical/Emergency:</b><br>" +
            "Description: Extremely urgent issues requiring immediate action to prevent serious harm or damage.<br>" +
            "Examples: Major natural disasters, large-scale public health emergencies." +
            "</html>";
       
            JOptionPane.showMessageDialog(urgencyLevelHelpButton, popUpDetails, "Urgency Level Guide", JOptionPane.INFORMATION_MESSAGE);
            
    }//GEN-LAST:event_urgencyLevelHelpButtonActionPerformed


    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Description;
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Landmark;
    private javax.swing.JLabel Location;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Urgency1;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JButton categoryHelpButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel content;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel footer;
    private javax.swing.JSeparator footerSeparator;
    private javax.swing.JPanel header;
    private javax.swing.JTextField landmarkTextField;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JLabel proofImageLabel;
    private javax.swing.JLabel proofLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton uploadFileButton;
    private javax.swing.JComboBox<String> urgencyLevelComboBox;
    private javax.swing.JComboBox<String> urgencyLevelComboBox1;
    private javax.swing.JButton urgencyLevelHelpButton;
    // End of variables declaration//GEN-END:variables
}
