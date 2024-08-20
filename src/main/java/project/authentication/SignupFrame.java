package project.authentication;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.database.Database;

/**
 * Represents the signup frame of the application.
 * Allows users to create a new account.
 * 
 * @since 4/27/2024
 */
public class SignupFrame extends javax.swing.JFrame {
    
    Connection connection;
    String firstName;
    String lastName;
    String email;
    String password;
    
    public SignupFrame() {
        initComponents();
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SignupFrame.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        loadPanelIcon();
        
        loadFrameIcon();
        
        customizeComponent();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        leftPanel = new javax.swing.JPanel();
        loginIcon = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        sigupPanel = new javax.swing.JPanel();
        addUserIcon = new javax.swing.JLabel();
        headerTitle = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        signupButton = new javax.swing.JButton();
        questionText = new javax.swing.JLabel();
        loginLink = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign up");
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        leftPanel.setBackground(new java.awt.Color(40, 167, 69));
        leftPanel.setPreferredSize(new java.awt.Dimension(500, 600));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        getContentPane().add(leftPanel);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        rightPanel.setLayout(new java.awt.CardLayout());

        java.awt.GridBagLayout sigupPanelLayout = new java.awt.GridBagLayout();
        sigupPanelLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        sigupPanelLayout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        sigupPanel.setLayout(sigupPanelLayout);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        sigupPanel.add(addUserIcon, gridBagConstraints);

        headerTitle.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        headerTitle.setForeground(new java.awt.Color(40, 167, 69));
        headerTitle.setText("Sign up");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        sigupPanel.add(headerTitle, gridBagConstraints);

        description.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        description.setForeground(new java.awt.Color(102, 102, 102));
        description.setText("Create an account to access our application.");
        description.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        sigupPanel.add(description, gridBagConstraints);

        firstNameTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        firstNameTextField.setForeground(new java.awt.Color(51, 51, 51));
        firstNameTextField.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        sigupPanel.add(firstNameTextField, gridBagConstraints);

        lastNameTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        lastNameTextField.setForeground(new java.awt.Color(51, 51, 51));
        lastNameTextField.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        sigupPanel.add(lastNameTextField, gridBagConstraints);

        emailTextField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        sigupPanel.add(emailTextField, gridBagConstraints);

        passwordField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        passwordField.setForeground(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        sigupPanel.add(passwordField, gridBagConstraints);

        signupButton.setBackground(new java.awt.Color(40, 167, 69));
        signupButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setText("Sign up");
        signupButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        sigupPanel.add(signupButton, gridBagConstraints);

        questionText.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        questionText.setForeground(new java.awt.Color(102, 102, 102));
        questionText.setText("Already have an account?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 0);
        sigupPanel.add(questionText, gridBagConstraints);

        loginLink.setBackground(new java.awt.Color(242, 242, 242));
        loginLink.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        loginLink.setForeground(new java.awt.Color(40, 167, 69));
        loginLink.setText("LOG IN");
        loginLink.setToolTipText("Go to log in");
        loginLink.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLoginFrame(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 2);
        sigupPanel.add(loginLink, gridBagConstraints);

        rightPanel.add(sigupPanel, "card3");

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
     * Loads icons for panels.
     * This method loads the icons for the left and right panels of the signup frame.
     */
    private void loadPanelIcon() { 
        
        loginIcon.setIcon(new ImageIcon(getClass().getResource("/icon/left-panel-icon.png")));
        addUserIcon.setIcon(new ImageIcon(getClass().getResource("/icon/add-user.png")));
        
    }
    
    
    /**
     * Customizes UI components.
     * This method customizes the appearance and behavior of various UI components 
     * such as text fields, buttons, and labels.
     */
    private void  customizeComponent() {
        
        firstNameTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        firstNameTextField.putClientProperty(FlatClientProperties.STYLE, 
                "focusColor: #28a745;" +
                "margin: 3, 10, 3, 10;");
        
        lastNameTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");
        lastNameTextField.putClientProperty(FlatClientProperties.STYLE, 
                "focusColor: #28a745;" +
                "margin: 3, 10, 3, 10;");
        
        emailTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");
        emailTextField.putClientProperty(FlatClientProperties.STYLE, 
                "focusColor: #28a745;" +
                "margin: 3, 10, 3, 10;" +
                "showClearButton: true;");
        
        passwordField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        passwordField.putClientProperty(FlatClientProperties.STYLE, 
                "focusColor: #28a745;" +
                "showRevealButton: true;" +
                "showCapsLock: true;" +
                "margin: 3, 10, 3, 10;");
        
        signupButton.putClientProperty(FlatClientProperties.STYLE, "focusColor: #28a745;");
        
        loginLink.putClientProperty(FlatClientProperties.STYLE, 
                "hoverForeground: #5cb85c;" +
                "pressedForeground: #1e7e34;");
        
    }
    
    
    /**
     * Displays the login frame.
     * This method is invoked when the user clicks on the login link.
     * It closes the current signup frame and opens the login frame.
     */
    private void showLoginFrame(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLoginFrame
        
        new LoginFrame().setVisible(true);
        dispose();

    }//GEN-LAST:event_showLoginFrame

    
    /**
     * Handles sign-up button action.
     * This method is invoked when the user clicks on the sign-up button.
     * It retrieves the user input from the text fields, validates the input, 
     * and then attempts to create a new user account in the database.
     */
    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        email = emailTextField.getText();
        password = String.valueOf(passwordField.getPassword());
        
        if (isInputFieldEmpty()) {
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "Fill out needed information!",
                    "Reminder",
                    JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        
        if (isPasswordLengthValid(passwordField.getPassword())) {
            
            if (!isDigitCountValid(passwordField.getPassword())) {

                JOptionPane.showMessageDialog(
                        rightPanel, 
                        "Must contain atleast 2 digits!",
                        "Password requirement",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }
            
        }
        else {
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "Password must be 8 characters long!",
                    "Password requirement",
                    JOptionPane.WARNING_MESSAGE);

                return;
        }
        
        String insertToUserQuery = 
                "INSERT INTO [User] ([Firstname], [Lastname], [Email]) " +
                "VALUES (?, ?, ?)";
        
        String insertToAccountQuery = 
                "INSERT INTO [Account] ([Email], [Password], [Role], [Status]) " +
                "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement insertToAccount = connection.prepareStatement(insertToAccountQuery);
             PreparedStatement insertToUser = connection.prepareStatement(insertToUserQuery);) {

            connection.setAutoCommit(false);
            
            insertToAccount.setString(1, email);
            insertToAccount.setString(2, password);
            insertToAccount.setString(3, "Citizen");
            insertToAccount.setString(4, "Active");
            insertToAccount.executeUpdate();
            
            insertToUser.setString(1, firstName);
            insertToUser.setString(2, lastName);
            insertToUser.setString(3, email);
            insertToUser.executeUpdate();
            
            connection.commit();
            
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "Sign up Succesful",
                    "Notice",
                    JOptionPane.INFORMATION_MESSAGE);
            
            new LoginFrame().setVisible(true);
            dispose();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    rightPanel, 
                    "An account with this email already exists. Please use a different email or log in.",
                    "Sign Up Error",
                    JOptionPane.ERROR_MESSAGE);
            
            clearUserInput();
            
            Logger.getLogger(SignupFrame.class.getName())
                  .log(Level.INFO, "Sign up failed", ex);
        }
        
    }//GEN-LAST:event_signupButtonActionPerformed

    
    private boolean isPasswordLengthValid(char[] password) {
        
        return password.length <= 8;
        
    }
    
    
    private boolean isDigitCountValid(char[] password) {
        
        int digitCount = 0;
        for (char character : password) {
            
            if (Character.isDigit(character)) {
                digitCount++;
            }
            
        }
        
        return digitCount >= 2;
        
    }
    
    private void clearUserInput() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        passwordField.setText("");
    }
    
    /**
     * Checks if any input field is empty.
     * This method checks whether any of the input fields (first name, last name, email, password) are empty.
     * 
     * @return true if any input field is empty, false otherwise.
     */
    boolean isInputFieldEmpty() {
        
        return firstName.isBlank() || lastName.isBlank() ||
               email.isBlank() || password.isBlank();
        
    }
    
                
    public static void main(String args[]) {
        
        FlatMacLightLaf.setup();
        
        // FlatLaf setup and UI theme customization...
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.background", Color.decode("#ffffff"));
        UIManager.put("TitlePane.showIcon", true);

        UIManager.put( "Button.arc", 8);
        UIManager.put("TextComponent.arc", 8);
        UIManager.put( "Component.focusWidth", 1);
        
             
        java.awt.EventQueue.invokeLater(() -> {
            new SignupFrame().setVisible(true);
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addUserIcon;
    private javax.swing.JLabel description;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel loginIcon;
    private javax.swing.JButton loginLink;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel questionText;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton signupButton;
    private javax.swing.JPanel sigupPanel;
    // End of variables declaration//GEN-END:variables
}
