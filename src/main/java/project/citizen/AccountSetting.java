package project.citizen;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import project.authentication.LoginFrame;
import project.concrete_class.ImageFilter;
import project.database.Database;

/**
 * This class represents the Account Setting panel of the application.
 * Users can view and update their account information and change their password using this panel.
 * 
 */
public class AccountSetting extends javax.swing.JPanel {

    // Account Info
    private String email;
    
    // User Info
    private byte[] profilePictureData;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String birthdate;
    private String street;
    private String barangay;
    private String city;
    private String province;
    
    // Change password Info
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    Connection connection;
    
    public AccountSetting() {
        initComponents();
        customizeComponent();
        loadDefaultProfileImage();
        try {       
            this.connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountSetting.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
        }
                        
        this.email = LoginFrame.email;
        
        initIntanceVariable();
        
        try {
            fetchDataIntoInputField();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AccountSetting.class.getName())
                  .log(Level.INFO, "fetchDataIntoInputField method", ex);
        } 
        
        
    }
    

    /**
     * Customizes the appearance of Swing components.
     * Sets styles and properties for password fields.
     */
    private void customizeComponent() {
        
        currentPasswordField.putClientProperty(FlatClientProperties.STYLE, 
                "showRevealButton: true;" +
                "showCapsLock: true;" +
                "margin: 3, 10, 3, 10;");
        
        newPasswordField.putClientProperty(FlatClientProperties.STYLE, 
                "showRevealButton: true;" +
                "showCapsLock: true;" +
                "margin: 3, 10, 3, 10;");
        
        confirmPasswordField.putClientProperty(FlatClientProperties.STYLE, 
                "showRevealButton: true;" +
                "showCapsLock: true;" +
                "margin: 3, 10, 3, 10;");
        
    }
    
    private void loadDefaultProfileImage() {
        ImageIcon defaultProfile = new ImageIcon(getClass().getResource("/profile-picture/default-profile-picture.png"));
        Image scaledImage = defaultProfile.getImage().getScaledInstance(70,
                                                                        70, 
                                                                        Image.SCALE_SMOOTH);
        
        profilePictureLabel.setIcon(new ImageIcon(scaledImage));
        profilePictureLabel.setHorizontalAlignment(JLabel.CENTER);
        profilePictureLabel.setVerticalAlignment(JLabel.CENTER);
    }
    
    /**
     * Fetches user data from the database and populates input fields with retrieved data.
     * Throws IllegalArgumentException if any retrieved data is null.
     */
    private void fetchDataIntoInputField() throws IllegalArgumentException {
        
        firstNameTextField.setText(this.firstName);
        lastNameTextField.setText(this.lastName);
        emailTextField.setText(this.email);
        contactNoTextField.setText(this.contactNo);
        streetTextField.setText(this.street);
        barangayTextField.setText(this.barangay);
        cityTextField.setText(this.city);
        provinceTextField.setText(this.province);
        try {
            
            profilePictureLabel.setIcon(scaledImageIcon(this.profilePictureData));

            birthdateChooser.setDate(java.sql.Date.valueOf(this.birthdate));
            
        } catch (NullPointerException ex) {
            Logger.getLogger(AccountSetting.class.getName())
                  .log(Level.SEVERE, ex.getMessage(), ex);
        }    
            
    }
    
    
    /**
     * Scales the profile picture image to fit a 100x100 pixel area.
     * 
     * @param pictureData The byte array data of the profile picture.
     * @return The scaled ImageIcon of the profile picture.
     * @throws NullPointerException If the profile picture data is null.
     */
    private ImageIcon scaledImageIcon(byte[] pictureData) throws NullPointerException {
        
        Image imageIcon = new ImageIcon(profilePictureData).getImage()
                                                           .getScaledInstance(100,
                                                                              100, 
                                                                              Image.SCALE_SMOOTH);

        return new ImageIcon(imageIcon);
    }
    
    /**
     * Retrieves user data from the database based on the logged-in email and
     * initializes instance variables.
     */
    private void initIntanceVariable() {
        
        String selectQuery = 
                "SELECT [Firstname], [Lastname], [ContactNo], [Birthdate], [Street], [Barangay], [City], [Province], [ProfilePicture] " +
                "FROM [User] " +
                "WHERE [Email] = ?";

        try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {
            
            pst.setString(1, this.email);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                this.firstName = rs.getString("Firstname");
                this.lastName = rs.getString("Lastname");
                this.contactNo = rs.getString("ContactNo");
                this.birthdate = rs.getString("Birthdate");
                this.street = rs.getString("Street");
                this.barangay = rs.getString("Barangay");
                this.city = rs.getString("City");
                this.province = rs.getString("Province");
                this.profilePictureData = rs.getBytes("ProfilePicture");
                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountSetting.class.getName()).log(Level.SEVERE, "initIntanceVariable method", ex);
        } 
        
    }        
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        personalInformationPanel = new javax.swing.JPanel();
        descriptionPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        header1Label = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        profilePictureLabel = new javax.swing.JLabel();
        uploadButton = new javax.swing.JButton();
        inputFieldsPanel = new javax.swing.JPanel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        contactNoTextField = new javax.swing.JTextField();
        birthdateChooser = new com.toedter.calendar.JDateChooser();
        streetTextField = new javax.swing.JTextField();
        barangayTextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        provinceTextField = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        changePasswordPanel = new javax.swing.JPanel();
        descriptionPanel2 = new javax.swing.JPanel();
        titleLabel1 = new javax.swing.JLabel();
        header1Label1 = new javax.swing.JLabel();
        inputFieldsPanel2 = new javax.swing.JPanel();
        changePasswordSaveButton = new javax.swing.JButton();
        currentPasswordField = new javax.swing.JPasswordField();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(1056, 750));

        personalInformationPanel.setBackground(new java.awt.Color(255, 255, 255));
        personalInformationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 209, 209)));

        descriptionPanel.setOpaque(false);
        java.awt.GridBagLayout descriptionPanelLayout = new java.awt.GridBagLayout();
        descriptionPanelLayout.columnWidths = new int[] {0};
        descriptionPanelLayout.rowHeights = new int[] {0, 5, 0};
        descriptionPanel.setLayout(descriptionPanelLayout);

        titleLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleLabel.setText("Personal Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 15, 0, 0);
        descriptionPanel.add(titleLabel, gridBagConstraints);

        header1Label.setForeground(new java.awt.Color(51, 51, 51));
        header1Label.setText("Update your personal details here.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 236, 0);
        descriptionPanel.add(header1Label, gridBagConstraints);

        profilePanel.setOpaque(false);
        java.awt.GridBagLayout profilePanelLayout = new java.awt.GridBagLayout();
        profilePanelLayout.columnWidths = new int[] {0};
        profilePanelLayout.rowHeights = new int[] {0, 15, 0};
        profilePanel.setLayout(profilePanelLayout);

        profilePictureLabel.setBackground(new java.awt.Color(255, 255, 255));
        profilePictureLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 209, 209)));
        profilePictureLabel.setOpaque(true);
        profilePictureLabel.setPreferredSize(new java.awt.Dimension(100, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        profilePanel.add(profilePictureLabel, gridBagConstraints);

        uploadButton.setBackground(new java.awt.Color(245, 245, 245));
        uploadButton.setText("Upload");
        uploadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uploadButton.setEnabled(false);
        uploadButton.setPreferredSize(new java.awt.Dimension(79, 40));
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 119, 0);
        profilePanel.add(uploadButton, gridBagConstraints);

        inputFieldsPanel.setOpaque(false);
        java.awt.GridBagLayout inputFieldsPanelLayout = new java.awt.GridBagLayout();
        inputFieldsPanelLayout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0};
        inputFieldsPanelLayout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        inputFieldsPanel.setLayout(inputFieldsPanelLayout);

        firstNameTextField.setForeground(new java.awt.Color(51, 51, 51));
        firstNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        firstNameTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        firstNameTextField.setEnabled(false);
        firstNameTextField.setOpaque(true);
        firstNameTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(firstNameTextField, gridBagConstraints);

        lastNameTextField.setForeground(new java.awt.Color(51, 51, 51));
        lastNameTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        lastNameTextField.setEnabled(false);
        lastNameTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(lastNameTextField, gridBagConstraints);

        emailTextField.setForeground(new java.awt.Color(51, 51, 51));
        emailTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        emailTextField.setEnabled(false);
        emailTextField.setPreferredSize(new java.awt.Dimension(400, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(emailTextField, gridBagConstraints);

        contactNoTextField.setForeground(new java.awt.Color(51, 51, 51));
        contactNoTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact No", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        contactNoTextField.setEnabled(false);
        contactNoTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(contactNoTextField, gridBagConstraints);

        birthdateChooser.setBackground(new java.awt.Color(255, 255, 255));
        birthdateChooser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Birthdate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0))); // NOI18N
        birthdateChooser.setForeground(new java.awt.Color(51, 51, 51));
        birthdateChooser.setEnabled(false);
        birthdateChooser.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        inputFieldsPanel.add(birthdateChooser, gridBagConstraints);

        streetTextField.setForeground(new java.awt.Color(51, 51, 51));
        streetTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Street", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        streetTextField.setEnabled(false);
        streetTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(streetTextField, gridBagConstraints);

        barangayTextField.setForeground(new java.awt.Color(51, 51, 51));
        barangayTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Barangay", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        barangayTextField.setEnabled(false);
        barangayTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(barangayTextField, gridBagConstraints);

        cityTextField.setForeground(new java.awt.Color(51, 51, 51));
        cityTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "City", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        cityTextField.setEnabled(false);
        cityTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(cityTextField, gridBagConstraints);

        provinceTextField.setForeground(new java.awt.Color(51, 51, 51));
        provinceTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Province", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        provinceTextField.setEnabled(false);
        provinceTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        inputFieldsPanel.add(provinceTextField, gridBagConstraints);

        editButton.setBackground(new java.awt.Color(245, 245, 245));
        editButton.setForeground(new java.awt.Color(51, 51, 51));
        editButton.setText("Edit");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setPreferredSize(new java.awt.Dimension(95, 40));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 122, 255));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.setEnabled(false);
        saveButton.setPreferredSize(new java.awt.Dimension(95, 40));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout personalInformationPanelLayout = new javax.swing.GroupLayout(personalInformationPanel);
        personalInformationPanel.setLayout(personalInformationPanelLayout);
        personalInformationPanelLayout.setHorizontalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputFieldsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        personalInformationPanelLayout.setVerticalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputFieldsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        changePasswordPanel.setBackground(new java.awt.Color(255, 255, 255));
        changePasswordPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 209, 209)));

        descriptionPanel2.setOpaque(false);
        java.awt.GridBagLayout descriptionPanel2Layout = new java.awt.GridBagLayout();
        descriptionPanel2Layout.columnWidths = new int[] {0};
        descriptionPanel2Layout.rowHeights = new int[] {0, 5, 0};
        descriptionPanel2.setLayout(descriptionPanel2Layout);

        titleLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        titleLabel1.setForeground(new java.awt.Color(51, 51, 51));
        titleLabel1.setText("Change Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        descriptionPanel2.add(titleLabel1, gridBagConstraints);

        header1Label1.setForeground(new java.awt.Color(51, 51, 51));
        header1Label1.setText("<html>Update your password associated </br >with your account.</html>\n\n");
        header1Label1.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 150, 0);
        descriptionPanel2.add(header1Label1, gridBagConstraints);

        inputFieldsPanel2.setOpaque(false);
        java.awt.GridBagLayout inputFieldsPanel1Layout = new java.awt.GridBagLayout();
        inputFieldsPanel1Layout.columnWidths = new int[] {0, 20, 0};
        inputFieldsPanel1Layout.rowHeights = new int[] {0, 10, 0, 10, 0};
        inputFieldsPanel2.setLayout(inputFieldsPanel1Layout);

        changePasswordSaveButton.setBackground(new java.awt.Color(0, 122, 255));
        changePasswordSaveButton.setForeground(new java.awt.Color(255, 255, 255));
        changePasswordSaveButton.setText("Save");
        changePasswordSaveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordSaveButton.setPreferredSize(new java.awt.Dimension(95, 40));
        changePasswordSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordSaveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 35, 0);
        inputFieldsPanel2.add(changePasswordSaveButton, gridBagConstraints);

        currentPasswordField.setForeground(new java.awt.Color(51, 51, 51));
        currentPasswordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        currentPasswordField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        inputFieldsPanel2.add(currentPasswordField, gridBagConstraints);

        newPasswordField.setForeground(new java.awt.Color(51, 51, 51));
        newPasswordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "New Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        newPasswordField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        inputFieldsPanel2.add(newPasswordField, gridBagConstraints);

        confirmPasswordField.setForeground(new java.awt.Color(51, 51, 51));
        confirmPasswordField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirm Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 5, 0))); // NOI18N
        confirmPasswordField.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        inputFieldsPanel2.add(confirmPasswordField, gridBagConstraints);

        javax.swing.GroupLayout changePasswordPanelLayout = new javax.swing.GroupLayout(changePasswordPanel);
        changePasswordPanel.setLayout(changePasswordPanelLayout);
        changePasswordPanelLayout.setHorizontalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(inputFieldsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        changePasswordPanelLayout.setVerticalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputFieldsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changePasswordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personalInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(personalInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

     /**
     * Enables input fields for editing user information.
     * Enables save button and disables edit button.
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        
        enableInputFields(true);
        saveButton.setEnabled(true);
        editButton.setEnabled(false);
        
    }//GEN-LAST:event_editButtonActionPerformed

    
    
    /**
     * Saves the edited user information to the database.
     * Disables input fields and save button, and enables edit button.
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        enableInputFields(false);
        saveButton.setEnabled(false);
        editButton.setEnabled(true);
        
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        contactNo = contactNoTextField.getText();
        try {
            birthdate = birthdateChooser.getDate().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate().toString();
        } catch (NullPointerException ex) {
            Logger.getLogger(AccountSetting.class.getName()).log(Level.SEVERE, "saveButtonActionPerformed method", ex);
        }
        street = streetTextField.getText();
        barangay = barangayTextField.getText();
        city = cityTextField.getText();
        province = provinceTextField.getText();
        
        String updateQuery =  
                "UPDATE [User] " +
                "SET " +
                    "[Firstname] = ?, [Lastname] = ?, [ContactNo] = ?, " +
                    "[BirthDate] = ?, [Street] = ?, [Barangay] = ?, " +
                    "[City] = ?, [Province] = ?, [ProfilePicture] = ? " + 
                "WHERE [Email] = ?";
        
        try (PreparedStatement userTable = connection.prepareStatement(updateQuery)) {
            
            userTable.setString(1, this.firstName);
            userTable.setString(2, this.lastName);
            userTable.setString(3, this.contactNo);
            userTable.setString(4, this.birthdate);
            userTable.setString(5, this.street);
            userTable.setString(6, this.barangay);
            userTable.setString(7, this.city);
            userTable.setString(8, this.province);
            userTable.setBytes(9, this.profilePictureData);
            userTable.setString(10, this.email);
            userTable.executeUpdate();
            
            System.out.println("Successful");
            JOptionPane.showMessageDialog(
                    this,
                    "Your changes have been successfully saved.",
                    "Account Settings Updated",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountSetting.class.getName())
                  .log(Level.SEVERE, "saveButtonActionPerformed method", ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    
        /**
     * Enables or disables input fields for editing user information.
     * 
     * @param isEnabled If true, input fields are enabled; if false, input fields are disabled.
     */
    private void enableInputFields(boolean isEnabled) {
        
        firstNameTextField.setEnabled(isEnabled);
        lastNameTextField.setEnabled(isEnabled);
        contactNoTextField.setEnabled(isEnabled);
        birthdateChooser.setEnabled(isEnabled);
        streetTextField.setEnabled(isEnabled);
        barangayTextField.setEnabled(isEnabled);
        cityTextField.setEnabled(isEnabled);
        provinceTextField.setEnabled(isEnabled);
        uploadButton.setEnabled(isEnabled);
        
    }
    
    
    /**
     * Allows the user to select and upload a profile picture.
     */
    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Image image  = new ImageIcon(file.getAbsolutePath()).getImage();
            Image scaledImage = image.getScaledInstance(profilePictureLabel.getWidth(),
                                                        profilePictureLabel.getHeight(),
                                                        Image.SCALE_SMOOTH);
            
            
            profilePictureLabel.setIcon(new ImageIcon(scaledImage));
            
            try {
                this.profilePictureData = readImageToByteArray(file);
            } catch (IOException ex) {
                Logger.getLogger(AccountSetting.class.getName()).log(Level.SEVERE, "readImageToByteArray method", ex);
            }
        }
        
    }//GEN-LAST:event_uploadButtonActionPerformed

    
     /**
     * Saves the newly set password to the database after validating the current password.
     */
    private void changePasswordSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordSaveButtonActionPerformed
        
        this.currentPassword = String.valueOf(currentPasswordField.getPassword());
        this.newPassword = String.valueOf(newPasswordField.getPassword());
        this.confirmPassword = String.valueOf(confirmPasswordField.getPassword());
        
        if (isPaswordFieldEmpty()) {
            
            JOptionPane.showMessageDialog(
                            this,
                            "Fill out needed information.",
                            "Change Password",
                            JOptionPane.ERROR_MESSAGE);
            return;
            
        }
        
        String selectQuery = 
                "SELECT [Password] FROM [Account] " +
                "WHERE [Email] = ?";
        
        String updatePasswordQuery = 
                "UPDATE [Account] " +
                "SET [Password] = ?" +
                "WHERE [Email] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectQuery);
             PreparedStatement updatePassword = connection.prepareStatement(updatePasswordQuery)) {
            
            pst.setString(1, this.email);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String dbCurrentPassword = rs.getString("Password");
                
                if (currentPasswordMatch(dbCurrentPassword)) {
                    
                    if (newAndConfirmPasswordMatch()) {
                        
                        updatePassword.setString(1, this.newPassword);
                        updatePassword.setString(2, this.email);
                        updatePassword.executeUpdate();
                        
                        JOptionPane.showMessageDialog(
                                this,
                                "Password changed successful.",
                                "Change Password",
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        clearPasswordInputFields();
                        
                        return;
                    }
                    else {
                        
                        JOptionPane.showMessageDialog(
                                this,
                                "New Password and Confirm Password doens't match.",
                                "Change Password",
                                JOptionPane.ERROR_MESSAGE);
                       
                        return;
                    } 
                }  
                
                JOptionPane.showMessageDialog(
                        this,
                        "Current Password doesn't match.",
                        "Change Password",
                        JOptionPane.ERROR_MESSAGE);
                
                return;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountSetting.class.getName())
                  .log(Level.SEVERE, "changePasswordSaveButtonActionPerformed method", ex);
        }
   
    }//GEN-LAST:event_changePasswordSaveButtonActionPerformed
     
    /**
     * Checks if any of the password fields is empty.
     * 
     * @return true if any of the password fields is empty, false otherwise.
     */
    private boolean isPaswordFieldEmpty() {
        
        return currentPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank();
        
    }
    
    /**
     * Clears the input fields for the current password, new password, and confirm password.
     */
    private void clearPasswordInputFields() {
        
        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
        
    }
    
    /**
     * Checks if the entered current password matches the password stored in the database.
     * 
     * @param dbCurrentPassword The password retrieved from the database.
     * @return true if the current password matches, false otherwise.
     */
    private boolean currentPasswordMatch(String dbCurrentPassword) {
        
        return this.currentPassword.equals(dbCurrentPassword);
        
    }
    
    
    /**
     * Checks if the new password matches the confirmed password.
     * 
     * @return true if the new password matches the confirmed password, false otherwise.
     */
    private boolean newAndConfirmPasswordMatch() {
        
        return this.newPassword.equals(this.confirmPassword);
    
    }
    
    /**
     * Reads an image file and converts it into a byte array.
     * 
     * @param file The image file to be converted.
     * @return The byte array representing the image.
     * @throws IOException If an I/O error occurs.
     */
    
    private byte[] readImageToByteArray(File file) throws IOException {
        
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            return buffer;
        }
        
    }
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barangayTextField;
    private com.toedter.calendar.JDateChooser birthdateChooser;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JButton changePasswordSaveButton;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField contactNoTextField;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JPanel descriptionPanel2;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel header1Label;
    private javax.swing.JLabel header1Label1;
    private javax.swing.JPanel inputFieldsPanel;
    private javax.swing.JPanel inputFieldsPanel2;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JPanel personalInformationPanel;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePictureLabel;
    private javax.swing.JTextField provinceTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField streetTextField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
