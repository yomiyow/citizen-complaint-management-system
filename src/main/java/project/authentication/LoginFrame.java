package project.authentication;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.admin.AdminFrame;
import project.citizen.CitizenFrame;
import project.database.Database;

/**
 * Represents the login frame of the application where users can log in.
 * This frame allows users to enter their credentials and log in to the system.
 * It provides a user interface for authentication purposes.
 * @since 4/25/2024
 */
public class LoginFrame extends javax.swing.JFrame {
    
    // Variables for database connection and user credentials
    private Connection connection;
    private String role;
    public static String email;
    private String password;
    private int attemptLeft = 3;
    
    public LoginFrame() {
        initComponents();
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
     
        loadFrameIcon();
        
        loadPanelIcon();
        
        customizeComponentProperties();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        leftPanel = new javax.swing.JPanel();
        loginIcon = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        headerTitle = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        question = new javax.swing.JLabel();
        signupLink = new javax.swing.JButton();
        loginAs = new javax.swing.JLabel();
        roleComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log in");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        leftPanel.setBackground(new java.awt.Color(0, 123, 255));
        leftPanel.setPreferredSize(new java.awt.Dimension(500, 600));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        getContentPane().add(leftPanel);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        rightPanel.setLayout(new java.awt.CardLayout());

        java.awt.GridBagLayout loginPanelLayout = new java.awt.GridBagLayout();
        loginPanelLayout.columnWidths = new int[] {0, 50, 0, 50, 0, 50, 0, 50, 0};
        loginPanelLayout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        loginPanel.setLayout(loginPanelLayout);

        userIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        loginPanel.add(userIcon, gridBagConstraints);

        headerTitle.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        headerTitle.setForeground(new java.awt.Color(0, 123, 255));
        headerTitle.setText("Log in");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        loginPanel.add(headerTitle, gridBagConstraints);

        description.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        description.setForeground(new java.awt.Color(102, 102, 102));
        description.setText("Log in to continue to our application.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        loginPanel.add(description, gridBagConstraints);

        emailTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        loginPanel.add(emailTextField, gridBagConstraints);

        passwordField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        passwordField.setForeground(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        loginPanel.add(passwordField, gridBagConstraints);

        loginButton.setBackground(new java.awt.Color(0, 123, 255));
        loginButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Log in");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        loginPanel.add(loginButton, gridBagConstraints);

        question.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        question.setForeground(new java.awt.Color(102, 102, 102));
        question.setText("Don't have an account?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 34, 0, 3);
        loginPanel.add(question, gridBagConstraints);

        signupLink.setBackground(new java.awt.Color(242, 242, 242));
        signupLink.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        signupLink.setForeground(new java.awt.Color(0, 123, 255));
        signupLink.setText("SIGN UP");
        signupLink.setToolTipText("Go to sign up");
        signupLink.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        signupLink.setBorderPainted(false);
        signupLink.setContentAreaFilled(false);
        signupLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signupLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSignupFrame(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 34);
        loginPanel.add(signupLink, gridBagConstraints);

        loginAs.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        loginAs.setForeground(new java.awt.Color(0, 123, 255));
        loginAs.setText("Login as");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        loginPanel.add(loginAs, gridBagConstraints);

        roleComboBox.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Citizen", "Admin" }));
        roleComboBox.setPreferredSize(new java.awt.Dimension(92, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 6, 0);
        loginPanel.add(roleComboBox, gridBagConstraints);

        rightPanel.add(loginPanel, "card3");

        getContentPane().add(rightPanel);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Loads the icon for the frame title.
     */
    private void loadFrameIcon() {
        final ImageIcon titlePaneIcon = new ImageIcon(getClass().getResource("/icon/CICT-logo-icon.png"));
        this.setIconImage(titlePaneIcon.getImage());  
    }
    
    /**
     * Loads icons for various panels in the login frame.
     */
    private void loadPanelIcon() {
        loginIcon.setIcon(new ImageIcon(getClass().getResource("/icon/left-panel-icon.png")));
        userIcon.setIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
    }
    
    /**
     * Customizes the properties of GUI components using FlatLaf styles.
     */
    private void customizeComponentProperties() {
        //emailTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
        //        new ImageIcon(getClass().getResource("/mail.png")));
        
        emailTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");
        emailTextField.putClientProperty(FlatClientProperties.STYLE, 
                "margin: 3, 10, 3, 10;" +
                "showClearButton: true;");
        
        //passwordField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
        //        new ImageIcon(getClass().getResource("/pass.png")));
        
        passwordField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        passwordField.putClientProperty(FlatClientProperties.STYLE, 
                "showRevealButton: true;" +
                "showCapsLock: true;" +
                "margin: 3, 10, 3, 10;");
        
        signupLink.putClientProperty(FlatClientProperties.STYLE, 
                "hoverForeground: #4d97ff;" +
                "pressedForeground: #0058cc;");
        
        roleComboBox.putClientProperty(FlatClientProperties.STYLE, 
                "padding: 0, 10, 0, 10;");
    }
    
    /**
    * Opens the sign-up frame when the corresponding button is clicked.
    * This method creates a new instance of the SignupFrame class and makes it visible.
    * It also disposes of the current login frame.
    */
    private void showSignupFrame(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSignupFrame
        
        new SignupFrame().setVisible(true);
        dispose();
        
    }//GEN-LAST:event_showSignupFrame

    
    /**
     * Handles login button action.
     * Performs login operation when the login button is clicked.
     * Retrieves user input, verifies credentials, and opens the Citizen application frame if successful.
     * Displays error message if login fails.
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
        role = roleComboBox.getSelectedItem().toString();
        email = emailTextField.getText();
        password = String.valueOf(passwordField.getPassword());
        
        if (isInputFieldsEmpty()) {
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "Fill out needed information.", 
                    "Reminder", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        

       String selectQuery = "SELECT * FROM Account WHERE Email = ?";
       try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {
           pst.setString(1, email);
           ResultSet rs = pst.executeQuery();
           
           while (rs.next()) {
                String dbEmail = rs.getString("Email");
                String dbPassword = rs.getString("Password");
                String dbRole = rs.getString("Role");
                String dbStatus = rs.getString("Status");
               
                if (isCredentialMatch(dbEmail, dbPassword, dbRole) && !dbStatus.equals("Suspended")) {
                    JOptionPane.showMessageDialog(
                            rightPanel,
                            "Log in succesful.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    currentLoginAccount(role);
                    
                    dispose();
                    
                    return;
                } 
                
                if (isCredentialMatch(dbEmail, dbPassword, dbRole) && dbStatus.equals("Suspended")) {
                    JOptionPane.showMessageDialog(
                            rightPanel,
                            "Your account has been suspended.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
               
            }
           

            
            attemptLeft--;
            
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "This credential doesn't match our record. \n" +
                    "You have " + attemptLeft + " attempts remaining.",
                    "Log in failed",
                    JOptionPane.ERROR_MESSAGE);
            
            
            String updateQuery = 
                    "UPDATE [ACCOUNT] " +
                    "SET [Status] = 'Suspended' " +
                    "WHERE [Email] = ?";
            
            if (attemptLeft == 0) {
                
                try (PreparedStatement updateAccountStatus = connection.prepareStatement(updateQuery)) {
                    
                    updateAccountStatus.setString(1, this.email);
                    updateAccountStatus.executeUpdate();
 
                    JOptionPane.showMessageDialog(
                            rightPanel,
                            "Your account has been suspended.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    
                    clearUserInput();
                    
                
                } catch(SQLException ex) {
                      Logger.getLogger(LoginFrame.class.getName())
                            .log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            
            
       } catch(SQLException ex) {
           Logger.getLogger(LoginFrame.class.getName())
                 .log(Level.SEVERE, ex.getMessage(), ex);
       }
            
    }//GEN-LAST:event_loginButtonActionPerformed
    
    private void clearUserInput() {
    
        emailTextField.setText("");
        passwordField.setText("");
        
    }
    
    
    private void currentLoginAccount (String role) {
        
        switch (role) {
            case "Citizen": new CitizenFrame().setVisible(true); break;
            case "Admin": new AdminFrame().setVisible(true); break;
        }    
    }
    
    
    /**
    * Checks if the email and password input fields are empty.
    * 
    * @return true if either the email or password input field is empty, false otherwise.
    */
    private boolean isInputFieldsEmpty() {
        
        return email.isBlank() || password.isBlank();
        
    }
    
    
    /**
     * 
     * Check if the credentials match.
     * Checks if the provided email, password, and role match the corresponding values in the database.
     * 
     * @param dbEmail The email retrieved from the database.
     * @param dbPassword The password retrieved from the database.
     * @param dbRole The role retrieved from the database.
     * @return true if the provided email, password, and role match the database values, false otherwise.
     */
    private boolean isCredentialMatch(String dbEmail, String dbPassword, String dbRole) {
        
        return email.equals(dbEmail) && 
               password.equals(dbPassword) &&
               role.equals(dbRole);
        
    }
       
    public static void main(String args[]) {
        
        FlatMacLightLaf.setup();
        
        // FlatLaf setup and UI theme customization...
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.background", Color.decode("#ffffff"));      
        
        UIManager.put( "Button.arc", 8);
        UIManager.put("TextComponent.arc", 8);
        UIManager.put( "Component.focusWidth", 1);
        
        // Menu Customization
        UIManager.put("List.selectionBackground", Color.decode("#007AFF"));
        UIManager.put("List.selectionInactiveBackground", Color.decode("#007AFF"));
        UIManager.put("List.selectionInactiveForeground", Color.decode("#FFFFFF"));
        
        // Table UI Customization
        UIManager.put("TableHeader.separatorColor", Color.decode("#FFFFFF"));
        UIManager.put("TableHeader.hoverBackground", Color.decode("#F2F2F2"));
        UIManager.put("TableHeader.height", 40);
        UIManager.put("Table.selectionBackground", Color.decode("#C2E7FF"));
        UIManager.put("Table.selectionForeground", Color.decode("#333333"));
        UIManager.put("Table.showCellFocusIndicator", false);
        UIManager.put("Table.selectionInactiveBackground", Color.decode("#C2E7FF"));
        //UIManager.put("Table.alternateRowColor", Color.decode("#F2F2F2"));
        UIManager.put("Table.cellFocusColor", Color.decode("#B0E2FF"));
                
        // TextField UI Customization
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("TextField.disabledBackground", Color.decode("#FFFFFF"));
        UIManager.put("TextField.disabledForeground", Color.decode("#333333"));
             
        
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel description;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel loginAs;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginIcon;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel question;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JButton signupLink;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables

}
