package project.admin;

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
import javax.swing.tree.DefaultMutableTreeNode;
import project.authentication.LoginFrame;
import project.database.Database;


public class AdminFrame extends javax.swing.JFrame {
    CardLayout cl;
    
    private Connection connection;
    private String email;
    private byte[] profilePictureData;
    private String fullName;
    
    public AdminFrame() {
        initComponents();
        customizeComponent();
        
        cl = (CardLayout) contentPanel.getLayout();
        
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE,
                    ex.getMessage(), ex);
        }
        
        this.email = LoginFrame.email;

        initProfileInformation();
        
    }

    private void customizeComponent() {
        
        menuTree.putClientProperty(FlatClientProperties.STYLE,
                "rootVisible: false;" +
                "selectionBackground: #007AFF;" +
                "selectionInactiveBackground: #007AFF;" +
                "selectionInactiveForeground: #FFFFFF;" +
                "selectionArc: 8;");
        
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
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE,
                    " initProfileInformation method ", ex);
        }
        
        nameLabel.setText(this.fullName);
        
        try {
            this.profilePictureLabel.setIcon(scaledImageIcon(profilePictureData));
        } catch (NullPointerException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE,
                    " scaledImageIcon method ", ex);
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
        jSeparator1 = new javax.swing.JSeparator();
        logoutButton = new javax.swing.JButton();
        menuTreeScrollPane = new javax.swing.JScrollPane();
        menuTree = new javax.swing.JTree();
        contentPanel = new javax.swing.JPanel();
        manageComplaints1 = new project.admin.ManageComplaints();
        manageUsers1 = new project.admin.ManageUsers();
        environmentReport1 = new project.admin.report.category.EnvironmentReport();
        infrastructureReport1 = new project.admin.report.category.InfrastructureReport();
        utilitiesReport1 = new project.admin.report.category.UtilitiesReport();
        publicServices1 = new project.admin.report.category.PublicServices();
        governmentAgenciesReport1 = new project.admin.report.category.GovernmentAgenciesReport();
        lowReport1 = new project.admin.report.urgency_level.LowReport();
        mediumReport1 = new project.admin.report.urgency_level.MediumReport();
        highReport1 = new project.admin.report.urgency_level.HighReport();
        emergencyReport1 = new project.admin.report.urgency_level.EmergencyReport();
        newReport1 = new project.admin.report.status.NewReport();
        underReviewReport1 = new project.admin.report.status.UnderReviewReport();
        assignedReport1 = new project.admin.report.status.AssignedReport();
        resolvedReport1 = new project.admin.report.status.ResolvedReport();

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
        emailLabel.setText("admin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        profilePanel.add(emailLabel, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(235, 235, 235));
        jSeparator1.setPreferredSize(new java.awt.Dimension(230, 10));

        logoutButton.setBackground(java.awt.Color.red);
        logoutButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("Log out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        menuTreeScrollPane.setBorder(null);

        menuTree.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Manage Complaints");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Manage Users");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reports");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Category");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Environment");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Infrastructure");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Utilities");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Public Services");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Government Agencies");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Urgency Level");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Low");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Medium");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("High");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Emergency");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Status");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("New");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Under Review");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Assigned");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Resolved");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        menuTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        menuTree.setRootVisible(false);
        menuTree.setRowHeight(40);
        menuTree.setSelectionRows(new int[] {0});
        menuTree.setShowsRootHandles(true);
        menuTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuTreeMousePressed(evt);
            }
        });
        menuTreeScrollPane.setViewportView(menuTree);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuTreeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                .addComponent(menuTreeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        contentPanel.setBackground(new java.awt.Color(204, 204, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        contentPanel.setLayout(new java.awt.CardLayout());
        contentPanel.add(manageComplaints1, "Card1");
        contentPanel.add(manageUsers1, "Card2");
        contentPanel.add(environmentReport1, "Card3");
        contentPanel.add(infrastructureReport1, "Card4");
        contentPanel.add(utilitiesReport1, "Card5");
        contentPanel.add(publicServices1, "Card6");
        contentPanel.add(governmentAgenciesReport1, "Card7");
        contentPanel.add(lowReport1, "Card8");
        contentPanel.add(mediumReport1, "Card9");
        contentPanel.add(highReport1, "Card10");
        contentPanel.add(emergencyReport1, "Card11");
        contentPanel.add(newReport1, "Card12");
        contentPanel.add(underReviewReport1, "Card13");
        contentPanel.add(assignedReport1, "Card14");
        contentPanel.add(resolvedReport1, "Card15");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE))
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
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTreeMousePressed
        
        int selectedRow = menuTree.getClosestRowForLocation(evt.getX(), evt.getY());
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)menuTree.getPathForRow(selectedRow).getLastPathComponent();
        String selectedNode = node.toString();    
         
        switch(selectedNode) {
            
            // Main menu 
            case "Manage Complaints": cl.show(contentPanel, "Card1"); break;
            case "Manage Users": cl.show(contentPanel, "Card2"); break;
            
            // Category sub-menu
            case "Environment": cl.show(contentPanel, "Card3"); break;
            case "Infrastructure": cl.show(contentPanel, "Card4"); break;
            case "Utilities": cl.show(contentPanel, "Card5"); break;
            case "Public Services": cl.show(contentPanel, "Card6"); break;
            case "Government Agencies": cl.show(contentPanel, "Card7"); break;
            
            // Urgency Level sub-menu
            case "Low": cl.show(contentPanel, "Card8"); break;
            case "Medium": cl.show(contentPanel, "Card9"); break;
            case "High": cl.show(contentPanel, "Card10"); break;
            case "Emergency": cl.show(contentPanel, "Card11"); break;
            
            // Status sub-menu
            case "New": cl.show(contentPanel, "Card12"); break;
            case "Under Review": cl.show(contentPanel, "Card13"); break;
            case "Assigned": cl.show(contentPanel, "Card14"); break;
            case "Resolved": cl.show(contentPanel, "Card15"); break;
            
        }
        
    }//GEN-LAST:event_menuTreeMousePressed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        int response = JOptionPane.showConfirmDialog(
                this, 
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
           
        // Overall UI Customization
        FlatLaf.registerCustomDefaultsSource("theme");
        FlatMacLightLaf.setup();
        
        FlatRobotoFont.install();

        // TitlePane Customization
        UIManager.put("TitlePane.unifiedBackground", false);
        
        // Table Customization
        UIManager.put("TableHeader.separatorColor", Color.decode("#ffffff"));
        UIManager.put("TableHeader.hoverBackground", Color.decode("#f2f2f2"));
        UIManager.put("TableHeader.height", 40);
        UIManager.put("Table.selectionBackground", Color.decode("#c2e7ff"));
        UIManager.put("Table.selectionForeground", Color.decode("#333333"));
        UIManager.put("Table.showCellFocusIndicator", true);
        //UIManager.put("Table.alternateRowColor", Color.decode("#f2f2f2"));
        //UIManager.put("Table.cellFocusColor", Color.decode("#B0E2FF"));
            
        java.awt.EventQueue.invokeLater(() -> {
            new AdminFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.admin.report.status.AssignedReport assignedReport1;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel emailLabel;
    private project.admin.report.urgency_level.EmergencyReport emergencyReport1;
    private project.admin.report.category.EnvironmentReport environmentReport1;
    private project.admin.report.category.GovernmentAgenciesReport governmentAgenciesReport1;
    private project.admin.report.urgency_level.HighReport highReport1;
    private project.admin.report.category.InfrastructureReport infrastructureReport1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logoutButton;
    private project.admin.report.urgency_level.LowReport lowReport1;
    private javax.swing.JPanel mainPanel;
    private project.admin.ManageComplaints manageComplaints1;
    private project.admin.ManageUsers manageUsers1;
    private project.admin.report.urgency_level.MediumReport mediumReport1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTree menuTree;
    private javax.swing.JScrollPane menuTreeScrollPane;
    private javax.swing.JLabel nameLabel;
    private project.admin.report.status.NewReport newReport1;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePictureLabel;
    private project.admin.report.category.PublicServices publicServices1;
    private project.admin.report.status.ResolvedReport resolvedReport1;
    private project.admin.report.status.UnderReviewReport underReviewReport1;
    private project.admin.report.category.UtilitiesReport utilitiesReport1;
    // End of variables declaration//GEN-END:variables
}
