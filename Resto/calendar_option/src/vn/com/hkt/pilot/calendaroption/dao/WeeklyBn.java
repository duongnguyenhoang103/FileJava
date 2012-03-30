/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.calendaroption.dao.api.IWeeklyBN;
import vn.com.hkt.pilot.calendaroption.entity.Weekly;
import vn.com.hkt.pilot.calendaroption.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IWeeklyBN.class)
public class WeeklyBn extends AccessData<Weekly> implements IWeeklyBN{

    public WeeklyBn() {
        setAccessData(PersistenceUtility.getEmf(), Weekly.class);
    }
}
