/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.department.extW41.dao;

import java.util.List;
import vn.com.hkt.pilot.identity.access.spi.AccessData;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.Information;
import vn.com.hkt.pilot.sb31.generic.jpas.utils.PersistenceUtility;

/**
 *
 * @author khangpn
 */
public class InformationBN extends AccessData<Information> {

    public InformationBN() {
        setAccessData(PersistenceUtility.getEmf(), Information.class);
    }

    public List<Information> searchInformationByName(String name) {
        return select(Information.FIELD_INFORMATION_NAME, name);
    }
}
