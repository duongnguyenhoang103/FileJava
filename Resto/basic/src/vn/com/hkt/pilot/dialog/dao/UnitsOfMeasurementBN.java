/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.IUnitsOfMeasurementBN;
import vn.com.hkt.pilot.entities.UnitsOfMeasurement;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IUnitsOfMeasurementBN.class)
public class UnitsOfMeasurementBN extends AccessData<UnitsOfMeasurement> implements IUnitsOfMeasurementBN {

    public UnitsOfMeasurementBN() {
        setAccessData(PersistenceUltility.getEMF(), UnitsOfMeasurement.class);
    }

    @Override
    public List<UnitsOfMeasurement> filterUnitsOfMeasurementByName(String name) {
        return filter(UnitsOfMeasurement.FIELD_NAME_UNITS, name);
    }
}
