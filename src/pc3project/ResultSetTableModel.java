/*
 * Name:        Matthew Farley
 * Student ID:  90045985
 * Date:        12 July 2013
  */

package pc3project;


import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class ResultSetTableModel extends AbstractTableModel {
    private ArrayList data;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private javax.swing.JOptionPane msgMessage;
    
    public ResultSetTableModel(ResultSet aResultSet, javax.swing.JOptionPane msgMessage){
        this.msgMessage = msgMessage;
        
        try{
            rs = aResultSet;
            rsmd = rs.getMetaData();
            data = new ArrayList();
            int cols = rsmd.getColumnCount();
            while(rs.next()){
                Object[] row = new Object[cols];
                for(int i = 0; i < row.length; i++){
                    row[i] = rs.getObject(i + 1);
                }
                data.add(row);
            }
        }
        catch(SQLException e){
            msgMessage.showMessageDialog(null, e.toString(), "SQL Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
  
        public Object getValueAt(int row, int col){
            if(row < data.size()){
                return ((Object[])data.get(row))[col];
            }
            else{
                return null;
            }
        }
        
        public int getRowCount(){
            return data.size();
        }
        
        public String getColumnName(int count){
            try{
                return rsmd.getColumnName(++count);
            }
            catch(SQLException e){
                msgMessage.showMessageDialog(null, e.toString(), "SQL Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
                return "";
            }
        }
        
        public int getColumnCount(){
            try{
                return rsmd.getColumnCount();
            }
            catch(SQLException e){
                msgMessage.showMessageDialog(null, e.toString(), "SQL Exception", javax.swing.JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            
        }
}
