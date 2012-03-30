/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = ICityBN.class)
public class CityBN extends AccessData<City> implements ICityBN {

    public CityBN() {
        setAccessData(PersistenceUltility.getEMF(), City.class);
    }

    @Override
    public List<City> filterCityByName(String name) {
        return filter(City.FIELD_CITY_NAME, name);
    }

    @Override
    public List<City> filterCityByID(String id) {
        return filter(City.FIELD_CITY_ID, id);
    }

    @Override
    public List<City> getCityByCountry(Country country) {
        return select(City.FIELD_COUNTRY_ID, String.valueOf(country.getId()));
    }

    @Override
    public List<City> getCityByPartition(Partition partition) {
        return select(City.FIELD_PARTITION_ID, String.valueOf(partition.getId()));
    }
}
