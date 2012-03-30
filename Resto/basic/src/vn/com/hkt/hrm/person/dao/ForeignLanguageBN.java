/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.hrm.person.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IForeignLanguageBN;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IForeignLanguageBN.class)
public class ForeignLanguageBN extends AccessData<ForeignLanguage> implements IForeignLanguageBN{

    public ForeignLanguageBN() {
        setAccessData(PersistenceUltility.getEMF(), ForeignLanguage.class);
    }

    @Override
    public List<ForeignLanguage> selectLanguageByCountryId(int countryId) {
        return select(ForeignLanguage.FIELD_COUNTRY_ID_ACTUAL, String.valueOf(countryId));
    }
}
