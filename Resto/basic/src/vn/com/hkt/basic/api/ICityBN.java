/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author duong
 */
public interface ICityBN extends IAccessData<City> {   
 
    public List<City> filterCityByID(String id);

    public List<City> filterCityByName(String name);
    
    public List<City> getCityByCountry(Country country);
    
    public List<City> getCityByPartition(Partition partition);
    
}
