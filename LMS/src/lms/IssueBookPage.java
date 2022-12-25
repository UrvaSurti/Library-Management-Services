/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lms;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.jdbc.PgStatement;

/**
 *
 * @author urvas
 */
public class IssueBookPage extends javax.swing.JFrame {

    /**
     * Creates new form IssueBookPage
     */
    String doc_name;
    String user_name;
    Integer copies;
    DefaultTableModel model;

    public IssueBookPage() {
        initComponents();
    }

    public void getDocdetails() {

        try {
            String docID = txt_docid.getText();
            Connection con = DBConnection.getConnection();
            if (txt_docid.getText().equals("")) {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"DOCUMENTS\"");

                while (rs.next()) {

                    System.out.println("SearchDocument.search()");

                    String doc_ID = rs.getString("doc_ID");
                    doc_name = rs.getString("doc_name");
                    String doc_author = rs.getString("doc_author");
                    //String pub_date = rs.getString("pub_date");
                    String no_of_copies = rs.getString("no_of-copies");
                    String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {doc_ID, doc_name, doc_author, no_of_copies, cat_ID};
                    model = (DefaultTableModel) tbIss_docdetails.getModel();
                    model.addRow(obj);
                }

            } else {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"DOCUMENTS\"  where \"DOCUMENTS\".\"doc_ID\" = ?");
                pst.setInt(1, Integer.parseInt(docID));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {

                    System.out.println("SearchDocument.search()");

                    String doc_ID = rs.getString("doc_ID");
                    doc_name = rs.getString("doc_name");
                    String doc_author = rs.getString("doc_author");
                    //String pub_date = rs.getString("pub_date");
                    String no_of_copies = rs.getString("no_of-copies");
                    String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {doc_ID, doc_name, doc_author, no_of_copies, cat_ID};
                    model = (DefaultTableModel) tbIss_docdetails.getModel();
                    model.addRow(obj);
                } else {
                    lab_invdocid.setText("Invalid DocID");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserdetails() {

        try {
            String userID = txt_userid.getText();
            Connection con = DBConnection.getConnection();
            if (txt_docid.getText().equals("")) {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"USERS\"");

                while (rs.next()) {

                    String user_ID = rs.getString("user_ID");
                    user_name = rs.getString("user_name");
                    String usermob_no = rs.getString("usermob_no");

                    Object[] obj = {user_ID, user_name, usermob_no};
                    model = (DefaultTableModel) tbIss_userdetails.getModel();
                    model.addRow(obj);
                }

            } else {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"USERS\"  where \"USERS\".\"user_ID\" = ?");
                pst.setInt(1, Integer.parseInt(userID));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {

                    String user_ID = rs.getString("user_ID");
                    user_name = rs.getString("user_name");
                    String usermob_no = rs.getString("usermob_no");

                    Object[] obj = {user_ID, user_name, usermob_no};
                    model = (DefaultTableModel) tbIss_userdetails.getModel();
                    model.addRow(obj);
                } else {

                    lab_invuserid.setText("Invalid UserID");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean issuedocument() {
        boolean issued_is = false;
        int documentid = Integer.parseInt(txt_docid.getText());
        int usersid = Integer.parseInt(txt_userid.getText());
        copies = Integer.parseInt(txt_copies.getText());

        java.util.Date issdateu = dateIssue.getDatoFecha();
        java.util.Date duedateu = datedue.getDatoFecha();

        Long l1 = issdateu.getTime();
        Long l2 = duedateu.getTime();

        java.sql.Date issuedates = new java.sql.Date(l1);
        java.sql.Date duedates = new java.sql.Date(l2);
        //System.out.println(issuedates);
        //System.out.println(duedates);
        try {

            Connection con = DBConnection.getConnection();
            String sql = "insert into \"public\".\"RECORDS\" (\"docu_ID\", \"user_ID\", copies_borrow, issue_date, due_date, status) values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, documentid);
            pst.setInt(2, usersid);
            pst.setInt(3, copies);
            pst.setDate(4, issuedates);
            pst.setDate(5, duedates);
            pst.setString(6, "pending");

            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {

                issued_is = true;
            } else {

                issued_is = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issued_is;
    }

    public boolean validateIssuedocumennt() {
        copies = Integer.parseInt(txt_copies.getText());
        String documentid = txt_docid.getText();
        String usersid = txt_userid.getText();
        int no_of_copies = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT \"no_of-copies\" FROM \"public\".\"DOCUMENTS\" where \"DOCUMENTS\".\"doc_ID\" = ?");
            pst.setInt(1, Integer.parseInt(documentid));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                no_of_copies = rs.getInt("no_of-copies");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (copies == null || copies == 0) {

            JOptionPane.showMessageDialog(this, "Please enter Quantity (quantiity cannot be greater than 3)");
            return false;

        }
        else if (no_of_copies < copies) {

            JOptionPane.showMessageDialog(this, "Documnet Quantity entered is out of stock we only have " + no_of_copies + " copies");
            return false;
        }
        else if (copies > 3) {
            JOptionPane.showMessageDialog(this, "Quantity cannot be greater than 3");
            return false;
        }
        else if (documentid.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Document ID");
            return false;
        }
        else if (usersid.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter User ID");
            return false;
        }
        return true;
    }

    public void updatedocCount() {

        Integer documentid = Integer.parseInt(txt_docid.getText());
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean AlreadyExist() {

        boolean Already_is = false;
        int documentid = Integer.parseInt(txt_docid.getText());
        int usersid = Integer.parseInt(txt_userid.getText());
        
        try {

            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM \"public\".\"RECORDS\" where \"docu_ID\" = ? and \"user_ID\" = ? and \"status\" = 'pending'";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, documentid);
            pst.setInt(2, usersid);
            //pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
           
            //Integer UpdateRowCount = pst.executeUpdate
            //System.out.println(UpdateRowCount);
            //System.out.println(String.valueOf(pst.execute()));
            if (rs.next()) {
                System.out.println("lms.IssueBookPage.AlreadyExist() IF ");
                Already_is = true;
            }else {
                System.err.println("here");
                Already_is = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("return statement ");
        return Already_is;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbIss_userdetails = new rojeru_san.complementos.RSTableMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbIss_docdetails = new rojeru_san.complementos.RSTableMetro();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_docid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_userid = new javax.swing.JTextField();
        dateIssue = new rojeru_san.componentes.RSDateChooser();
        lab_invuserid = new javax.swing.JLabel();
        datedue = new rojeru_san.componentes.RSDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lab_invdocid = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_copies = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        tbIss_userdetails.setBackground(new java.awt.Color(195, 206, 223));
        tbIss_userdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Pho Number "
            }
        ));
        tbIss_userdetails.setColorBackgoundHead(new java.awt.Color(138, 131, 121));
        tbIss_userdetails.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tbIss_userdetails.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tbIss_userdetails.setColorFilasBackgound1(new java.awt.Color(195, 206, 223));
        tbIss_userdetails.setColorFilasBackgound2(new java.awt.Color(195, 206, 223));
        tbIss_userdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbIss_userdetails.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        tbIss_userdetails.setRowHeight(40);
        jScrollPane1.setViewportView(tbIss_userdetails);

        tbIss_docdetails.setBackground(new java.awt.Color(195, 206, 223));
        tbIss_docdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Document ID", "Name", "Author", "No of Copies", "Category ID"
            }
        ));
        tbIss_docdetails.setColorBackgoundHead(new java.awt.Color(138, 131, 121));
        tbIss_docdetails.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tbIss_docdetails.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tbIss_docdetails.setColorFilasBackgound1(new java.awt.Color(195, 206, 223));
        tbIss_docdetails.setColorFilasBackgound2(new java.awt.Color(195, 206, 223));
        tbIss_docdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbIss_docdetails.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        tbIss_docdetails.setRowHeight(40);
        jScrollPane2.setViewportView(tbIss_docdetails);
        if (tbIss_docdetails.getColumnModel().getColumnCount() > 0) {
            tbIss_docdetails.getColumnModel().getColumn(3).setHeaderValue("No of Copies");
            tbIss_docdetails.getColumnModel().getColumn(4).setResizable(false);
            tbIss_docdetails.getColumnModel().getColumn(4).setHeaderValue("Category ID");
        }

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
        jLabel2.setText("Issue Document");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(295, 295, 295)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 830));

