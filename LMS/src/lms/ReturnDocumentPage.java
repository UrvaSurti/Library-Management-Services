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
public class ReturnDocumentPage extends javax.swing.JFrame {

    /**
     * Creates new form IssueBookPage
     */
    String doc_name;
    String user_name;
    Integer copies;
    Integer documentid;
    Integer usersid;
    DefaultTableModel model;

    public ReturnDocumentPage() {
        initComponents();
    }

    public void getRecordsdetails() {

        try {
            System.out.println("lms.ReturnDocumentPage.getRecordsdetails()");
            documentid = Integer.parseInt(txtre_docid.getText());
            usersid = Integer.parseInt(txtre_userid.getText());

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"RECORDS\"  where \"RECORDS\".\"docu_ID\" = ? and \"RECORDS\".\"user_ID\" = ?");
            pst.setInt(1, documentid);
            pst.setInt(2, usersid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("lms.ReturnDocumentPage.getRecordsdetails() If ststement");
                String docu_ID = rs.getString("docu_ID");
                String user_ID = rs.getString("user_ID");
                copies = rs.getInt("copies_borrow");
                String issue_date = rs.getString("issue_date");
                String due_date = rs.getString("due_date");
                String status = rs.getString("status");
                //String eidtor = rs.getString("editor");
                //String contributor = rs.getString("contributor");

                Object[] obj = {docu_ID, user_ID, copies, issue_date, due_date, status};
                model = (DefaultTableModel) tbret_recordetails.getModel();
                model.addRow(obj);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validaterecorddocumennt() {
        //copies = Integer.parseInt(txt_copies.getText());
        String docid = txtre_docid.getText();
        String userid = txtre_userid.getText();
        //int no_of_copies = 0;
        
        if (docid.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Document ID");
            return false;
        }
        if (userid.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter User ID");
            return false;
        }
        return true;
    }
    
    public boolean returndocument(){
        
        boolean returned_is = false;
        documentid = Integer.parseInt(txtre_docid.getText());
        usersid = Integer.parseInt(txtre_userid.getText());
        
        try {

            Connection con = DBConnection.getConnection();
            String sql = "Update \"public\".\"RECORDS\" set \"status\" = ? where \"docu_ID\" = ? and \"user_ID\" = ? and \"status\" = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, "returned");
            pst.setInt(2, documentid);
            pst.setInt(3, usersid);
            pst.setString(4, "pending");
            

            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {

                returned_is = true;
            } else {

                returned_is = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return returned_is;
    }
    
    public void updatedocCount(){
        
        Integer documentid = Integer.parseInt(txtre_docid.getText());
        try {
            Connection con = DBConnection.getConnection();
            System.out.println(copies);
            
            PreparedStatement pst = con.prepareStatement("Update \"public\".\"DOCUMENTS\" set \"no_of-copies\" = \"no_of-copies\" + "+ copies +" where \"DOCUMENTS\".\"doc_ID\" = ?");
            pst.setInt(1, documentid);
            
            
            int UpdateRowCount = pst.executeUpdate();

            if (UpdateRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Quantity Updated");
                
            } else {

                JOptionPane.showMessageDialog(this, "Quantity Updated");
            }
        }catch (Exception e) {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbret_recordetails = new rojeru_san.complementos.RSTableMetro();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtre_docid = new javax.swing.JTextField();
        txtre_userid = new javax.swing.JTextField();
        lab_invuserid = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lab_invdocid = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        tbret_recordetails.setBackground(new java.awt.Color(195, 206, 223));
        tbret_recordetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doc ID", "User ID", "Quantity", "Issue date", "Due Date", "Status"
            }
        ));
        tbret_recordetails.setColorBackgoundHead(new java.awt.Color(138, 131, 121));
        tbret_recordetails.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tbret_recordetails.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tbret_recordetails.setColorFilasBackgound1(new java.awt.Color(195, 206, 223));
        tbret_recordetails.setColorFilasBackgound2(new java.awt.Color(195, 206, 223));
        tbret_recordetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbret_recordetails.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        tbret_recordetails.setRowHeight(40);
        jScrollPane1.setViewportView(tbret_recordetails);

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
        jLabel2.setText("Return Document");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(295, 295, 295)
                .addComponent(jLabel2)
                .addContainerGap(296, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
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
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 100, 50));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Documnet ID ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 110, 30));

        txtre_docid.setBackground(new java.awt.Color(195, 206, 223));
        txtre_docid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtre_docidFocusLost(evt);
            }
        });
        jPanel2.add(txtre_docid, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 250, -1));

        txtre_userid.setBackground(new java.awt.Color(195, 206, 223));
        txtre_userid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtre_useridFocusLost(evt);
            }
        });
        jPanel2.add(txtre_userid, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 250, -1));

        lab_invuserid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lab_invuserid.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lab_invuserid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 320, 30));

        jButton1.setBackground(new java.awt.Color(195, 206, 223));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setText("Return Document");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, -1, 40));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("User ID ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 80, 30));

        lab_invdocid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lab_invdocid.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lab_invdocid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 330, 30));

        jButton2.setBackground(new java.awt.Color(195, 206, 223));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("Find ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, -1, 40));

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
        if (returndocument() == true) {
            JOptionPane.showMessageDialog(this, "Records Update! Document has been issued");
            updatedocCount();
            //updatedocCount();
        } else {
            JOptionPane.showMessageDialog(this, "Failed ! Try later");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtre_docidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtre_docidFocusLost
        // TODO add your handling code here:


    }//GEN-LAST:event_txtre_docidFocusLost

    private void txtre_useridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtre_useridFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_txtre_useridFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        if (validaterecorddocumennt() == true){
            
            getRecordsdetails();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnDocumentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnDocumentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnDocumentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnDocumentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnDocumentPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_invdocid;
    private javax.swing.JLabel lab_invuserid;
    private rojeru_san.complementos.RSTableMetro tbret_recordetails;
    private javax.swing.JTextField txtre_docid;
    private javax.swing.JTextField txtre_userid;
    // End of variables declaration//GEN-END:variables
}
