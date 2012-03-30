/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = ICountryBN.class)
public class CountryBN extends AccessData<Country> implements ICountryBN {

    public CountryBN() {
        setAccessData(PersistenceUltility.getEMF(), Country.class);
    }

    
    @Override
    public List<Country> filterCountryByID(String id) {
        return filter(Country.FIELD_COUNTRY_ID, id);
    }

    @Override
    public List<Country> filterCountryByName(String countryName) {
        return filter(Country.FIELD_COUNTRY_NAME, countryName);
    }
}