        jPanel2.setBackground(new java.awt.Color(223, 212, 195));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(138, 131, 121));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 100, 50));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Documnet ID ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 110, 30));

        txt_docid.setBackground(new java.awt.Color(195, 206, 223));
        txt_docid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_docidFocusLost(evt);
            }
        });
        jPanel2.add(txt_docid, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 250, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Issue Date");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 80, 30));

        txt_userid.setBackground(new java.awt.Color(195, 206, 223));
        txt_userid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_useridFocusLost(evt);
            }
        });
        jPanel2.add(txt_userid, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 250, -1));

        dateIssue.setBackground(new java.awt.Color(195, 206, 223));
        dateIssue.setColorBackground(new java.awt.Color(138, 131, 121));
        dateIssue.setColorForeground(new java.awt.Color(138, 131, 121));
        dateIssue.setPlaceholder("Select Issue Date");
        jPanel2.add(dateIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 250, -1));

        lab_invuserid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lab_invuserid.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lab_invuserid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 320, 30));

        datedue.setBackground(new java.awt.Color(195, 206, 223));
        datedue.setColorBackground(new java.awt.Color(138, 131, 121));
        datedue.setColorForeground(new java.awt.Color(138, 131, 121));
        datedue.setPlaceholder("Select Due Date");
        jPanel2.add(datedue, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, 250, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setText("Due Date ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 580, 80, 30));

        jButton1.setBackground(new java.awt.Color(195, 206, 223));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setText("Issue Document");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 690, -1, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("User ID ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 80, 30));

        lab_invdocid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lab_invdocid.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lab_invdocid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 330, 30));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setText("Quantity");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 80, 30));

        txt_copies.setBackground(new java.awt.Color(195, 206, 223));
        txt_copies.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_copiesFocusLost(evt);
            }
        });
        txt_copies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_copiesActionPerformed(evt);
            }
        });
        jPanel2.add(txt_copies, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 250, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 760, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        UserHomePage userhome = new UserHomePage();
        userhome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validateIssuedocumennt() == true) {

            if (AlreadyExist() == false) {
                System.err.println("in already exist IF");
                if (issuedocument() == true) {
                    JOptionPane.showMessageDialog(this, "Records Update! Document has been issued");
                    updatedocCount();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed ! Try later");
                }

            } else {
                JOptionPane.showMessageDialog(this, "You have already Issued Document");
            }


    }//GEN-LAST:event_jButton1ActionPerformed
    }
    private void txt_docidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_docidFocusLost
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbIss_docdetails.getModel();
        model.setRowCount(0);
        getDocdetails();

    }//GEN-LAST:event_txt_docidFocusLost

    private void txt_useridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_useridFocusLost
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbIss_userdetails.getModel();
        model.setRowCount(0);
        getUserdetails();
    }//GEN-LAST:event_txt_useridFocusLost

    private void txt_copiesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_copiesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_copiesFocusLost

    private void txt_copiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_copiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_copiesActionPerformed

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
            java.util.logging.Logger.getLogger(IssueBookPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBookPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBookPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBookPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBookPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser dateIssue;
    private rojeru_san.componentes.RSDateChooser datedue;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lab_invdocid;
    private javax.swing.JLabel lab_invuserid;
    private rojeru_san.complementos.RSTableMetro tbIss_docdetails;
    private rojeru_san.complementos.RSTableMetro tbIss_userdetails;
    private javax.swing.JTextField txt_copies;
    private javax.swing.JTextField txt_docid;
    private javax.swing.JTextField txt_userid;
    // End of variables declaration//GEN-END:variables
}
