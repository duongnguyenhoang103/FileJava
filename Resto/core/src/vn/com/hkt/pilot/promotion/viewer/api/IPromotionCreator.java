/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.viewer.api;

import javax.swing.JPanel;
import org.openide.util.Lookup;

/**
 *
 * @author HKT01
 */
public interface IPromotionCreator {
    
    public JPanel getPromotionCreator();
    
    public Lookup getPromotionLookup();
    
    public double getLevelNumber();
}
