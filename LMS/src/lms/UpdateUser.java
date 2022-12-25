/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author urvas
 */
public class UpdateUser extends javax.swing.JFrame {

    /**
     * Creates new form UpdateUser
     */
    boolean is_admin;
    DefaultTableModel model;

    public UpdateUser() {
        initComponents();
    }

    public boolean addUsers() {
        boolean add_bool = false;
        String name = txtU_username.getText();
        //String password = txt_password.getText();
        String contact = txtU_contact.getText();

        try {

            Connection con = DBConnection.getConnection();
            String sql = "insert into public.\"USERS\"(user_name, usermob_no, password, is_admin) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, Double.parseDouble(contact));
            pst.setString(3, "12345678");
            pst.setBoolean(4, is_admin);

            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted Succesfully");
                add_bool = true;

            } else {
                JOptionPane.showMessageDialog(this, "Failed");
                add_bool = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return add_bool;
    }

    public boolean validateUserDetails() {
        String name = txtU_username.getText();
        //String password = txt_password.getText();
        String contact = txtU_contact.getText();

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Username");
            return false;
        }
        //System.out.println(is_admin);
        //System.err.println(is_admin != false);
        if (is_admin != true && is_admin != false) {
            JOptionPane.showMessageDialog(this, "Please enter User Type");
            return false;
        }
        if (contact.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Contact number");
            return false;
        }

        return true;
    }

