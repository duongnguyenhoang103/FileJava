/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.operation.viewer.api;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;

/**
 *
 * @author HKT01
 */
public interface IOperationCreater {
    
    public JPanel getOperationCreater();
    
    public List<JTable> getTables();
    
    public Lookup getOperationLookup();
    
    public double getLevelNumber();
    
}
