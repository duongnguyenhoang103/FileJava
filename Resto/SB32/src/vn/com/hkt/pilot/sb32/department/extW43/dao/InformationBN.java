/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb32.department.extW43.dao;

import java.util.List;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb32.deparment.extW43.entity.Information;
import vn.com.hkt.pilot.sb32.subdepartment32.jpa.util.PersistenceUtility;

/**
 *
 * @author khangpn
 */
public class InformationBN extends AccessData<Information> {

    public InformationBN() {
        setAccessData(PersistenceUtility.getEmf(), Information.class);
    }

    public List<Information> SearchInformationByName(String key) {
        return select(Information.FIELD_INFORMATION_NAME, key);
    }
}
