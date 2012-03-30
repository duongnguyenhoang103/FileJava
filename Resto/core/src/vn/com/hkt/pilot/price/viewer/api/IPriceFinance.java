/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.price.viewer.api;

import java.util.List;

/**
 *
 * @author HKT01
 */
public interface IPriceFinance {
    
    public boolean isValidate(String date);
    
    public int getPriceValue();
    
    public List<Integer> getAllValues();
    
}
