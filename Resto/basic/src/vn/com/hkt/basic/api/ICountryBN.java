/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author duong
 */
public interface ICountryBN extends IAccessData<Country> {   
    
    public List<Country> filterCountryByID(String id);
    
    public List<Country> filterCountryByName(String countryName);
    
    
}
