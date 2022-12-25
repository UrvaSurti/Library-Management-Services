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
public class UpdateDocument extends javax.swing.JFrame {

    /**
     * Creates new form UpdateUser
     */
    int categoryID;
    boolean is_admin;
    DefaultTableModel model;
    int UpdateRowCount;

    public UpdateDocument() {
        initComponents();
        UpdateComboBox();
    }

    public void UpdateComboBox() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            //PreparedStatement pst = con.prepareStatement("SELECT category_name FROM \"public\".\"CATEGORY\"");
            ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"CATEGORY\"");
            while (rs.next()) {
                jComboBoxCat.addItem(rs.getString("category_name"));
                categoryID = rs.getInt("category_ID");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addDocument() {
        boolean add_bool = false;
        String name = txtU_docname.getText();
        //String password = txt_password.getText();
        String author = txtU_author.getText();
        String copies = txtU_copies.getText();
        String artname = txtU_artname.getText();
        String issues = txtU_issue.getText();
        String editor = txtU_editor.getText();
        String contributor = txtU_Contributor.getText();
        String publisher = txtU_publisher.getText();
        java.util.Date pubdateu = dateIssue.getDatoFecha();

        Long l1 = pubdateu.getTime();
        java.sql.Date pubdates = new java.sql.Date(l1);

        try {
            if (categoryID == 1) {
                System.out.println("here in add method");
                Connection con = DBConnection.getConnection();
                String sql = "insert into public.\"DOCUMENTS\"(doc_name, doc_author, pub_date, \"no_of-copies\", \"cat_ID\") values(?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, name);
                pst.setString(2, author);
                pst.setDate(3, pubdates);
                pst.setInt(4, Integer.parseInt(copies));
                pst.setInt(5, categoryID);

                UpdateRowCount = pst.executeUpdate();
            }
            if (categoryID == 2) {
                Connection con = DBConnection.getConnection();
                String sql = "insert into public.\"DOCUMENTS\"(doc_name, pub_date, \"no_of-copies\", article_name,  editor, \"cat_ID\") values(?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, name);
                //pst.setString(2, author);
                pst.setDate(2, pubdates);
                pst.setInt(3, Integer.parseInt(copies));
                pst.setString(4, artname);
                //pst.setString(4, issues);
                pst.setString(5, editor);
                //pst.setString(4, publisher);
                pst.setInt(6, categoryID);
                UpdateRowCount = pst.executeUpdate();

            }
            if (categoryID == 3) {

                Connection con = DBConnection.getConnection();
                String sql = "insert into public.\"DOCUMENTS\"(doc_name,  pub_date, \"no_of-copies\", article_name,  editor, contributor, \"cat_ID\") values(?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, name);
                //pst.setString(2, author);
                pst.setDate(2, pubdates);
                pst.setInt(3, Integer.parseInt(copies));
                pst.setString(4, artname);
                //pst.setString(4, issues);
                pst.setString(5, editor);
                pst.setString(6, contributor);
                pst.setInt(7, categoryID);

                UpdateRowCount = pst.executeUpdate();
            }
            if (categoryID > 3) {

                Connection con = DBConnection.getConnection();
                String sql = "insert into public.\"DOCUMENTS\"(doc_name, doc_author, pub_date, \"no_of-copies\", article_name,  editor, contributor, \"cat_ID\") values(?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, name);
                pst.setString(2, author);
                pst.setDate(3, pubdates);
                pst.setInt(3, Integer.parseInt(copies));
                pst.setString(5, artname);
                //pst.setString(6, issues);
                pst.setString(6, editor);
                pst.setString(7, contributor);
                pst.setInt(8, categoryID);

                UpdateRowCount = pst.executeUpdate();
            }

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

    /*public boolean validateUserDetails() {
        String name = txtU_docname.getText();
        //String password = txt_password.getText();
        String contact = txtU_author.getText();

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
            JOptionPane.showMessageDialog(this, "Please enter Contact unumber");
            return false;
        }

        return true;
    }*/
    public void getDocdetailstable() {

        String docid = txtU_docid.getText();

        //String name = txtU_username.getText();
        //String password = txt_password.getText();
        //String contact = txtU_contact.getText();
        if (docid.equals("")) {
            if (this.model != null) {
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
                ResultSet rs = st.executeQuery("SELECT * FROM \"public\".\"DOCUMENTS\"");

                while (rs.next()) {
                    int i = 0;
                    System.out.println("I am here" + i);
                    String doc_ID = rs.getString("doc_ID");
                    String doc_name = rs.getString("doc_name");
                    String doc_author = rs.getString("doc_author");
                    //String pub_date = rs.getString("pub_date");
                    String no_of_copies = rs.getString("no_of-copies");
                    String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {doc_ID, doc_name, doc_author, no_of_copies, cat_ID};
                    model = (DefaultTableModel) tbUpd_docdetails.getModel();
                    model.addRow(obj);
                    i = i + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            //String row = this.model;
            if (this.model != null) {
                System.out.println(this.model);
                model.setRowCount(0);

            }

            try {
                System.out.println(this.model);
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM \"public\".\"DOCUMENTS\" where \"doc_ID\" = ?");
                pst.setInt(1, Integer.parseInt(docid));
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //System.out.println("SearchDocument.search()");
                    String doc_ID = rs.getString("doc_ID");
                    String doc_name = rs.getString("doc_name");
                    String doc_author = rs.getString("doc_author");
                    //String pub_date = rs.getString("pub_date");
                    String no_of_copies = rs.getString("no_of-copies");
                    String cat_ID = rs.getString("cat_ID");
                    //String article_name = rs.getString("article_name");
                    //String eidtor = rs.getString("editor");
                    //String contributor = rs.getString("contributor");

                    Object[] obj = {doc_ID, doc_name, doc_author, no_of_copies, cat_ID};
                    model = (DefaultTableModel) tbUpd_docdetails.getModel();
                    model.addRow(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*public boolean UpdateUsers() {

        boolean upd_bool = false;
        String userid = txtU_docid.getText();
        String name = txtU_docname.getText();
        //String password = txt_password.getText();
        String contact = txtU_author.getText();

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
    }*/
    public boolean deletedocs() {
        boolean add_bool = false;
        //String name = txtU_username.getText();
        //String password = txt_password.getText();
        //String contact = txtU_contact.getText();
        String docid = txtU_docid.getText();

        try {

            Connection con = DBConnection.getConnection();
            String sql = "delete FROM \"public\".\"DOCUMENTS\" where \"doc_ID\" = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(docid));
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
        tbUpd_docdetails = new rojeru_san.complementos.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtU_docname = new javax.swing.JTextField();
        txtU_author = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtU_docid = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtU_copies = new javax.swing.JTextField();
        jComboBoxCat = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtU_artname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtU_issue = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtU_editor = new javax.swing.JTextField();
        txtU_Contributor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtU_publisher = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        dateIssue = new rojeru_san.componentes.RSDateChooser();
        jButton4 = new javax.swing.JButton();

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
        jLabel2.setText("Update Document ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(268, Short.MAX_VALUE)
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

        tbUpd_docdetails.setBackground(new java.awt.Color(195, 206, 223));
        tbUpd_docdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doc ID", "Name", "Author", "Copies", "Category ID"
            }
        ));
        tbUpd_docdetails.setColorBackgoundHead(new java.awt.Color(138, 131, 121));
        tbUpd_docdetails.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tbUpd_docdetails.setColorBordeHead(new java.awt.Color(153, 153, 153));
        tbUpd_docdetails.setColorFilasBackgound1(new java.awt.Color(195, 206, 223));
        tbUpd_docdetails.setColorFilasBackgound2(new java.awt.Color(195, 206, 223));
        tbUpd_docdetails.setColorSelBackgound(new java.awt.Color(255, 102, 102));
        tbUpd_docdetails.setFont(new java.awt.Font("Calibri", 1, 8)); // NOI18N
        tbUpd_docdetails.setRowHeight(40);
        tbUpd_docdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUpd_docdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUpd_docdetails);

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
        jLabel4.setText("Name");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Author");

        txtU_docname.setBackground(new java.awt.Color(195, 206, 223));
        txtU_docname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_docnameFocusLost(evt);
            }
        });
        txtU_docname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_docnameActionPerformed(evt);
            }
        });

        txtU_author.setBackground(new java.awt.Color(195, 206, 223));
        txtU_author.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_authorFocusLost(evt);
            }
        });
        txtU_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_authorActionPerformed(evt);
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
        jButton3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton3.setText("Add Category");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("Doc ID");

        txtU_docid.setBackground(new java.awt.Color(195, 206, 223));
        txtU_docid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_docidFocusLost(evt);
            }
        });
        txtU_docid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_docidActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("Category");

        txtU_copies.setBackground(new java.awt.Color(195, 206, 223));
        txtU_copies.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_copiesFocusLost(evt);
            }
        });
        txtU_copies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_copiesActionPerformed(evt);
            }
        });

        jComboBoxCat.setBackground(new java.awt.Color(195, 206, 223));
        jComboBoxCat.setToolTipText("");
        jComboBoxCat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxCatMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setText("Copies");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setText("Article Name");

        txtU_artname.setBackground(new java.awt.Color(195, 206, 223));
        txtU_artname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_artnameFocusLost(evt);
            }
        });
        txtU_artname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_artnameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setText("Issue");

        txtU_issue.setBackground(new java.awt.Color(195, 206, 223));
        txtU_issue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_issueFocusLost(evt);
            }
        });
        txtU_issue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_issueActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setText("Editor");

        txtU_editor.setBackground(new java.awt.Color(195, 206, 223));
        txtU_editor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_editorFocusLost(evt);
            }
        });
        txtU_editor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_editorActionPerformed(evt);
            }
        });

        txtU_Contributor.setBackground(new java.awt.Color(195, 206, 223));
        txtU_Contributor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_ContributorFocusLost(evt);
            }
        });
        txtU_Contributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_ContributorActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setText("Contributor");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setText("Published Date");

        txtU_publisher.setBackground(new java.awt.Color(195, 206, 223));
        txtU_publisher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtU_publisherFocusLost(evt);
            }
        });
        txtU_publisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtU_publisherActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setText("Publishers");

        dateIssue.setBackground(new java.awt.Color(195, 206, 223));
        dateIssue.setColorBackground(new java.awt.Color(138, 131, 121));
        dateIssue.setColorForeground(new java.awt.Color(138, 131, 121));
        dateIssue.setPlaceholder("Select Issue Date");

        jButton4.setBackground(new java.awt.Color(195, 206, 223));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(11, 216, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtU_author, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtU_docname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(71, 71, 71)
                                        .addComponent(txtU_docid, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(136, 136, 136))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtU_Contributor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtU_editor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtU_issue, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(20, 20, 20)
                                        .addComponent(txtU_artname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtU_publisher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtU_copies, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(93, 93, 93)
                        .addComponent(jButton4)
                        .addGap(67, 67, 67)
                        .addComponent(jButton2)
                        .addGap(107, 107, 107)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtU_docid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU_docname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtU_author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtU_copies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtU_artname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtU_issue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtU_editor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtU_Contributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtU_publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateIssue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 840, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        AdminHomePage adminhome = new AdminHomePage();
        adminhome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtU_docnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_docnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_docnameFocusLost

    private void txtU_docnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_docnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_docnameActionPerformed

    private void txtU_authorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_authorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_authorFocusLost

    private void txtU_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_authorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //if (validateUserDetails() == true) {

        if (addDocument() == true) {
            JOptionPane.showMessageDialog(this, "Document added to the table");
            model.setRowCount(0);
            System.out.println("before get call");
            getDocdetailstable();

        } else {
            JOptionPane.showMessageDialog(this, "Failed to add Document to the table");
        }
        //}

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        /*if (validateUserDetails() == true) {

            if (Updatedoc() == true) {
                JOptionPane.showMessageDialog(this, "user details updated  to the table");
                model.setRowCount(0);
                getUserdetailstable();

            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user to the table");
            }
       // }*/
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //if (validateUserDetails() == true) {
        CategoryPage catpage = new CategoryPage();
        catpage.setVisible(true);
        //this.dispose();
        //}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtU_docidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_docidFocusLost
        // TODO add your handling code here:
        System.out.println();

        getDocdetailstable();
        //model.setRowCount(0);

    }//GEN-LAST:event_txtU_docidFocusLost

    private void txtU_docidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_docidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_docidActionPerformed

    private void tbUpd_docdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUpd_docdetailsMouseClicked
        // TODO add your handling code here:
        int rowNo = tbUpd_docdetails.getSelectedRow();
        TableModel model = tbUpd_docdetails.getModel();

        txtU_docid.setText(model.getValueAt(rowNo, 0).toString());
        txtU_docname.setText(model.getValueAt(rowNo, 1).toString());
        //String password = txt_password.getText();
        txtU_author.setText(model.getValueAt(rowNo, 2).toString());
        txtU_copies.setText(model.getValueAt(rowNo, 3).toString());


    }//GEN-LAST:event_tbUpd_docdetailsMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtU_copiesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_copiesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_copiesFocusLost

    private void txtU_copiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_copiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_copiesActionPerformed

    private void txtU_artnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_artnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_artnameFocusLost

    private void txtU_artnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_artnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_artnameActionPerformed

    private void txtU_issueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_issueFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_issueFocusLost

    private void txtU_issueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_issueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_issueActionPerformed

    private void txtU_editorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_editorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_editorFocusLost

    private void txtU_editorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_editorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_editorActionPerformed

    private void txtU_ContributorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_ContributorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_ContributorFocusLost

    private void txtU_ContributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_ContributorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_ContributorActionPerformed

    private void txtU_publisherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtU_publisherFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_publisherFocusLost

    private void txtU_publisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtU_publisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtU_publisherActionPerformed

    private void jComboBoxCatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxCatMouseClicked
        String catname = jComboBoxCat.getSelectedItem().toString();

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pst = con.prepareStatement("SELECT \"category_ID\" FROM \"public\".\"CATEGORY\" where \"category_name\" = ?");
            pst.setString(1, catname);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                categoryID = rs.getInt("category_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBoxCatMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateDocument.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateDocument.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateDocument.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateDocument.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateDocument().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private rojeru_san.componentes.RSDateChooser dateIssue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro tbUpd_docdetails;
    private javax.swing.JTextField txtU_Contributor;
    private javax.swing.JTextField txtU_artname;
    private javax.swing.JTextField txtU_author;
    private javax.swing.JTextField txtU_copies;
    private javax.swing.JTextField txtU_docid;
    private javax.swing.JTextField txtU_docname;
    private javax.swing.JTextField txtU_editor;
    private javax.swing.JTextField txtU_issue;
    private javax.swing.JTextField txtU_publisher;
    // End of variables declaration//GEN-END:variables
}