    public void getUserdetailstable() {

        String userid = txtU_userid.getText();

        //String name = txtU_username.getText();
        //String password = txt_password.getText();
        //String contact = txtU_contact.getText();
        if (userid.equals("")) {
            if(this.model != null){
                System.out.println(this.model);
                model.setRowCount(0);
            
            }
            //System.out.println("hey here i am ");
            //System.out.println(this.model);
            //model.setRowCount(0);
            System.out.println("lms.UpdateUser.getUserdetailstable() if equals()");
            try {
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"USERS\"");

                while (rs.next()) {

                    //System.out.println("SearchDocument.search()");
                    String user_ID = rs.getString("user_ID");
                    String user_name = rs.getString("user_name");
                    String usermob_no = rs.getString("usermob_no");
                    //String pub_date = rs.getString("pub_date");
                    //String no_of_copies = rs.getString("no_of-copies");
                    //String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {user_ID, user_name, usermob_no};
                    model = (DefaultTableModel) tbUpd_userdetails.getModel();
                    model.addRow(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            
            //String row = this.model;
            if(this.model != null){
                System.out.println(this.model);
                model.setRowCount(0);
            
            }
            
            try {
                System.out.println(this.model);
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"USERS\" where \"user_ID\" = ?");
                pst.setInt(1, Integer.parseInt(userid));
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //System.out.println("SearchDocument.search()");
                    String user_ID = rs.getString("user_ID");
                    String user_name = rs.getString("user_name");
                    String usermob_no = rs.getString("usermob_no");
                    //String pub_date = rs.getString("pub_date");
                    //String no_of_copies = rs.getString("no_of-copies");
                    //String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {user_ID, user_name, usermob_no};
                    model = (DefaultTableModel) tbUpd_userdetails.getModel();
                    model.addRow(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean UpdateUsers(){
        
        boolean upd_bool = false;
        String userid = txtU_userid.getText();
        String name = txtU_username.getText();
        //String password = txt_password.getText();
        String contact = txtU_contact.getText();

        try {

            Connection con = DBConnection.getConnection();
            String sql = "Update \"public\".\"USERS\" set \"user_name\" = ? ,\"usermob_no\" = ? ,\"password\" = ? ,\"is_admin\" = ? where \"USERS\".\"user_ID\" = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, Double.parseDouble(contact));
            pst.setString(3, "12345678");
            pst.setBoolean(4, is_admin);
            pst.setInt(5, Integer.parseInt(userid));
            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted Succesfully, Default password = 12345678");
                upd_bool = true;

            } else {
                JOptionPane.showMessageDialog(this, "Failed");
                upd_bool = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return upd_bool;
    }
    
    public boolean deleteUsers() {
        boolean add_bool = false;
        //String name = txtU_username.getText();
        //String password = txt_password.getText();
        //String contact = txtU_contact.getText();
        String userid = txtU_userid.getText();

        try {

            Connection con = DBConnection.getConnection();
            String sql = "delete FROM \"public\".\"USERS\" where \"user_ID\" = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(userid));
            //pst.setString(1, name);
            //pst.setDouble(2, Double.parseDouble(contact));
            //pst.setString(3, "12345678");
            //pst.setBoolean(4, is_admin);

            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Record Deleted Succesfully");
                add_bool = true;

            } else {
                JOptionPane.showMessageDialog(this, "Failed");
                add_bool = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return add_bool;
    }

    
    /*     try {
            Connection con = DBConnection.getConnection();
        //System.out.println(copies);
        PreparedStatement pst = con.prepareStatement("Update \"public\".\"DOCUMENTS\" set \"no_of-copies\" = \"no_of-copies\" - " + copies + " where \"DOCUMENTS\".\"doc_ID\" = ?");
        pst.setInt(1, documentid);

        int UpdateRowCount = pst.executeUpdate();
        //System.out.println(UpdateRowCount);
        if (UpdateRowCount > 0) {
            JOptionPane.showMessageDialog(this, "Quantity Updated");

        } else {

            JOptionPane.showMessageDialog(this, "Quantity Updated");
        }
    }
    catch (Exception e

    
        ) {
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

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUpd_userdetails = new rojeru_san.complementos.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtU_username = new javax.swing.JTextField();
        txtU_contact = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtU_userid = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Update User ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(298, 298, 298))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        tbUpd_userdetails.setBackground(new java.awt.Color(195, 206, 223));
        tbUpd_userdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Pho Number "
            }
        ));
        tbUpd_userdetails.setColorBackgoundHead(new java.awt.Color(138, 131, 121));
        tbUpd_userdetails.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tbUpd_userdetails.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tbUpd_userdetails.setColorFilasBackgound1(new java.awt.Color(195, 206, 223));
        tbUpd_userdetails.setColorFilasBackgound2(new java.awt.Color(195, 206, 223));
        tbUpd_userdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbUpd_userdetails.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        tbUpd_userdetails.setRowHeight(40);
        tbUpd_userdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUpd_userdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUpd_userdetails);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(221, 221, 221)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 710, 730));

        jPanel2.setBackground(new java.awt.Color(223, 212, 195));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Mobie No");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("User Type");

        txtU_username.setBackground(new java.awt.Color(195, 206, 223));
        txtU_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_usernameFocusLost(evt);
            }
        });
        txtU_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_usernameActionPerformed(evt);
            }
        });

        txtU_contact.setBackground(new java.awt.Color(195, 206, 223));
        txtU_contact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_contactFocusLost(evt);
            }
        });
        txtU_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_contactActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(223, 212, 195));
        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jRadioButton1.setText("  User");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(223, 212, 195));
        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jRadioButton2.setText("Admin");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(195, 206, 223));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(195, 206, 223));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("MODIFY");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(195, 206, 223));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("User ID");

        txtU_userid.setBackground(new java.awt.Color(195, 206, 223));
        txtU_userid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_useridFocusLost(evt);
            }
        });
        txtU_userid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_useridActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(138, 131, 121));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtU_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtU_username, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(64, 64, 64)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtU_userid, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU_userid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(215, 215, 215))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 830, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        AdminHomePage adminhome = new AdminHomePage();
        adminhome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtU_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_usernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_usernameFocusLost

    private void txtU_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_usernameActionPerformed

    private void txtU_contactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_contactFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_contactFocusLost

    private void txtU_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_contactActionPerformed

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // TODO add your handling code here:
        /*System.out.println("SearchDocument.jRadioButton1MouseClicked()");
        String category = "1";
        System.out.println(category);*/
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        is_admin = false;
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        is_admin = true;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (validateUserDetails() == true) {

            if (addUsers() == true) {
                JOptionPane.showMessageDialog(this, "user added to the table");
                model.setRowCount(0);
                getUserdetailstable();

            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user to the table");
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (validateUserDetails() == true) {

            if (UpdateUsers()== true) {
                JOptionPane.showMessageDialog(this, "user details updated  to the table");
                model.setRowCount(0);
                getUserdetailstable();

            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user to the table");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (validateUserDetails() == true) {

            if (deleteUsers()== true) {
                JOptionPane.showMessageDialog(this, "user details Deleted from the table");
                model.setRowCount(0);
                getUserdetailstable();

            } else {
                JOptionPane.showMessageDialog(this, "Failed to Delete user to the table");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtU_useridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_useridFocusLost
        // TODO add your handling code here:
        System.out.println();
        
        getUserdetailstable();
        //model.setRowCount(0);

    }//GEN-LAST:event_txtU_useridFocusLost

    private void txtU_useridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_useridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_useridActionPerformed

    private void tbUpd_userdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUpd_userdetailsMouseClicked
        // TODO add your handling code here:
        int rowNo = tbUpd_userdetails.getSelectedRow();
        TableModel model = tbUpd_userdetails.getModel();

        txtU_userid.setText(model.getValueAt(rowNo, 0).toString());
        txtU_username.setText(model.getValueAt(rowNo, 1).toString());
        //String password = txt_password.getText();
        txtU_contact.setText(model.getValueAt(rowNo, 2).toString());


    }//GEN-LAST:event_tbUpd_userdetailsMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(UpdateUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro tbUpd_userdetails;
    private javax.swing.JTextField txtU_contact;
    private javax.swing.JTextField txtU_userid;
    private javax.swing.JTextField txtU_username;
    // End of variables declaration//GEN-END:variables
}
