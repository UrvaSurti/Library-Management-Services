/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lms.SearchDocument;
import lms.UserHomePage;
import lms.AdminHomePage;
/**
 *
 * @author urvas
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public String nameUN;
    public boolean is_admin;
    
    public LoginPage() {
        initComponents();
    }

    public void insertsignup() {

        String name = txt_username.getText();
        String password = txt_password.getText();
        String contact = txt_contact.getText();

        try {

            Connection con = DBConnection.getConnection();
            String sql = "insert into public.\"USERS\"(user_name, usermob_no, password) values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, Double.parseDouble(contact));
            pst.setString(3, password);

            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted Succesfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateInsert() {
        String name = txt_username.getText();
        String password = txt_password.getText();
        String contact = txt_contact.getText();

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Username");
            return false;
        }
        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password");
            return false;
        }
        if (contact.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Username");
            return false;
        }

        return true;
    }

    public boolean dublicate() {
        System.out.println("lms.LoginPage.dublicate()");
        String name = txt_username.getText();
        boolean IsExist = false;

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"USERS\" where \"USERS\".\"user_name\" = ?");
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                IsExist = true;
            } else {
                IsExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IsExist;

    }

    public boolean validateLogin() {
        String nameL = Ltxt_username.getText();
        String passwordL = Ltxt_password.getText();

        if (nameL.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Username");
            return false;
        }
        if (passwordL.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter password");
            return false;
        }
        return true;
    }

    public void Login() {

        String nameL = Ltxt_username.getText();
        String passwordL = Ltxt_password.getText();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"USERS\" where \"USERS\".\"user_name\" = ? and \"USERS\".\"password\" = ?");

            pst.setString(1, nameL);
            pst.setString(2, passwordL);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                is_admin = rs.getBoolean("is_admin");
                nameUN = rs.getString("user_name");
                lms.UserHomePage.method(nameUN);
                lms.SearchDocument.method(is_admin);
                System.out.println("is_admin assignment");
                System.out.println(is_admin);
                if (is_admin == false) {
                    System.out.println("lms.LoginPage.Login() inside if false");
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    UserHomePage userhome = new UserHomePage();
                    userhome.setVisible(true);
                    this.dispose();
                }if(is_admin == true){
                    
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    AdminHomePage adminhome = new AdminHomePage();
                    adminhome.setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Ltxt_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Ltxt_password = new javax.swing.JPasswordField();
        SignUpPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(223, 212, 195));
        setIconImages(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loginpageIcons/loginPage_Background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 1080));

        jPanel1.setBackground(new java.awt.Color(223, 212, 195));

        loginPanel.setBackground(new java.awt.Color(138, 131, 121));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Login");
        loginPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 70, 60));

        Ltxt_username.setBackground(new java.awt.Color(195, 206, 223));
        loginPanel.add(Ltxt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 250, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Username");
        loginPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 80, 30));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Password");
        loginPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 90, -1));

        jButton1.setBackground(new java.awt.Color(195, 206, 223));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        loginPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, 40));

        Ltxt_password.setBackground(new java.awt.Color(195, 206, 223));
        loginPanel.add(Ltxt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 250, -1));

        SignUpPanel.setBackground(new java.awt.Color(138, 131, 121));
        SignUpPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("Contact");
        SignUpPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 80, 30));

        txt_username.setBackground(new java.awt.Color(195, 206, 223));
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        SignUpPanel.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 250, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setText("Password");
        SignUpPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 90, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setText("Username");
        SignUpPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 80, 30));

        txt_contact.setBackground(new java.awt.Color(195, 206, 223));
        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        SignUpPanel.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 250, -1));

        jButton2.setBackground(new java.awt.Color(195, 206, 223));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("SIGN UP");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        SignUpPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, 40));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel6.setText("Sign Up");
        SignUpPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 80, 60));

        txt_password.setBackground(new java.awt.Color(195, 206, 223));
        SignUpPanel.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 250, -1));

        jPanel2.setBackground(new java.awt.Color(138, 131, 121));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(SignUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(492, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(385, 385, 385))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SignUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(360, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 1280, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (validateInsert() == true) {
            if (dublicate() == false) {
                insertsignup();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exist");
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        if (dublicate() == false) {
            JOptionPane.showMessageDialog(this, "Username already exist");

        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validateLogin() == true) {
            Login();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Ltxt_password;
    private javax.swing.JTextField Ltxt_username;
    private javax.swing.JPanel SignUpPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
