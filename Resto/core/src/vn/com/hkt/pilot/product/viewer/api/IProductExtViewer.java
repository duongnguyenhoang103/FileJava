/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.product.viewer.api;

import java.util.Hashtable;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;

/**
 *
 * @author HKT01
 */
public interface IProductExtViewer {
    
    public JPanel getProductExtViewer();
    
    public Lookup getProductExtViewerLookup();
    
    public double getLevelNumber();
    
    public JTable getTable();
    
    public Hashtable getTableHeader();
    
}
