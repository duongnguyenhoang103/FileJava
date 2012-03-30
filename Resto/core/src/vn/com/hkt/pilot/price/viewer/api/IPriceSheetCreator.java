/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.price.viewer.api;

import javax.swing.JPanel;
import org.openide.util.Lookup;

/**
 *
 * @author HKT01
 */
public interface IPriceSheetCreator {
    
    public JPanel getPriceCreator();
    
    public Lookup getPriceLookup();
    
    public double getLevelNumber();
}
