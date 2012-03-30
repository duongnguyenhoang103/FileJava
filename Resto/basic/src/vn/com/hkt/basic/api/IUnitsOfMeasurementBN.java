/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.api;

import java.util.List;
import vn.com.hkt.pilot.entities.UnitsOfMeasurement;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author duong
 */
public interface IUnitsOfMeasurementBN extends IAccessData<UnitsOfMeasurement> {

    public List<UnitsOfMeasurement> filterUnitsOfMeasurementByName(String name);
}
