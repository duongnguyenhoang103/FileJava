/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.enterprise.viewer.api;

import java.util.Hashtable;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;

/**
 *
 * @author khanguct
 */
public interface IEnterpriseViewer {
    
    public JPanel getEnterpriseViewer();
    
    public Lookup getEnterpriseLookup();
    
    public double getLevelNumber();
    
    public JTable getTable();
    
    public Hashtable getTableHeader();
    
}
