/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.report.api;

import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DONGTAM
 */
public interface IReportManager {
    public void setReportManager(HashMap hasMap, TableModel model); 
    public DefaultTableModel getModel(JTable table);
    public HashMap getHashMap(String[] keys,String [] values);    
    public void exportReport();
   
    
}
