package project.citizen;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import project.authentication.LoginFrame;
import project.database.Database;


public class CitizenFrame extends javax.swing.JFrame {
    
    Connection connection;
    
    CardLayout cl;
    
    private String email;
    private String fullName;
    private byte[] profilePictureData;
    
    public CitizenFrame() {
        initComponents();
        
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CitizenFrame.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        cl = (CardLayout) contentPanel.getLayout();
        
        customizeComponent();
        
        initProfileInformation();
    
    }

    private void customizeComponent() {
        menuList.putClientProperty(FlatClientProperties.STYLE, 
                "cellMargins: 0, 10, 0, 0;" +
                "selectionArc: 10;");
    }
    
    
    private void initProfileInformation() {
        
        this.email = LoginFrame.email;
        
        String selectQuery = 
                "SELECT [Firstname] + ' ' + [Lastname] AS Fullname, [ProfilePicture] " +
                "FROM [User]" +
                "WHERE [Email] = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(selectQuery)) {
            
            pst.setString(1, this.email);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                this.fullName = rs.getString("Fullname");
                this.profilePictureData = rs.getBytes("ProfilePicture");
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(CitizenFrame.class.getName())
                  .log(Level.SEVERE, "initProfileInformation method", ex);
        }
        
        nameLabel.setText(this.fullName);
        emailLabel.setText(this.email);
        
        try {
            this.profilePictureLabel.setIcon(scaledImageIcon(profilePictureData));
        } catch (NullPointerException ex) {
            Logger.getLogger(CitizenFrame.class.getName())
                  .log(Level.SEVERE, "scaledImageIcon method", ex);
        }

    }
    
    private ImageIcon scaledImageIcon(byte[] pictureData) throws NullPointerException {
        
        Image imageIcon = new ImageIcon(profilePictureData).getImage()
                                                           .getScaledInstance(profilePictureLabel.getWidth(),
                                                                              profilePictureLabel.getHeight(), 
                                                                              Image.SCALE_SMOOTH);

        return new ImageIcon(imageIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        profilePanel = new javax.swing.JPanel();
        profilePictureLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        menuScrollPane = new javax.swing.JScrollPane();
        menuList = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        logoutButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        myComplaint1 = new project.citizen.MyComplaint();
        createComplaint1 = new project.citizen.CreateComplaint();
        accountSetting1 = new project.citizen.AccountSetting();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(new java.awt.Dimension(1300, 800));

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));

        profilePanel.setBackground(new java.awt.Color(255, 255, 255));
        profilePanel.setPreferredSize(new java.awt.Dimension(240, 170));
        profilePanel.setLayout(new java.awt.GridBagLayout());

        profilePictureLabel.setPreferredSize(new java.awt.Dimension(70, 70));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        profilePanel.add(profilePictureLabel, gridBagConstraints);

        nameLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(51, 51, 51));
        nameLabel.setText("Full Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        profilePanel.add(nameLabel, gridBagConstraints);

        emailLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(128, 128, 128));
        emailLabel.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        profilePanel.add(emailLabel, gridBagConstraints);

        menuScrollPane.setBorder(null);
        menuScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menuScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        menuScrollPane.setWheelScrollingEnabled(false);

        menuList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuList.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        menuList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "My Complaint", "Create Complaint", "Account" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menuList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        menuList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuList.setFixedCellHeight(40);
        menuList.setPreferredSize(new java.awt.Dimension(220, 400));
        menuList.setSelectedIndex(0);
        menuList.setVisibleRowCount(4);
        menuList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                menuListValueChanged(evt);
            }
        });
        menuScrollPane.setViewportView(menuList);

        jSeparator1.setForeground(new java.awt.Color(235, 235, 235));
        jSeparator1.setPreferredSize(new java.awt.Dimension(230, 10));

        logoutButton.setBackground(java.awt.Color.red);
        logoutButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("Log out");
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(menuScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(profilePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        contentPanel.setBackground(new java.awt.Color(204, 204, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        contentPanel.setLayout(new java.awt.CardLayout());
        contentPanel.add(myComplaint1, "myComplaintCard");
        contentPanel.add(createComplaint1, "createComplaintCard");
        contentPanel.add(accountSetting1, "accountSettingCard");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1308, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_menuListValueChanged
        
        final int MENU1 = 0;
        final int MENU2 = 1;
        final int MENU3 = 2;
        int selectedMenu = menuList.getSelectedIndex();
        
        switch(selectedMenu) {
            case MENU1: cl.show(contentPanel, "myComplaintCard"); break;
            case MENU2: cl.show(contentPanel, "createComplaintCard"); break;
            case MENU3: cl.show(contentPanel, "accountSettingCard"); break;
        }
         
    }//GEN-LAST:event_menuListValueChanged

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        int response = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to log out?", 
                "Log out Confirmation", 
                JOptionPane.YES_NO_OPTION);
        
        final int YES = 0;
        if (response == YES) {
            new LoginFrame().setVisible(true);
            dispose();
        }
        
    }//GEN-LAST:event_logoutButtonActionPerformed


    public static void main(String args[]) {
        
        FlatLaf.registerCustomDefaultsSource("/theme");
        
        FlatMacLightLaf.setup();
        
        // Font used
        FlatRobotoFont.install();

        // TitlePane Customization
        UIManager.put("TitlePane.unifiedBackground", false);
        
        // Menu Customization
        UIManager.put("List.selectionBackground", Color.decode("#007AFF"));
        UIManager.put("List.selectionInactiveBackground", Color.decode("#007AFF"));
        UIManager.put("List.selectionInactiveForeground", Color.decode("#FFFFFF"));
        
        // TextField UI Customization
        UIManager.put("TextComponent.arc", 8);
        UIManager.put("TextField.disabledBackground", Color.decode("#FFFFFF"));
        UIManager.put("TextField.disabledForeground", Color.decode("#333333"));
        
        // Table UI Customization
        UIManager.put("TableHeader.separatorColor", Color.decode("#FFFFFF"));
        UIManager.put("TableHeader.hoverBackground", Color.decode("#F2F2F2"));
        UIManager.put("TableHeader.height", 40);
        UIManager.put("Table.selectionBackground", Color.decode("#C2E7FF"));
        UIManager.put("Table.selectionForeground", Color.decode("#333333"));
        //nUIManager.put("Table.showCellFocusIndicator", false);
        UIManager.put("Table.selectionInactiveBackground", Color.decode("#C2E7FF"));
        //UIManager.put("Table.alternateRowColor", Color.decode("#F2F2F2"));
        UIManager.put("Table.cellFocusColor", Color.decode("#B0E2FF"));
        
        java.awt.EventQueue.invokeLater(() -> {
            new CitizenFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.citizen.AccountSetting accountSetting1;
    private javax.swing.JPanel contentPanel;
    private project.citizen.CreateComplaint createComplaint1;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JList<String> menuList;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JScrollPane menuScrollPane;
    private project.citizen.MyComplaint myComplaint1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePictureLabel;
    // End of variables declaration//GEN-END:variables
}
