/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.calendaroption.dao.api.IYearlyBN;
import vn.com.hkt.pilot.calendaroption.entity.Yearly;
import vn.com.hkt.pilot.calendaroption.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IYearlyBN.class)
public class YearlyBN extends AccessData<Yearly> implements IYearlyBN{

    public YearlyBN() {
        setAccessData(PersistenceUtility.getEmf(), Yearly.class);
    }
}
