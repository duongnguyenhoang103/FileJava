/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.dao;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.calendaroption.dao.api.IDailyBN;
import vn.com.hkt.pilot.calendaroption.entity.Daily;
import vn.com.hkt.pilot.calendaroption.jpa.utils.PersistenceUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service=IDailyBN.class)
public class DailyBN extends AccessData<Daily> implements IDailyBN {

    public DailyBN() {
        setAccessData(PersistenceUtility.getEmf(), Daily.class);
    }
}
