/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.calendaroption.dao.api.IMonthlyBN;
import vn.com.hkt.pilot.calendaroption.entity.Monthly;
import vn.com.hkt.pilot.calendaroption.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IMonthlyBN.class)
public class MonthlyBN extends AccessData<Monthly> implements IMonthlyBN{

    public MonthlyBN() {
        setAccessData(PersistenceUtility.getEmf(), Monthly.class);
    }
}
