/*
 * Name:        Matthew Farley
 * Student ID:  90045985
 * Date:        12 July 2013
  */

package pc3project;


import java.sql.*;
import java.io.*;

public class EmployeeFormFrame extends javax.swing.JFrame {
    String url;
    Connection con;
    Statement stmt = null;
    ResultSetTableModel model;
    String queryFramesql;
    
    
    
    /** Creates new form EmployeeFormFrame */
    public EmployeeFormFrame() {
        initComponents();
        this.setSize(1018, 780);
        displayMetaData();
        retrieveEmployeeDetails(true);
    }
    
    public void openDatabase(){
        if(!txtDatabase.getText().equals("")){
            url = "jdbc:odbc:" + txtDatabase.getText();
        }
        else{
            url = "jdbc:odbc:Employee";
        }
        
        try{
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            //Message for Debugging only
            System.out.println("Database " + url + " is connected\n");
        }
        catch(Exception e){
            
           msgMessage.showMessageDialog(this, "Problems connecting to " + url + "\n Exception: " + e.toString(), "Connection Error", javax.swing.JOptionPane.ERROR_MESSAGE);
       }
    }
    
    
    public void closeDatabase(){
        try{
            stmt.close();
            con.close();
            //Message for Debugging only
            System.out.println("Database " + url + "is closed\n");
        }
        catch(Exception e){
            msgMessage.showMessageDialog(this, "Problems closing connection to"+ url + "\n Exception: " + e.toString(), "Error Closing Connection", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void displayMetaData(){
    String displayMessage = "";
    openDatabase();
    
    try{
        
        ResultSet result = stmt.executeQuery("SELECT * FROM empDetail");
        ResultSetMetaData meta = result.getMetaData(); 
        DatabaseMetaData md = con.getMetaData();
        int columns = meta.getColumnCount();
        
        txtDBName.setText(md.getDatabaseProductName());
        txtDBVer.setText(md.getDatabaseProductVersion());
        txtDBDriver.setText(md.getDriverName());
        txtDBTableName.setText(meta.getTableName(1));
        
        for(int i = 1; i < columns +1; i++){
            displayMessage += meta.getColumnLabel(i) + "\t" + meta.getColumnTypeName(i) + "\n";
        }
        txtTableCols.setText(displayMessage);
    }
    catch(SQLException e){
        msgMessage.showMessageDialog(this, e.toString(), "SQL Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    closeDatabase();
}
    public void retrieveEmployeeDetails(boolean isStartUp){
        String sql;
        if(isStartUp){
        sql = "SELECT * FROM empDetail WHERE [Emp ID] IN(SELECT MIN([Emp ID]) FROM empDetail)";
        }
        else{
            if(txtEmpNo.getText().length()== 0 ){
                 msgMessage.showMessageDialog(this, "You must enter a valid Employee No", "Employee No Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                 return;
            }
            sql = "SELECT * FROM empDetail WHERE [Emp ID] = " + txtEmpNo.getText();
        }

        openDatabase();
        try{
                ResultSet rs = stmt.executeQuery(sql);
                if(!rs.next()){
                       
                       txtEmpName.setText("No record exists with this number");
                        txtEmpAddress.setText("");
                        txtEmpSuburb.setText("");
                        txtEmpPostcode.setText("");
                        txtEmpDOB.setText("");
                        txtEmpPhone.setText("");
                        txtEmpExt.setText("");
                        txtEmpMobile.setText("");
                        txtEmpEmail.setText("");
                } 
                else{
                do{    
                    txtEmpNo.setText(rs.getString(1));
                    txtEmpName.setText(rs.getString(2));
                    txtEmpAddress.setText(rs.getString(3));
                    txtEmpSuburb.setText(rs.getString(4));
                    txtEmpPostcode.setText(rs.getString(5));
                    txtEmpDOB.setText(rs.getString(6));
                    txtEmpPhone.setText(rs.getString(7));
                    txtEmpExt.setText(rs.getString(8));
                    txtEmpMobile.setText(rs.getString(9));
                    txtEmpEmail.setText(rs.getString(10));
                }
                    while (rs.next());
                }
            }
           catch(SQLException e){
                    msgMessage.showMessageDialog(this, e.toString(), "SQL Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            closeDatabase();
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgMessage = new javax.swing.JOptionPane();
        pnlEmployeeDetails = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtEmpNo = new javax.swing.JTextField();
        txtEmpEmail = new javax.swing.JTextField();
        txtEmpName = new javax.swing.JTextField();
        txtEmpAddress = new javax.swing.JTextField();
        txtEmpSuburb = new javax.swing.JTextField();
        txtEmpPostcode = new javax.swing.JTextField();
        txtEmpDOB = new javax.swing.JTextField();
        txtEmpPhone = new javax.swing.JTextField();
        txtEmpExt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtEmpMobile = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRetrieve = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        pnlQuery = new javax.swing.JPanel();
        txtDatabase = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtQuery = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        scrlEmployee = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        txtRecordNo = new javax.swing.JTextField();
        txtReportFile = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnReportFile = new javax.swing.JButton();
        pnlMetaData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTableCols = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDBTableName = new javax.swing.JTextField();
        txtDBName = new javax.swing.JTextField();
        txtDBVer = new javax.swing.JTextField();
        txtDBDriver = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee");
        getContentPane().setLayout(null);

        pnlEmployeeDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        pnlEmployeeDetails.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Email:");
        pnlEmployeeDetails.add(jLabel4);
        jLabel4.setBounds(10, 300, 80, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Employee no:");
        pnlEmployeeDetails.add(jLabel9);
        jLabel9.setBounds(10, 30, 80, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Name:");
        pnlEmployeeDetails.add(jLabel10);
        jLabel10.setBounds(10, 60, 80, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Address:");
        pnlEmployeeDetails.add(jLabel11);
        jLabel11.setBounds(10, 90, 80, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Suburb:");
        pnlEmployeeDetails.add(jLabel12);
        jLabel12.setBounds(10, 120, 80, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Postcode:");
        pnlEmployeeDetails.add(jLabel13);
        jLabel13.setBounds(10, 150, 80, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("DOB:");
        pnlEmployeeDetails.add(jLabel14);
        jLabel14.setBounds(10, 180, 80, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Phone (hm):");
        pnlEmployeeDetails.add(jLabel15);
        jLabel15.setBounds(10, 210, 80, 20);
        pnlEmployeeDetails.add(txtEmpNo);
        txtEmpNo.setBounds(100, 30, 110, 20);
        pnlEmployeeDetails.add(txtEmpEmail);
        txtEmpEmail.setBounds(100, 300, 180, 20);
        pnlEmployeeDetails.add(txtEmpName);
        txtEmpName.setBounds(100, 60, 260, 20);
        pnlEmployeeDetails.add(txtEmpAddress);
        txtEmpAddress.setBounds(100, 90, 260, 20);
        pnlEmployeeDetails.add(txtEmpSuburb);
        txtEmpSuburb.setBounds(100, 120, 180, 20);
        pnlEmployeeDetails.add(txtEmpPostcode);
        txtEmpPostcode.setBounds(100, 150, 110, 20);
        pnlEmployeeDetails.add(txtEmpDOB);
        txtEmpDOB.setBounds(100, 180, 150, 20);
        pnlEmployeeDetails.add(txtEmpPhone);
        txtEmpPhone.setBounds(100, 210, 110, 20);
        pnlEmployeeDetails.add(txtEmpExt);
        txtEmpExt.setBounds(100, 240, 110, 20);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Work Ext:");
        pnlEmployeeDetails.add(jLabel16);
        jLabel16.setBounds(10, 240, 80, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Mobile:");
        pnlEmployeeDetails.add(jLabel17);
        jLabel17.setBounds(10, 270, 80, 20);
        pnlEmployeeDetails.add(txtEmpMobile);
        txtEmpMobile.setBounds(100, 270, 130, 20);

        btnUpdate.setMnemonic('U');
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlEmployeeDetails.add(btnUpdate);
        btnUpdate.setBounds(280, 340, 90, 23);

        btnNew.setMnemonic('N');
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlEmployeeDetails.add(btnNew);
        btnNew.setBounds(10, 340, 90, 23);

        btnAdd.setMnemonic('A');
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlEmployeeDetails.add(btnAdd);
        btnAdd.setBounds(100, 340, 90, 23);

        btnRetrieve.setMnemonic('R');
        btnRetrieve.setText("Retrieve");
        btnRetrieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrieveActionPerformed(evt);
            }
        });
        pnlEmployeeDetails.add(btnRetrieve);
        btnRetrieve.setBounds(190, 340, 90, 23);

        btnDelete.setMnemonic('D');
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlEmployeeDetails.add(btnDelete);
        btnDelete.setBounds(370, 340, 80, 23);

        getContentPane().add(pnlEmployeeDetails);
        pnlEmployeeDetails.setBounds(20, 60, 460, 380);

        pnlQuery.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Query", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        pnlQuery.setLayout(null);
        pnlQuery.add(txtDatabase);
        txtDatabase.setBounds(90, 30, 150, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Query:");
        pnlQuery.add(jLabel3);
        jLabel3.setBounds(20, 60, 50, 14);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Database: ");
        pnlQuery.add(jLabel18);
        jLabel18.setBounds(20, 30, 70, 14);
        pnlQuery.add(txtQuery);
        txtQuery.setBounds(90, 60, 390, 20);

        btnSubmit.setMnemonic('S');
        btnSubmit.setText("Submit Query");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        pnlQuery.add(btnSubmit);
        btnSubmit.setBounds(340, 90, 140, 23);

        scrlEmployee.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrlEmployee.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrlEmployee.setViewportView(tblEmployee);

        pnlQuery.add(scrlEmployee);
        scrlEmployee.setBounds(10, 120, 480, 430);

        txtRecordNo.setEditable(false);
        pnlQuery.add(txtRecordNo);
        txtRecordNo.setBounds(370, 570, 110, 20);
        pnlQuery.add(txtReportFile);
        txtReportFile.setBounds(100, 630, 250, 20);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Number of Records:");
        pnlQuery.add(jLabel19);
        jLabel19.setBounds(250, 570, 110, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Report File:");
        pnlQuery.add(jLabel20);
        jLabel20.setBounds(20, 630, 64, 20);

        btnReportFile.setMnemonic('F');
        btnReportFile.setText("Report File");
        btnReportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportFileActionPerformed(evt);
            }
        });
        pnlQuery.add(btnReportFile);
        btnReportFile.setBounds(380, 630, 100, 23);

        getContentPane().add(pnlQuery);
        pnlQuery.setBounds(490, 60, 500, 670);

        pnlMetaData.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MetaData", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        pnlMetaData.setLayout(null);

        txtTableCols.setColumns(20);
        txtTableCols.setEditable(false);
        txtTableCols.setRows(5);
        jScrollPane1.setViewportView(txtTableCols);

        pnlMetaData.add(jScrollPane1);
        jScrollPane1.setBounds(120, 150, 270, 110);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Table Cols:");
        pnlMetaData.add(jLabel2);
        jLabel2.setBounds(10, 140, 80, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Database Ver: ");
        pnlMetaData.add(jLabel5);
        jLabel5.setBounds(10, 50, 110, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Database Driver: ");
        pnlMetaData.add(jLabel6);
        jLabel6.setBounds(10, 80, 110, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Table Name:");
        pnlMetaData.add(jLabel7);
        jLabel7.setBounds(10, 110, 110, 20);

        txtDBTableName.setEditable(false);
        pnlMetaData.add(txtDBTableName);
        txtDBTableName.setBounds(120, 110, 170, 20);

        txtDBName.setEditable(false);
        pnlMetaData.add(txtDBName);
        txtDBName.setBounds(120, 20, 100, 20);

        txtDBVer.setEditable(false);
        pnlMetaData.add(txtDBVer);
        txtDBVer.setBounds(120, 50, 100, 20);

        txtDBDriver.setEditable(false);
        pnlMetaData.add(txtDBDriver);
        txtDBDriver.setBounds(120, 80, 280, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Database Name: ");
        pnlMetaData.add(jLabel8);
        jLabel8.setBounds(10, 20, 110, 20);

        getContentPane().add(pnlMetaData);
        pnlMetaData.setBounds(20, 450, 460, 280);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("Employee Form");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 200, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
    queryFramesql = txtQuery.getText();
    openDatabase();
    String rowCount; 
    ResultSet rs = null;
    
    try{
        rs = stmt.executeQuery(queryFramesql);
        model = new ResultSetTableModel(rs, msgMessage);
        tblEmployee.setModel(model);
        txtRecordNo.setText(Integer.toString(model.getRowCount()));
    
        }
    catch(SQLException e){
        msgMessage.showMessageDialog(this, e.toString(), "SQL Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    closeDatabase();
}//GEN-LAST:event_btnSubmitActionPerformed

private void btnRetrieveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrieveActionPerformed
    retrieveEmployeeDetails(false);
}//GEN-LAST:event_btnRetrieveActionPerformed

private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
     String sql;
        
        sql = "SELECT MAX([Emp ID])+1 FROM empDetail";
       
        openDatabase();
        
        try{
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    txtEmpNo.setText(rs.getString(1));
                    txtEmpName.setText("");
                    txtEmpAddress.setText("");
                    txtEmpSuburb.setText("");
                    txtEmpPostcode.setText("");
                    txtEmpDOB.setText("");
                    txtEmpPhone.setText("");
                    txtEmpExt.setText("");
                    txtEmpMobile.setText("");
                    txtEmpEmail.setText("");
                }
            }
                catch(SQLException e){
                    msgMessage.showMessageDialog(this, e.toString(), "SQL Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            closeDatabase();
}//GEN-LAST:event_btnNewActionPerformed

private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    String sql;
    openDatabase();
    
    sql = "INSERT INTO empDetail VALUES(";
    	sql += txtEmpNo.getText() + ",";
        sql += "'" + txtEmpName.getText() + "',";
        sql += "'" + txtEmpAddress.getText() + "',";
        sql += "'" + txtEmpSuburb.getText()+ "',";
        sql +=  "'" + txtEmpPostcode.getText() + "',";
        sql += "'" + txtEmpDOB.getText() + "',";
        sql +=  "'" + txtEmpPhone.getText() + "',";
        sql += "'" + txtEmpExt.getText() + "',";
        sql +=  "'" + txtEmpMobile.getText() + "',";
        sql += "'" + txtEmpEmail.getText() + "')";
        
        try{
            stmt.executeUpdate(sql);
            msgMessage.showMessageDialog(this, "Record inserted into the database", "Record Added", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            msgMessage.showMessageDialog(this, e.toString(), "Insertion Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        closeDatabase();
}//GEN-LAST:event_btnAddActionPerformed

private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

    String sql;
    //Checking for valid ID
    if(txtEmpNo.getText().length()== 0 ){
                 msgMessage.showMessageDialog(this, "You must enter a valid Employee No", "Employee No Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                 return;
            }
     sql = "SELECT * FROM empDetail WHERE [Emp ID] = " + txtEmpNo.getText();
     openDatabase();
      
     try{
           ResultSet rs = stmt.executeQuery(sql);
           if(!rs.next()){
                msgMessage.showMessageDialog(this, "There are no matching records for this Employee No", "No Matching Records", javax.swing.JOptionPane.ERROR_MESSAGE);
                } 
           else{
                   
    
        sql = "UPDATE empDetail Set";
    	sql += "[Emp ID] =" + txtEmpNo.getText() + ",";
        sql += "[Emp Name] = '" + txtEmpName.getText() + "',";
        sql += "Address = '" + txtEmpAddress.getText() + "',";
        sql += "Suburb = '" + txtEmpSuburb.getText()+ "',";
        sql +=  "Postcode = '" + txtEmpPostcode.getText() + "',";
        sql += "DOB = '" + txtEmpDOB.getText() + "',";
        sql +=  "[Home Phone] = '" + txtEmpPhone.getText() + "',";
        sql += "Extension = '" + txtEmpExt.getText() + "',";
        sql +=  "Mobile = '" + txtEmpMobile.getText() + "',";
        sql += "Email = '" + txtEmpEmail.getText() + "'";
        sql += "WHERE [Emp ID] = " + txtEmpNo.getText();
        
        try{
            stmt.executeUpdate(sql);
            msgMessage.showMessageDialog(this, "Record updated in the database", "Record Updated", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            msgMessage.showMessageDialog(this, e.toString(), "Update Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
           }
     }
     catch(SQLException e){
         msgMessage.showMessageDialog(this, e.toString(), "Update Error", javax.swing.JOptionPane.ERROR_MESSAGE);
     }
     
        closeDatabase();
}//GEN-LAST:event_btnUpdateActionPerformed

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         String sql;
    //Checking for valid ID
    if(txtEmpNo.getText().length()== 0 ){
                 msgMessage.showMessageDialog(this, "You must enter a valid Employee No", "Employee No Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                 return;
            }
     sql = "SELECT * FROM empDetail WHERE [Emp ID] = " + txtEmpNo.getText();
     openDatabase();
      
     try{
           ResultSet rs = stmt.executeQuery(sql);
           if(!rs.next()){
                msgMessage.showMessageDialog(this, "There are no matching records for this Employee No", "No Matching Records", javax.swing.JOptionPane.ERROR_MESSAGE);
                } 
           else{
                   
    
        sql = "DELETE FROM empDetail WHERE [Emp ID] =" + txtEmpNo.getText();
               
        try{
            stmt.executeUpdate(sql);
            txtEmpNo.setText("");
            txtEmpName.setText("");
            txtEmpAddress.setText("");
            txtEmpSuburb.setText("");
            txtEmpPostcode.setText("");
            txtEmpDOB.setText("");
            txtEmpPhone.setText("");
            txtEmpExt.setText("");
            txtEmpMobile.setText("");
            txtEmpEmail.setText("");
            msgMessage.showMessageDialog(this, "Record removed from database", "Record Deleted", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            msgMessage.showMessageDialog(this, e.toString(), "Deletion Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
           }
     }
     catch(SQLException e){
         msgMessage.showMessageDialog(this, e.toString(), "Deletion Error", javax.swing.JOptionPane.ERROR_MESSAGE);
     }

        closeDatabase();
}//GEN-LAST:event_btnDeleteActionPerformed

private void btnReportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportFileActionPerformed
    String fileText = ""; 
    FileWriter fw;
    BufferedWriter bw;
       
    openDatabase();
    ResultSet rs = null;
    
    //Setting the text
    try{
        rs = stmt.executeQuery(queryFramesql);
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount(); 
        //Creating String
    
        try{
            fw = new FileWriter(txtReportFile.getText());
            bw = new BufferedWriter(fw);

        for( int col = 1; col < colCount+1; col++){
            bw.write(meta.getColumnLabel(col) + " | ");
        }
        bw.newLine();

            while(rs.next()){
                for(int col = 1; col < colCount + 1; col++ ){
                   bw.write(rs.getString(col) + " | ");
                    
            }
                bw.newLine();
            }

        bw.close();
        fw.close();

        }
        catch(IOException e){
        msgMessage.showMessageDialog(this, e.toString(), "File I/O Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
             
    }
  catch(SQLException e){
        msgMessage.showMessageDialog(this, e.toString(), "SQL Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    closeDatabase();

}//GEN-LAST:event_btnReportFileActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeFormFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnReportFile;
    private javax.swing.JButton btnRetrieve;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JOptionPane msgMessage;
    private javax.swing.JPanel pnlEmployeeDetails;
    private javax.swing.JPanel pnlMetaData;
    private javax.swing.JPanel pnlQuery;
    private javax.swing.JScrollPane scrlEmployee;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtDBDriver;
    private javax.swing.JTextField txtDBName;
    private javax.swing.JTextField txtDBTableName;
    private javax.swing.JTextField txtDBVer;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JTextField txtEmpAddress;
    private javax.swing.JTextField txtEmpDOB;
    private javax.swing.JTextField txtEmpEmail;
    private javax.swing.JTextField txtEmpExt;
    private javax.swing.JTextField txtEmpMobile;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpNo;
    private javax.swing.JTextField txtEmpPhone;
    private javax.swing.JTextField txtEmpPostcode;
    private javax.swing.JTextField txtEmpSuburb;
    private javax.swing.JTextField txtQuery;
    private javax.swing.JTextField txtRecordNo;
    private javax.swing.JTextField txtReportFile;
    private javax.swing.JTextArea txtTableCols;
    // End of variables declaration//GEN-END:variables

}